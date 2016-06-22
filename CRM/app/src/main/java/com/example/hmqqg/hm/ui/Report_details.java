package com.example.hmqqg.hm.ui;

import android.app.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Reportde_adapter;
import com.example.hmqqg.hm.adapter.Submit_Adapter;
import com.example.hmqqg.hm.entity.DailyEntity_d;
import com.example.hmqqg.hm.entity.ReviewedEnyity;
import com.example.hmqqg.hm.entity.SubmitSuperior_Entity;
import com.example.hmqqg.hm.entity.UndlingWeekEntity;
import com.example.hmqqg.hm.entity.WeekEntity_d;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;

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
 * 员工周报汇报详情
 * Created by Administrator on 2016/1/6.
 */
public class Report_details extends BaseRequestActivity implements View.OnClickListener {
    private TextView title_top_bar;
    private ImageView back;

    private TextView work_time;//工作时间
    private TextView plan;
    private TextView team;//所属团队
    private TextView manager;//团队经理
    private TextView client;//客户经理
    private TextView amount1;//陌电数量
    private TextView amount2;//回访客户数量
    private TextView amount3;//意向客户数量
    private TextView condition1;//回访客户情况
    private TextView condition2;//关单情况
    private TextView condition3;//下周工作安排
    //    private LinearLayout linear_comment;//评论的线性布局
    private Submit_Adapter adapter;
    private List<SubmitSuperior_Entity.DetailInfoEntity> list;
    private Reportde_adapter readapter;
    private List<WeekEntity_d.CommentsEntity> weeklist;

    //    private Button mBt_worksave;//保存评论按钮
    private ListView ed_workevaluate;//评论
    private String hbid;
    private String userid;
    private String stamess;
    private String staval;
    private String dpnr;
    private String Suserid;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_details);
        initView();
        hbid = getIntent().getStringExtra("hdid");
        userid = getIntent().getStringExtra("userid");
//        message = getIntent().getStringExtra("message");
        gethttp();

    }

    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);

        work_time = (TextView) findViewById(R.id.work_time);
        plan = (TextView) findViewById(R.id.plan);
        plan.setText("次日工作日志");
        amount1 = (TextView) findViewById(R.id.amount1);
        amount2 = (TextView) findViewById(R.id.amount2);
        amount3 = (TextView) findViewById(R.id.amount3);
        condition1 = (TextView) findViewById(R.id.condition1);
        condition2 = (TextView) findViewById(R.id.condition2);
        condition3 = (TextView) findViewById(R.id.condition3);
        weeklist = new ArrayList<>();
        ed_workevaluate = (ListView) findViewById(R.id.ed_workevaluate);
        list = new ArrayList<>();
        adapter = new Submit_Adapter(list, Report_details.this);
        readapter = new Reportde_adapter(weeklist, Report_details.this);
        ed_workevaluate.setAdapter(readapter);
        adapter.notifyDataSetChanged();
        readapter.notifyDataSetChanged();
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    private void getDiaLog() {
        AlertDialog.Builder buildalert = new AlertDialog.Builder(this);
        buildalert.setTitle("是否传阅至上级？").setPositiveButton("传阅", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gethttpspinner();//从服务器获取上级审核人列表
                dialog.dismiss();
            }
        }).setNegativeButton("不传阅", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getReviewed();//直接保存评论
                dialog.dismiss();
            }
        }).create().show();
    }

    private void gethttpspinner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getauditor");
                requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            Gson gson = new Gson();
                            SubmitSuperior_Entity entity = gson.fromJson(result, SubmitSuperior_Entity.class);
                            list = entity.getDetailInfo();
                            adapter.notifyDataSetChanged();
                            getApproverList();//获取上级审核人列表的Dialog
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(Report_details.this, "传阅上级审核人列表获取失败，请稍后重试~", Toast.LENGTH_SHORT).show();
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

    private void getApproverList() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("传阅上级审核人列表");
        build.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Suserid = list.get(which).getUserid();
            }
        });
        build.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Report_details.this, Suserid, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        build.setNegativeButton("取消", null);
        build.create().show();
    }

    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getoneweek");
                requestParams.addBodyParameter("hbid", hbid);
                requestParams.addBodyParameter("Operid", str);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new WeekEntity_d()));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        WeekEntity_d de = (WeekEntity_d) object;
        if (de.getStatus().get(0).getStaval().equals("1")) {
            pd.dismiss();
            work_time.setText(de.getDetailInfo().get(0).getStartDate() + "");
            amount1.setText(de.getDetailInfo().get(0).getAlienCall() + "");
            amount2.setText(de.getDetailInfo().get(0).getReturnCall() + "");
            amount3.setText(de.getDetailInfo().get(0).getIntentNum() + "");
            condition1.setText(de.getDetailInfo().get(0).getReturnCond() + "");
            condition2.setText(de.getDetailInfo().get(0).getDealCond() + "");
            condition3.setText(de.getDetailInfo().get(0).getNextWork() + "");
            title_top_bar.setText(de.getDetailInfo().get(0).getUSERNAME() + "工作日志");
            weeklist.addAll(de.getComments());
            readapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    public void getReviewed() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "dpworkhb");
                requestParams.addBodyParameter("hbid", hbid);
                requestParams.addBodyParameter("Operid", str);
                requestParams.addBodyParameter("userid", userid);
                requestParams.addBodyParameter("dpnr", dpnr);
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        try {
                            JSONArray json = new JSONObject(result).getJSONArray("status");
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject j = (JSONObject) json.get(i);
                                stamess = j.getString("stamess");
                                staval = j.getString("staval");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if ("1".equals(staval)) {
                            Toast.makeText(Report_details.this, stamess, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Report_details.this, stamess, Toast.LENGTH_SHORT).show();

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

    private void progressD() {
        pd = new ProgressDialog(Report_details.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
        pd.setMessage("正在保存,请稍候...");
        pd.show();
    }
}
