package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Person_Adapter;
import com.example.hmqqg.hm.entity.DeptDetailEntity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.example.hmqqg.hm.view.MyListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 部门详情
 * Created by Administrator on 2016/1/14.
 */
public class Department_Details extends BaseRequestActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView title_top_bar;
//    private TextView compile;//编辑
    private ImageView back;//返回
    private Person_Adapter adapter;
    private MyListView lstv;
    private List<String> list = new ArrayList<String>();
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter<HeadAdapter.MyViewHolder> adapter1;
    private MyListView rc_phone;
    private HeadManPhoneAdapter adapter2;
    private DeptDetailEntity.DetailInfoEntity detailInfo = null;
    private List<DeptDetailEntity.LeadersEntity> leaders = new ArrayList<>();
    private List<DeptDetailEntity.StaffersEntity> staffers = new ArrayList<>();
    private TextView departPhone;//部门电话
    private TextView leadNum;//负责人人数
    private TextView stufferNum;//部门人员数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department_details);

        initView();
        initData();
    }

    private void initData() {
        pd.setMessage("网络数据加载中,请稍候...");
        pd.show();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        String deptcode = getIntent().getStringExtra("deptcode");
        RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_service));
        requestParams.addBodyParameter("action","deptdetail");
        requestParams.addBodyParameter("deptcode",deptcode);
        x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new DeptDetailEntity(), pd));
//            }
//        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        DeptDetailEntity entity = (DeptDetailEntity) object;
        if ("1".equals(entity.getStatus().get(0).getStaval())) {
            leaders.clear();
            leaders.addAll(entity.getLeaders());

            staffers.clear();
            staffers.addAll(entity.getStaffers());

            detailInfo = entity.getDetailInfo();

            leadNum.setText(""+leaders.size());
            stufferNum.setText("" + staffers.size());
            String text = detailInfo.getPhone().toString();
            title_top_bar.setText(detailInfo.getDeptName());
            departPhone.setText(text);

            adapter.notifyDataSetChanged();
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
//        compile = (TextView) findViewById(R.id.refresh);
        back = (ImageView) findViewById(R.id.back);
        departPhone = ((TextView) findViewById(R.id.depart_phone));
        leadNum = ((TextView) findViewById(R.id.lead_num));
        stufferNum = ((TextView) findViewById(R.id.stuffer_num));
        myRecyclerView = ((RecyclerView) findViewById(R.id.recycler_headman));
        rc_phone = ((MyListView) findViewById(R.id.rc_headman_phone));
        lstv = (MyListView) findViewById(R.id.lstv);


        //设置负责人头像,名字
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecyclerView.setLayoutManager(layout);
//        //设置adapter
        adapter1 = new HeadAdapter();
        myRecyclerView.setAdapter(adapter1);
        //设置Item增加、移除动画
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置负责人电话

        adapter2 = new HeadManPhoneAdapter();
        rc_phone.setAdapter(adapter2);

        //部门人员
        adapter = new Person_Adapter(staffers, this);
        lstv.setAdapter(adapter);

//        title_top_bar.setText("组织架构管理");
//        compile.setText("编辑");
//        compile.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;

        }
    }


    class HeadAdapter extends RecyclerView.Adapter<HeadAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    Department_Details.this).inflate(R.layout.item_headman, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(leaders.get(position).getUserName());
        }

        @Override
        public int getItemCount() {
            return leaders.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView iv;
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv_head);
                iv = (ImageView) view.findViewById(R.id.iv_head1);
            }
        }
    }

    class HeadManPhoneAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return leaders.size();
        }

        @Override
        public Object getItem(int position) {
            return leaders.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = LayoutInflater.from(Department_Details.this).inflate(R.layout.item_headman_phone, null);
            TextView userName = (TextView) inflate.findViewById(R.id.tv_man);
            TextView userPhone = (TextView) inflate.findViewById(R.id.tv_phone);
            userName.setText(leaders.get(position).getUserName());
            userPhone.setText(leaders.get(position).getMobile());
            return inflate;
        }
    }


    class Memberdapter extends RecyclerView.Adapter<Memberdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    Department_Details.this).inflate(R.layout.person_adapter, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 9;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView head;
            TextView name;
            TextView job;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                head = (ImageView) view.findViewById(R.id.imagehead);
                job = (TextView) view.findViewById(R.id.job);
            }
        }
    }
}
