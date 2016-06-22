package com.example.hmqqg.hm.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.MarketEntity;
import com.example.hmqqg.hm.adapter.Notice_Adapter;
import com.example.hmqqg.hm.entity.NoticeEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 公告浏览
 * Created by Administrator on 2016/1/2.
 */
public class Notice_Activity extends BaseRequestActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView back;//返回
    private PullToRefreshListView lstv;
    private List<NoticeEntity.DetailInfoEntity> list = new ArrayList<>();
    private Notice_Adapter noticeAdapter;
    private TextView title_top_bar;
    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notices_main);
        initView();
        gethttp();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        NoticeEntity me = (NoticeEntity)object;
        list.addAll(me.getDetailInfo());
        noticeAdapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
    Toast.makeText(Notice_Activity.this,getResources().getString(R.string.ToastString),Toast.LENGTH_SHORT);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        title_top_bar.setText("公告");
        noticeAdapter = new Notice_Adapter(list,this);
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        noticeAdapter.notifyDataSetChanged();
        lstv.setAdapter(noticeAdapter);
        back.setOnClickListener(this);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (list.size() % pageSize !=0) {// 列表数据未满最大页行数
                    stopRefresh();
                    Toast.makeText(Notice_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPage = startPage + 1;
                try {
                    gethttp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                noticeAdapter.notifyDataSetChanged();
                stopRefresh();
                // 默认显示，作为外层tab的首页
                try {
                    gethttp();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:

                finish();
                break;

        }
    }

    public void A() {
        Toast.makeText(this,"11",Toast.LENGTH_SHORT).show();
        Notice_Activity.this.openOptionsMenu();
    }

    private void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
    }

    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action","getnewslist");
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
//                params.addBodyParameter("newsType","2");
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new NoticeEntity()));
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Notice_details_Activity.class);
        String newsid = list.get((int) id).getNewsId();
        intent.putExtra("newsid",newsid);
        startActivity(intent);
    }
}
