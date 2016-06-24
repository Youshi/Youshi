package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;

/**
 * 版本信息
 * Created by bona on 2016/6/24.
 */
public class VersionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title_top_bar;
    private ImageView back;//返回
    private TextView number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_main);
        initView();
    }

    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        number = (TextView) findViewById(R.id.number);
        back.setOnClickListener(this);
        title_top_bar.setText("版本信息");

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
