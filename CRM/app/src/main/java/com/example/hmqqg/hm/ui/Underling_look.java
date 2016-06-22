package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.adapter.Custom_lookAdapter;
import com.example.hmqqg.hm.entity.Add_UmInformationEntity;
import com.example.hmqqg.hm.entity.CustomLookEntity;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.LinkmanEntity;
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
import java.util.Map;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 下属客户
 * Created by Administrator on 2016/1/2.
 */
public class Underling_look extends BaseActivity implements View.OnClickListener{
    private ImageView back;//返回
    private TextView title_top_bar;
    private TextView name;//姓名
    private TextView tel;//电话
    private TextView birthday;//出生日期
    private TextView origine;//客户来源
    private TextView sex;//性别
    private TextView certificate;//证件类型
    private TextView address;//地址
    private TextView IDcard;//身份证号
    private TextView purpose;//意向程度
    private TextView invest;//投资能力
    private TextView marriage;//婚姻状况
    private TextView email;//电子邮箱
    private TextView profession;//职业
    private TextView refresh;
    private String tel1;
    private String birthday1;
    private String origine1;
    private String sex1;
    private String address1;
    private String certifiyType1;
    private String certifiyNO1;
    private String purpose1;
    private String marriage1;
    private String email1;
    private String profession1;
    private String userid;
    private String username;

    int a =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.under_look);
        initView();
        userid = getIntent().getStringExtra("userid");
    }


    private void initView() {
        Intent intent = getIntent();
        username = intent.getStringExtra("UserName");
        sex1 = intent.getStringExtra("UserSexName");
        tel1 = intent.getStringExtra("Secretphone");
        birthday1 = intent.getStringExtra("BirthDate");
        certifiyType1 = intent.getStringExtra("CertifiyTypeName");
        certifiyNO1 = intent.getStringExtra("CertifiyNO");
        origine1 = intent.getStringExtra("OriginName");
        purpose1 = intent.getStringExtra("UserSortName");
        marriage1 = intent.getStringExtra("MarriTypeName");
        email1 = intent.getStringExtra("Email");
        profession1 = intent.getStringExtra("ContactName");
        address1 = intent.getStringExtra("Address");

        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh = (TextView) findViewById(R.id.refresh);
        name = (TextView) findViewById(R.id.name);
        tel = (TextView) findViewById(R.id.tel);
        birthday = (TextView) findViewById(R.id.birthday);
        origine = (TextView) findViewById(R.id.origine);
        sex = (TextView) findViewById(R.id.spinner_sex);
        certificate = (TextView) findViewById(R.id.spinner_certificate);
        address = (TextView) findViewById(R.id.address);
        IDcard = (TextView) findViewById(R.id.IDcard);
        purpose = (TextView) findViewById(R.id.purpose);
        marriage = (TextView) findViewById(R.id.marriage);
        email = (TextView) findViewById(R.id.email);
        profession = (TextView) findViewById(R.id.profession);

        back.setOnClickListener(this);
        refresh.setVisibility(View.GONE);
        title_top_bar.setText("客户资料");

        profession.setText(profession1+"");
        email.setText(email1+"");
        marriage.setText(marriage1+"");
        purpose.setText(purpose1+"");
        profession.setText(profession1+"");
        IDcard.setText(certifiyNO1+"");
        address.setText(address1+"");
        certificate.setText(certifiyType1+"");
        sex.setText(sex1+"");
        origine.setText(origine1+"");
        birthday.setText(birthday1+"");
        tel.setText(tel1+"");
        name.setText(username+"");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back://返回
                onBackPressed();
                break;
        }
    }

}


