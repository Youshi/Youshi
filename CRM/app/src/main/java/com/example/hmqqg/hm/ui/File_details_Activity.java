package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.File_Adapter;
import com.example.hmqqg.hm.adapter.Filet_Adapter;
import com.example.hmqqg.hm.entity.FileEntity;
import com.example.hmqqg.hm.entity.FiletEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 企业文件详情
 * Created by Administrator on 2016/1/7.
 */
public class File_details_Activity extends BaseRequestActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView back;//返回
    private TextView title_top_bar;
    private TextView refresh;//下载
    private PullToRefreshListView lstv;
    private List<FiletEntity.DetailInfoEntity> mAppList = new ArrayList<>();
    private Filet_Adapter filetAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_main);
        initView();
        gethttp();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        FiletEntity filet = (FiletEntity) object;
        mAppList.addAll(filet.getDetailInfo());
        filetAdapter.notifyDataSetChanged();

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
//        refresh = (TextView) findViewById(R.id.refresh);
        title_top_bar.setText("企业文件");
//        refresh.setText("下载");
        back.setOnClickListener(this);
        lstv.setOnItemClickListener(this);
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        filetAdapter = new Filet_Adapter(mAppList,this);
        lstv.setAdapter(filetAdapter);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                filetAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                filetAdapter.notifyDataSetChanged();
                stopRefresh();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back://返回
                onBackPressed();
                break;
        }
    }
    private void gethttp() {
        Intent intent = getIntent();
        final String fid = intent.getStringExtra("floderid");
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action","filelist");
//                params.addBodyParameter("PageNum",String.valueOf(startPage));
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("fid",fid);
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new FiletEntity()));
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


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
