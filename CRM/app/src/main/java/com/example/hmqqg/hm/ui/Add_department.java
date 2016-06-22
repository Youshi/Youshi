package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;

/**
 * 添加部门
 * Created by Administrator on 2016/1/2.
 */
public class Add_department extends BaseActivity implements View.OnClickListener{
    private ImageView back;//返回
    private TextView refresh;//确定
    private TextView title_top_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_department);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        refresh = (TextView) findViewById(R.id.refresh);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("添加部门");
        refresh.setText("确定");
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.refresh://完成
                finish();
                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
