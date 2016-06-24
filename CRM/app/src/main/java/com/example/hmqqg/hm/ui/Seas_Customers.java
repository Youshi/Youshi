package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.adapter.Seaser_Adapter;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 客户资料
 * Created by Administrator on 2016/2/19.
 */
public class Seas_Customers extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView back;//返回
    private ImageView refresh;//添加
    private TextView title_top_bar;
    public static final int TwoNextPage = 1000;
    private PullToRefreshListView lstv;//刷新分页的列表
    private List<CustomerEntity.DetailInfoEntity> mAppList = new ArrayList<CustomerEntity.DetailInfoEntity>();
    private Seaser_Adapter customerAdapter;

    private Integer startPage = 1;//开始页
    private Integer pageSize = 10;//一页显示几条数据
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    private String isShow ;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seas_customers);
        initView();
        gethttp(REFRESH);
    }


    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        refresh = (ImageView) findViewById(R.id.refresh);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        customerAdapter = new Seaser_Adapter(mAppList, this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        title_top_bar.setText("客户资料");
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        customerAdapter.notifyDataSetChanged();
        lstv.setAdapter(customerAdapter);
        lstv.setOnItemClickListener(this);
        isShow = getIntent().getStringExtra("isShow");
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                customerAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mAppList.size() % pageSize != 0) {// 列表数据未满最大页行数
                    stopRefresh();
                    Toast.makeText(Seas_Customers.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPage = startPage + 1;
                try {
                    gethttp(APPEND);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                customerAdapter.notifyDataSetChanged();
                stopRefresh();

            }
        });
    }

    private void gethttp(final int what) {
        mAppList.clear();
        // 这里从服务器获取数据
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action", "custinfolist");
                requestParams.addBodyParameter("pagenum", String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        CustomerEntity cus = gson.fromJson(result,CustomerEntity.class);
                        if ("1".equals(cus.getStatus().get(0).getStaval())){
                            mAppList.addAll(cus.getDetailInfo());
                            customerAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(Seas_Customers.this, "获取客户列表失败！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(Seas_Customers.this,getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();
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
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.refresh://添加客户基本信息
                Intent intent = new Intent(this, Add_Customer.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Seas_Look.class);
        String userid = mAppList.get((int) id).getUserId();
        intent.putExtra("userid", userid);
        intent.putExtra("isShow","Show");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if("".equals(isShow)||isShow==null){
        }else if("disShow".equals(isShow)){
            gethttp(REFRESH);
        }else{
            isShow ="disShow";
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
