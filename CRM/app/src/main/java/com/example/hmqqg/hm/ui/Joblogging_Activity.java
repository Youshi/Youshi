package com.example.hmqqg.hm.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.adapter.Vip_ChangePassAdapter;
import com.example.hmqqg.hm.fragment.MessageFragment;

/**
 * 工作汇报
 * Created by Administrator on 2016/1/4.
 */
public class Joblogging_Activity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private TextView title_top_bar;
    private ImageView back;//返回
    private ImageView refresh;//添加
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int isreport=1;
    private int DAILYREPORT=1;
    private int WEEKREPORT=2;
    private int MONTHYREPORT=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_logging);
        initView();
    }

    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        refresh = (ImageView) findViewById(R.id.refresh);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        title_top_bar.setText("工作录入");
        viewPager.setAdapter(new Vip_ChangePassAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.refresh://添加
                new  AlertDialog.Builder(Joblogging_Activity.this)
                        .setTitle("请选择" )
                        .setSingleChoiceItems(new  String[] {"编写日报","编写周报", "编写月报"},  0 ,
                                new  DialogInterface.OnClickListener() {

                                    public   void  onClick(DialogInterface dialog, int which) {
                                        if(which==0){
                                            isreport = DAILYREPORT;
                                        }
                                        if(which==1){
                                            isreport = WEEKREPORT;
                                        }
                                        if(which==2){
                                            isreport = MONTHYREPORT;
                                        }

                                    }
                                }
                        )
                        .setNeutralButton("取消" , null )
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(isreport==DAILYREPORT){
                                    Intent intent = new Intent(Joblogging_Activity.this,
                                            AddJob_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }else if(isreport == WEEKREPORT){
                                    Intent intent = new Intent(Joblogging_Activity.this,
                                            Weekly_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }else if(isreport == MONTHYREPORT){
                                    Intent intent = new Intent(Joblogging_Activity.this,
                                            Monthly_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                dialog.dismiss();
                            }
                        }).show();

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
