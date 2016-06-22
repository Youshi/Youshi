package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.MarketDetailsAdapter;
import com.example.hmqqg.hm.adapter.MarketEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 活动详情
 * Created by bona on 2016/3/3.
 */
public class Marketing_Details2 extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;
    private TextView title_top_bar;
    private TextView theme;//主题
    private TextView time;//时间
    private TextView address;//地点
    private TextView type;//类型
    private TextView goal;//目的
    private TextView jieguo;//结果

    private String userid;
    private String actid;

    String mtitle;//活动主题
    String mtime;//时间
    String maddress;//地址
    String mtypename ;//活动类型
    //        String mtypename ;//活动目的
    String mstate;//审核结果
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketing_details2);
        initView();
        gethttp();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        theme = (TextView) findViewById(R.id.theme);
        time = (TextView) findViewById(R.id.time);
        address = (TextView) findViewById(R.id.address);
        type = (TextView) findViewById(R.id.type);
        goal = (TextView) findViewById(R.id.goal);
        jieguo = (TextView) findViewById(R.id.jieguo);
        title_top_bar.setText("活动详情");

        back.setOnClickListener(this);
    }
    private void gethttp(){
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        actid = intent.getStringExtra("actid");
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_addCustomer));
                params.addBodyParameter("action","getoneact");
                params.addBodyParameter("actid",actid);
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new MarketDetailsAdapter()));
            }
        }).start();

    }


    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        MarketDetailsAdapter mda = (MarketDetailsAdapter) object;
        String a  = mda.getStatus().get(0).getStamess();
        Toast.makeText(this,a,Toast.LENGTH_SHORT).show();
        mtypename = mda.getDetailInfo().getActTypeName().toString();//活动类型
        mtitle = mda.getDetailInfo().getActTitle().toString();//活动主题
        mtime = mda.getDetailInfo().getActDate().toString();//时间
        maddress = mda.getDetailInfo().getActAddress().toString();//地址
//        String mtypename = mda.getDetailInfo().().toString();//活动目的
        mstate = mda.getDetailInfo().getState().toString();//审核结果
        success();


    }

    private void success() {
        theme.setText(mtitle);
        time.setText(mtime);
        address.setText(maddress);
        type.setText(mtypename);
//        goal.setText(活动目的);
        if(mstate.equals("N")){
            jieguo.setTextColor(getResources().getColor(R.color.orange));
            jieguo.setText("未审核");
        }else if(mstate.equals("P")){
            jieguo.setTextColor(getResources().getColor(R.color.green));
            jieguo.setText("成功");
        }else if(mstate.equals("R")){
            jieguo.setTextColor(getResources().getColor(R.color.red));
            jieguo.setText("驳回");
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(this,"服务器出错了",Toast.LENGTH_SHORT).show();
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
