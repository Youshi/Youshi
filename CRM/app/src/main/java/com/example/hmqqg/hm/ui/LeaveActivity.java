package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;

/**
 * 添加申请单
 * Created by Android-Wq on 2016/1/21.
 */
public class LeaveActivity extends BaseActivity implements View.OnClickListener{
    private ImageView back;//返回
    private TextView title_top_bar;
    private Spinner v_spinner;//下拉
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_main);
        initView();

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        v_spinner = (Spinner) findViewById(R.id.v_spinner);
        title_top_bar.setText("新建申请");
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }
}
