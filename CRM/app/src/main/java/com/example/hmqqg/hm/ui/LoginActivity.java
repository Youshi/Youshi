package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.example.hmqqg.hm.DemoHelper;
import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.activity.MainActivity;
import com.example.hmqqg.hm.activity.chat.ImmersedStatusbarUtils;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class LoginActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener,PopupWindow.OnDismissListener{
    private EditText lg_username;//账号输入框
    private EditText ps_password_lg;//密码输入框
    private TextView remember_ps_1;//记住密码
    //    private TextView passwordl;//忘记密码
//    private TextView register_ps;//注册
    private Button btn_login;//登录
    private CheckBox vip_check;//记住密码
    private ImageView login_history;//下拉箭头
    private boolean progressShow;
    private ProgressDialog pd;
    private String username;
    private String password;
    private ListView mListView;
    private ArrayList<String> mList = new ArrayList<String>();
    private Map<String,String> map = new TreeMap<>();
    private boolean mInitPopup;
    private PopupWindow mPopup;
    private boolean mShowing;
    private ArrayAdapter<String> mAdapter;
    private SharedPreferences SharedPreference;
    private static final String FILE_NAME="saveUserNamePwd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lg_main);
//        View topView = findViewById(R.id.top_1);
//        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.statusbar_bg2);//通知栏所需颜色
//        pd = new ProgressDialog(this);
//        pd.setMessage("正在登陆");

        initView();
        //监听
        lg_username.setOnClickListener(this);
//        passwordl.setOnClickListener(this);
//        register_ps.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        init();
//        saveCheck();
    }
    private void GetView() {
        String name = lg_username.getText().toString();
        String pass = ps_password_lg.getText().toString();
        //获取SharedPreferences时，需要设置两参数
        //第一个是保存的文件的名称，第二个参数是保存的模式（是否只被本应用使用）
        SharedPreference =
                this.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SharedPreference.edit();
        //添加要保存的数据
        editor.putString("username", name);
        editor.putString("password", pass);
        //确认保存
        editor.commit();

    }

    private void init() {
        ObjectInputStream in = null;
        try {
            InputStream is = openFileInput("account.obj");
            in = new ObjectInputStream(is);
            TreeMap<String, String> map = (TreeMap<String, String>) in.readObject();
            if (map.size() > 0) {
                lg_username.setText(map.firstKey());
                ps_password_lg.setText(map.get(map.firstKey()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String input = lg_username.getText().toString();
        String input1 = ps_password_lg.getText().toString();
//        map.remove(input);
        map.put(input,input1);
        if (mList.size() > 5) {
            mList.remove(0);
        }
        ObjectOutputStream out = null;
        try {
            FileOutputStream os = openFileOutput("account.obj",
                    MODE_PRIVATE);
            out = new ObjectOutputStream(os);
            out.writeObject(map);
        } catch (Exception e) {


        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }




    //实例化
    private void initView() {
        lg_username = (EditText) findViewById(R.id.lg_username);
        ps_password_lg = (EditText) findViewById(R.id.ps_password_lg);
//        passwordl = (TextView) findViewById(R.id.passwordl);
//        register_ps = (TextView) findViewById(R.id.register_ps);
//        vip_check = (CheckBox) findViewById(R.id.vip_check);//记住密码
        btn_login = (Button) findViewById(R.id.btn_login);
//        login_history = (ImageView) findViewById(R.id.login_history);//下拉记住账号
//        login_history.setOnClickListener(this);
//        vip_check.setOnClickListener(this);

        // 如果用户名改变，清空密码
        lg_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ps_password_lg.setText(null);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login://登录
                login();
                GetView();
                break;
//            case R.id.login_history://下拉
//                if (mList != null && mList.size() > 0 && !mInitPopup) {
//                    mInitPopup = true;
//                    initPopup();
//                }
//                login_history.setImageResource(R.mipmap.jiao);
//                if (mPopup != null) {
//                    if (!mShowing) {
//                        mPopup.showAsDropDown(lg_username, 0, -5);
//                        mShowing = true;
//                    } else {
//
//                        mPopup.dismiss();
//                    }
//                }
//
//                break;
//            case R.id.vip_check://记住密码
////                saveBox();
//                break;

//            case R.id.passwordl:
//                intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//                break;


        }
    }

    private void saveCheck() {
        SharedPreference =
                this.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if(vip_check.isChecked()||SharedPreference.getBoolean("isSave",true)){
            vip_check.setChecked(true);
            //从文件中获取保存的数据
            String usernameContent = SharedPreference.getString("username", "");
            String passwordContent = SharedPreference.getString("password", "");
            //判断是否有数据存在，并进行相应处理
            if(usernameContent != null && !"".equals(usernameContent))
                lg_username.setText(usernameContent);
            ps_password_lg.setText(passwordContent);

        }else{
            vip_check.setChecked(false);
        }
    }
    //Override
    protected void onNewIntent(Intent intent) {
// TODO Auto-generated method stub
        super.onNewIntent(intent);
        //退出
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            finish();
        }
    }

    private void initPopup() {
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, (List<String>) map);
        mListView = new ListView(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        int width = lg_username.getWidth();
        System.out.println(width);
        mPopup = new PopupWindow(mListView, width, height, true);
        mPopup.setOutsideTouchable(true);
        mPopup.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.xialakuang));
        mPopup.setOnDismissListener(this);
    }


    /**
     * 登陆操作
     */
    private void login() {
        username = lg_username.getText().toString().trim();
        password = ps_password_lg.getText().toString().trim();
        if (username.equals("") || username == null) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("") || password == null) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressShow = true;
            pd = new ProgressDialog(LoginActivity.this);
            pd.setCanceledOnTouchOutside(false);
            pd.setOnCancelListener(

                    new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            progressShow = false;
                        }
                    });
            pd.setMessage("正在登录,请稍候...");
            pd.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    RequestParams requestParams =
                            new RequestParams(getResources().getString(R.string.http_service));
                    requestParams.addBodyParameter("action","login");
                    requestParams.addBodyParameter("username",lg_username.getText().toString());
                    requestParams.addBodyParameter("pwd",ps_password_lg.getText().toString());
//                    x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new LoginEntity(), pd));
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
                            LoginEntity loginEntity = gson.fromJson(result,LoginEntity.class);

                            // 进入主页面
                            String str = loginEntity.getStatus().get(0).getStaval();
                            if ("1".equals(str)) {
                                pd.dismiss();
                                MyApplication.getInstance().setUserInfo(loginEntity.getDetailInfo());//将用户信息存入内存中
                                Toast.makeText(LoginActivity.this, "登录成功~", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,
                                        MainActivity.class);
                                loginEaseMobchat(username, "888888");
                                DemoHelper.getInstance().setCurrentUserName(username);
                                String Remindset = String.valueOf(loginEntity.isRemindset());
                                String Wakeup = String.valueOf(loginEntity.isWakeup());
                                intent.putExtra("Remindset",Remindset);
                                intent.putExtra("Wakeup",Wakeup);
                                startActivity(intent);

                                finish();
                            }else{
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "帐号或密码错误~", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            pd.dismiss();
                            Toast.makeText(LoginActivity.this, "您的网络不稳定，请稍后重试！", Toast.LENGTH_SHORT).show();
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

    }

//    @Subscribe(threadMode = ThreadMode.MainThread)
//    @Override
//    public void onRequestSuccess(Object object) {
//        LoginEntity loginEntity = (LoginEntity) object;
//
//        // 进入主页面
//        String str = loginEntity.getStatus().get(0).getStaval();
//        if ("1".equals(str)) {
//            MyApplication.getInstance().setUserInfo(loginEntity.getDetailInfo());//将用户信息存入内存中
//            Toast.makeText(LoginActivity.this, "登录成功~", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(LoginActivity.this,
//                    MainActivity.class);
//            loginEaseMobchat(username, "888888");
//            String Remindset = String.valueOf(loginEntity.isRemindset());
//            String Wakeup = String.valueOf(loginEntity.isWakeup());
//            intent.putExtra("Remindset",Remindset);
//            intent.putExtra("Wakeup",Wakeup);
//            startActivity(intent);
//
//            finish();
//        }else{
//            Toast.makeText(LoginActivity.this, "帐号或密码错误~", Toast.LENGTH_SHORT).show();
//        }
////        if ("0".equals(str)) {
////            Toast.makeText(LoginActivity.this, "帐号或密码错误~", Toast.LENGTH_SHORT).show();
////        }
//
//    }
//
//    @Subscribe(threadMode = ThreadMode.MainThread)
//    @Override
//    public void onRequestError(Throwable ex) {
//        Toast.makeText(LoginActivity.this, "您的网络不稳定，请稍后重试！", Toast.LENGTH_SHORT).show();
//    }
    /**
     * 登录环信聊天服务器
     *
     * @param username
     * @param password
     */
    private void loginEaseMobchat(final String username, String password) {
        final long start = System.currentTimeMillis();

        EMChatManager.getInstance().login(username, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                // 登陆成功，保存用户名
//                DemoHelper.getInstance().setCurrentUserName(username);
                // 注册群组和联系人监听
                DemoHelper.getInstance().registerGroupAndContactListener();

                // ** 第一次登录或者之前logout后再登录，加载所有本地群和回话
                // ** manually load all local groups and
                EMGroupManager.getInstance().loadAllGroups();
                EMChatManager.getInstance().loadAllConversations();

                // 更新当前用户的nickname 此方法的作用是在ios离线推送时能够显示用户nick
                boolean updatenick = EMChatManager.getInstance().updateCurrentUserNick(
                        MyApplication.currentUserNick.trim());
                if (!updatenick) {
                    Log.e("LoginActivity", "update current user nick fail");
                }
                //异步获取当前用户的昵称和头像(从自己服务器获取，demo使用的一个第三方服务)
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();

                if (!LoginActivity.this.isFinishing() && pd.isShowing()) {
                    pd.dismiss();
                }
                progressShow = false;
                pd.dismiss();

//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        EMGroupManager.getInstance().loadAllGroups();
//                        EMChatManager.getInstance().loadAllConversations();
////                        Toast.makeText(LoginActivity.this, "登陆聊天服务器成功!", Toast.LENGTH_SHORT).show();
//                    }
//                });
                // 进入主页面
//                Intent intent = new Intent(LoginActivity.this,
//                        MainActivity.class);
//                startActivity(intent);
//
//                finish();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, final String message) {

                if (!progressShow) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "登录失败,失败信息:" + message,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        lg_username.setText(mList.get(position));
        mPopup.dismiss();

    }

    @Override
    public void onDismiss() {
        login_history.setImageResource(R.mipmap.fff);
        mShowing = false;
    }
}
