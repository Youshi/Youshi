package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.hmqqg.hm.adapter.MeetAdapter;
import com.example.hmqqg.hm.adapter.Outaline_Adapter;
import com.example.hmqqg.hm.adapter.OutaloneAdapter;
import com.example.hmqqg.hm.adapter.Report_Adapter;
import com.example.hmqqg.hm.adapter.Vip_ChangePassAdapter;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.MyOutaloneEntity;
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
 * 外出单(列表)
 * Created by Administrator on 2016/1/9.
 */
public class Outalone_Activity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private ImageView back;//返回
    private TextView title_top_bar;
    private ImageView new_add;//新建

    private PullToRefreshListView lstv;
    private List<MyOutaloneEntity.DetailInfoEntity> list = new ArrayList<>();
    private OutaloneAdapter outAdapter;

    private Integer startPage = 1;//开始页
    private Integer pageSize = 15;//一页显示几条数据
    private static final int APPEND = 1;
    private static final int REFRESH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_alone);
        initView();
        gethttp(REFRESH);
    }

    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        new_add = (ImageView) findViewById(R.id.refresh);
        title_top_bar.setText("外出单");
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        outAdapter = new OutaloneAdapter(list,this);
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        outAdapter.notifyDataSetChanged();
        lstv.setAdapter(outAdapter);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                gethttp(REFRESH);
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                    stopRefresh();
                    Toast.makeText(Outalone_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPage = startPage + 1;
                try {
                    gethttp(APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                outAdapter.notifyDataSetChanged();
                stopRefresh();
            }
        });
        new_add.setOnClickListener(this);
        back.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back://返回
                onBackPressed();
                break;
            case R.id.refresh://新建
                Intent intent = new Intent(this,OutbillActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void gethttp(final int what) {
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action","getoutlist");
                requestParams.addBodyParameter("Operid",str);
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
//                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new MyOutaloneEntity()));
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson  = new Gson();
                        MyOutaloneEntity myoutlone = gson.fromJson(result,MyOutaloneEntity.class);
                        if("0".equals(myoutlone.getStatus().get(0).getStaval())){
                            list.addAll(myoutlone.getDetailInfo());
                            outAdapter.notifyDataSetChanged();
                        }else if("1".equals(myoutlone.getStatus().get(0).getStaval())){
                            Toast.makeText(Outalone_Activity.this, "获取外出列表失败！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
//                        Toast.makeText(Outalone_Activity.this, "您的网络不稳定", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(Outalone_Activity.this, My_ApplyOut_Idea.class);
        String title = list.get((int)id).getApprovalTitle();
        String ApprovalType = list.get((int)id).getApprovalType();
        String Reason = list.get((int)id).getApprovalReason();
        String StartDate = list.get((int)id).getStartDate();
        String EndDate = list.get((int)id).getEndDate();
        String days = String.valueOf(list.get((int)id).getDays());
        String hours = String.valueOf(list.get((int)id).getHours());
        String flag = String.valueOf(list.get((int)id).getFlag());
        String message = (String) list.get((int)id).getOperateMessage();
        String name = list.get((int)id).getOperateByName();
        intent.putExtra("isshow","unshow");
        intent.putExtra("title", title);
        intent.putExtra("ApprovalType", ApprovalType);
        intent.putExtra("Reason", Reason);
        intent.putExtra("StartDate", StartDate);
        intent.putExtra("EndDate", EndDate);
        intent.putExtra("days", days);
        intent.putExtra("hours",hours);
        intent.putExtra("flag",flag);
        intent.putExtra("message",message);
        intent.putExtra("name",name);
        startActivity(intent);
    }

}
