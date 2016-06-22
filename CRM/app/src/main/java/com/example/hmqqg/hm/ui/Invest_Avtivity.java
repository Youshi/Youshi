package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.example.hmqqg.hm.adapter.Invest_Adapter;
import com.example.hmqqg.hm.adapter.Report_Adapter;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.TouZiEntity;
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
 * 投资信息列表
 * Created by bona on 2016/3/19.
 */
public class Invest_Avtivity extends BaseRequestActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private ImageView back;
    private TextView title_top_bar;
    private ImageView refresh;//添加投资信息列表
    /** 投资理财**/
//    private TextView product;//投资产品
//    private TextView money;//金额
//    private TextView retum;//利率返还日
//    private TextView gate;//投资回报率
//    private TextView pact;//合同编号
//    private TextView bank;//银行账号
//    private TextView bankname;//银行名称

    private PullToRefreshListView lstv;
    private List<TouZiEntity.DetailInfoEntity> list = new ArrayList<>();
    private Invest_Adapter investadapter;
    private String userid;//客户编号
    private String username;//客户姓名

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invest_list);
        initView();
        gethttp(REFRESH);
    }

    private void initView() {
        Intent intent  = getIntent();
        userid = intent.getStringExtra("userid");
        username = intent.getStringExtra("username");

//        product = (TextView) findViewById(R.id.cl_product);//产品
//        money = (TextView) findViewById(R.id.cl_money);//金额
//        retum = (TextView) findViewById(R.id.cl_retum);//利率返还日
//        gate = (TextView) findViewById(R.id.cl_gate);//投资回报率
//        pact = (TextView) findViewById(R.id.cl_pact);//合同编号
//        bank = (TextView) findViewById(R.id.cl_banknumber);//银行账号
//        bankname = (TextView) findViewById(R.id.cl_bankname);//银行名称
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh = (ImageView) findViewById(R.id.refresh);

        title_top_bar.setText("投资列表");
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);

        lstv = (PullToRefreshListView) findViewById(R.id.lstv);

        investadapter = new Invest_Adapter(list,this);
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
//        list.add(0,"哈哈");
        investadapter.notifyDataSetChanged();
        lstv.setAdapter(investadapter);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                gethttp(REFRESH);
                investadapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
               if (list.size() % pageSize != 0) {// 列表数据未满最大页行数
                stopRefresh();
                Toast.makeText(Invest_Avtivity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
                return;
            }
            startPage = startPage + 1;
            try {
                gethttp(APPEND);
            } catch (Exception e) {
                e.printStackTrace();
            }
                investadapter.notifyDataSetChanged();
            stopRefresh();
            // 默认显示，作为外层tab的首页
//                try {
//                    gethttp(REFRESH);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }

        }
    });
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        TouZiEntity tou = (TouZiEntity) object;
        list.addAll(tou.getDetailInfo());
        investadapter.notifyDataSetChanged();

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

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
//                RequestParams requestParams = new RequestParams("http://139.129.9.221/mobile/CustomerHandle.ashx");
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","investlist");
                requestParams.addBodyParameter("pagenum",String.valueOf(startPage));
                requestParams.addBodyParameter("Operid", str);
                requestParams.addBodyParameter("userid", userid);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new TouZiEntity()));
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.refresh://添加投资信息
                Intent intent = new Intent(this,Add_Invest_InformationActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,Invest_Details.class);
        int invest = list.get((int)id).getInvestId();
        String investid = String.valueOf(invest);
        intent.putExtra("investid", investid);
        startActivity(intent);
    }
    private void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
    }
}
