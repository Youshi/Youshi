package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Custom_lookAdapter;
import com.example.hmqqg.hm.adapter.Marketing_Adapter;
import com.example.hmqqg.hm.entity.CustomerEntity;
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
 * 市场活动
 * Created by bona on 2016/3/3.
 */
public class Marketing_Activity extends BaseRequestActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView back;
    private TextView title_top_bar;
    private TextView refresh;//添加活动
    private PullToRefreshListView lstv;
    private List<String> list = new ArrayList<String>();
    private Marketing_Adapter madapter;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketing_main);
        initView();
        gethttp(REFRESH);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh = (TextView) findViewById(R.id.refresh);
        title_top_bar.setText("市场活动");
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
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
                madapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (list.size() % pageSize !=0) {// 列表数据未满最大页行数
                    stopRefresh();
                    Toast.makeText(Marketing_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPage = startPage + 1;
                try {
                    gethttp(APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                madapter.notifyDataSetChanged();
                stopRefresh();
                // 默认显示，作为外层tab的首页
                try {
                    gethttp(REFRESH);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

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
    private void gethttp(final int what){
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_addCustomer));
                params.addBodyParameter("action","actlist");
                params.addBodyParameter("userid","wq");
                params.addBodyParameter("PageNum",String.valueOf(startPage));
                params.addBodyParameter("State","N");
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new CustomerEntity()));
            }
        }).start();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.refresh:
//                Intent intent = new Intent(this,Approve_Activity.class);
                Intent intent = new Intent(this,Add_Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,Marketing_Details.class);
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
