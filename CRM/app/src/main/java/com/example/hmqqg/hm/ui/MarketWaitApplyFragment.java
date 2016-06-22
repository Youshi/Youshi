package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.Approval_adap;
import com.example.hmqqg.hm.adapter.MarketEntity;
import com.example.hmqqg.hm.adapter.Marketing_Adapter;
import com.example.hmqqg.hm.adapter.Marketing_Adapter2;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.fragment.base.BaseRequestFragment;
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
 * 市场活动待审批（）
 * Created by Baotao on 2016/3/8.
 */
public class MarketWaitApplyFragment extends BaseRequestFragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView lstv;
    private List<MarketEntity.DetailInfoEntity> list = new ArrayList<MarketEntity.DetailInfoEntity>();
    private Marketing_Adapter madapter;

    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;

    private int actid;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily_main,container,false);
        iniView(view);
        gethttp(REFRESH);
        return view;

    }

    private void iniView(View view) {
        lstv = (PullToRefreshListView) view.findViewById(R.id.lstv);
        madapter = new Marketing_Adapter(list,getActivity());
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
        madapter.notifyDataSetChanged();
        lstv.setAdapter(madapter);
        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                madapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {


                if (list.size() % pageSize !=0) {// 列表数据未满最大页行数
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
                madapter.notifyDataSetChanged();
                stopRefresh();
                // 默认显示，作为外层tab的首页
                try {
//                    gethttp(REFRESH);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


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


    private void gethttp(final int what){
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_addCustomer));
                params.addBodyParameter("action","actlist");
                params.addBodyParameter("userid",MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("PageNum",String.valueOf(startPage));
                params.addBodyParameter("State","N");
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new MarketEntity()));
            }
        }).start();

    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        MarketEntity me = (MarketEntity)object;
        actid = me.getDetailInfo().get(0).getActId();

        list.addAll(me.getDetailInfo());
        madapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),Marketing_Details.class);
        String userid = list.get((int)id).getFzUserId();
        String titile = list.get((int)id).getActTitle();
        String ActDate = list.get((int)id).getActDate();
//        String ActAddress = list.get((int)id).getActAddress();
        String ActTypeName = list.get((int)id).getActTypeName();
//        String Applyid = list.get((int)id).getApplyId().toString();

        intent.putExtra("userid", userid);
        intent.putExtra("actid", String.valueOf(actid));
        intent.putExtra("titile", titile);
        intent.putExtra("ActDate", ActDate);
//        intent.putExtra("ActAddress", ActAddress);
        intent.putExtra("ActTypeName", ActTypeName);
//        intent.putExtra("Applyid", Applyid);
        startActivity(intent);
//        getActivity().finish();
    }


}
