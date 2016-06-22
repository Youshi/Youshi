package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.adapter.Custom_lookAdapter;
import com.example.hmqqg.hm.entity.CustomLookEntity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 意向客户
 * Created by Administrator on 2016/1/2.
 */
public class CustomLook_Activity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView back;//返回
    private LinearLayout linear_find;//查找意向客户信息的linear布局
    private EditText ed_find;//查找意向客户信息输入框
    private String ed = null;

    private TextView title_top_bar;
    private PullToRefreshListView lstv;
    private List<CustomLookEntity.DetailInfoEntity> list = new ArrayList<>();
    private Custom_lookAdapter customLookAdapter;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;

    private PopupWindow popupWindow;// popupwindow

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_look);
        initView();
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
            ed = ed_find.getText().toString();
            if ("".equals(ed) || ed == null) {
                list.clear();
                gethttp(REFRESH);
            } else {
                Find(REFRESH);
            }

        }
    };

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        linear_find = (LinearLayout) findViewById(R.id.linear_find);
        ed_find = (EditText) findViewById(R.id.ed_find_client);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        customLookAdapter = new Custom_lookAdapter(list, this);
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        customLookAdapter.notifyDataSetChanged();
        lstv.setAdapter(customLookAdapter);
        title_top_bar.setText("意向客户资料");
        back.setOnClickListener(this);
        linear_find.setOnClickListener(this);
        lstv.setOnItemClickListener(this);
        ed_find.addTextChangedListener(textwatcher);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                customLookAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if ("".equals(ed) || ed == null) {
                    if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                        stopRefresh();
                        Toast.makeText(CustomLook_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startPage = startPage + 1;
                    try {
                        gethttp(APPEND);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    customLookAdapter.notifyDataSetChanged();
                    stopRefresh();
                } else {
                    if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                        stopRefresh();
                        Toast.makeText(CustomLook_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startPage = startPage + 1;
                    try {
                        Find(APPEND);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    customLookAdapter.notifyDataSetChanged();
                    stopRefresh();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.open:
                popupWindow.dismiss();
                break;
            case R.id.save:
                popupWindow.dismiss();
                break;
            case R.id.close:
                popupWindow.dismiss();
                break;
            case R.id.linear_find://查找客户信息的Linear布局
                Find(REFRESH);
                break;

        }
    }

    private void Find(final int what) {//获取搜索的内容
        if (what == REFRESH) {
            startPage = 1;
        }
        list.clear();
        customLookAdapter.notifyDataSetChanged();
        final String gettext = ed_find.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action", "willcustlist");
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
                                CustomLookEntity.DetailInfoEntity cle = new CustomLookEntity.DetailInfoEntity();
                                cle.setUserName(username);
                                cle.setPhone(phone);
                                cle.setUserGradeName(usergradlename);
                                list.add(cle);
                                customLookAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(CustomLook_Activity.this,getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();
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


    private void gethttp(final int what) {
        // 这里从服务器获取数据
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","willcustlist");
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
                requestParams.addBodyParameter("Operid",str);
                x.http().get(requestParams, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONArray json = new JSONObject(result).getJSONArray("DetailInfo");
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject j = (JSONObject) json.get(i);
                                String userid = j.getString("UserId");
                                String username = j.getString("UserName");
                                String phone = j.getString("Phone");
                                String usergradlename = j.getString("UserGradeName");
                                CustomLookEntity.DetailInfoEntity cle = new CustomLookEntity.DetailInfoEntity();
                                cle.setUserId(userid);
                                cle.setUserName(username);
                                cle.setPhone(phone);
                                cle.setUserGradeName(usergradlename);
                                list.add(cle);
                                customLookAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(CustomLook_Activity.this, getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Customer_Look.class);
        String userid = list.get((int) id).getUserId();
        String username = list.get((int) id).getUserName();
        intent.putExtra("userid", userid);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}


