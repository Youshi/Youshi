package com.example.hmqqg.hm.ui;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Seaser_Adapter;
import com.example.hmqqg.hm.adapter.Submit_Adapter;
import com.example.hmqqg.hm.entity.Apply_Eneity;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.entity.SubmitSuperior_Entity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.example.hmqqg.hm.view.CustomDialog;
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
 * 审批 外出单（详情）
 * Created by BONA on 2016/1/22.
 */
public class Leave_Application extends BaseRequestActivity implements View.OnClickListener {

    private ImageView back;//返回
    private TextView title_top_bar;//标题

    private TextView mTv_title;//标题
    private TextView mTv_outtype;//外出类型
    private TextView mTv_startime;//开始时间
    private TextView mTv_endtime;//结束时间
    private TextView mTv_days;//时长 天
    private TextView mTv_hours;//时长  小时
    private TextView mTv_absence_cause;//外出事由
    private TextView tv_view;//审批意见
    private TextView tv_auditor;//审批人
//    private Spinner sp_superior;//审核人列表（spinner）
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();
    private List<SubmitSuperior_Entity.DetailInfoEntity> list;
    private ImageView addimageview1;//上传图片
    private ImageView addimageview2;//上传图片
    private ImageView addimageview3;//上传图片
    private Button mBt_agree;//同意按钮
    private Button mBt_disagree;//不同意按钮
    private CheckBox mBt_submit;//传阅上级审核
    private Spinner sp_superior;//传阅人员列表
    private LinearLayout liner_autidor;//审批人Spinner的布局
    private TableRow tab_auditor;//审批人的布局
    private TableRow tab_idea;//审批意见的布局

    private String idea;//获取审批意见
    private String appid;
    private String procid;

    private String title;
    private String approvaltype;
    private String startdata;
    private String enddata;
    private String days;
    private String hours;
    private String reason;
    private String userid;
    private String superior;//获取审核人列表
    private String number;
    private String message;
    private String name;
    private Submit_Adapter adapter;

    private java.util.List<String> mAppList = new ArrayList<String>();
    private OperateByEntity.DetailInfoEntity ob;

    private int ISAGREE =1;
    private int ISDISAGREE =2;
    private int SUBMIT =3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_out_activity);
        iniview();
        getintent();
        gethttpspinner();
    }

    private void getintent() {
        appid = getIntent().getStringExtra("appid");
        procid = getIntent().getStringExtra("procid");

        title = getIntent().getStringExtra("title");
        approvaltype = getIntent().getStringExtra("approvaltype");
        startdata = getIntent().getStringExtra("startdata");
        enddata = getIntent().getStringExtra("enddata");
        days = getIntent().getStringExtra("days");
        hours = getIntent().getStringExtra("hours");
        reason = getIntent().getStringExtra("reason");
        number = getIntent().getStringExtra("number");
        message = getIntent().getStringExtra("message");
        name = getIntent().getStringExtra("name");
        mTv_title.setText(title);//标题
        mTv_outtype.setText(approvaltype);//外出类型
        mTv_startime.setText(startdata);//开始时间
        mTv_endtime.setText(enddata);//结束时间
        mTv_days.setText(days);//时长 天
        mTv_hours.setText(hours);//时长  小时
        mTv_absence_cause.setText(reason);//外出事由
        tv_view.setText(message);//审批意见
        tv_auditor.setText(name);//审批人
        if ("1".equals(number)) {
            mBt_agree.setVisibility(View.GONE);
            mBt_disagree.setVisibility(View.GONE);
            liner_autidor.setVisibility(View.GONE);
        }else {
            tab_auditor.setVisibility(View.GONE);
            tab_idea.setVisibility(View.GONE);
        }
    }

    private void iniview() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("外出申请单");

        list = new ArrayList<>();

        mTv_title = (TextView) findViewById(R.id.tv_title);
        mTv_outtype = (TextView) findViewById(R.id.tv_outtype);
        mTv_startime = (TextView) findViewById(R.id.tv_startime);
        mTv_endtime = (TextView) findViewById(R.id.tv_endtime);
        mTv_days = (TextView) findViewById(R.id.tv_days);
        mTv_hours = (TextView) findViewById(R.id.tv_hours);
        mTv_absence_cause = (TextView) findViewById(R.id.tv_absence_cause);
        tv_view = (TextView) findViewById(R.id.tv_view);
        tv_auditor = (TextView) findViewById(R.id.tv_auditor);
        sp_superior = (Spinner) findViewById(R.id.sp_superior);
        liner_autidor = (LinearLayout) findViewById(R.id.liner_autidor);
        tab_auditor = (TableRow) findViewById(R.id.tab_auditor);
        tab_idea = (TableRow) findViewById(R.id.tab_idea);

        addimageview1 = (ImageView) findViewById(R.id.addimageview1);
        addimageview2 = (ImageView) findViewById(R.id.addimageview2);
        addimageview3 = (ImageView) findViewById(R.id.addimageview3);

        mBt_agree = (Button) findViewById(R.id.bt_agree);
        mBt_disagree = (Button) findViewById(R.id.bt_disagree);
        mBt_submit = (CheckBox) findViewById(R.id.bt_submit);

        back.setOnClickListener(this);
        mBt_agree.setOnClickListener(this);
        mBt_disagree.setOnClickListener(this);
        mBt_submit.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.bt_agree:
                if(mBt_submit.isChecked()){
//                    progressD();
                    showAlertDialog(SUBMIT);
                }
                showAlertDialog(ISAGREE);
                break;
            case R.id.bt_disagree:
                showAlertDialog(ISDISAGREE);
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
                                ArrayAdapter adapter = new ArrayAdapter(Leave_Application.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
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

                                ArrayAdapter adapter = new ArrayAdapter(Leave_Application.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_superior.setAdapter(adapter);
//                                pd.dismiss();
                            }
//                            Operate.setSelection(0, false);
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
    private void getHttp(final String flag) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","outaudit");
                requestParams.addBodyParameter("Operid",MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("AppID",appid);
                requestParams.addBodyParameter("ProcID",procid);
                if("S".equals(flag)){
                    requestParams.addBodyParameter("OperatedBy",superior);//上级审批人编号
                }
                requestParams.addBodyParameter("Flag",flag);
                requestParams.addBodyParameter("OperateMessage",idea);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Apply_Eneity()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Apply_Eneity ae = (Apply_Eneity) object;
        String isStaval = ae.getStatus().get(0).getStaval().toString();
        if("1".equals(isStaval)){
            Toast.makeText(Leave_Application.this, "审核成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Leave_Application.this,ApprovalActivity.class);
            startActivity(intent);
            finish();
        }else if("0".equals(isStaval)){
            Toast.makeText(Leave_Application.this, "审核失败，请稍后重试！", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(Leave_Application.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
    }

    private void showAlertDialog(final int isagree) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入审批意见");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                idea = builder.getMessage().toString();
                if(isagree==ISAGREE){
                    getHttp("Y");
                }else if(isagree==ISDISAGREE){
                    getHttp("R");
                }else if(isagree==SUBMIT) {
                    getHttp("S");
                }
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
    private void progressD() {
        pd = new ProgressDialog(Leave_Application.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
        pd.setMessage("正在上传,请稍候...");
        pd.show();
    }
}
