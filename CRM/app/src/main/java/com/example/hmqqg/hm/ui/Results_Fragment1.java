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
import com.example.hmqqg.hm.adapter.Report_Adapter;
import com.example.hmqqg.hm.adapter.ResultsAdapter;
import com.example.hmqqg.hm.adapter.UndlingMonthAdapter;
import com.example.hmqqg.hm.entity.DailyEntity;
import com.example.hmqqg.hm.entity.MonthlyEntity;
import com.example.hmqqg.hm.entity.ResultsEntity;
import com.example.hmqqg.hm.entity.UndlingMonthEntity;
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
 * 工作审批----审批结果列表
 * Created by Administrator on 2016/1/5.
 */
public class Results_Fragment1 extends BaseRequestFragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView lstv;
    private List<ResultsEntity.DetailInfoEntity> list = new ArrayList<ResultsEntity.DetailInfoEntity>();
    private ResultsAdapter reportAdapter;

    private Integer startPage = 1;
    private Integer pageSize = 15;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_main,container,false);
        initView(view);
        gethttp(REFRESH);
        return  view;
    }

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        reportAdapter = new ResultsAdapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        list.clear();
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

    private void gethttp(final int what) {
// 这里从服务器获取数据
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "loghasaudit");
                requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("PageNum",  String.valueOf(startPage));
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new ResultsEntity()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        ResultsEntity mothly = (ResultsEntity) object;
        if(mothly.getStatus().get(0).getStaval().equals("1")){
            list.addAll(mothly.getDetailInfo());
            reportAdapter.notifyDataSetChanged();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String Wtype = list.get((int) id).getWtype();//汇报类型
        String Flag = list.get((int) id).getFlag();// 已阅
        String UserName = list.get((int) id).getUserName();//某人的汇报
        String ID = list.get((int) id).getId();//id
        String WorkTime = String.valueOf(list.get((int) id).getWorkTime());//工作时间
        String StartDate = list.get((int) id).getStartDate();//开始时间
        String EndDate = list.get((int) id).getEndDate();//结束时间
        String AlienCall = String.valueOf(list.get((int) id).getAlienCall());//陌电数量
        String Returncall = String.valueOf(list.get((int) id).getReturncall());//回访数量
        String Intentnum = String.valueOf(list.get((int) id).getIntentnum());//意向客户
        String Invitnum = String.valueOf(list.get((int) id).getInvitnum());//邀请数量
        String Returncond = list.get((int) id).getReturncond();//回访情况
        String Dealcond = list.get((int) id).getDealcond();//关单情况
        String Nextwork = list.get((int) id).getNextwork();//次日工作计划
        String CreatedBy = String.valueOf(list.get((int) id).getCreatedBy());//都是null
        String OperatedBy = list.get((int) id).getOperatedBy();//审核人
        String Operateflag = list.get((int) id).getOperateflag();// Y
        String Wtitle = list.get((int) id).getWtitle();//都是null
        Intent intent = new Intent(getActivity(),ApprovalResult_Activity.class);
        intent.putExtra("Wtype",Wtype);
        intent.putExtra("ID",ID);
        intent.putExtra("Flag",Flag);
        intent.putExtra("UserName",UserName);
        intent.putExtra("WorkTime",WorkTime);
        intent.putExtra("StartDate",StartDate);
        intent.putExtra("EndDate",EndDate);
        intent.putExtra("AlienCall",AlienCall);
        intent.putExtra("Returncall",Returncall);
        intent.putExtra("Intentnum",Intentnum);
        intent.putExtra("Invitnum",Invitnum);
        intent.putExtra("Returncond",Returncond);
        intent.putExtra("Dealcond",Dealcond);
        intent.putExtra("Nextwork",Nextwork);
        intent.putExtra("CreatedBy",CreatedBy);
        intent.putExtra("OperatedBy",OperatedBy);
        intent.putExtra("Operateflag",Operateflag);
        intent.putExtra("Wtitle",Wtitle);
        startActivity(intent);
    }

}
