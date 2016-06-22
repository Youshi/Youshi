package com.example.hmqqg.hm.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.chat.ChatActivity;
import com.example.hmqqg.hm.adapter.Group_Adapter;
import com.example.hmqqg.hm.common.Constant;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 群
 * Created by Android-Wq on 2015/12/30.
 */
public class GroupActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private TextView title_top_bar;
    private ImageView back;//返回
    private ImageView change;//添加群组
    private PullToRefreshListView lstv;
    private Group_Adapter groupAdapter;
    private List<String> list = new ArrayList<String>(); //设置标记列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_main);
        intaview();
        title_top_bar.setText("群");
        back.setOnClickListener(this);
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        list.add(0,"哈哈");
        groupAdapter.notifyDataSetChanged();
        lstv.setAdapter(groupAdapter);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.add(1,"哈哈");
                groupAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.add(0,"哈哈");
                groupAdapter.notifyDataSetChanged();
                stopRefresh();

            }
        });
    }

    private void intaview() {
        groupAdapter = new Group_Adapter(list,this);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        change = (ImageView) findViewById(R.id.change);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();//返回
                break;
            case R.id.change:
                Intent intent = new Intent(this,GroupsActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(Constant.EXTRA_CHAT_TYPE,Constant.CHATTYPE_GROUP);
        intent.putExtra(Constant.EXTRA_USER_ID,"151545008204284456");
        startActivity(intent);
    }
    public void stopRefresh(){
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        },1000);
    }
}
