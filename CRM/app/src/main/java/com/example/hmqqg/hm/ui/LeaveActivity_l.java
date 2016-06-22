package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;

/**
 * Created by Administrator on 2016/4/7.
 */
public class LeaveActivity_l extends BaseActivity implements View.OnClickListener{
    private ImageView back;//返回
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_details);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
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
