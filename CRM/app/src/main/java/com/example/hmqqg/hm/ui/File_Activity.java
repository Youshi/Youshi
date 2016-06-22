package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.File_Adapter;
import com.example.hmqqg.hm.adapter.MarketEntity;
import com.example.hmqqg.hm.entity.FileEntity;
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
 * 公司文件下载
 * Created by Administrator on 2016/1/7.
 */
public class File_Activity extends BaseRequestActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private TextView title_top_bar;
    private ImageView back;//返回
    private PullToRefreshListView lstv;
    public  static final int TwoNextPage = 1000;
//    private SwipeMenuListView listview;
    private List<FileEntity.DetailInfoEntity> mAppList = new ArrayList<>();
    private File_Adapter fileAdapter;
    private Integer startPage = 1;
    private Integer pageSize = 10;
    private static final int APPEND = 1;
    private static final int REFRESH = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_main);
        initView();
        gethttp(REFRESH);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        FileEntity file = (FileEntity) object;
        mAppList.addAll(file.getDetailInfo());
        fileAdapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(File_Activity.this,getResources().getString(R.string.ToastString),Toast.LENGTH_SHORT);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("企业文件夹");
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
//        listview = (SwipeMenuListView) findViewById(R.id.listView);
        lstv.setOnItemClickListener(this);
        lstv.setMode(PullToRefreshBase.Mode.BOTH);
//        fileAdapter.notifyDataSetChanged();
        fileAdapter = new File_Adapter(mAppList,this);
        lstv.setAdapter(fileAdapter);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                fileAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                if (mAppList.size() % pageSize !=0) {// 列表数据未满最大页行数
//                    stopRefresh();
//                    Toast.makeText(File_Activity.this, "无更新数据~", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                startPage = startPage + 1;
//                try {
//                    gethttp(APPEND);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                fileAdapter.notifyDataSetChanged();
                stopRefresh();
//                // 默认显示，作为外层tab的首页
//                try {
//                    gethttp(REFRESH);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }


            }
        });
        // step 1. create a MenuCreator
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//                // create "open" item
//                SwipeMenuItem openItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//                        0xCE)));
//                // set item width
//                openItem.setWidth(dp2px(90));
//                // set item title
//                openItem.setTitle("编辑");
//                // set item title fontsize
//                openItem.setTitleSize(18);
//                // set item title font color
//                openItem.setTitleColor(Color.WHITE);
//                // add to menu
//                menu.addMenuItem(openItem);
//
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(dp2px(90));
//                // set a icon
//                deleteItem.setTitle("删除");
//                // set item title font color
//                deleteItem.setTitleColor(Color.WHITE);
//                // set item title fontsize
//                deleteItem.setTitleSize(18);
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };
//        // set creator
//        listview.setMenuCreator(creator);
//
//        // step 2. listener item click event
//        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
//                switch (index) {
//                    case 0:
//                        // open
////                        open(item);
//                        break;
//                    case 1:
//                        // delete
////					delete(item);
//                        mAppList.remove(position);
//                        fileAdapter.notifyDataSetChanged();
//                        break;
//                }
//            }
//        });
//
//        // set SwipeListener
//        listview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
//
//            @Override
//            public void onSwipeStart(int position) {
//                // swipe start
//            }
//
//            @Override
//            public void onSwipeEnd(int position) {
//                // swipe end
//            }
//        });
//
//        // other setting
////		listView.setCloseInterpolator(new BounceInterpolator());
//
//        // test item long click
//        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                           int position, long id) {
//                Toast.makeText(getApplicationContext(), position + " long click", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back://返回
                onBackPressed();
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
    private void gethttp(final int what){
        if (what == REFRESH) {
            startPage = 1;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action","floderlist");
//                params.addBodyParameter("PageNum",String.valueOf(startPage));
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new FileEntity()));
            }
        }).start();

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,File_details_Activity.class);
        String floderid = mAppList.get((int) id).getFolderId();
        intent.putExtra("floderid",floderid);
        startActivity(intent);
    }

}
