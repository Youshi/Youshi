package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Customer_Adapter;
import com.example.hmqqg.hm.entity.CustomLookEntity;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 关单客户资料()
 * Created by Administrator on 2015/12/31.
 */
public class Customer_Activity extends BaseRequestActivity implements View.OnClickListener,OnItemClickListener {
    private ImageView back;//返回
    private ImageView chaxun;//查询
    private EditText edittext;//输入框
    private TextView title_top_bar;
    private PullToRefreshListView lstv;
    private List<CustomerEntity.DetailInfoEntity> list = new ArrayList<>();
    private Customer_Adapter customerAdapter;
    private String ed = null;

    private String str;
    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main);
        initView();//初始化
        list.clear();
        gethttp(REFRESH);

    }
    private TextWatcher textwatcher = new TextWatcher() {//搜索框实时监听搜索
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            ed = edittext.getText().toString();
            if ("".equals(ed) || ed == null) {
                list.clear();
                gethttp(REFRESH);
            } else {
                Find(REFRESH);
            }

        }
    };
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        CustomerEntity ce = (CustomerEntity) object;
        list.addAll(ce.getDetailInfo());
        customerAdapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(Customer_Activity.this,getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        chaxun = (ImageView) findViewById(R.id.chaxun);
        edittext = (EditText) findViewById(R.id.edittext);//搜索框
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        customerAdapter = new Customer_Adapter(list,this);
        title_top_bar.setText("关单客户");
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        customerAdapter.notifyDataSetChanged();
        lstv.setAdapter(customerAdapter);
        back.setOnClickListener(this);
        edittext.addTextChangedListener(textwatcher);
        lstv.setOnItemClickListener(this);
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
                if ("".equals(ed) || ed == null) {
                    if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                        stopRefresh();
                        Toast.makeText(Customer_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
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
                } else {
                    if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                        stopRefresh();
                        Toast.makeText(Customer_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startPage = startPage + 1;
                    try {
                        Find(APPEND);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    customerAdapter.notifyDataSetChanged();
                    stopRefresh();
                }
            }
        });
    }

    private void gethttp(final int what) {
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","custnumlist");
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                requestParams.addBodyParameter("userid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new CustomerEntity()));
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.chaxun://查询
                Find(REFRESH);
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
        Intent intent = new Intent(this,ColseClient_Look.class);
        String userid = list.get((int)id).getUserId();
        String username = list.get((int)id).getUserName();
        intent.putExtra("userid", userid);
        intent.putExtra("username", username);
        startActivity(intent);
    }



    private void Find(final int what) {//获取搜索的内容
        if (what == REFRESH) {
            startPage = 1;
        }
        list.clear();
        customerAdapter.notifyDataSetChanged();
        final String gettext = edittext.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action", "custnumlist");
                requestParams.addBodyParameter("keyword", gettext);
                requestParams.addBodyParameter("pagenum", String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONArray json = new JSONObject(result).getJSONArray("DetailInfo");
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject j = (JSONObject) json.get(i);
                                String username = j.getString("UserName");
                                String phone = j.getString("Phone");
                                String usergradlename = j.getString("UserGradeName");
                                CustomerEntity.DetailInfoEntity cle = new CustomerEntity.DetailInfoEntity();
                                cle.setUserName(username);
                                cle.setPhone(phone);
                                cle.setUserGradeName(usergradlename);
                                list.add(cle);
                                customerAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
}
