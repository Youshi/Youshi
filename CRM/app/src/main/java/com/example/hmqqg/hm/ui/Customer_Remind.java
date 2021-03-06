package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.Staff_Adapter;
import com.example.hmqqg.hm.entity.Customer_ReEntity;
import com.example.hmqqg.hm.entity.Invest_Entity;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
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
 * 客户生日提醒
 * Created by bona on 2016/3/19.
 */
public class Customer_Remind extends BaseRequestFragment implements AdapterView.OnItemClickListener{

    private PullToRefreshListView lstv;
    private List<Customer_ReEntity.DetailInfoEntity> list = new ArrayList<Customer_ReEntity.DetailInfoEntity>();
    private Staff_Adapter staffadapter;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.staff_remind,container,false);
        initView(view);
        gethttp(REFRESH);
        return  view;
    }

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        staffadapter = new Staff_Adapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        list.clear();
        lstv.setAdapter(staffadapter);
        staffadapter.notifyDataSetChanged();
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
                if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                    stopRefresh();
                    Toast.makeText(getActivity(), "无更新数据~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPage = startPage + 1;
                try {
//                    gethttp(APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                staffadapter.notifyDataSetChanged();
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
    private void gethttp(final int what) {//显示列表

        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","birthlist");
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                requestParams.addBodyParameter("istoday", "1");
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Customer_ReEntity()));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Customer_ReEntity ce = (Customer_ReEntity) object;
        list.clear();
        list.addAll(ce.getDetailInfo());
        staffadapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(getActivity(),getResources().getString(R.string.ToastString),Toast.LENGTH_SHORT);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(getActivity(),Invest_Details.class);
//        String invest = list.get((int)id).getUserId();
//        intent.putExtra("investid", invest);
//        startActivity(intent);
    }
}
