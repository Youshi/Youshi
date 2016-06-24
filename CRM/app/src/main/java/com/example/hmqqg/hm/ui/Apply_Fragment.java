package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.Applyout_Adapter;
import com.example.hmqqg.hm.adapter.Approval_adap;
import com.example.hmqqg.hm.entity.Apply_Eneity;
import com.example.hmqqg.hm.entity.DailyEntity;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.fragment.base.BaseRequestFragment;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 外出单审核
 * Created by Administrator on 2016/1/5.
 */
public class Apply_Fragment extends BaseFragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView lstv;
    private List<Apply_Eneity.DetailInfoEntity> list = new ArrayList<>();
    private Applyout_Adapter reportAdapter;

    private Integer startPage = 1;//开始页
    private Integer pageSize = 15;//一页显示几条数据
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    private boolean isShow = true;
    private boolean isResume = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_main,container,false);
        initView(view);
        list.clear();
//        gethttp(REFRESH);
        return  view;
    }

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        reportAdapter = new Applyout_Adapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        lstv.setAdapter(reportAdapter);
        reportAdapter.notifyDataSetChanged();
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
                if (list.size() % pageSize !=0) {// 列表数据未满最大页行数
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
    public void gethttp(final int what) {
        // 这里从服务器获取数据
        if (what == REFRESH) {
            startPage = 1;
        }
        list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getoutaudit");
                requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("pagenum", String.valueOf(startPage));
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Apply_Eneity()));
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson =new Gson();
                        Apply_Eneity app =gson.fromJson(result,Apply_Eneity.class);
                        if("1".equals(app.getStatus().get(0).getStaval())){
                            list.addAll(app.getDetailInfo());
                            reportAdapter.notifyDataSetChanged();
                        }
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
//        isResume=false;
        Intent intent = new Intent(getActivity(),Leave_Application.class);
        String appid = String.valueOf(list.get((int) id).getApprovalID());
        String procid = String.valueOf(list.get((int) id).getPROCID());

        String title = String.valueOf(list.get((int) id).getApprovalTitle());
        String approvaltype = String.valueOf(list.get((int) id).getApprovalType());
        String startdata = String.valueOf(list.get((int) id).getStartDate());
        String enddata = String.valueOf(list.get((int) id).getEndDate());
        String days = String.valueOf(list.get((int) id).getDays());
        String hours = String.valueOf(list.get((int) id).getHours());
        String reason = String.valueOf(list.get((int) id).getApprovalReason());
//        String reason = String.valueOf(list.get((int) id).getApprovalReason());//获取定位信息（暂时不清楚字段）

        intent.putExtra("appid",appid);
        intent.putExtra("procid",procid);
        intent.putExtra("title",title);
        intent.putExtra("approvaltype",approvaltype);
        intent.putExtra("startdata",startdata);
        intent.putExtra("enddata",enddata);
        intent.putExtra("days",days);
        intent.putExtra("hours",hours);
        intent.putExtra("reason",reason);
        startActivity(intent);
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
