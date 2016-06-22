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
import com.example.hmqqg.hm.adapter.Applyout_Adapter;
import com.example.hmqqg.hm.adapter.Applyresult_Adapter;
import com.example.hmqqg.hm.adapter.Approval_adap;
import com.example.hmqqg.hm.entity.ApplyEntity;
import com.example.hmqqg.hm.entity.Apply_Eneity;
import com.example.hmqqg.hm.entity.ApplyresxultEntity;
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
 * 申请结果列表
 * Created by bona on 2016/5/6.
 */
public class Apply_result extends BaseRequestFragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView lstv;
    private Applyresult_Adapter reportAdapter;
    private List<ApplyresxultEntity.DetailInfoEntity> list = new ArrayList<>();
    private Integer startPage = 1;
    private Integer pageSize = 15;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_main,container,false);
        list.clear();
        initView(view);
        getHttp(REFRESH);
        return  view;
    }

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        reportAdapter = new Applyresult_Adapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        lstv.setAdapter(reportAdapter);
        lstv.setOnItemClickListener(this);
        reportAdapter.notifyDataSetChanged();
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
        if (what == REFRESH) {
            startPage = 1;
        }
        String str = MyApplication.getInstance().getUserInfo().getUserId();
        RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
        requestParams.addBodyParameter("action","hasaudit");
        requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
        requestParams.addBodyParameter("operid", str);
        //CustomerEntity容器需要根据数据来定，暂时没有数据
        x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new ApplyresxultEntity()));

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        ApplyresxultEntity app = (ApplyresxultEntity) object;
        if ("1".equals(app.getStatus().get(0).getStaval())){
            list.addAll(app.getDetailInfo());
            reportAdapter.notifyDataSetChanged();
        }else {
//            Toast.makeText(getActivity(), "您的网络不稳定，请稍后重试~", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(getActivity(), R.string.ToastString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String flowid = String.valueOf(list.get((int) id).getFlowID());
        if ("7".equals(flowid)){//请假单
            Intent intent = new Intent(getActivity(),Application_details.class);
            String titile = list.get((int)id).getApprovalTitle();
            String ApprovalID = String.valueOf(list.get((int)id).getApprovalID());
            String PROCID = String.valueOf(list.get((int)id).getPROCID());
            String ApprovalType = list.get((int)id).getApprovalType();
            String Reason = list.get((int)id).getApprovalReason();
            String StartDate = list.get((int)id).getStartDate();
            String EndDate = list.get((int)id).getEndDate();
            String minute = String.valueOf(list.get((int)id).getDays());
            String hours = String.valueOf(list.get((int)id).getHours());
            String message = list.get((int)id).getOperateMessage();
            String name = list.get((int)id).getOperateByName();
            intent.putExtra("titile", titile);
            intent.putExtra("ApprovalID", ApprovalID);
            intent.putExtra("PROCID", PROCID);
            intent.putExtra("ApprovalType",ApprovalType);
            intent.putExtra("Reason", Reason);
            intent.putExtra("StartDate", StartDate);
            intent.putExtra("EndDate", EndDate);
            intent.putExtra("minute", minute);
            intent.putExtra("hours", hours);
            intent.putExtra("message",message);
            intent.putExtra("name",name);
            intent.putExtra("number", "2");
            startActivity(intent);

        }
        if ("15".equals(flowid)){//外出单
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
            String message = list.get((int) id).getOperateMessage();
            String name = list.get((int)id).getOperateByName();
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
            intent.putExtra("message",message);
            intent.putExtra("number", "1");
            intent.putExtra("name",name);
            startActivity(intent);
        }
    }
}
