package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 添加备忘录信息
 * Created by Administrator on 2016/1/22.
 */
public class AddMessage_Activity extends BaseRequestActivity implements View.OnClickListener{
    private TextView title_top_bar;
    private ImageView back;//返回
    private TextView refresh;//清空
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmessage);
        initView();
    }

    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        title_top_bar.setText("备忘");
        back.setOnClickListener(this);
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
