package com.example.hmqqg.hm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.Report_Adapter;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.ui.Outdetails_Activity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 外出单
 * Created by Administrator on 2016/1/9.
 */
public class Out_Fragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView lstv;
    private List<String> list = new ArrayList<String>();
    private Report_Adapter reportAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meet_fragment,container,false);
        initView(view);
        return  view;
    }

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
//        reportAdapter = new Report_Adapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        list.add(0,"哈哈");
        reportAdapter.notifyDataSetChanged();
        lstv.setAdapter(reportAdapter);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//                list.add(1,"哈哈");
//                reportAdapter.notifyDataSetChanged();
//                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.add(0,"哈哈");
//
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
        Intent intent = new Intent(getActivity(), Outdetails_Activity.class);
        startActivity(intent);
    }


}
