package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Organization_Adapter;
import com.example.hmqqg.hm.entity.OrganizationEntity;
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
 * 组织架构管理
 * Created by Administrator on 2015/12/31.
 */
public class Organization_Activity extends BaseRequestActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView back;
    private TextView title_top_bar;
//    private ImageView add;//添加部门
    private Organization_Adapter adapter;
    private PullToRefreshListView lstv;
    private List<OrganizationEntity.DetailInfoEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_main);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("组织架构管理");
        adapter = new Organization_Adapter(list, this);
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        adapter.notifyDataSetChanged();
        lstv.setAdapter(adapter);

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
                stopRefresh();
            }
        });
        initView();
        gethttp();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        OrganizationEntity oe = (OrganizationEntity) object;
        list.addAll(oe.getDetailInfo());
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
//        add = (ImageView) findViewById(R.id.add);
        back.setOnClickListener(this);
//        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                intent = new Intent(this, Add_department.class);
                startActivity(intent);
                break;
        }
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
        Intent intent = new Intent(this, Department_Details.class);
        String deptCode = list.get(((int) id)).getDeptCode();
        intent.putExtra("deptcode", deptCode);
        startActivity(intent);
    }

    public void gethttp() {
        RequestParams params = new RequestParams(getResources().getString(R.string.http_service));
        params.addBodyParameter("action", "deptall");
        x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new OrganizationEntity()));
    }
}
