package com.example.hmqqg.hm.ui;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Submit_Adapter;
import com.example.hmqqg.hm.entity.DailyEntity_d;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.entity.SubmitSuperior_Entity;
import com.example.hmqqg.hm.entity.UndlingMonthEntity;
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

/**员工下属月报详情
 * Created by Administrator on 2016/4/21.
 */
public class UndlingMonth_Details extends BaseRequestActivity implements View.OnClickListener {
    private TextView title_top_bar;
    private ImageView back;

    private TextView work_time;//录入时间
    private TextView plan;
    private TextView amount1;//陌电数量
    private TextView amount2;//回访客户数量
    private TextView amount3;//意向客户数量
    private TextView condition1;//回访客户情况
    private TextView condition2;//关单情况
    private TextView condition3;//下周工作安排

    private Spinner sp_superior;//审核人列表spinner
    private Button mBt_agree;//同意按钮
    private Button mBt_disagree;//不同意按钮
    private CheckBox mBt_submit;//传阅上级审核
//    private Button mBt_worksave;//保存评论按钮
//    private EditText mEd_workevaluate;//评论输入框
    private String hbid;
    private String procid;
    private String stamess;
    private String staval;
    private String dpnr;
    private String result;

    private String OperateBy;//获取审核人id
    private ArrayAdapter<String> approverAdapter;//系统默认adapter
    private List<String> approverList;//审核人的列表List
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();
    OperateByEntity.DetailInfoEntity op;

    private CustomDialog.Builder builder;
    private Submit_Adapter adapter;
    private List<SubmitSuperior_Entity.DetailInfoEntity> list;
    private String Suserid;
    private String APPROVER ="请选择上级审核人";
    private Boolean ischeck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.undlingmonth_details);
        iniView();
        hbid = getIntent().getStringExtra("hdid");
        procid = getIntent().getStringExtra("procid");
        gethttp();
        gethttpspinner();
    }

    private void iniView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        work_time = (TextView) findViewById(R.id.work_time);
        plan = (TextView) findViewById(R.id.plan);
        plan.setText("下月工作安排");
        amount1 = (TextView) findViewById(R.id.amount1);
        amount2 = (TextView) findViewById(R.id.amount2);
        amount3 = (TextView) findViewById(R.id.amount3);
        condition1 = (TextView) findViewById(R.id.condition1);
        condition2 = (TextView) findViewById(R.id.condition2);
        condition3 = (TextView) findViewById(R.id.condition3);
//        mBt_worksave = (Button) findViewById(R.id.bt_worksave);
//        mEd_workevaluate = (EditText) findViewById(R.id.ed_workevaluate);
        mBt_agree = (Button) findViewById(R.id.bt_agree);
        mBt_disagree = (Button) findViewById(R.id.bt_disagree);
        mBt_submit = (CheckBox) findViewById(R.id.bt_submit);
        sp_superior = (Spinner) findViewById(R.id.sp_superior);
        approverList = new ArrayList<>();
        approverAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,approverList);
        //设置下拉风格
        approverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_superior.setAdapter(approverAdapter);
//        list = new ArrayList<>();
//        adapter = new Submit_Adapter(list,UndlingMonth_Details.this);
//        adapter.notifyDataSetChanged();

        back.setOnClickListener(this);
//        mBt_worksave.setOnClickListener(this);
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
            case R.id.bt_agree://同意按钮
                ischeck  = mBt_submit.isChecked();
                String strspinner = sp_superior.getSelectedItem().toString();
                if(ischeck&&APPROVER.equals(strspinner)){
                    Toast.makeText(UndlingMonth_Details.this, "请先选择审批人", Toast.LENGTH_SHORT).show();
                }else{
                    showAlertDialog1();
                }
                break;
            case R.id.bt_disagree://不同意按钮
                finish();
                break;
        }
    }
    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","getonemonth");
                requestParams.addBodyParameter("hbid",hbid);
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new WeekEntity_d()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        WeekEntity_d de = (WeekEntity_d)object;
        if(de.getStatus().get(0).getStaval().equals("1")){
            work_time.setText(de.getDetailInfo().get(0).getStartDate()+"");
            amount1.setText(de.getDetailInfo().get(0).getAlienCall()+"");
            amount2.setText(de.getDetailInfo().get(0).getReturnCall()+"");
            amount3.setText(de.getDetailInfo().get(0).getIntentNum()+"");
            condition1.setText(de.getDetailInfo().get(0).getReturnCond()+"");
            condition2.setText(de.getDetailInfo().get(0).getDealCond()+"");
            condition3.setText(de.getDetailInfo().get(0).getNextWork()+"");
            title_top_bar.setText(de.getDetailInfo().get(0).getUSERNAME()+"的工作月报");
            String dpnr = de.getDetailInfo().get(0).getDPNR().toString();//获取评论内容
        }


    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(UndlingMonth_Details.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
    }

    private void gethttpspinner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","getauditor");
                requestParams.addBodyParameter("operid",MyApplication.getInstance().getUserInfo().getUserId());
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        OperateByEntity opby =gson.fromJson(result,OperateByEntity.class);
                        String stave = opby.getStatus().get(0).getStaval().toString();
                        if("1".equals(stave)){
                            List.clear();
                            approverList.clear();
                            approverList.add(APPROVER);
                            op = new OperateByEntity.DetailInfoEntity();
                            op.setUserid("op10001");
                            op.setUserName("审核人admin");
                            List.add(op);
                            for(int i=0;i<opby.getDetailInfo().size();i++){
                                String name = opby.getDetailInfo().get(i).getUserName().toString();
                                String userid = opby.getDetailInfo().get(i).getUserid().toString();
                                approverList.add(name);
                                op = new OperateByEntity.DetailInfoEntity();
                                op.setUserid(userid);
                                op.setUserName(name);
                                List.add(op);
                            }
                            approverAdapter.notifyDataSetChanged();
                        }else{
                            List.clear();
                            approverList.clear();
                            approverList.add("审核人admin");
                            op = new OperateByEntity.DetailInfoEntity();
                            op.setUserid("op10001");
                            op.setUserName("审核人admin");
                            List.add(op);
                            approverAdapter.notifyDataSetChanged();
                        }
                        sp_superior.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                OperateBy = List.get((int) id).getUserid();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(UndlingMonth_Details.this, "传阅上级审核人列表获取失败，请稍后重试~", Toast.LENGTH_SHORT).show();
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

    public void getReviewed(final Boolean isOpera) {
        if(!isOpera){
            OperateBy="";
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","workhbaudit");
                requestParams.addBodyParameter("AppID",hbid);
                requestParams.addBodyParameter("OperId", str);
                requestParams.addBodyParameter("ProcID", procid);
                requestParams.addBodyParameter("Flag","Y");
                requestParams.addBodyParameter("Operatedby",OperateBy);
                requestParams.addBodyParameter("OperateMessage",result);
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        pd.dismiss();
                        try {
                            JSONArray json = new JSONObject(result).getJSONArray("status");
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject j = (JSONObject) json.get(i);
                                stamess = j.getString("stamess");
                                staval = j.getString("staval");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if("1".equals(staval)){
                            Toast.makeText(UndlingMonth_Details.this,"审批成功",Toast.LENGTH_SHORT).show();
//                            Intent i= new Intent(UndlingMonth_Details.this,Lookreport_Activity.class);
//                            startActivity(i);
                            finish();
                        }else if("0".equals(staval)){
                            Toast.makeText(UndlingMonth_Details.this,R.string.ToastString, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
                        Toast.makeText(UndlingMonth_Details.this,R.string.ToastString,Toast.LENGTH_SHORT).show();
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
        pd = new ProgressDialog(UndlingMonth_Details.this);
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
    private void showAlertDialog1() {
        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入理由");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                progressD();
                result = builder.getMessage();//获取弹框输入内容
                getReviewed(ischeck);
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

}
