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
import com.example.hmqqg.hm.entity.MarkerDEntity;
import com.example.hmqqg.hm.entity.OperateByEntity;
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
 * 申请的详情
 * Created by Administrator on 2016/1/21.
 */
public class Application_details extends BaseRequestActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title_top_bar;
    private TextView name;
    private TextView leaveadd;
    private TextView yuanyin;
    private TextView start;
    private TextView end;
    private TextView minute;
    private TextView hours;
    private TextView opinion;
    private TextView perpleol;
    private LinearLayout chuanyue;
    private LinearLayout opinionlin;//审批意见的linerlayout
    private LinearLayout perple;//审批人的linerlayout
    private LinearLayout button;//同意。取消按钮的linerlayout
    private Button bt_agree;
    private Button bt_disagree;
    CustomDialog.Builder builder;
    private String ApprovalID;
    private String PROCID;
    private String result;
    private CheckBox bt_submit;
    private Spinner sp_superior;
    OperateByEntity.DetailInfoEntity ob;
    private String OperateBy;
    private Spinner Operate;//审核人

    private java.util.List<String> mAppList = new ArrayList<String>();
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_details);
        initView();
        getview();
        getOperateBy();
    }

    private void getview() {
        Intent intent = getIntent();
        String titile = intent.getStringExtra("titile");
        ApprovalID = intent.getStringExtra("ApprovalID");
        PROCID = intent.getStringExtra("PROCID");
        String ApprovalType = intent.getStringExtra("ApprovalType");
        String Reason = intent.getStringExtra("Reason");
        String StartDate = intent.getStringExtra("StartDate");
        String EndDate = intent.getStringExtra("EndDate");
        String mminute = intent.getStringExtra("minute");
        String hourss = intent.getStringExtra("hours");
        String number = intent.getStringExtra("number");
        String message = intent.getStringExtra("message");
        String OperateByName = intent.getStringExtra("name");

        name.setText(titile + "");
        leaveadd.setText(ApprovalType + "");
        yuanyin.setText(Reason + "");
        start.setText(StartDate + "");
        end.setText(EndDate + "");
        minute.setText(mminute + "");
        hours.setText(hourss + "");
        perpleol.setText(OperateByName + "");
        if("null".equals(message)){
        opinion.setText("无审批意见");
        }else{
        opinion.setText(message+ "");
        }
        if ("1".equals(number)) {//判断是否为请假审批，1为请假审批
            opinionlin.setVisibility(View.GONE);
            perple.setVisibility(View.GONE);
        } else {//2为我的请假列表和请假结果
            chuanyue.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            bt_submit.setVisibility(View.GONE);
            sp_superior.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        AddjobEntity ae = (AddjobEntity) object;
        if ("1".equals(ae.getStatus().get(0).getStaval())) {
            Toast.makeText(Application_details.this, "审批成功！", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(Application_details.this,ApprovalActivity.class);
//            startActivity(intent);
            finish();
        } else {
            Toast.makeText(Application_details.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        sp_superior = (Spinner) findViewById(R.id.sp_superior);
        bt_submit = (CheckBox) findViewById(R.id.bt_submit);
        name = (TextView) findViewById(R.id.name);
        leaveadd = (TextView) findViewById(R.id.leaveadd);
        yuanyin = (TextView) findViewById(R.id.yuanyin);
        start = (TextView) findViewById(R.id.start);
        end = (TextView) findViewById(R.id.end);
        opinion = (TextView) findViewById(R.id.opinion);
        perpleol = (TextView) findViewById(R.id.perpleol);
        minute = (TextView) findViewById(R.id.minute);
        hours = (TextView) findViewById(R.id.hours);
        chuanyue = (LinearLayout) findViewById(R.id.chuanyue);
        opinionlin = (LinearLayout) findViewById(R.id.opinionlin);
        perple = (LinearLayout) findViewById(R.id.perple);
        button = (LinearLayout) findViewById(R.id.button);
        bt_agree = (Button) findViewById(R.id.bt_agree);
        bt_disagree = (Button) findViewById(R.id.bt_disagree);
        title_top_bar.setText("请假申请单");
        back.setOnClickListener(this);
        bt_agree.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        bt_disagree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.bt_agree:
                if (bt_submit.isChecked()) {
                    showAlertDialog3();
                }else {
                showAlertDialog1();
                }
                break;
            case R.id.bt_disagree:
                showAlertDialog2();
                break;
            case R.id.bt_submit:
                getspinner();
                break;
        }
    }
    private void getspinner(){
//        sp_superior.setVisibility(View.VISIBLE);
    }
    private void showAlertDialog1() {

//        final String a = edit.getText().toString();
//        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入理由");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = builder.getMessage();//获取弹框输入内容
//                if(a.equals("")||a==null){
//                    Toast.makeText(Marketing_Details.this, "理由不能为空", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(Marketing_Details.this, "你写的内容是" + a, Toast.LENGTH_SHORT).show();
//                }
                gethttpagree();
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

    private void showAlertDialog3() {

        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入理由");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = builder.getMessage();//获取弹框输入内容
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

    private void showAlertDialog2() {

        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入理由");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = builder.getMessage();//获取弹框输入内容
                gethttpdisagree();
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

    public void gethttpdisagree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "leaveaudit");
                params.addBodyParameter("AppID", ApprovalID);
                params.addBodyParameter("ProcID", PROCID);
                if (result != null || !result.equals("")) {
                    params.addBodyParameter("OperateMessage", result);
                }
                params.addBodyParameter("Flag", "R");
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new AddjobEntity()));
            }
        }).start();
    }

    //
    public void gethttpagree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "leaveaudit");
                params.addBodyParameter("AppID", ApprovalID);
                params.addBodyParameter("ProcID", PROCID);
                if (result != null || !result.equals("")) {
                    params.addBodyParameter("OperateMessage", result);
                }
                params.addBodyParameter("Flag", "Y");
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new AddjobEntity()));
            }
        }).start();
    }

    public void gethttpagreetwo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "leaveaudit");
                params.addBodyParameter("AppID", ApprovalID);
                params.addBodyParameter("ProcID", PROCID);
                params.addBodyParameter("OperatedBy", OperateBy);
                if (result != null || !result.equals("")) {
                    params.addBodyParameter("OperateMessage", result);
                }
                params.addBodyParameter("Flag", "S");
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new AddjobEntity()));
            }
        }).start();
    }

    public void getOperateBy() {
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
                                ArrayAdapter adapter = new ArrayAdapter(Application_details.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
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

                                ArrayAdapter adapter = new ArrayAdapter(Application_details.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                sp_superior.setAdapter(adapter);
                            }
//                            Operate.setSelection(0, false);
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
        pd = new ProgressDialog(Application_details.this);
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
