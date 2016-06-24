package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.Report_Adapter;
import com.example.hmqqg.hm.entity.DailyEntity;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.fragment.base.BaseRequestFragment;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 日报
 * Created by Administrator on 2016/1/5.
 */
public class Daily_Fragment1 extends BaseRequestFragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView lstv;
    private List<DailyEntity.DetailInfoEntity> list = new ArrayList<DailyEntity.DetailInfoEntity>();
    private Report_Adapter reportAdapter;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;

    private int year;
    private int month;
    private int date;
    private String time;
    private boolean isShow = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_main, container, false);
        initView(view);
        //用来获取日期和时间的
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);
        time= year + "-" +(month+1) + "-" + date ;
        list.clear();
//        gethttp(REFRESH);
        return view;
    }

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        reportAdapter = new Report_Adapter(list, getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        reportAdapter.notifyDataSetChanged();
        lstv.setAdapter(reportAdapter);

        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                gethttp(REFRESH);
                reportAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                    stopRefresh();
                    Toast.makeText(getActivity(), "无更新数据~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPage = startPage + 1;
                try {
                    gethttp(APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                reportAdapter.notifyDataSetChanged();
                stopRefresh();
                // 默认显示，作为外层tab的首页
//                try {
//                    gethttp(REFRESH);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String WLogId = list.get((int) id).getWLogId();
        String PROCID = String.valueOf(list.get((int)id).getPROCID());
        Intent intent = new Intent(getActivity(), MyWorkReport_Activity.class);
        intent.putExtra("WLogId", WLogId);
        intent.putExtra("PROCID",PROCID);
        intent.putExtra("number", "5");
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {

        DailyEntity de = (DailyEntity) object;
        if (de.getStatus().get(0).getStaval().equals("1")) {

            list.addAll(de.getDetailInfo());
            reportAdapter.notifyDataSetChanged();
        } else {
//            Toast.makeText(getActivity(), "您的网络不稳定，请重试", Toast.LENGTH_SHORT).show();
        }


    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    public void gethttp(final int what) {
        // 这里从服务器获取数据
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getlowerworklog");
                requestParams.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("PageNum", String.valueOf(startPage));
                requestParams.addBodyParameter("LogDate", time);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new DailyEntity()));
            }
        }).start();
    }
    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {//ViewPager中设置listener,当滑动到该页面时调用onResume方法
            @Override
            public void run() {
                lstv.setRefreshing(true);
//                gethttp(REFRESH);
            }
        }, 500);
    }

}