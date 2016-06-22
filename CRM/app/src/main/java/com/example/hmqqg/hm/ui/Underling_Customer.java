package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Underadapter;
import com.example.hmqqg.hm.entity.UnderEntity;
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
 * 意向客户
 * Created by Administrator on 2016/1/2.
 */
public class Underling_Customer extends BaseRequestActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView back;//返回
    private String ed = null;
    private TextView title_top_bar;
    private PullToRefreshListView lstv;
    private List<UnderEntity.DetailInfoEntity> list = new ArrayList<>();
    private Underadapter customLookAdapter;

    private Integer startPage = 1;
    private Integer pageSize = 15;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;

    private PopupWindow popupWindow;// popupwindow

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underl);
        initView();
        gethttp(REFRESH);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        UnderEntity ue = (UnderEntity) object;
        if(ue.getStatus().get(0).getStaval().equals("1")){
            list.addAll(ue.getDetailInfo());
            customLookAdapter.notifyDataSetChanged();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(Underling_Customer.this,getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();
    }


    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        customLookAdapter = new Underadapter(list, this);
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        lstv.setAdapter(customLookAdapter);
        customLookAdapter.notifyDataSetChanged();
        title_top_bar.setText("下属客户资料");
        back.setOnClickListener(this);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                gethttp(REFRESH);
                customLookAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if ("".equals(ed) || ed == null) {
                    if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                        stopRefresh();
                        Toast.makeText(Underling_Customer.this, "无更新数据~", Toast.LENGTH_SHORT).show();
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
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void gethttp(final int what) {
        list.clear();
        // 这里从服务器获取数据
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action", "getdwcust");
                requestParams.addBodyParameter("pagenum", String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new UnderEntity()));
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
        Intent intent = new Intent(this, Underling_look.class);
        String userid = list.get((int) id).getUserId();
        String UserName = list.get((int) id).getUserName();
        String UserSexName = list.get((int) id).getUserSexName();
        String Secretphone = list.get((int) id).getSecretphone();
        String BirthDate = list.get((int) id).getBirthDate();
        String CertifiyTypeName = list.get((int) id).getCertifiyTypeName();
        String CertifiyNO = list.get((int) id).getCertifiyNO();
        String OriginName = list.get((int) id).getOriginName();
        String UserSortName = list.get((int) id).getUserSortName();
        String MarriTypeName = list.get((int) id).getMarriTypeName();
        String Email = list.get((int) id).getEmail();
        String ContactName = list.get((int) id).getContactName();
        String Address = list.get((int) id).getAddress();
        String Name = list.get((int) id).getName();
        intent.putExtra("userid", userid);
        intent.putExtra("UserName", UserName);
        intent.putExtra("UserSexName", UserSexName);
        intent.putExtra("Secretphone", Secretphone);
        intent.putExtra("BirthDate", BirthDate);
        intent.putExtra("CertifiyTypeName", CertifiyTypeName);
        intent.putExtra("CertifiyNO", CertifiyNO);
        intent.putExtra("OriginName", OriginName);
        intent.putExtra("UserSortName", UserSortName);
        intent.putExtra("MarriTypeName", MarriTypeName);
        intent.putExtra("Email", Email);
        intent.putExtra("ContactName", ContactName);
        intent.putExtra("Address", Address);
        intent.putExtra("Name", Name);
        startActivity(intent);
    }
}


