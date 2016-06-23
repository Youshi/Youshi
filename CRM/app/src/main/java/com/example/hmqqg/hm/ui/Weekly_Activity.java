package com.example.hmqqg.hm.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.AddjobEntity;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 编写周报工作录入
 * Created by Administrator on 2016/1/19.
 */
public class Weekly_Activity extends BaseRequestActivity implements View.OnClickListener{
    private TextView title_top_bar;
    private ImageView back;//返回
    private TextView refresh;//保存按钮
    private final int DATE_DIALOG = 1;

    private TextView time;//时间
    private EditText worktime;//工作时间
    private EditText amount1;//陌电数量
    private EditText amount2;//回访客户数量
    private EditText amount3;//意向客户数量
    private EditText condition1;//回访客户情况
    private EditText condition2;//关单情况
    private EditText condition3;//下周工作安排
    private Spinner spi_approver;//上级审核人Spinner

    private String Stime;
    private String Sworktime;
    private String Samount1;
    private String Samount2;
    private String Samount3;
    private String Scondition1;
    private String Scondition2;
    private String Scondition3;
    private String Strapprover;
    private String OperateBy;
    private ArrayAdapter<String> approverAdapter;//系统默认adapter
    private List<String> mAppList = new ArrayList<String>();;//审核人的列表List
    private List<OperateByEntity.DetailInfoEntity> approverList = new ArrayList<OperateByEntity.DetailInfoEntity>();
    OperateByEntity.DetailInfoEntity ob;
    private int year;
    private int month;
    private int week;
    private int date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compile_job1);
        View.OnClickListener dateBtnListener =
                new BtnOnClickListener(DATE_DIALOG);
        time = (TextView) findViewById(R.id.time);
        time.setOnClickListener(dateBtnListener);
        initView();
        gethttpspi();//从服务器获取审核人
        //用来获取日期和时间的
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);
        time.setText( year + "-" +(month+1) + "-" + date );
    }



    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh = (TextView) findViewById(R.id.refresh);
        back = (ImageView) findViewById(R.id.back);
        worktime = (EditText) findViewById(R.id.worktime);
        amount1 = (EditText) findViewById(R.id.amount1);
        amount2 = (EditText) findViewById(R.id.amount2);
        amount3 = (EditText) findViewById(R.id.amount3);
        condition1 = (EditText) findViewById(R.id.condition1);
        condition2 = (EditText) findViewById(R.id.condition2);
        condition3 = (EditText) findViewById(R.id.condition3);
        spi_approver = (Spinner) findViewById(R.id.spi_approver);
        approverList = new ArrayList<>();

        spi_approver.setAdapter(approverAdapter);
        title_top_bar.setText("工作记录录入");
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.refresh:

                gethttp();
                break;
        }
    }
    private void progressD() {
        pd = new ProgressDialog(Weekly_Activity.this);
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
    private void gethttp() {
        Stime = time.getText().toString();
        Samount1 = amount1.getText().toString();
        Samount2 = amount2.getText().toString();
        Samount3 = amount3.getText().toString();
        Scondition1 = condition1.getText().toString();
        Scondition2 = condition2.getText().toString();
        Scondition3 = condition3.getText().toString();
        Strapprover = spi_approver.getSelectedItem().toString();//获取Spinner的值
        if("".equals(Scondition1)||Scondition1==null){
            Toast.makeText(Weekly_Activity.this, "回访客户情况不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if((Scondition1.length())>50){
            Toast.makeText(Weekly_Activity.this, "回访客户情况超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if("".equals(Scondition2)||Scondition2==null){
            Toast.makeText(Weekly_Activity.this, "关单情况不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if((Scondition2.length())>50){
            Toast.makeText(Weekly_Activity.this, "关单情况超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if("".equals(Scondition3)||Scondition3==null){
            Toast.makeText(Weekly_Activity.this, "次日工作安排不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if((Scondition3.length())>50){
            Toast.makeText(Weekly_Activity.this, "次日工作安排超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if("请选择审核人".equals(Strapprover)){
            Toast.makeText(Weekly_Activity.this, "请选择审核人", Toast.LENGTH_SHORT).show();
            return;
        }
        progressD();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_remindset));
                requestParams.addBodyParameter("action","weeklogadd");
                requestParams.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("startDate",Stime);
                requestParams.addBodyParameter("endDate",Stime);
                requestParams.addBodyParameter("AlienCall",Samount1);//陌电数量
                requestParams.addBodyParameter("ReturnCall",Samount2);//回访数量
                requestParams.addBodyParameter("IntentNum",Samount3);//意向客户
                requestParams.addBodyParameter("ReturnCond",Scondition1);//回访情况
                requestParams.addBodyParameter("DealCond",Scondition2);//关单情况
                requestParams.addBodyParameter("invitnum","1");//邀请数量
                requestParams.addBodyParameter("operateby",OperateBy);//审核人
                requestParams.addBodyParameter("NextWork",Scondition3);//次日工作计划
                requestParams.addBodyParameter("userId",MyApplication.getInstance().getUserInfo().getUserId());
//                x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new AddjobEntity()));
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        pd.dismiss();
                        Gson gson = new Gson();
                        AddjobEntity addentity =gson.fromJson(result,AddjobEntity.class);
                        String str = addentity.getStatus().get(0).getStaval();
                        if("1".equals(str)){
                            Toast.makeText(Weekly_Activity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Weekly_Activity.this,Joblogging_Activity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Weekly_Activity.this,"保存失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
                        Toast.makeText(Weekly_Activity.this,R.string.ToastString,Toast.LENGTH_SHORT).show();
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
//        pd.dismiss();
//        AddjobEntity l = (AddjobEntity) object;
//        String str = l.getStatus().get(0).getStaval();
//        if(str.equals("1")){
//            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this,Joblogging_Activity.class);
//            startActivity(intent);
//            finish();
//        }else{
//            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
//        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
//        pd.dismiss();
//        Toast.makeText(this,R.string.ToastString,Toast.LENGTH_SHORT).show();
    }
    private void gethttpspi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","getauditor");
                requestParams.addBodyParameter("operid",MyApplication.getInstance().getUserInfo().getUserId());
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
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
                                approverList.add(ob);
                                ArrayAdapter adapter = new ArrayAdapter(Weekly_Activity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spi_approver.setAdapter(adapter);
                            } else {
                                JSONArray json = new JSONObject(result).getJSONArray("DetailInfo");
                                mAppList.add("请选择审核人");
                                ob = new OperateByEntity.DetailInfoEntity();
                                ob.setUserid("op10001");
                                ob.setUserName("审核人");
                                approverList.add(ob);
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject j = (JSONObject) json.get(i);
                                    String userid = j.getString("userid");
                                    String username = j.getString("UserName");
                                    ob = new OperateByEntity.DetailInfoEntity();
                                    ob.setUserid(userid);
                                    ob.setUserName(username);
                                    approverList.add(ob);
                                    mAppList.add(username);
                                }

                                ArrayAdapter adapter = new ArrayAdapter(Weekly_Activity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spi_approver.setAdapter(adapter);
                            }
                            spi_approver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    OperateBy = approverList.get((int) id).getUserid();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(Weekly_Activity.this, R.string.ToastString, Toast.LENGTH_SHORT).show();
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
    /*
	     * 成员内部类,此处为提高可重用性，也可以换成匿名内部类
	     */
    private class BtnOnClickListener implements View.OnClickListener {

        private int dialogId = 0;   //默认为0则不显示对话框

        public BtnOnClickListener(int dialogId) {
            this.dialogId = dialogId;
        }
        @Override
        public void onClick(View view) {
            showDialog(dialogId);
        }

    }
}
