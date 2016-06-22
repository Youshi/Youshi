package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.adapter.Applyforadapter;
import com.example.hmqqg.hm.adapter.Approvaladapter;

/**
 * Created by Administrator on 2016/2/21.
 * 申请
 */
public class ApplyforActivity  extends BaseActivity implements View.OnClickListener{
    private ImageView back;//返回

    private ImageView add;//添加
    private TextView title_top_bar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);
        initView();
    }

    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        add = (ImageView) findViewById(R.id.refresh);
        back = (ImageView) findViewById(R.id.back);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new Applyforadapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        title_top_bar.setText("申请");
        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back://返回
                onBackPressed();
                break;
            case R.id.refresh://添加
                Intent intent = new Intent(ApplyforActivity.this,LeaveActivity.class);
                startActivity(intent);
                break;
        }
    }
}
