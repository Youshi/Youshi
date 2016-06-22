package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 审批活动详情
 * Created by bona on 2016/3/3.
 */
public class Approve_Details extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;
    private TextView title_top_bar;
    private TextView theme;//主题
    private TextView time;//时间
    private TextView address;//地点
    private TextView type;//类型
    private TextView goal;//目的
    private Button bt_agree;//同意
    private Button bt_disagree;//不同意
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_details);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        theme = (TextView) findViewById(R.id.theme);
        time = (TextView) findViewById(R.id.time);
        address = (TextView) findViewById(R.id.address);
        type = (TextView) findViewById(R.id.type);
        goal = (TextView) findViewById(R.id.goal);
        bt_agree = (Button) findViewById(R.id.bt_agree);
        bt_disagree = (Button) findViewById(R.id.bt_disagree);
        back.setOnClickListener(this);
        title_top_bar.setText("活动详情");
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {

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
        }
    }
}
