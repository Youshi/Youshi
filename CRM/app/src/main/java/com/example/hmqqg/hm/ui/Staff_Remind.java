package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.Approval_adap;
import com.example.hmqqg.hm.adapter.StaffAdapter;
import com.example.hmqqg.hm.adapter.Staff_Adapter;
import com.example.hmqqg.hm.entity.Customer_ReEntity;
import com.example.hmqqg.hm.entity.Staff_Entity;
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
 * 员工生日提醒
 * Created by bona on 2016/3/19.
 */
public class Staff_Remind extends BaseRequestFragment {
    private PullToRefreshListView lstv;
    private List<Staff_Entity.DetailInfoEntity> list = new ArrayList<Staff_Entity.DetailInfoEntity>();
    private StaffAdapter staffadapter;

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
        staffadapter = new StaffAdapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        list.clear();
        staffadapter.notifyDataSetChanged();
        lstv.setAdapter(staffadapter);
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
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_remindset));
                requestParams.addBodyParameter("action","staffbirth");
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Staff_Entity()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Staff_Entity staff = (Staff_Entity) object;
        list.clear();
        list.addAll(staff.getDetailInfo());
        staffadapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(getActivity(),getResources().getString(R.string.ToastString),Toast.LENGTH_SHORT);
    }
}
