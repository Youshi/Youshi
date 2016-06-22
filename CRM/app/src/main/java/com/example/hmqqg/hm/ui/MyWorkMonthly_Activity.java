package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.ApprovalMonthly_Adapter;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.MonthlyEntity;
import com.example.hmqqg.hm.entity.MyMonentity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 工作汇报---我的月报详情
 * Created by Administrator on 2016/4/20.
 */
public class MyWorkMonthly_Activity extends BaseRequestActivity implements View.OnClickListener{
    private TextView title_top_bar;
    private ImageView back;

    private TextView workreport_time;//工作时间
    private TextView amount1;//陌电数量
    private TextView amount2;//回访客户数量
    private TextView amount3;//意向客户数量
    private TextView condition1;//回访客户情况
    private TextView condition2;//关单情况
    private TextView condition3;//下月工作安排
    private TextView plan;//下月工作安排
    private LinearLayout linear_comment;//评论的线性布局
    private ListView lit_comment;

    private ApprovalMonthly_Adapter appmonthAdapter;
    private List<MyMonentity.CommentsEntity> list = new ArrayList<>();
    private String hdid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workreport_daily);
        iniView();
        hdid = getIntent().getStringExtra("hdid");
        gethttp();//接口请求
    }

    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","getonemonth");
                requestParams.addBodyParameter("hbid",hdid);
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new MyMonentity()));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        MyMonentity month = (MyMonentity) object;
        if("1".equals(month.getStatus().get(0).getStaval())){
            workreport_time.setText(month.getDetailInfo().get(0).getStartDate()+"");
            amount1.setText(month.getDetailInfo().get(0).getAlienCall()+"");
            amount2.setText(month.getDetailInfo().get(0).getReturnCall()+"");
            amount3.setText(month.getDetailInfo().get(0).getIntentNum()+"");
            condition1.setText(month.getDetailInfo().get(0).getReturnCond()+"");
            condition2.setText(month.getDetailInfo().get(0).getDealCond()+"");
            condition3.setText(month.getDetailInfo().get(0).getNextWork()+"");
            title_top_bar.setText(month.getDetailInfo().get(0).getUSERNAME()+"的工作月报");
            list.addAll(month.getComments());
            appmonthAdapter.notifyDataSetChanged();
        }

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(MyWorkMonthly_Activity.this, "您的网络不稳定，请稍后重试！", Toast.LENGTH_SHORT).show();

    }
    private void iniView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        title_top_bar.setText("我的月报");

        workreport_time = (TextView) findViewById(R.id.workreport_time);
        amount1 = (TextView) findViewById(R.id.amount1);
        amount2 = (TextView) findViewById(R.id.amount2);
        amount3 = (TextView) findViewById(R.id.amount3);
        condition1 = (TextView) findViewById(R.id.condition1);
        condition2 = (TextView) findViewById(R.id.condition2);
        condition3 = (TextView) findViewById(R.id.condition3);
        plan = (TextView) findViewById(R.id.plan);
        lit_comment = (ListView) findViewById(R.id.lit_comment);//评论列表
        linear_comment = (LinearLayout) findViewById(R.id.linear_comment);//评论的线性布局
        plan.setText("下月工作安排");
        appmonthAdapter = new ApprovalMonthly_Adapter(list,MyWorkMonthly_Activity.this);
        lit_comment.setAdapter(appmonthAdapter);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
        }
    }
}
