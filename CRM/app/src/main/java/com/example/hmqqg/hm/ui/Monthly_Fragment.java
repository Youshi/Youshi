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
import com.example.hmqqg.hm.adapter.MonthlyAdapter;
import com.example.hmqqg.hm.entity.MonthlyEntity;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * （我的工作汇报）月报列表
 * Created by Administrator on 2016/1/5.
 */
public class Monthly_Fragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView lstv;
    private List<MonthlyEntity.DetailInfoEntity> list = new ArrayList<>();
    private MonthlyAdapter reportAdapter;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.monthly_main,container,false);
        initView(view);
        list.clear();
        getHttp(REFRESH);
        return  view;
    }
    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        reportAdapter = new MonthlyAdapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        list.clear();
        lstv.setAdapter(reportAdapter);
        reportAdapter.notifyDataSetChanged();
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                getHttp(REFRESH);
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
                    getHttp(APPEND);
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
    private void getHttp(final int what) {
        // 这里从服务器获取数据
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String operid = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","getmonthlist");
                requestParams.addBodyParameter("operId", operid);
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        MonthlyEntity month = gson.fromJson(result,MonthlyEntity.class);
                        list.addAll(month.getDetailInfo());
                        reportAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        }).start();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String hdid = list.get((int) id).getHbId();
        String OperateMessage = list.get((int) id).getOperateMessage();
        Intent intent = new Intent(getActivity(),MyWorkMonthly_Activity.class);
        intent.putExtra("hdid", hdid);
        intent.putExtra("OperateMessage", OperateMessage);
        startActivity(intent);
    }

}
