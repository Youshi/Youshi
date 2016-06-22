package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.adapter.Followadapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户跟进记录
 * Created by Administrator on 2016/1/8.
 */
public class Customer_follow extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private TextView title_top_bar;
    private ImageView back;
    private ImageView refresh;
    private PullToRefreshListView lstv;
    private Followadapter followadapter;
    private List<String> list = new ArrayList<String>(); //设置标记列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followup_main);
        intaview();
        title_top_bar.setText("客户跟进记录");
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        list.add(0, "哈哈");
        followadapter.notifyDataSetChanged();
        lstv.setAdapter(followadapter);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.add(1, "哈哈");
                followadapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.add(0, "哈哈");
                followadapter.notifyDataSetChanged();
                stopRefresh();

            }
        });
    }

    private void intaview() {
        followadapter = new Followadapter(list, this);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        refresh = (ImageView) findViewById(R.id.refresh);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back://返回
                onBackPressed();
                break;
            case R.id.refresh://添加跟进记录
                Intent intent = new Intent(this,Add_recordsActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
    }
}