package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.ApprovalMonthly_Adapter;
import com.example.hmqqg.hm.adapter.Mywork_adapter;
import com.example.hmqqg.hm.adapter.Reportde_adapter;
import com.example.hmqqg.hm.entity.DailyEntity_d;
import com.example.hmqqg.hm.entity.MyMonentity;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.entity.WeekEntity_d;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 工作审批----审批结果详情
 * Created by Administrator on 2016/5/27.
 */
public class ApprovalResult_Activity extends BaseRequestActivity {
    private TextView title_top_bar;
    private ImageView back;

    private TextView work_time;//录入时间
    private TextView plan;//下（月或者周或者日）工作安排
    private TextView amount1;//陌电数量
    private TextView amount2;//回访客户数量
    private TextView amount3;//意向客户数量
    private TextView condition1;//回访客户情况
    private TextView condition2;//关单情况
    private TextView condition3;//下周工作安排
    private TextView name;//评论人
    private TextView number;//内容
    private ListView lit_comment;//内容
    private Intent intent;

    private String Wtype;
    private String UserName;
    private String WorkTime;
    private String AlienCall;
    private String Returncall;
    private String Intentnum;
    private String Returncond;
    private String Dealcond;
    private String Nextwork;
    private String ID;

    private String DAY = "day";
    private String WEEK = "week";
    private String MONTH = "month";
    private int what;
    private int a = 1;
    private int b = 2;
    private int c = 3;
    DailyEntity_d.CommentsEntity de;
    WeekEntity_d.CommentsEntity we;
    MyMonentity.CommentsEntity mm;
    private List<DailyEntity_d.CommentsEntity> worklist;
    private List<WeekEntity_d.CommentsEntity> weeklist;
    private List<MyMonentity.CommentsEntity> monthlist;
    private Mywork_adapter readapter;
    private Reportde_adapter readapter1;
    private ApprovalMonthly_Adapter appmonthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approval_result);
        intent = getIntent();
        Wtype = intent.getStringExtra("Wtype");//判断日、月、周报
        ID = intent.getStringExtra("ID");//id
        iniView();//初始化控件
        if ("day".equals(Wtype)) {
            gethttp(a);
        }
        if ("week".equals(Wtype)) {
            gethttp(b);
        }
        if ("month".equals(Wtype)) {
            gethttp(c);
        }
        getintent();
        settext();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void iniView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        work_time = (TextView) findViewById(R.id.work_time);
        plan = (TextView) findViewById(R.id.plan);
        amount1 = (TextView) findViewById(R.id.amount1);
        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.number);
        amount2 = (TextView) findViewById(R.id.amount2);
        amount3 = (TextView) findViewById(R.id.amount3);
        condition1 = (TextView) findViewById(R.id.condition1);
        condition2 = (TextView) findViewById(R.id.condition2);
        condition3 = (TextView) findViewById(R.id.condition3);
        lit_comment = (ListView) findViewById(R.id.lit_comment);
        if ("day".equals(Wtype)) {
            worklist = new ArrayList<>();
            readapter = new Mywork_adapter(worklist, ApprovalResult_Activity.this);
            lit_comment.setAdapter(readapter);
            readapter.notifyDataSetChanged();
        }
        if ("week".equals(Wtype)) {
            weeklist = new ArrayList<>();
            readapter1 = new Reportde_adapter(weeklist, ApprovalResult_Activity.this);
            lit_comment.setAdapter(readapter1);
            readapter1.notifyDataSetChanged();
        }
        if ("month".equals(Wtype)) {
            monthlist = new ArrayList<>();
            appmonthAdapter = new ApprovalMonthly_Adapter(monthlist, ApprovalResult_Activity.this);
            lit_comment.setAdapter(appmonthAdapter);
            appmonthAdapter.notifyDataSetChanged();
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void getintent() {
        UserName = intent.getStringExtra("UserName");//被审核人的用户名
        WorkTime = intent.getStringExtra("StartDate");//录入时间
        AlienCall = intent.getStringExtra("AlienCall");//陌电数量
        Returncall = intent.getStringExtra("Returncall");//回访客户数量
        Intentnum = intent.getStringExtra("Intentnum");//意向客户数量
        Returncond = intent.getStringExtra("Returncond");//回访客户情况
        Dealcond = intent.getStringExtra("Dealcond");//关单情况
        Nextwork = intent.getStringExtra("Nextwork");//次日工作安排
    }

    private void settext() {
        work_time.setText(WorkTime.replace("T00:00:00", "") + "");
        if ("".equals(Wtype) || Wtype == null) {
            title_top_bar.setText("员工工作审核");
        } else if (DAY.equals(Wtype)) {
            title_top_bar.setText(UserName + "的工作日报审核");
            plan.setText("次日工作安排");
        } else if (WEEK.equals(Wtype)) {
            title_top_bar.setText(UserName + "的工作周报审核");
            plan.setText("下周工作安排");
        } else if (MONTH.equals(Wtype)) {
            title_top_bar.setText(UserName + "的工作月报审核");
            plan.setText("下月工作安排");
        }
        amount1.setText(AlienCall + "");
        amount2.setText(Returncall + "");
        amount3.setText(Intentnum + "");
        condition1.setText(Returncond + "");
        condition2.setText(Dealcond + "");
        condition3.setText(Nextwork + "");
    }

    public void gethttp(final int what) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                if (what == a) {
                    requestParams.addBodyParameter("action", "getoneworklog");
                    requestParams.addBodyParameter("wlId", ID);
                    requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
//                        x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new DailyEntity_d()));
                    x.http().get(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
//                            DailyEntity_d de = gson.fromJson(result, DailyEntity_d.class);
                            JSONArray json = null;
                            try {
                                json = new JSONObject(result).getJSONArray("Comments");
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject j = (JSONObject) json.get(i);
                                    String OperateByName = j.getString("OperateByName");
                                    String username = j.getString("OperateMessage");
                                    de = new DailyEntity_d.CommentsEntity();
                                    de.setOperateByName(OperateByName);
                                    de.setOperateMessage(username);
                                    worklist.add(de);
                                }
                                readapter.notifyDataSetChanged();
                            } catch (JSONException e) {
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
                if (what == b) {
                    requestParams.addBodyParameter("action", "getoneweek");
                    requestParams.addBodyParameter("hbid", ID);
                    requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
//                        x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new WeekEntity_d()));
                    x.http().get(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
//                            WeekEntity_d de = gson.fromJson(result, WeekEntity_d.class);
                            JSONArray json = null;
                            try {
                                json = new JSONObject(result).getJSONArray("Comments");
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject j = (JSONObject) json.get(i);
                                    String OperateByName = j.getString("OperateByName");
                                    String username = j.getString("OperateMessage");
                                    we = new WeekEntity_d.CommentsEntity();
                                    we.setOperateByName(OperateByName);
                                    we.setOperateMessage(username);
                                    weeklist.add(we);
                                }
                                readapter1.notifyDataSetChanged();
                            } catch (JSONException e) {
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
                if (what == c) {
                    requestParams.addBodyParameter("action", "getonemonth");
                    requestParams.addBodyParameter("hbid", ID);
                    requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
//                        x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new MyMonentity()));
                    x.http().get(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Gson gson = new Gson();
//                            WeekEntity_d de = gson.fromJson(result, WeekEntity_d.class);
                            JSONArray json = null;
                            try {
                                json = new JSONObject(result).getJSONArray("Comments");
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject j = (JSONObject) json.get(i);
                                    String OperateByName = j.getString("OperateByName");
                                    String username = j.getString("OperateMessage");
                                    mm = new MyMonentity.CommentsEntity();
                                    mm.setOperateByName(OperateByName);
                                    mm.setOperateMessage(username);
                                    monthlist.add(mm);
                                }
                                appmonthAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
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
            }
        }).start();
    }
}
