package com.example.hmqqg.hm.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.util.ArrayMap;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMConversation;
import com.easemob.exceptions.EaseMobException;
import com.example.hmqqg.hm.DemoHelper;
import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.MainActivity;
import com.example.hmqqg.hm.activity.chat.ChatActivity;
import com.example.hmqqg.hm.adapter.ContactAdapter;
import com.example.hmqqg.hm.common.Constant;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.ui.AddContactActivity;
import com.example.hmqqg.hm.ui.Add_department;
import com.example.hmqqg.hm.ui.ChineseToPinyinHelper;
import com.example.hmqqg.hm.ui.GroupActivity;
import com.example.hmqqg.hm.ui.GroupsActivity;
import com.example.hmqqg.hm.ui.LetterIndexView;
import com.example.hmqqg.hm.ui.NewFriendsMsgActivity;
import com.example.hmqqg.hm.ui.Organization_Activity;
import com.example.hmqqg.hm.ui.Outside_Activity;
import com.example.hmqqg.hm.widgets.AnimatedExpandableListView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import easeui.DemoHXSDKHelper;
import easeui.applib.controller.HXSDKHelper;
import easeui.db.DemoDBManager;
import easeui.db.InviteMessgeDao;
import easeui.db.UserDao;
import easeui.domain.EaseUser;
import easeui.utils.EaseCommonUtils;
import easeui.widget.EaseContactList;
import easeui.widget.EaseSidebar;

/**
 * 联系人
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout ll_module1;//申请与通知
    private LinearLayout ll_module2;//群
    private LinearLayout ll_module3;//组织架构
    private ImageView add;//添加
    protected List<EaseUser> contactList;
    private List<String> usernames;
    private TextView title_top_bar;//标题
    private ListView listView;
    private TextView textView;
    private ImageView unread_msg_number;//小红点
    private TextView tv_location;//定位
    private LetterIndexView sidebar;
    private ContactAdapter contactadapter;
    protected EaseUser toBeProcessUser;
    protected String toBeProcessUsername;
    protected EaseContactList contactListLayout;
    private boolean hidden;

    private LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int TwoNextPage = 1000;

    //    public TextView mLocationResult, mLocationResult2, logMsg;
    public TextView trigger, exit;
    public Vibrator mVibrator;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor = "gcj02";
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        progressD();//建DiaLog
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();//初始化布局
    }

    /**
     * 初始化界面控件
     *
     * @param
     */
    private void initView() {
        title_top_bar = (TextView)getView().findViewById(R.id.title_top_bar);//标题
        ll_module1 = (LinearLayout) getView().findViewById(R.id.ll_module1);//申请与通知
        ll_module2 = (LinearLayout) getView().findViewById(R.id.ll_module2);//群
//        ll_module3 = (LinearLayout) getView().findViewById(R.id.ll_module3);//组织架构
        add = (ImageView) getView().findViewById(R.id.add);
        title_top_bar.setText("联系人");
        textView = (TextView) getView().findViewById(R.id.show_letter_in_center);
        unread_msg_number = (ImageView) getView().findViewById(R.id.unread_msg_number);//红点
        listView = (ListView) getView().findViewById(R.id.list);//好友列表
        sidebar = (LetterIndexView) getView().findViewById(R.id.sidebar);//侧边字母导航栏
        tv_location = (TextView) getView().findViewById(R.id.tv_location);
        tv_location.setMovementMethod(ScrollingMovementMethod.getInstance());
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        mVibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        contactList= new ArrayList<EaseUser>();
        usernames = new ArrayList<>();
        initLocation();
        mLocationClient.start();
        ll_module1.setOnClickListener(this);
        ll_module2.setOnClickListener(this);
//        ll_module3.setOnClickListener(this);
        add.setOnClickListener(this);
        registerForContextMenu(listView);//注册上下文菜单
        contactadapter = new ContactAdapter(contactList,getActivity());
        getContactList();//从服务器获取好友列表
        listView.setAdapter(contactadapter);
        contactadapter.notifyDataSetChanged();
        onlistview();
    }



    private void getContactList() {
        contactList.clear();
        usernames.clear();
//        List<String> strnames = null;
        try {
//            Map<String, EaseUser> a = DemoDBManager.getInstance().getContactList();
            Map<String, EaseUser> map = DemoHelper.getInstance().getContactList();
//            strnames = EMContactManager.getInstance().getContactUserNames();

//                        pd.dismiss();
//                        for (String username : strnames) {
//                            usernames.add(username);
//                        }
                        Iterator<Map.Entry<String, EaseUser>> iterator = map.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, EaseUser> entry = iterator.next();
                            usernames.add(entry.getKey());
//                            contactList.add(entry.getValue());
                        }
                        initData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
    private void onlistview() {
        sidebar.setTextViewDialog(textView);
        sidebar.setUpdateListView(new LetterIndexView.UpdateListView() {
            @Override
            public void updateListView(String currentChar) {
                int positionForSection = contactadapter.getPositionForSection(currentChar.charAt(0));
                listView.setSelection(positionForSection);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (contactList.size()>0){
                    int sectionForPosition = contactadapter.getSectionForPosition(firstVisibleItem);
                    sidebar.updateLetterIndexView(sectionForPosition);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里应该先点击进入详情页，之后再进入聊天，后台不支持详情，就不实现了。
                String nike = contactList.get((int) id).getNick();
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                intent.putExtra("userId",nike);
                startActivity(intent);
            }
        });
    }
    public void  initData() {
        Map<String,EaseUser> m = new TreeMap<>();
        for (String allUserName : usernames) {
            EaseUser user = new EaseUser(allUserName);
            user.setNick(allUserName);
            String convert = ChineseToPinyinHelper.getInstance().getPinyin(allUserName).toUpperCase();
            user.setPinyin(convert);
            String substring = convert.substring(0, 1);
            if (substring.matches("[A-Z]")) {
                user.setInitialLetter(substring);
            }else{
                user.setInitialLetter("#");
            }
            contactList.add(user);
            m.put(allUserName,user);
            contactadapter.notifyDataSetChanged();
        }
        Collections.sort(contactList, new Comparator<EaseUser>() {
            @Override
            public int compare(EaseUser lhs, EaseUser rhs) {
                if (lhs.getInitialLetter().contains("#")) {
                    return 1;
                } else if (rhs.getInitialLetter().contains("#")) {
                    return -1;
                }else{
                    return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
                }
            }
        });


    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
//        int span = 100000;
//        try {
//            span = Integer.valueOf(frequence.getText().toString());
//        } catch (Exception e) {
//        }
//        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setIsNeedAddress(checkGeoLocation.isChecked());//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setLocationNotify(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedAddress(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
    }

    /**
     * 接受从LocationActivit中传过来的poiInfo(name,address)
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == Outside_Activity.TwoNextPage && resultCode == RESULT_OK) {
//            setResult(RESULT_OK, intent);
//            finish();
//            return;
//        }
        /**
         * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
         * 所以为了区别到底选择了那个方式获取图片要进行判断，
         * 这里的requestCode跟startActivityForResult里面第二个参数对应
         */

//        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

//            if (resultCode == RESULT_OK) {
//                //Log.e(tag, "获取图片成功，path="+realPath);
//                if (a == 1) {
//                    setImageView(realPath1, addimageview1.getWidth(), addimageview1.getHeight());
////                    addimageview2.setVisibility(View.VISIBLE);
//
//                }
////                if (a == 2) {
////                    setImageView(realPath2, addimageview2.getWidth(), addimageview2.getHeight());
////                    addimageview3.setVisibility(View.VISIBLE);
////
////                }
////                if (a == 3) {
////                    setImageView(realPath3, addimageview3.getWidth(), addimageview3.getHeight());
////                }
//
//
//            } else if (resultCode == RESULT_CANCELED) {
//                // 用户取消了图像捕获
//            } else {
//                // 图像捕获失败，提示用户
//                //Log.e(tag, "拍照失败");
//            }
        if (requestCode == Constant.REQUEST_LOCATION){
            if (resultCode == Constant.RESULT_LOCATION){
                String name = data.getStringExtra("city");
                String address = data.getStringExtra("address");
                tv_location.setText(name);
//                mLocationResult2.setText(address);
            }
        }
    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            SharedPreferences sp = getActivity().getSharedPreferences("location", getActivity().MODE_PRIVATE);
            sp.edit().putString("latitude", "" + latitude).putString("longitude", "" + longitude).commit();

            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            StringBuffer sb2 = new StringBuffer(256);
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append(location.getCity());
                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb2.append("(").append(location.getLocationDescribe()).append(")");
//                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append(location.getCity());
                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb2.append("(").append(location.getLocationDescribe()).append(")");
                //运营商信息
//                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("定位失败,请检查您的手机是否联网");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            logMsg(sb.toString(), sb2.toString());
        }

    }


    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str, String str2) {
        try {
            if (tv_location != null)
                tv_location.setText(str);
//            if (mLocationResult2 != null) {
//                mLocationResult2.setText(str2);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        mLocationClient.stop();
    }


    private void progressD() {
        pd = new ProgressDialog(getActivity());
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
        pd.setMessage("正在加载,请稍候...");
        pd.show();
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if(!hidden){
////            progressD();
//            usernames.clear();
//            getContactList();//从服务器获取好友列表
//            contactadapter.notifyDataSetChanged();
//        }
//    }



    // 刷新ui
    public  void refresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getContactList();
                contactadapter.notifyDataSetChanged();
            }
        });
    }
    public void isShowRed(final boolean isshow){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isshow){
                    unread_msg_number.setVisibility(View.VISIBLE);
                    refresh();
                }else{
                    unread_msg_number.setVisibility(View.GONE);
                }

            }
        });
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_module2://群
                intent = new Intent(getActivity(), GroupsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_module1://申请与通知
                unread_msg_number.setVisibility(View.INVISIBLE);
                intent = new Intent(getActivity(), NewFriendsMsgActivity.class);
                startActivity(intent);
            break;
//            case R.id.ll_module3://组织架构
//                intent = new Intent(getActivity(), Organization_Activity.class);
//                startActivity(intent);
//                break;
            case R.id.add://添加
                intent = new Intent(getActivity(), AddContactActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        toBeProcessUser = (EaseUser) listView.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
        toBeProcessUsername = toBeProcessUser.getUsername();
        getActivity().getMenuInflater().inflate(R.menu.em_context_contact_list, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_contact) {
            try {
                // 删除此联系人
                deleteContact(toBeProcessUser);
                // 删除相关的邀请消息
                InviteMessgeDao dao = new InviteMessgeDao(getActivity());
                dao.deleteMessage(toBeProcessUser.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onContextItemSelected(item);
    }
    /**
     * 删除联系人
     *
     * @param
     */
    public void deleteContact(final EaseUser tobeDeleteUser) {
        String st1 = getResources().getString(R.string.deleting);
        final String st2 = getResources().getString(R.string.Delete_failed);
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage(st1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    EMContactManager.getInstance().deleteContact(tobeDeleteUser.getUsername());
                    // 删除db和内存中此用户的数据
                    UserDao dao = new UserDao(getActivity());
                    dao.deleteContact(tobeDeleteUser.getUsername());
                    DemoHelper.getInstance().getContactList().remove(tobeDeleteUser.getUsername());
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            contactList.remove(tobeDeleteUser);
//                            contactListLayout.refresh();
                            contactadapter.notifyDataSetChanged();
                        }
                    });
                } catch (final Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            Toast.makeText(getActivity(), st2 + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        }).start();

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        if (!hidden) {
            refresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hidden) {
            refresh();
        }
    }

}
