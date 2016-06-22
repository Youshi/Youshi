package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.Product_Adapter;
import com.example.hmqqg.hm.adapter.Staff_Adapter;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.Invest_Entity;
import com.example.hmqqg.hm.entity.TouZiEntity;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.fragment.base.BaseRequestFragment;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.body.StringBody;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 投资产品提醒
 * Created by bona on 2016/3/19.
 */
public class Product_Remind extends BaseRequestFragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView lstv;
    private List<Invest_Entity.DetailInfoEntity> list = new ArrayList<>();
    private Product_Adapter productadapter;
    private EditText edit_search;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    private String search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.invest_alert,container,false);
        initView(view);
        gethttp(REFRESH);
        return  view;
    }
//=========================================================================================================================================================

    private void initView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        edit_search = (EditText) view.findViewById(R.id.edit_search);
        edit_search.addTextChangedListener(textwatcher);
        productadapter = new Product_Adapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        list.clear();
        lstv.setAdapter(productadapter);
        productadapter.notifyDataSetChanged();
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
                if ("".equals(search) || search == null) {
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
                    productadapter.notifyDataSetChanged();
                    stopRefresh();
                } else {
                    if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                        stopRefresh();
                        Toast.makeText(getActivity(), "无更新数据~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startPage = startPage + 1;
                    try {
                        Find(APPEND);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    productadapter.notifyDataSetChanged();
                    stopRefresh();
                }
            }
        });
    }
//=========================================================================================================================================================

    private TextWatcher textwatcher = new TextWatcher() {//搜索框实时监听搜索
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            search = edit_search.getText().toString();
            if ("".equals(search) || search == null) {
                list.clear();
                gethttp(REFRESH);
            } else {
                Find(REFRESH);
            }
        }
    };
//=========================================================================================================================================================
    private void gethttp(final int what) {//显示列表

        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","investdue");
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Invest_Entity()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Invest_Entity ce = (Invest_Entity) object;
        list.clear();
        list.addAll(ce.getDetailInfo());
        productadapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(getActivity(),getResources().getString(R.string.ToastString),Toast.LENGTH_SHORT);

    }
//=========================================================================================================================================================

    private void Find(final int what) {//获取搜索的内容
        if (what == REFRESH) {
            startPage = 1;
        }
        list.clear();
        productadapter.notifyDataSetChanged();
        final String gettext = edit_search.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action", "investdue");
                requestParams.addBodyParameter("KeyWord", gettext);
                requestParams.addBodyParameter("pagenum", String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONArray json = new JSONObject(result).getJSONArray("DetailInfo");
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject j = (JSONObject) json.get(i);
                                String username = j.getString("userName");//用户名字
                                String invest_name = j.getString("InvestName");//投资产品名称
                                String money = j.getString("InvestSum");//投资金额
                                String endTime = j.getString("EndDate");//到期时间
                                Invest_Entity.DetailInfoEntity cle = new Invest_Entity.DetailInfoEntity();
                                cle.setUserName(username);
                                cle.setInvestName(invest_name);
                                cle.setInvestSum(Double.valueOf(money));
                                cle.setEndDate(endTime);
                                list.add(cle);
                                productadapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(getActivity(),"服务器出错啦~",Toast.LENGTH_SHORT).show();
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
//=========================================================================================================================================================

    private void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
    }
//=========================================================================================================================================================

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),Invest_Details.class);
        int invest = list.get((int)id).getInvestId();
        String investid = String.valueOf(invest);
        intent.putExtra("investid", investid);
        startActivity(intent);
    }


}
