package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Mywork_adapter;
import com.example.hmqqg.hm.adapter.Reportde_adapter;
import com.example.hmqqg.hm.entity.AddjobEntity;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.DailyEntity;
import com.example.hmqqg.hm.entity.DailyEntity_d;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.entity.WeekEntity_d;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.example.hmqqg.hm.view.CustomDialog;
import com.google.gson.Gson;

import org.json.JSONArray;
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
 * 我的工作汇报日报详情
 * Created by baotao on 2016/3/19.
 */
public class MyWorkReport_Activity extends BaseRequestActivity implements View.OnClickListener {
    private TextView title_top_bar;
    private ImageView back;

    private TextView workreport_time;//工作时间
    private TextView amount1;//陌电数量
    private TextView amount2;//回访客户数量
    private TextView amount3;//意向客户数量
    private TextView condition1;//回访客户情况
    private TextView condition2;//关单情况
    private TextView condition3;//次日工作安排
    private ListView lit_comment;//显示评论
    private TextView plan;//根据自己改
    private LinearLayout linear_comment;//评论
    private LinearLayout chuanyue;//传阅
    private LinearLayout button;//按钮

    private String work_time1;
    private String amount11;
    private String amount21;
    private String amount31;
    private String condition11;
    private String condition21;
    private String condition31;

    private String WLogId;
    private String number;
    private CheckBox bt_submit;
    private Button bt_agree;
    private Button bt_disagree;
    private Spinner sp_superior;
    private java.util.List<String> mAppList = new ArrayList<String>();
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();
    private Mywork_adapter readapter;
    private List<DailyEntity_d.CommentsEntity> weeklist;
    OperateByEntity.DetailInfoEntity ob;
    private String OperateBy;
    CustomDialog.Builder builder;
    private String result;
    private String PROCID;
    private String OperateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workreport_daily);
        WLogId = getIntent().getStringExtra("WLogId");
        number = getIntent().getStringExtra("number");
        PROCID = getIntent().getStringExtra("PROCID");
        OperateMessage = getIntent().getStringExtra("OperateMessage");
        iniView();
        if ("5".equals(number)) {
            chuanyue.setVisibility(View.VISIBLE);
            linear_comment.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
        }
        getOperateBy();
        gethttp();//接口请求

    }

    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getoneworklog");
                requestParams.addBodyParameter("wlId", WLogId);
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new DailyEntity_d()));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        DailyEntity_d de = (DailyEntity_d) object;
        if (de.getStatus().get(0).getStaval().equals("1")) {
            workreport_time.setText(de.getDetailInfo().get(0).getStartDate() + "");
            amount1.setText(de.getDetailInfo().get(0).getAlienCall() + "");
            amount2.setText(de.getDetailInfo().get(0).getReturnCall() + "");
            amount3.setText(de.getDetailInfo().get(0).getIntentNum() + "");
            condition1.setText(de.getDetailInfo().get(0).getReturnCond() + "");
            condition2.setText(de.getDetailInfo().get(0).getDealCond() + "");
            condition3.setText(de.getDetailInfo().get(0).getNextDay() + "");
            title_top_bar.setText(de.getDetailInfo().get(0).getUserName() + "工作日志");
            weeklist.addAll(de.getComments());
            readapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void iniView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        workreport_time = (TextView) findViewById(R.id.workreport_time);
        plan = (TextView) findViewById(R.id.plan);
        plan.setText("次日工作安排");
        amount1 = (TextView) findViewById(R.id.amount1);
        amount2 = (TextView) findViewById(R.id.amount2);
        amount3 = (TextView) findViewById(R.id.amount3);
        bt_agree = (Button) findViewById(R.id.bt_agree);
        bt_disagree = (Button) findViewById(R.id.bt_disagree);
        sp_superior = (Spinner) findViewById(R.id.sp_superior);
        bt_submit = (CheckBox) findViewById(R.id.bt_submit);
        condition1 = (TextView) findViewById(R.id.condition1);
        condition2 = (TextView) findViewById(R.id.condition2);
        condition3 = (TextView) findViewById(R.id.condition3);
        lit_comment = (ListView) findViewById(R.id.lit_comment);
        linear_comment = (LinearLayout) findViewById(R.id.linear_comment);
        button = (LinearLayout) findViewById(R.id.button);
        chuanyue = (LinearLayout) findViewById(R.id.chuanyue);
        weeklist = new ArrayList<>();
        readapter = new Mywork_adapter(weeklist, MyWorkReport_Activity.this);
        lit_comment.setAdapter(readapter);
        readapter.notifyDataSetChanged();
        back.setOnClickListener(this);
        bt_disagree.setOnClickListener(this);
        bt_agree.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        sp_superior.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                       long id) {
                OperateBy = List.get((int) id).getUserid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.bt_agree:
                if (bt_submit.isChecked()) {
                    if("请选择审核人".equals(sp_superior.getSelectedItem().toString())){
                        Toast.makeText(MyWorkReport_Activity.this, "请选择审核人", Toast.LENGTH_SHORT).show();
                    }else{
                        showAlertDialog2();//传阅
                    }
                } else {
                    showAlertDialog1();//审批
                }
                break;
            case R.id.bt_disagree:
                onBackPressed();
                break;
        }
    }

    public void getOperateBy() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getauditor");
                requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            Gson gson = new Gson();
                            OperateByEntity opby = gson.fromJson(result, OperateByEntity.class);
                            String staval = opby.getStatus().get(0).getStaval();
                             if ("1".equals(staval)){
                                JSONArray json = new JSONObject(result).getJSONArray("DetailInfo");
                                mAppList.add("请选择审核人");
                                ob = new OperateByEntity.DetailInfoEntity();
                                ob.setUserid("op10001");
                                ob.setUserName("审核人");
                                List.add(ob);
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject j = (JSONObject) json.get(i);
                                    String userid = j.getString("userid");
                                    String username = j.getString("UserName");
                                    ob = new OperateByEntity.DetailInfoEntity();
                                    ob.setUserid(userid);
                                    ob.setUserName(username);
                                    List.add(ob);
                                    mAppList.add(username);
                                }

                                ArrayAdapter adapter = new ArrayAdapter(MyWorkReport_Activity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_superior.setAdapter(adapter);
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

    private void showAlertDialog1() {

        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入理由");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = builder.getMessage();//获取弹框输入内容
                progressD();
                gethttpagree();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    private void showAlertDialog2() {

        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入理由");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = builder.getMessage();//获取弹框输入内容
                progressD();
                gethttpagreetwo();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    public void gethttpagree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "worklogaudit");
                requestParams.addBodyParameter("AppID", WLogId);
                requestParams.addBodyParameter("ProcID", PROCID);
                requestParams.addBodyParameter("Flag", "Y");
                requestParams.addBodyParameter("OperateMessage", result);
                requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        pd.dismiss();
                        try {
                            Gson gson = new Gson();
                            AddjobEntity ae = gson.fromJson(result, AddjobEntity.class);
                            if ("1".equals(ae.getStatus().get(0).getStaval())) {
                                Toast.makeText(MyWorkReport_Activity.this, "审批成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(MyWorkReport_Activity.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
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

    public void gethttpagreetwo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "worklogaudit");
                requestParams.addBodyParameter("AppID", WLogId);
                requestParams.addBodyParameter("ProcID", PROCID);
                requestParams.addBodyParameter("Flag", "Y");
                requestParams.addBodyParameter("OperatedBy", OperateBy);
                requestParams.addBodyParameter("OperateMessage", result);
                requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        pd.dismiss();
                        try {
                            Gson gson = new Gson();
                            AddjobEntity ae = gson.fromJson(result, AddjobEntity.class);
                            if ("1".equals(ae.getStatus().get(0).getStaval())) {
                                Toast.makeText(MyWorkReport_Activity.this, "审批成功！", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(MyWorkReport_Activity.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
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

    private void progressD() {
        pd = new ProgressDialog(MyWorkReport_Activity.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
        pd.setMessage("正在保存,请稍候...");
        pd.show();
    }
}
