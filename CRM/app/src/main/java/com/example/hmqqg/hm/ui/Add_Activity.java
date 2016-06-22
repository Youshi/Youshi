package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 添加市场活动页面
 * Created by bona on 2016/3/8.
 */
public class Add_Activity extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;
    private TextView title_top_bar;
    private TextView refresh;//保存
    private EditText theme;//活动主题
    private EditText time;//活动时间
    private EditText address;//活动地点
    private EditText money;//活动经费
    private EditText plan;//活动计划
    private Spinner type;//活动类型
    private EditText goal;//负责人编号
    private String theme1;
    private String address1;
    private String type1;
    private String time1;
    private String money1;
    private String plan1;
    private String actType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        initView();
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh = (TextView) findViewById(R.id.refresh);
        theme = (EditText) findViewById(R.id.theme);
        time = (EditText) findViewById(R.id.time);
        address = (EditText) findViewById(R.id.address);
        money = (EditText) findViewById(R.id.money);
        plan = (EditText) findViewById(R.id.plan);
        type = (Spinner) findViewById(R.id.type);
        title_top_bar.setText("添加市场活动");
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        LoginEntity l = (LoginEntity) object;
        String str = l.getStatus().get(0).getStaval();
        if(str.equals("1")){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Add_Activity.this,MarketPriceActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }
    private void gethttp(){
        theme1 = theme.getText().toString();
        time1 = time.getText().toString();
        address1 = address.getText().toString();
        money1 = money.getText().toString();
        plan1 = plan.getText().toString();
        if (type.getSelectedItem().toString().equals("促销活动")){
            actType = "HDLX01";
        }else if (type.getSelectedItem().toString().equals("网络搜索引擎")){
            actType = "HDLX02";
        }else if (type.getSelectedItem().toString().equals("广告/邮寄/信函/简讯")){
            actType = "HDLX03";
        }else if (type.getSelectedItem().toString().equals("讨论会/活动/展览")){
            actType = "HDLX04";
        }else if (type.getSelectedItem().toString().equals("宣传/录音/录像/光盘")){
            actType = "HDLX05";
        }else if (type.getSelectedItem().toString().equals("技术培训")){
            actType = "HDLX06";
        }else if (type.getSelectedItem().toString().equals("销售训练")){
            actType = "HDLX07";
        }else if (type.getSelectedItem().toString().equals("其他活动")){
            actType = "HDLX08";
        }
        if(theme1==null||theme1.equals("")){
            Toast.makeText(Add_Activity.this,"请输入活动主题", Toast.LENGTH_SHORT).show();
            return;
        }
        if(address1==null||address1.equals("")){
            Toast.makeText(Add_Activity.this,"请输入活动地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if(time1==null||time1.equals("")){
            Toast.makeText(Add_Activity.this,"请输入活动时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if(money1==null||money1.equals("")){
            Toast.makeText(Add_Activity.this,"请输入活动经费", Toast.LENGTH_SHORT).show();
            return;
        }
        if(plan1==null||plan1.equals("")){
            Toast.makeText(Add_Activity.this,"请输入活动计划", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_addCustomer));
                params.addBodyParameter("action","actadd");
                params.addBodyParameter("actTitle",theme1);
                params.addBodyParameter("actType",actType);
                params.addBodyParameter("actAddress",address1);
                params.addBodyParameter("actDate",time1);
                params.addBodyParameter("charge",money1);
                params.addBodyParameter("actPlan",plan1);
                params.addBodyParameter("operId",MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new LoginEntity()));
            }
        }).start();
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
}
