package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 添加客户跟进记录
 * Created by bona on 2016/3/2.
 */
public class Add_recordsActivity extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;
    private TextView title_top_bar;
    private TextView refresh;//保存
    private EditText name;
    private EditText phone;
    private EditText origne;
    private EditText intention;
    private EditText time1;
    private EditText time2;
    private EditText money;
    private EditText product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_records);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh = (TextView) findViewById(R.id.refresh);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        origne = (EditText) findViewById(R.id.origne);
        intention = (EditText) findViewById(R.id.intention);
        time1 = (EditText) findViewById(R.id.time1);
        time2 = (EditText) findViewById(R.id.time2);
        money = (EditText) findViewById(R.id.money);
        product = (EditText) findViewById(R.id.product);
        back.setOnClickListener(this);
        title_top_bar.setText("客户跟进记录");
        refresh.setText("保存");
        refresh.setOnClickListener(this);
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
            case R.id.refresh:
                onBackPressed();
                break;
        }
    }
}
