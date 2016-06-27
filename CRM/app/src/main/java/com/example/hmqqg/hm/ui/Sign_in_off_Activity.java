package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.MarketEntity;
import com.example.hmqqg.hm.adapter.SignTimeAdapter;
import com.example.hmqqg.hm.entity.CustomLookEntity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.entity.SignlistEntity;
import com.example.hmqqg.hm.entity.SingoneEntity;
import com.example.hmqqg.hm.entity.TimeEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 内勤签到界面
 * Created by Ella on 2016/1/26.
 */
public class Sign_in_off_Activity extends BaseRequestActivity
        implements View.OnClickListener {
    private Button sign_in;
    private Button sign_out;
    private TextView title_top_bar;
    private TextView time;
    private TextView week;
    private TextView datetime;
    private TextView address;

    private TextView BeginTime;//上班时间
    private TextView EndTime;//下班时间
    private String ltime;
    private String t;//当前日期+09:00
    private String d;//当前日期
    private long millionSecond = 0;//毫秒09:00
    private long millionSeconds = 0;//毫秒当前

    private boolean isSignIn;//是否签到过

    private String mYear;
    private String mMonth;
    private String mDay;
    private String mWay;
    private LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Battery_Saving;
    private String tempcoor = "bd09ll";
    private TextView signOutAddress;
    private TextView signOutTime;
//    private String timeStartWork = "09:00";//上班时间
//    private String timeEndWork = "17:00";//下班时间
    private String timeStartWork;//上班时间
    private String timeEndWork ;//下班时间
    private ImageView back;
    private ProgressDialog mypDialog;

    String getaddress;
    String getaddress2;

    String signtime1;
    String signouttime1;
    String signdata1;
    String signoutdata1;

    String resigntime;//修改完格式的签到时间
    String resignouttime;//修改完格式的签退时间
    boolean istrue;
    boolean isSignAddress;

    private PullToRefreshListView lstv;
    private SignTimeAdapter messageAdapter;
    private List<SignlistEntity.DetailInfoEntity> list = new ArrayList<SignlistEntity.DetailInfoEntity>();

    private Integer startPage = 1;//开始页
    private Integer pageSize = 10;//一页显示几条数据

    private double latitude1;
    private double longitude1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clocking_in);
        iniView();
        gettime();
        initDialog();
        inipulllist();
        initLocation();
        gethttp();
    }


    private void iniView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("考勤签到");
        back = ((ImageView) findViewById(R.id.back));
        time = (TextView) findViewById(R.id.time);
        datetime = (TextView) findViewById(R.id.datetime);
        EndTime = (TextView) findViewById(R.id.EndTime);
        BeginTime = (TextView) findViewById(R.id.BeginTime);
        signOutTime = (TextView) findViewById(R.id.sign_out_time);
        address = (TextView) findViewById(R.id.neirong);
        signOutAddress = (TextView) findViewById(R.id.sign_out_address);
        week = (TextView) findViewById(R.id.week);
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_out = (Button) findViewById(R.id.sign_out);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "天";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }

        time.setText(mYear + "-" + mMonth + "-" + mDay);
        week.setText(mWay);
        sign_in.setOnClickListener(this);
        sign_out.setOnClickListener(this);
        back.setOnClickListener(this);
        sign_out.setClickable(false);//先让其不可点击
        address.setOnClickListener(this);

        mLocationClient = new LocationClient(this.getApplicationContext());//定位
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);//获取签到定位信息以及判断是否早退晚签
    }

    private void gettime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "getsignset");
                params.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("claid", "101");
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        TimeEntity te = gson.fromJson(result, TimeEntity.class);
                        timeStartWork = te.getDetailInfo().getBeginTime().toString();
                        timeEndWork = te.getDetailInfo().getEndTime().toString();
                        BeginTime.setText(te.getDetailInfo().getBeginTime().toString()+"");
                        EndTime.setText(te.getDetailInfo().getEndTime().toString()+"");


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(Sign_in_off_Activity.this, "您的网络不稳定，请稍后重试~", Toast.LENGTH_SHORT).show();
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

    private void inipulllist() {
        gethttplist();
        messageAdapter = new SignTimeAdapter(list, Sign_in_off_Activity.this);
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        lstv.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                gethttplist();
                messageAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    private void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
    }

    public void gethttplist() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "signlist");
                params.addBodyParameter("UserID", MyApplication.getInstance().getUserInfo().getUserId());
//        params.addBodyParameter("DetaCode","signlist");
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONArray array = new JSONObject(result).getJSONArray("DetailInfo");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = (JSONObject) array.get(i);
                                SignlistEntity.DetailInfoEntity signentity = new SignlistEntity.DetailInfoEntity();
                                String data = object.getString("DateCode");
                                String signtime = object.getString("SignTime");
                                String signaddress = object.getString("SignAddress");
                                signentity.setSignAddress(signaddress);
                                signentity.setDateCode(data);
                                signentity.setSignTime(signtime);
                                list.add(signentity);
                            }
                            messageAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(Sign_in_off_Activity.this, "您的网络不稳定，请稍后重试~", Toast.LENGTH_SHORT).show();
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

    private void gethttp() {//获取签到状态
//        final String time1 = time.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "signone");
                requestParams.addBodyParameter("UserID", MyApplication.getInstance().getUserInfo().getUserId());
//                requestParams.addBodyParameter("DetaCode", time1);
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONArray json = new JSONObject(result).getJSONArray("status");
                            JSONObject jobject = (JSONObject) json.get(0);
                            String isSuccess = jobject.getString("staval");
                            if ("1".equals(isSuccess)) {//已经签到了
                                isSignIn = true;
                                sign_in.setVisibility(View.GONE);
                                JSONArray jsonarray = new JSONObject(result).getJSONArray("DetailInfo");
                                int jslength = jsonarray.length();
                                if (jslength > 1) {//签到签退都已经签了
                                    sign_out.setVisibility(View.GONE);
                                    JSONObject jarray = (JSONObject) jsonarray.get(0);
                                    String isinoff = jarray.getString("InOff");
                                    if ("Off".equals(isinoff)) {
                                        signoutdata1 = jarray.getString("DateCode");//签退日期年月日
                                        signouttime1 = jarray.getString("SignTime");//签退时间
                                        resignouttime = signouttime1.replace("-", ":");
                                        logMsg3(signoutdata1 + " " + resignouttime + ":00");//判断签到时间是否迟到
                                        String signaddress1 = jarray.getString("SignAddress");
                                        signOutAddress.setText(signaddress1);//添加签退地址

                                    } else {
                                        signdata1 = jarray.getString("DateCode");//签到年月日
                                        signtime1 = jarray.getString("SignTime");//签到时间
                                        resigntime = signtime1.replace("-", ":");
                                        logMsg2(signdata1 + " " + resigntime + ":00");//判断签到时间是否迟到
                                        String signaddress2 = jarray.getString("SignAddress");
                                        address.setText(signaddress2);//添加签到地址

                                    }
                                    JSONObject jarray2 = (JSONObject) jsonarray.get(1);
                                    String isinoff2 = jarray2.getString("InOff");
                                    if ("Off".equals(isinoff2)) {
                                        signoutdata1 = jarray2.getString("DateCode");//签退日期年月日
                                        signouttime1 = jarray2.getString("SignTime");//签退时间
                                        resignouttime = signouttime1.replace("-", ":");
                                        logMsg3(signoutdata1 + " " + resignouttime + ":00");//判断签到时间是否迟到
                                        String signaddress1 = jarray2.getString("SignAddress");
                                        signOutAddress.setText(signaddress1);//添加签退地址
                                    } else {
                                        signdata1 = jarray2.getString("DateCode");//签到年月日
                                        signtime1 = jarray2.getString("SignTime");//签到时间
                                        resigntime = signtime1.replace("-", ":");
                                        logMsg2(signdata1 + " " + resigntime + ":00");//判断签到时间是否迟到
                                        String signaddress2 = jarray2.getString("SignAddress");
                                        address.setText(signaddress2);//添加签到地址
                                    }
                                } else {//已经签到但是没有签退
                                    sign_out.setClickable(true);
                                    JSONObject jarray = (JSONObject) jsonarray.get(0);
                                    String isinoff = jarray.getString("InOff");
                                    if ("Off".equals(isinoff)) {
                                        signoutdata1 = jarray.getString("DateCode");//签退日期年月日
                                        signouttime1 = jarray.getString("SignTime");//签退时间
                                        resignouttime = signouttime1.replace("-", ":");
                                        logMsg3(signoutdata1 + " " + resignouttime + ":00");//判断签到时间是否迟到
                                        String signaddress1 = jarray.getString("SignAddress");
                                        signOutAddress.setText(signaddress1);//添加签退地址
                                    } else {
                                        signdata1 = jarray.getString("DateCode");//签到年月日
                                        signtime1 = jarray.getString("SignTime");//签到时间
                                        resigntime = signtime1.replace("-", ":");
                                        logMsg2(signdata1 + " " + resigntime + ":00");//判断签到时间是否迟到
                                        String signaddress2 = jarray.getString("SignAddress");
                                        address.setText(signaddress2);
                                    }
                                }

                            } else {
                                isSignIn = false;
                                sign_out.setClickable(false);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in://签到
                mLocationClient.start();
                mypDialog.show();
                istrue = true;
                break;
            case R.id.sign_out://签退
                mLocationClient.start();
                mypDialog.show();
                istrue = false;
                break;
            case R.id.back:
//                Intent intent = new Intent(this,LocationActivity.class);
//                double latitude1 = latitude;
//                double longitude1 = longitude;
//                intent.putExtra("latitude",latitude1);
//                intent.putExtra("longitude",longitude1);
//                startActivity(intent);
                onBackPressed();
                break;
            case R.id.address:
//                Intent intent = new Intent(this,LocationActivity.class);
//                startActivity(intent);
                break;

        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setLocationNotify(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedAddress(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
    }

    private void gethttpin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "signinadd");
                params.addBodyParameter("UserId", MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("SignType", "0");
                params.addBodyParameter("SignAddress", getaddress + getaddress2);
                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new MarketEntity()));
            }
        }).start();
    }

    private void gethttpout() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "signoffadd");
                params.addBodyParameter("UserId", MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("SignType", "0");
                params.addBodyParameter("SignAddress", getaddress + getaddress2);
                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new MarketEntity()));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        MarketEntity me = (MarketEntity) object;
        String isSuccess = me.getStatus().get(0).getStaval();
        if (isSuccess.equals("1")) {
            Toast.makeText(Sign_in_off_Activity.this, "签到(退)成功(∩_∩)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Sign_in_off_Activity.this, "签到(退)失败(ㄒoㄒ),请重新签到（退）", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(Sign_in_off_Activity.this, "服务器出错了(ㄒoㄒ)", Toast.LENGTH_SHORT).show();
    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            String time = location.getTime();
            latitude1 = location.getLatitude();
            longitude1 = location.getLongitude();
            SharedPreferences sp = getSharedPreferences("location", MODE_PRIVATE);
            sp.edit().putString("latitude1", "" + latitude1).putString("longitude1", "" + longitude1).commit();

            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            StringBuffer sb2 = new StringBuffer(256);
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb2.append("(").append(location.getLocationDescribe()).append(")");
//                sb.append("gps定位成功");
                getaddress = sb.toString();//签到地址
                getaddress2 = sb2.toString();//签到地址
                isSignAddress = true;
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb2.append("(").append(location.getLocationDescribe()).append(")");
                //运营商信息
//                sb.append("网络定位成功");
                getaddress = sb.toString();//签到地址
                getaddress2 = sb2.toString();//签到地址
                isSignAddress = true;
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
//                sb.append("定位失败,请检查您的手机是否联网");
                isSignAddress = false;
                Toast.makeText(Sign_in_off_Activity.this, "定位失败,请检查您的手机是否联网", Toast.LENGTH_SHORT).show();
            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
//                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                isSignAddress = false;
                Toast.makeText(Sign_in_off_Activity.this, "服务端网络定位失败", Toast.LENGTH_SHORT).show();
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
                isSignAddress = false;
                Toast.makeText(Sign_in_off_Activity.this, "网络不同导致定位失败，请检查网络是否通畅", Toast.LENGTH_SHORT).show();
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                isSignAddress = false;
                Toast.makeText(Sign_in_off_Activity.this, "定位失败,无法获取有效定位依据", Toast.LENGTH_SHORT).show();
            }

            if (istrue && isSignAddress) {
                gethttpin();
                logMsg(getaddress, getaddress2, time);
            } else if (!istrue && isSignAddress) {
                gethttpout();
                logMsg(getaddress, getaddress2, time);
            }

        }

    }

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str, String str2, String str3) {

        String s = str3.split(" ")[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String time = str3.substring(str3.indexOf(" ") + 1);
        if (isSignIn) {
            String timeAsk = new StringBuilder(s).append(" ").append(timeEndWork).append(":00").toString();
            signOutTime.setText(time);
            try {
                long dateAsk = dateFormat.parse(timeAsk).getTime();
                long dateNow = dateFormat.parse(str3).getTime();
                if (dateNow < dateAsk) {
                    int b = getResources().getColor(R.color.red);//得到配置文件里的颜色
                    signOutTime.setTextColor(b);
                    signOutTime.append("(早退)");
                } else {
                    int b = getResources().getColor(R.color.black);//得到配置文件里的颜色
                    signOutTime.setTextColor(b);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            signOutAddress.setText(str + str2);
            sign_out.setVisibility(View.GONE);
        } else {
            String timeAsk = new StringBuilder(s).append(" ").append(timeStartWork).append(":00").toString();
            datetime.setText(time);
            try {
                long dateAsk = dateFormat.parse(timeAsk).getTime();
                long dateNow = dateFormat.parse(str3).getTime();
                if (dateNow > dateAsk) {
                    int b = getResources().getColor(R.color.red);//得到配置文件里的颜色
                    datetime.setTextColor(b);
                    datetime.append("(晚签)");
                } else {
                    int b = getResources().getColor(R.color.black);//得到配置文件里的颜色
                    datetime.setTextColor(b);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            address.setText(str + str2);
            sign_in.setVisibility(View.GONE);
            isSignIn = true;
            sign_out.setClickable(true);
            sign_out.setBackgroundResource(R.drawable.sign_in_shape);
            sign_out.setTextColor(getResources().getColor(R.color.white));

        }
        mypDialog.dismiss();
        mLocationClient.stop();//定位结束

    }

    /**
     * 判断签到、退 时间是否迟到、早退
     *
     * @param signtime
     */
    private void logMsg2(String signtime) {//判断签到时间

        String s = signtime.split(" ")[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String time = signtime.substring(signtime.indexOf(" ") + 1);

        String timeAsk = new StringBuilder(s).append(" ").append(timeStartWork).append(":00").toString();
        datetime.setText(time);

        try {
            long dateAsk = dateFormat.parse(timeAsk).getTime();
            long dateNow = dateFormat.parse(signtime).getTime();
            if (dateNow > dateAsk) {
                int b = getResources().getColor(R.color.red);//得到配置文件里的颜色
                datetime.setTextColor(b);
                datetime.append("(晚签)");
            } else {
                int b = getResources().getColor(R.color.black);//得到配置文件里的颜色
                datetime.setTextColor(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        sign_in.setVisibility(View.GONE);
        isSignIn = true;
        sign_out.setClickable(true);
        sign_out.setBackgroundResource(R.drawable.sign_in_shape);
        sign_out.setTextColor(getResources().getColor(R.color.white));
    }

    private void logMsg3(String signoutTime) {//判断签退时间
        String s = signoutTime.split(" ")[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String time = signoutTime.substring(signoutTime.indexOf(" ") + 1);

        String timeAsk = new StringBuilder(s).append(" ").append(timeEndWork).append(":00").toString();
        signOutTime.setText(time);
        try {
            long dateAsk = dateFormat.parse(timeAsk).getTime();
            long dateNow = dateFormat.parse(signoutTime).getTime();
            if (dateNow < dateAsk) {
                int b = getResources().getColor(R.color.red);//得到配置文件里的颜色
                signOutTime.setTextColor(b);
                signOutTime.append("(早退)");
            } else {
                int b = getResources().getColor(R.color.black);//得到配置文件里的颜色
                signOutTime.setTextColor(b);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sign_out.setVisibility(View.GONE);
    }


    private void initDialog() {
        //实例化一个进度条对话框
        mypDialog = new ProgressDialog(this);
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);         //设置进度条风格
        mypDialog.setMessage("正在签到中,请稍后...");                        //设置提示信息
        mypDialog.setIndeterminate(true);                                                          //设置是否精度显示进度
        mypDialog.setCancelable(false);                                                            //设置是否触屏取消
        mypDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {                //设置按钮
                mypDialog.dismiss();
            }
        });
    }
}
