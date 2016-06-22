package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.NoticeDeEntity;
import com.example.hmqqg.hm.entity.NoticeEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Pattern;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 公告详情
 * Created by Administrator on 2016/1/5.
 */
public class Notice_details_Activity extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;//返回
    private TextView title_top_bar;
    private TextView title;//标题
    private TextView time;//时间
    private TextView name;//姓名
    private TextView content;//内容
    private TextView scope;//范围

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_details);
        initView();
        gethttp();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        NoticeDeEntity not = (NoticeDeEntity) object;
        title.setText(not.getDetailInfo().getTitle()+"");
        time.setText(not.getDetailInfo().getSendTime()+"");
        name.setText(not.getDetailInfo().getReceverItem()+"");
        content.setText(delHtml(not.getDetailInfo().getMContent()).replaceAll("\\s",""));

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        name = (TextView) findViewById(R.id.name);
        content = (TextView) findViewById(R.id.content);
        scope = (TextView) findViewById(R.id.scope);
        title_top_bar.setText("详情");
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
        }
    }
    private void gethttp() {
        Intent intent = getIntent();
        final String newsid = intent.getStringExtra("newsid");
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action","getnewsdetail");
                params.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("newsid",newsid);
                x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new NoticeDeEntity()));
            }
        }).start();
    }
    public static String delHtml(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            String regEx_script = "<[/s]*?script[^>]*?>[/s/S]*?<[/s]*?//[/s]*?script[/s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[/s/S]*?<//script>

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }
}
