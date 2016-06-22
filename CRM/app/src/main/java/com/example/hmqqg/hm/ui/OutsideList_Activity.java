package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.OutListAdapter;
import com.example.hmqqg.hm.adapter.Outaline_Adapter;
import com.example.hmqqg.hm.adapter.OutaloneAdapter;
import com.example.hmqqg.hm.entity.MyOutaloneEntity;
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
 * 外出事由列表
 * Created by bona on 2016/4/10.
 */
public class OutsideList_Activity extends BaseRequestActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    private ImageView back;//返回
    private TextView title_top_bar;

    private PullToRefreshListView lstv;
    private List<MyOutaloneEntity.DetailInfoEntity> list = new ArrayList<>();
    private OutaloneAdapter outAdapter;

    private Integer startPage = 1;//开始页
    private Integer pageSize = 15;//一页显示几条数据
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
//    private OutListAdapter outlistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_list);
        initView();
        gethttp(REFRESH);
    }



    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);

        back.setOnClickListener(this);
        title_top_bar.setText("外出签到");
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
//                outAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                    stopRefresh();
                    Toast.makeText(OutsideList_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
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
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new MyOutaloneEntity()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        MyOutaloneEntity myoutlone = (MyOutaloneEntity) object;
        if("0".equals(myoutlone.getStatus().get(0).getStaval())){
            list.clear();
            list.addAll(myoutlone.getDetailInfo());
            outAdapter.notifyDataSetChanged();
        }else if("1".equals(myoutlone.getStatus().get(0).getStaval())){
            Toast.makeText(OutsideList_Activity.this, "获取外出列表失败,请稍后重试~", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(OutsideList_Activity.this, R.string.ToastString, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String imageurl = (String) list.get((int)id).getAttachment();
        if (imageurl == null||"".equals(imageurl)){
            Intent intent = new Intent(OutsideList_Activity.this, My_ApplyOut_Idea.class);
            String title = list.get((int)id).getApprovalTitle();
            String ApprovalID = String.valueOf(list.get((int)id).getApprovalID());
            String ApprovalType = list.get((int)id).getApprovalType();
            String Reason = list.get((int)id).getApprovalReason();
            String StartDate = list.get((int)id).getStartDate();
            String EndDate = list.get((int)id).getEndDate();
            String days = String.valueOf(list.get((int)id).getDays());
            String hours = String.valueOf(list.get((int)id).getHours());
            String flag = String.valueOf(list.get((int)id).getFlag());
            String name = list.get((int)id).getOperateByName();
            imageurl = (String) list.get((int)id).getAttachment();
            intent.putExtra("isshow","show");
            intent.putExtra("title", title);
            intent.putExtra("ApprovalType", ApprovalType);
            intent.putExtra("Reason", Reason);
            intent.putExtra("StartDate", StartDate);
            intent.putExtra("EndDate", EndDate);
            intent.putExtra("days", days);
            intent.putExtra("hours",hours);
            intent.putExtra("flag",flag);
            intent.putExtra("ApprovalID",ApprovalID);
            intent.putExtra("name",name);
            startActivity(intent);
        }else{
            Intent intent = new Intent(OutsideList_Activity.this, My_ApplyOut_show.class);
            String title = list.get((int)id).getApprovalTitle();
            String ApprovalType = list.get((int)id).getApprovalType();
            String ApprovalID = String.valueOf(list.get((int)id).getApprovalID());
            String Reason = list.get((int)id).getApprovalReason();
            String StartDate = list.get((int)id).getStartDate();
            String EndDate = list.get((int)id).getEndDate();
            String days = String.valueOf(list.get((int)id).getDays());
            String hours = String.valueOf(list.get((int)id).getHours());
            String flag = String.valueOf(list.get((int)id).getFlag());
            String address = (String) list.get((int)id).getAddr();
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
            intent.putExtra("imageurl",imageurl);
            intent.putExtra("address",address);
            intent.putExtra("ApprovalID",ApprovalID);
            intent.putExtra("name",name);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
        }
    }
}
