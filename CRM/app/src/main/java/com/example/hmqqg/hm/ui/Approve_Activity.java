package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Marketing_Adapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 审批活动列表
 * Created by bona on 2016/3/3.
 */
public class Approve_Activity extends BaseRequestActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView back;
    private TextView title_top_bar;
    private PullToRefreshListView lstv;
    private List<String> list = new ArrayList<String>();
    private Marketing_Adapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_main);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("审批活动列表");
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        lstv.setOnItemClickListener(this);
//        madapter = new Marketing_Adapter(list, this);
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        list.add(0, "哈哈");

        madapter.notifyDataSetChanged();
        lstv.setAdapter(madapter);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.add(1, "哈哈");
                madapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.add(0, "哈哈");

                madapter.notifyDataSetChanged();
                stopRefresh();

            }
        });
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,Approve_Details.class);
        startActivity(intent);

    }
    private void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
    }
}
