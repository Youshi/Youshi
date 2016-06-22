package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import com.example.hmqqg.hm.adapter.Applyforadapter;
import com.example.hmqqg.hm.adapter.MarketEntity;
import com.example.hmqqg.hm.adapter.Marketing_Adapter;
import com.example.hmqqg.hm.adapter.MasrketAdapter;
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
 * 市场活动界面（新的）
 * Created by Administrator on 2016/3/8.
 */
public class MarketPriceActivity extends BaseRequestActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView back;//返回
    private ImageView refresh;//添加
    private PullToRefreshListView lstv;
    private List<MarketEntity.DetailInfoEntity> list = new ArrayList<MarketEntity.DetailInfoEntity>();
    private TextView title_top_bar;//标题
    private Marketing_Adapter madapter;
    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    private int actid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seas_customers);
        iniView();//初始化控件
        gethttp(REFRESH);
    }

    private void iniView() {
        back = (ImageView) findViewById(R.id.back);
        refresh = (ImageView) findViewById(R.id.refresh);

        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh.setVisibility(View.GONE);
//        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setAdapter(new MasrketAdapter(getSupportFragmentManager()));
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setupWithViewPager(viewPager);
        title_top_bar.setText("市场活动");
        back.setOnClickListener(this);
//        add.setOnClickListener(this);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        madapter = new Marketing_Adapter(list,MarketPriceActivity.this);
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        lstv.setAdapter(madapter);
        madapter.notifyDataSetChanged();
        lstv.setOnItemClickListener(this);
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
                    Toast.makeText(MarketPriceActivity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPage = startPage + 1;
                try {
//                    gethttp(APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                madapter.notifyDataSetChanged();
                stopRefresh();
                // 默认显示，作为外层tab的首页
                try {
//                    gethttp(REFRESH);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
        });
    }
    private void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
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
                params.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("PageNum",String.valueOf(startPage));
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new MarketEntity()));
            }
        }).start();

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        MarketEntity me = (MarketEntity)object;
        list.addAll(me.getDetailInfo());
        madapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(MarketPriceActivity.this,getResources().getString(R.string.ToastString),Toast.LENGTH_SHORT);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back:
                onBackPressed();
                break;
//            case R.id.refresh:
//                Intent intent = new Intent(this,Add_Activity.class);
//                startActivity(intent);
//                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MarketPriceActivity.this,Marketing_Details.class);
        String titile = list.get((int)id).getActTitle();
        String ActDate = list.get((int)id).getActDate();
        String ActAddress = list.get((int)id).getActAddr();
        String ActTypeName = list.get((int)id).getActTypeName();
        String ActPurpose = list.get((int)id).getActPurpose();


        intent.putExtra("titile", titile);
        intent.putExtra("ActDate", ActDate);
        intent.putExtra("ActAddress", ActAddress);
        intent.putExtra("ActTypeName", ActTypeName);
        intent.putExtra("ActPurpose", ActPurpose);
        startActivity(intent);

    }
}
