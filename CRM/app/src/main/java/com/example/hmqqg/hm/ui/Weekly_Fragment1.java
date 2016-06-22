package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.UndlingWeekAdapter;
import com.example.hmqqg.hm.entity.UndlingWeekEntity;
import com.example.hmqqg.hm.fragment.base.BaseRequestFragment;
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
 * 周报
 * Created by Administrator on 2016/1/5.
 */
public class Weekly_Fragment1 extends BaseRequestFragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView lstv;
    private List<UndlingWeekEntity.DetailInfoEntity> list = new ArrayList<>();
    private UndlingWeekAdapter reportAdapter;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    private boolean isShow = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_main,container,false);
        initView(view);
        gethttp(REFRESH);
        return  view;
    }

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        reportAdapter = new UndlingWeekAdapter(list,getActivity());
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
        String HbId = list.get((int) id).getHbId();
        String procid = String.valueOf(list.get((int) id).getPROCID());
        Intent intent = new Intent(getActivity(),MyWorkReport_Activity1.class);
        intent.putExtra("hdid", HbId);
        intent.putExtra("procid",procid);
        intent.putExtra("number","5");
        startActivity(intent);
    }
    private void gethttp(final int what) {
        list.clear();
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","getlowerweeklog");
                requestParams.addBodyParameter("PageNum",String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new UndlingWeekEntity()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        UndlingWeekEntity we = (UndlingWeekEntity) object;
        if(we.getStatus().get(0).getStaval().equals("1")){
            list.addAll(we.getDetailInfo());
            reportAdapter.notifyDataSetChanged();
        }else{
//            Toast.makeText(getActivity(),"您的网络不稳定，请重试",Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isShow){
            list.clear();
            gethttp(REFRESH);
        }
        isShow=false;
    }
}
