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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.AddjobEntity;
import com.example.hmqqg.hm.entity.Apply_Eneity;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.DailyEntity_d;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.entity.WeekEntity;
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
 * 工作审批和工作汇报的日报详情（没有评论）
 * Created by baotao on 2016/3/19.
 */
public class MyWorkReport_Activity1 extends BaseRequestActivity implements View.OnClickListener {
    private TextView title_top_bar;
    private ImageView back;

    private TextView workreport_time;//工作时间
    private TextView amount1;//陌电数量
    private TextView amount2;//回访客户数量
    private TextView amount3;//意向客户数量
    private TextView condition1;//回访客户情况
    private TextView condition2;//关单情况
    private TextView condition3;//下周工作安排
    private TextView plan;//下周工作安排
    //        private TextView ed_workevaluate;//评论
//    private LinearLayout linear_comment;//评论的线性布局
    private java.util.List<String> mAppList = new ArrayList<String>();
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();
    private OperateByEntity.DetailInfoEntity ob;
    private LinearLayout linear_comment;//评论
    private LinearLayout button;
    private Button mBt_agree;//同意按钮
    private Button mBt_disagree;//不同意按钮
    private LinearLayout chuanyue;
    private CheckBox mBt_submit;//传阅上级审核
    private Spinner sp_superior;//传阅人员列表
    private String work_time1;
    private String amount11;
    private String amount21;
    private String amount31;
    private String condition11;
    private String condition21;
    private String condition31;
    private String superior;//获取审核人列表
    private String hdid;
    private CustomDialog.Builder builder;
    private String result;
    private String number;
    private String procid;
    private Boolean ischeck;
    private String APPROVER ="请选择审核人";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workreport_daily);
        hdid = getIntent().getStringExtra("hdid");
        procid = getIntent().getStringExtra("procid");
        number = getIntent().getStringExtra("number");
        iniView();
        if("5".equals(number)){
            chuanyue.setVisibility(View.VISIBLE);
            linear_comment.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
        }
        gethttpspinner();
        gethttp();//接口请求
    }

    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getoneweek");
                requestParams.addBodyParameter("hbid", hdid);
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new WeekEntity_d()));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        WeekEntity_d de = (WeekEntity_d) object;
        if (de.getStatus().get(0).getStaval().equals("1")) {
            workreport_time.setText(de.getDetailInfo().get(0).getStartDate() + "");
            amount1.setText(de.getDetailInfo().get(0).getAlienCall()+"");
            amount2.setText(de.getDetailInfo().get(0).getReturnCall()+"");
            amount3.setText(de.getDetailInfo().get(0).getIntentNum()+"");
            condition1.setText(de.getDetailInfo().get(0).getReturnCond()+"");
            condition2.setText(de.getDetailInfo().get(0).getDealCond()+"");
            condition3.setText(de.getDetailInfo().get(0).getNextWork()+"");
            title_top_bar.setText(de.getDetailInfo().get(0).getUSERNAME()+"工作日志");
            String dpnr = de.getDetailInfo().get(0).getDPNR().toString();
        }

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(MyWorkReport_Activity1.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
    }

    private void iniView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        plan = (TextView) findViewById(R.id.plan);
        plan.setText("下周工作安排");

        workreport_time = (TextView) findViewById(R.id.workreport_time);
        amount1 = (TextView) findViewById(R.id.amount1);
        amount2 = (TextView) findViewById(R.id.amount2);
        amount3 = (TextView) findViewById(R.id.amount3);
        chuanyue = (LinearLayout) findViewById(R.id.chuanyue);
        button = (LinearLayout) findViewById(R.id.button);
        mBt_agree = (Button) findViewById(R.id.bt_agree);
        mBt_disagree = (Button) findViewById(R.id.bt_disagree);
        mBt_submit = (CheckBox) findViewById(R.id.bt_submit);
        sp_superior = (Spinner) findViewById(R.id.sp_superior);
        condition1 = (TextView) findViewById(R.id.condition1);
        condition2 = (TextView) findViewById(R.id.condition2);
        condition3 = (TextView) findViewById(R.id.condition3);
//        ed_workevaluate = (TextView) findViewById(R.id.ed_workevaluate);//评论
        linear_comment = (LinearLayout) findViewById(R.id.linear_comment);//评论线性布局
        back.setOnClickListener(this);
        mBt_agree.setOnClickListener(this);
        mBt_disagree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.bt_agree:
                ischeck  = mBt_submit.isChecked();
                String strspinner = sp_superior.getSelectedItem().toString();
                if(ischeck&&APPROVER.equals(strspinner)){
                    Toast.makeText(MyWorkReport_Activity1.this, "请先选择审批人", Toast.LENGTH_SHORT).show();
                }else{
                    showAlertDialog1();
                }
                break;
            case R.id.bt_disagree:
                finish();
                break;
        }
    }

    private void gethttpspinner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
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
                            if ("0".equals(staval)) {
                                mAppList.add("请选择审核人");
                                ob = new OperateByEntity.DetailInfoEntity();
                                ob.setUserid("op10001");
                                ob.setUserName("审核人");
                                List.add(ob);
                                ArrayAdapter adapter = new ArrayAdapter(MyWorkReport_Activity1.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_superior.setAdapter(adapter);
                            } else {
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

                                ArrayAdapter adapter = new ArrayAdapter(MyWorkReport_Activity1.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_superior.setAdapter(adapter);
//                                pd.dismiss();
                            }
                            sp_superior.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                                           long id) {
                                    superior = List.get((int) id).getUserid();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> arg0) {
                                    // TODO Auto-generated method stub

                                }
                            });

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

    private void showAlertDialog1() {
        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入理由");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = builder.getMessage();//获取弹框输入内容
                progressD();
                gethttpagree(ischeck);
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
    public void gethttpagree(final Boolean isOpera) {
        if(!isOpera){
            superior="";
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","workhbaudit");
                requestParams.addBodyParameter("AppID",hdid);
                requestParams.addBodyParameter("OperId",str);
                requestParams.addBodyParameter("ProcID",procid);
                requestParams.addBodyParameter("Flag","Y");
                requestParams.addBodyParameter("Operatedby",superior);
                requestParams.addBodyParameter("OperateMessage",result);
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        pd.dismiss();
                        try {
                            Gson gson = new Gson();
                            AddjobEntity ae = gson.fromJson(result, AddjobEntity.class);
                            if ("1".equals(ae.getStatus().get(0).getStaval())) {
                                Toast.makeText(MyWorkReport_Activity1.this, "审批成功！", Toast.LENGTH_SHORT).show();
//                                Intent i= new Intent(MyWorkReport_Activity1.this,Lookreport_Activity.class);
//                                startActivity(i);
                                Weekly_Fragment1 week = new Weekly_Fragment1();
                                finish();
                            } else {
                                Toast.makeText(MyWorkReport_Activity1.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
                        Toast.makeText(MyWorkReport_Activity1.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
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
                //设置你的操作事项
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
    public void gethttpagreetwo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getauditor");
                requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        pd.dismiss();
                        try {
                            Gson gson = new Gson();
                            AddjobEntity ae = gson.fromJson(result, AddjobEntity.class);
                            if ("1".equals(ae.getStatus().get(0).getStaval())) {
                                Toast.makeText(MyWorkReport_Activity1.this, "审批成功！", Toast.LENGTH_SHORT).show();
//                                Intent i= new Intent(MyWorkReport_Activity1.this,Lookreport_Activity.class);
//                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(MyWorkReport_Activity1.this, "您的网络不稳定，请稍后重试~", Toast.LENGTH_SHORT).show();
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
        pd = new ProgressDialog(MyWorkReport_Activity1.this);
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
