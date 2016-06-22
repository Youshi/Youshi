package com.example.hmqqg.hm.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.entity.MarkerDEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.example.hmqqg.hm.view.CustomDialog;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 活动详情
 * Created by bona on 2016/3/3.
 */
public class Marketing_Details extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;
    private TextView title_top_bar;
    private TextView theme;//主题
    private TextView time;//时间
    private TextView address;//地点
    private TextView type;//类型
    private TextView goal;//目的

    EditText edit;
    private EditText message;
//    private Button mBt_agree;//同意按钮
//    private Button mBt_disagree;//不同意按钮
    private int a =1;
    private String result;
    private String actid;
    CustomDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketing_details);
        initView();
        Intent intent = getIntent();
        String titile = intent.getStringExtra("titile");
        String ActDate = intent.getStringExtra("ActDate");
        String ActAddress = intent.getStringExtra("ActAddress");
        String ActTypeName = intent.getStringExtra("ActTypeName");
        String ActPurpose = intent.getStringExtra("ActPurpose");
        theme.setText(titile);
        time.setText(ActDate);
        address.setText(ActAddress);
        type.setText(ActTypeName+"");
        goal.setText(ActPurpose);


    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        theme = (TextView) findViewById(R.id.theme);
        time = (TextView) findViewById(R.id.time);
        address = (TextView) findViewById(R.id.address);
        type = (TextView) findViewById(R.id.type);
        goal = (TextView) findViewById(R.id.goal);
        message = (EditText) findViewById(R.id.message);

        title_top_bar.setText("活动详情");


        back.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
//        MarkerDEntity le = (MarkerDEntity) object ;
//        if("1".equals(le.getStatus().get(0).getStaval())){
//            Toast.makeText(this,"审批成功",Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(Marketing_Details.this,MarketPriceActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        else{
//            Toast.makeText(this,"网络连接不稳定，请重试",Toast.LENGTH_LONG).show();
//        }

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
//            case R.id.bt_agree:
//                showAlertDialog1();
//
////                Toast.makeText(this,"同意",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.bt_disagree:
//                showAlertDialog2();
////                Toast.makeText(this,"不同意",Toast.LENGTH_SHORT).show();
//    break;
}
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
//                gethttpagree();
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
//                gethttpdisagree();
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
//    public void gethttpdisagree() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RequestParams params = new RequestParams(getResources().getString(R.string.http_addCustomer));
//                params.addBodyParameter("action","actapply");
//                params.addBodyParameter("actid",actid);
//                if(result!=null||!result.equals("")){
//                    params.addBodyParameter("appmess",result);
//                }
//                params.addBodyParameter("State","P");
//                params.addBodyParameter("operId",MyApplication.getInstance().getUserInfo().getUserId());
//                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new MarkerDEntity()));
//            }
//        }).start();
//    }
//
//    public void gethttpagree() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RequestParams params = new RequestParams(getResources().getString(R.string.http_addCustomer));
//                params.addBodyParameter("action","actapply");
//                params.addBodyParameter("actid",actid);
//                if(result!=null||!result.equals("")){
//                    params.addBodyParameter("appmess",result);
//                }
//                params.addBodyParameter("State","R");
//                params.addBodyParameter("operId",MyApplication.getInstance().getUserInfo().getUserId());
//                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new MarkerDEntity()));
//            }
//        }).start();
//    }
}
