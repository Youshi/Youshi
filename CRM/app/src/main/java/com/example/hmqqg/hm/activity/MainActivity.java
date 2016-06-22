package com.example.hmqqg.hm.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.easemob.EMConnectionListener;
import com.easemob.EMCallBack;
import com.easemob.EMError;
import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.EMValueCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactListener;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMConversation;
import com.easemob.util.EMLog;
import com.easemob.util.HanziToPinyin;
import com.easemob.util.NetUtils;
import com.example.hmqqg.hm.DemoHelper;
import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.common.Constant;
import com.example.hmqqg.hm.domain.InviteMessage;
import com.example.hmqqg.hm.entity.AppVersionEntity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.fragment.HomeFragment;
import com.example.hmqqg.hm.fragment.ManageFragment;
import com.example.hmqqg.hm.fragment.MessageFragment;
import com.example.hmqqg.hm.fragment.New_MessageFragment;
import com.example.hmqqg.hm.fragment.SignFragment;
import com.example.hmqqg.hm.ui.Application_Memo;
import com.example.hmqqg.hm.ui.LoginActivity;
import com.example.hmqqg.hm.ui.Remind_Activity;
import com.example.hmqqg.hm.updateapp.UpdateAppManager;
import com.example.hmqqg.hm.util.FragmentTag;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import easeui.DemoHXSDKHelper;
import easeui.applib.controller.HXSDKHelper;
import easeui.controller.EaseUI;
import easeui.db.DemoDBManager;
import easeui.db.InviteMessgeDao;
import easeui.db.UserDao;
import easeui.domain.EaseUser;
import easeui.model.EaseNotifier;

public class MainActivity extends BaseRequestActivity implements View.OnClickListener{
    /**
     * 当前Fragment的key
     */
    private FragmentTag mCurrentTag;
    private FragmentTag mTAG_HOME;
    /**
     * 当前Fragment
     */
    private Fragment mCurrentFragment;
    /**
     * 选项卡按钮数组
     */
    private ArrayList<RadioButton> mBtnTabs;
    private UserDao userDao;
    private InviteMessgeDao inviteMessgeDao;
    private List<Fragment> fragmentList;
    private RadioGroup radioGroup;
    private MyConnectionListener connectionListener = null;
    private HomeFragment homefragment;
    private long firstTime = 0;//上次按下返回键的系统时间
    private String url;
    private String vercode;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        asyncFetchContactsFromServer();
        Intent intent = getIntent();
//        //判断是否再其他地方登陆
//        if (getIntent().getBooleanExtra(Constant.ACCOUNT_CONFLICT, false) && !isConflictDialogShow) {
//            showConflictDialog();
//        } else if (getIntent().getBooleanExtra(Constant.ACCOUNT_REMOVED, false) && !isAccountRemovedDialogShow) {
//            showAccountRemovedDialog();
//        }
        String remindset = intent.getStringExtra("Remindset");
        String wakeup = intent.getStringExtra("Wakeup");
        if("false".equals(remindset)&&"true".equals(wakeup)){
            getdialog();
        }
        isUpdate();
        if (savedInstanceState == null) {
            // 记录当前Fragment
            mCurrentTag = FragmentTag.TAG_SIGN;
            mTAG_HOME = FragmentTag.TAG_HOME;
            mCurrentFragment = new SignFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment, mCurrentFragment,
                            mCurrentTag.getTag()).add(R.id.main_fragment,homefragment,mTAG_HOME.getTag()).hide(homefragment).show(mCurrentFragment).commit();
            Drawable[] compoundDrawables = mBtnTabs.get(2).getCompoundDrawables();
            Drawable compoundDrawable = compoundDrawables[1];
             ((TransitionDrawable) compoundDrawable).startTransition(500);

        }
        connectionListener = new MyConnectionListener();
        EMChatManager.getInstance().addConnectionListener(connectionListener);
    }

    private void isUpdate() {//判断是否需要更新。

        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams =
                        new RequestParams(getResources().getString(R.string.http_service));
                requestParams.addBodyParameter("action","appver");
                requestParams.addBodyParameter("appid","1");
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        AppVersionEntity appver = gson.fromJson(result,AppVersionEntity.class);
                        String staval = appver.getStatus().get(0).getStaval();
                        if("1".equals(staval)){
                            url = appver.getDetailInfo().get(0).getAppUrl().toString();
                            vercode = String.valueOf(appver.getDetailInfo().get(0).getAppVer().toString());
                            String vername = String.valueOf(getResources().getString(R.string.versionName));
                            if(!vername.equals(vercode)){
                                String httphrl = getResources().getString(R.string.http_image)+url;//地址
                                UpdateAppManager update = new UpdateAppManager(MainActivity.this,httphrl);
                                update.checkUpdateInfo();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        }).start();

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        LoginEntity le = (LoginEntity) object;
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(MainActivity.this, "您的网络不稳定，请稍后重试！", Toast.LENGTH_SHORT).show();
    }

    public void getdialog() {
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //    设置Title的图标
//        builder.setIcon(R.drawable.ic_launcher);
        //    设置Title的内容
        builder.setTitle("提醒");
        //    设置Content来显示一个信息
        builder.setMessage("您有新的提醒");
        //    设置一个PositiveButton
        builder.setPositiveButton("查看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gethttp();
                Intent intent = new Intent(MainActivity.this, Remind_Activity.class);//跳转到提醒
                startActivity(intent);
            }
        });
        //    设置一个NeutralButton
        builder.setNeutralButton("不再提醒", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gethttp();
                Toast.makeText(MainActivity.this, "您今天将不会收到该类型的提醒！", Toast.LENGTH_SHORT).show();
            }
        });
        //    显示出该对话框
        builder.show();
    }
    private void gethttp() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    RequestParams requestParams =
                            new RequestParams(getResources().getString(R.string.http_remindset));
                    requestParams.addBodyParameter("action","remindset");
                    requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());
                    x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new LoginEntity()));
                }
            }).start();
        }
    private void initView() {
        radioGroup = ((RadioGroup) findViewById(R.id.radio_group));
        radioGroup.check(R.id.main_btn_sign);
        // 添加选项卡按钮
        mBtnTabs = new ArrayList<RadioButton>();
        mBtnTabs.add((RadioButton) findViewById(R.id.main_btn_home));//消息
        mBtnTabs.add((RadioButton) findViewById(R.id.main_btn_message));//联系人
        mBtnTabs.add((RadioButton) findViewById(R.id.main_btn_sign));//签到(协同办公)
        mBtnTabs.add((RadioButton) findViewById(R.id.main_btn_manage));//客户管理
        mBtnTabs.add((RadioButton) findViewById(R.id.main_btn_apply));//应用
        for (int i = 0; i < mBtnTabs.size(); i++) {
            mBtnTabs.get(i).setOnClickListener(this);
        }
        inviteMessgeDao = new InviteMessgeDao(this);
        userDao = new UserDao(this);
        homefragment = new HomeFragment();
        // setContactListener监听联系人的变化等
        EMContactManager.getInstance().setContactListener(new MyContactListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_home:
                // 切换首页选项卡
                switchFragment(FragmentTag.TAG_MANAGE );
                radioGroup.check(R.id. main_btn_home);
                break;
            case R.id.main_btn_message:
                // 切换消息选项卡
                switchFragment(FragmentTag.TAG_HOME   );
                radioGroup.check(R.id. main_btn_message);
                break;
            case R.id.main_btn_sign:
                // 切换签到选项卡
                switchFragment(FragmentTag.TAG_SIGN);
                radioGroup.check(R.id.main_btn_sign);
                break;
            case R.id.main_btn_manage:
                // 切换管理选项卡
                switchFragment(FragmentTag.TAG_MESSAGE );
                radioGroup.check(R.id.main_btn_manage);
                break;
            case R.id.main_btn_apply:
                // 切换应用选项卡
                switchFragment(FragmentTag.TAG_APPLY);
                radioGroup.check(R.id.main_btn_apply);
                break;
        }
    }
    /**
     * 切换Fragment
     *
     * @param to
     *            目标Fragment
     */

    private void switchFragment(FragmentTag to) {
        if (!mCurrentTag.equals(to)) {
            Fragment currentF = getSupportFragmentManager().findFragmentByTag(
                    mCurrentTag.getTag());
            Fragment toF = getSupportFragmentManager().findFragmentByTag(to.getTag());
            if (null == toF) { // 先判断是否被add过
                try {
                    // 未add过，使用反射新建一个Fragment并add到FragmentManager中
                    toF = (Fragment) Class.forName(to.getTag()).newInstance();
                    getSupportFragmentManager().beginTransaction().hide(currentF)
                            .add(R.id.main_fragment, toF, to.getTag()).commit(); // 隐藏当前的fragment，add下一个到Activity中
                    // 切换按钮动画
                    switchAnimation(to.ordinal());
                    // 更新当前Fragment
                    mCurrentTag = to;
                    mCurrentFragment = toF;
                } catch (Exception e) {
                }
            } else {
                // add过，直接hide当前，并show出目标Fragment
                getSupportFragmentManager().beginTransaction().hide(currentF)
                        .show(toF).commit(); // 隐藏当前的fragment，显示下一个
                // 切换按钮动画
                switchAnimation(to.ordinal());
                // 更新当前Fragment
                mCurrentTag = to;
                mCurrentFragment = toF;
            }
        }
    }

    /**
     * 切换Fragment时选项卡按钮的动画
     *
     * @param to
     *            目标选项卡的下标
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void switchAnimation(int to) {
        ((TransitionDrawable) mBtnTabs.get(mCurrentTag.ordinal()).getCompoundDrawables()[1])
                .reverseTransition(500);
        ((TransitionDrawable) mBtnTabs.get(to).getCompoundDrawables()[1])
                .startTransition(500);
    }

    /**
     * 获取当前显示的Fragment
     *
     * @return 当前Fragment
     */
    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    //监测返回键，再按一次退出程序
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) { //如果两次按键时间间隔大于2秒，则不退出
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_LONG).show();  //提示消息
                firstTime = secondTime;// 更新firstTime
                return true;
            } else { // 两次按键小于2秒时，退出应用
//                EMChatManager.getInstance().logout();//此方法为同步方法,里面的参数true表示退出登录时解绑GCM或者小米推送的token
                DemoHelper.getInstance().logout(false, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        DemoHelper demohelper = DemoHelper.getInstance();
                        demohelper.setContactList(null);
                        demohelper.setRobotList(null);
                        demohelper.getUserProfileManager().reset();
                        DemoDBManager.getInstance().closeDB();
                        System.exit(0);
                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(MainActivity.this, "退出失败，请重试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    private android.app.AlertDialog.Builder conflictBuilder;
    private android.app.AlertDialog.Builder accountRemovedBuilder;
    private boolean isConflictDialogShow;
    private boolean isAccountRemovedDialogShow;
    private BroadcastReceiver internalDebugReceiver;
    private BroadcastReceiver broadcastReceiver;
    private LocalBroadcastManager broadcastManager;
    // 账号在别处登录
    public boolean isConflict = false;
    // 账号被移除
    private boolean isCurrentAccountRemoved = false;

    /**
     * 显示帐号在别处登录dialog
     */
    private void showConflictDialog() {
        isConflictDialogShow = true;
        DemoHelper.getInstance().logout(false,null);
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //    设置Title的图标
//        builder.setIcon(R.drawable.ic_launcher);
        //    设置Title的内容
        builder.setTitle("下线通知");
        //    设置Content来显示一个信息
        builder.setMessage("您的账号在其他地方登陆，您被迫下线~");
        //    设置一个PositiveButton
        builder.setPositiveButton("下线", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(MainActivity.this, "下线", Toast.LENGTH_LONG).show();
            }
        });
        //    设置一个NeutralButton
//        builder.setNeutralButton("不再提醒", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//            }
//        });
        //    显示出该对话框
        builder.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(connectionListener != null){
            EMChatManager.getInstance().removeConnectionListener(connectionListener);
        }
        Log.i("111212121212121221211","我是销毁程序，哈哈哈哈哈哈哈哈");
        DemoHelper.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                DemoHelper demohelper = DemoHelper.getInstance();
                demohelper.setContactList(null);
                demohelper.setRobotList(null);
                demohelper.getUserProfileManager().reset();
                DemoDBManager.getInstance().closeDB();
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    /**
     * 帐号被移除的dialog
     */
    private void showAccountRemovedDialog() {
        isAccountRemovedDialogShow = true;
        DemoHelper.getInstance().logout(true,null);
        String st5 = getResources().getString(R.string.Remove_the_notification);
        if (!MainActivity.this.isFinishing()) {
            // clear up global variables
            try {
                if (accountRemovedBuilder == null)
                    accountRemovedBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
                accountRemovedBuilder.setTitle(st5);
                accountRemovedBuilder.setMessage(R.string.em_user_remove);
                accountRemovedBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        accountRemovedBuilder = null;
                        finish();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                });
                accountRemovedBuilder.setCancelable(false);
                accountRemovedBuilder.create().show();
                isCurrentAccountRemoved = true;
            } catch (Exception e) {
            }

        }

    }



    /**
     * 连接监听listener
     *
     */
    public class MyConnectionListener implements EMConnectionListener {

        @Override
        public void onConnected() {
            boolean groupSynced = DemoHelper.getInstance().isGroupsSyncedWithServer();
            boolean contactSynced = DemoHelper.getInstance().isContactsSyncedWithServer();

            // in case group and contact were already synced, we supposed to notify sdk we are ready to receive the events
            if(groupSynced && contactSynced){
                new Thread(){
                    @Override
                    public void run(){
                        DemoHelper.getInstance().notifyForRecevingEvents();
                    }
                }.start();
            }else{
                if(!groupSynced){
                    asyncFetchGroupsFromServer();
                }

                if(!contactSynced){
                    asyncFetchContactsFromServer();
                }

                if(!DemoHelper.getInstance().isBlackListSyncedWithServer()){
//                    asyncFetchBlackListFromServer();
                }
            }

//
//            runOnUiThread(new Runnable() {
//
//                @Override
//                public void run() {
//                    chatHistoryFragment.errorItem.setVisibility(View.GONE);
//                }
//            });
        }



        @Override
        public void onDisconnected(final int error) {
            final String st1 = getResources().getString(R.string.can_not_connect_chat_server_connection);
            final String st2 = getResources().getString(R.string.the_current_network);
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (error == EMError.USER_REMOVED) {
                        // 显示帐号已经被移除
                        showAccountRemovedDialog();
                    } else if (error == EMError.CONNECTION_CONFLICT) {
                        // 显示帐号在其他设备登陆dialog
                        showConflictDialog();
                    }
//                    else {
//                        chatHistoryFragment.errorItem.setVisibility(View.VISIBLE);
//                        if (NetUtils.hasNetwork(MainActivity.this))
//                            chatHistoryFragment.errorText.setText(st1);
//                        else
//                            chatHistoryFragment.errorText.setText(st2);
//
//                    }
                }

            });
        }

    }


    /***
     * 好友变化listener
     *
     */
    public class MyContactListener implements EMContactListener {

        @Override
        public void onContactAdded(List<String> usernameList) {//同意好友之后新增好友
            // 保存增加的联系人
            Map<String, EaseUser> map = DemoHelper.getInstance().getContactList();
//            Map<String, EaseUser> localUsers = ((DemoHXSDKHelper) HXSDKHelper.getInstance()).getContactList();
            Map<String, EaseUser> toAddUsers = new HashMap<String, EaseUser>();
            for (String username : usernameList) {
                EaseUser user = setUserHead(username);
                // 添加好友时可能会回调added方法两次
                if (!map.containsKey(username)) {
                    userDao.saveContact(user);
                    toAddUsers.put(username, user);
                }
            }
            map.putAll(toAddUsers);
            DemoHelper.getInstance().setContactList(map);
            // 刷新ui
            homefragment.refresh();
            }

        @Override
        public void onContactDeleted(List<String> removelist) {//对方删除你好友的时候调用
            Map<String, EaseUser> map = DemoHelper.getInstance().getContactList();
            for (String username : removelist) {
                map.remove(username);
                userDao.deleteContact(username);
                inviteMessgeDao.deleteMessage(username);
            }
            homefragment.refresh();
        }

        @Override
        public void onContactInvited(String username, String reason) {//对方添加你为好友的时候调用
            // 接到邀请的消息，如果不处理(同意或拒绝)，掉线后，服务器会自动再发过来，所以客户端不需要重复提醒
//            Map<String, EaseUser> map = DemoHelper.getInstance().getContactList();
//            if (!map.containsKey(username)) {
//                return;
//            }
            List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();
            for (InviteMessage inviteMessage : msgs) {
                if (inviteMessage.getGroupId() == null && inviteMessage.getFrom().equals(username)) {
                    inviteMessgeDao.deleteMessage(username);
                }
            }
            // 自己封装的javabean
            InviteMessage msg = new InviteMessage();
            msg.setFrom(username);
            msg.setTime(System.currentTimeMillis());
            msg.setReason(reason+"");
            // 设置相应status
            msg.setStatus(InviteMessage.InviteMesageStatus.BEINVITEED);
            homefragment.isShowRed(true);//显示红点
            notifyNewIviteMessage(msg);
        }

        @Override
        public void onContactAgreed(String username) {//对方同意添加你好友
            EaseUser easeuser = new EaseUser(username);
            easeuser.setPinyin(username);
            DemoHelper.getInstance().saveContact(easeuser);
            List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();
            for (InviteMessage inviteMessage : msgs) {
                if (inviteMessage.getFrom().equals(username)) {
                    return;
                }
            }
            // 自己封装的javabean
            InviteMessage msg = new InviteMessage();
            msg.setFrom(username);
            msg.setTime(System.currentTimeMillis());
            msg.setStatus(InviteMessage.InviteMesageStatus.BEAGREED);
            homefragment.isShowRed(true);//显示红点
            notifyNewIviteMessage(msg);
        }

        @Override
        public void onContactRefused(String s) {//对方拒绝好友请求（未实现）
            Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * set head
     *添加头字母
     * @param username
     * @return
     */
    EaseUser setUserHead(String username) {
        EaseUser user = new EaseUser(username);
        user.setUsername(username);
        String headerName = null;
        if (!TextUtils.isEmpty(user.getNick())) {
            headerName = user.getNick();
        } else {
            headerName = user.getUsername();
        }
        if (username.equals(Constant.NEW_FRIENDS_USERNAME)) {
            user.setHeader("");
        } else if (Character.isDigit(headerName.charAt(0))) {
            user.setHeader("#");
        } else {
            user.setHeader(HanziToPinyin.getInstance().get(headerName.substring(0, 1)).get(0).target.substring(0, 1)
                    .toUpperCase());
            char header = user.getHeader().toLowerCase().charAt(0);
            if (header < 'a' || header > 'z') {
                user.setHeader("#");
            }
        }
        return user;
    }

    /**
     * 保存提示新消息
     *
     * @param msg
     */
    private void notifyNewIviteMessage(InviteMessage msg) {
        saveInviteMsg(msg);
        // 提示有新消息
//        HXSDKHelper.getInstance().getNotifier().viberateAndPlayTone(null);

        // 刷新bottom bar消息未读数
//        updateUnreadLabel();
        // 刷新好友页面ui
            homefragment.refresh();
    }

    /**
     * 保存邀请等msg
     *
     * @param msg
     */
    private void saveInviteMsg(InviteMessage msg) {
        // 保存msg
        inviteMessgeDao.saveMessage(msg);
        // 未读数加1
//        EaseUser user = ((DemoHXSDKHelper)HXSDKHelper.getInstance()).getContactList().get(Constant.NEW_FRIENDS_USERNAME);
//        if (user.getUnreadMsgCount() == 0)
//            user.setUnreadMsgCount(user.getUnreadMsgCount() + 1);
    }
    /**
     * 刷新未读消息数
     */
    public void updateUnreadLabel() {
        int count = getUnreadMsgCountTotal();
//        if (count > 0) {
//            unreadLabel.setText(String.valueOf(count));
//            unreadLabel.setVisibility(View.VISIBLE);
//        } else {
//            unreadLabel.setVisibility(View.INVISIBLE);
//        }
    }
    /**
     * 获取未读消息数
     *
     * @return
     */
    public int getUnreadMsgCountTotal() {
        int unreadMsgCountTotal = 0;
        int chatroomUnreadMsgCount = 0;
        unreadMsgCountTotal = EMChatManager.getInstance().getUnreadMsgsCount();
        for(EMConversation conversation:EMChatManager.getInstance().getAllConversations().values()){
            if(conversation.getType() == EMConversation.EMConversationType.ChatRoom)
                chatroomUnreadMsgCount=chatroomUnreadMsgCount+conversation.getUnreadMsgCount();
        }
        return unreadMsgCountTotal-chatroomUnreadMsgCount;
    }
    /**
     * 检查当前用户是否被删除
     */
    public boolean getCurrentAccountRemoved() {
        return isCurrentAccountRemoved;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra(Constant.ACCOUNT_CONFLICT, false) && !isConflictDialogShow) {
            showConflictDialog();
        } else if (intent.getBooleanExtra(Constant.ACCOUNT_REMOVED, false) && !isAccountRemovedDialogShow) {
            showAccountRemovedDialog();
        }
    }

    public static void asyncFetchGroupsFromServer(){
        DemoHelper.getInstance().asyncFetchGroupsFromServer(new EMCallBack(){

            @Override
            public void onSuccess() {
                DemoHelper.getInstance().noitifyGroupSyncListeners(true);

                if(DemoHelper.getInstance().isContactsSyncedWithServer()){
                    DemoHelper.getInstance().notifyForRecevingEvents();
                }
            }

            @Override
            public void onError(int code, String message) {
                DemoHelper.getInstance().noitifyGroupSyncListeners(false);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

        });
    }

    public void asyncFetchContactsFromServer(){
        DemoHelper.getInstance().asyncFetchContactsFromServer(new EMValueCallBack<List<String>>() {
            @Override
            public void onSuccess(List<String> usernames) {
//                Context context = HXSDKHelper.getInstance().getAppContext();
                Map<String, EaseUser> userlist = new HashMap<String, EaseUser>();
                for (String username : usernames) {
                    EaseUser user = new EaseUser(username);
                    user.setUsername(username);
                    setUserHead(username);
                    userlist.put(username, user);
                }
                DemoHelper.getInstance().setContactList(userlist);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }



}
