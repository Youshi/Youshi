package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.Add_UmInformationEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 公海客户的资料详情
 * Created by tao on 2016/3/2.
 */
public class Seas_Look extends BaseRequestActivity implements View.OnClickListener {
    private ImageView back;//返回
    private TextView title_top_bar;
    private TextView refresh;//修改
    private TextView name;//姓名
    private TextView tel;//电话
    private TextView birthday;//出生日期

    private TextView contact;//联系人
    private TextView phone;//联系人电话
    private TextView relation;//和客户的关系
    private TextView named;//称呼
//    private TextView remark;//备注

    private TextView origine;//客户来源
    private TextView sex;//性别
    private TextView certificate;//证件类型
    private TextView address;//地址
    private TextView IDcard;//
    private TextView purpose;//意向程度
    private TextView invest;//投资能力
    private TextView marriage;//婚姻状况
    private TextView email;//电子邮箱
    private TextView profession;//职业


    private ImageView addimageview1;//查看图片
    private ImageView addimageview2;
    private ImageView addimageview3;
    private ImageView addimageview4;
    private ImageView addimageview5;
    private ImageView addimageview6;
    private ImageView addimageview7;
    private ImageView addimageview8;
    private ImageView addimageview9;
    private ImageView addimageview10;

    private String name1;
    private String tel1;
    private String birthday1;
    private String origine1;
    private String sex1;
    private String address1;
    private String certifiyType1;
    private String certifiyNO1;
    private String purpose1;
    private String invest1;
    private String email1;
    private String profession1;
    private String contact1;
    private String marriage1;

    private String phone1;
    private String relation1;
    private String named1;
    private String userid;
    private String isShow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seas_look);
        initView();
        gethttp();
    }

    private void initView() {//初始化控件
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
//        invest = (TextView) findViewById(R.id.invest);
        marriage = (TextView) findViewById(R.id.marriage);
        email = (TextView) findViewById(R.id.email);
        profession = (TextView) findViewById(R.id.profession);

        contact = (TextView) findViewById(R.id.contact);
        phone = (TextView) findViewById(R.id.phone);
        relation = (TextView) findViewById(R.id.relation);
        named = (TextView) findViewById(R.id.named);
        isShow = getIntent().getStringExtra("isShow");
//        remark = (TextView) findViewById(R.id.remark);


//        addimageview1 = (ImageView) findViewById(R.id.addimageview1);
//        addimageview2 = (ImageView) findViewById(R.id.addimageview2);
//        addimageview3 = (ImageView) findViewById(R.id.addimageview3);
//        addimageview4 = (ImageView) findViewById(R.id.addimageview4);
//        addimageview5 = (ImageView) findViewById(R.id.addimageview5);
//        addimageview6 = (ImageView) findViewById(R.id.addimageview6);
//        addimageview7 = (ImageView) findViewById(R.id.addimageview7);
//        addimageview8 = (ImageView) findViewById(R.id.addimageview8);
//        addimageview9 = (ImageView) findViewById(R.id.addimageview9);
//        addimageview10 = (ImageView) findViewById(R.id.addimageview10);
//
//        addimageview1.setVisibility(View.GONE);
//        addimageview2.setVisibility(View.GONE);
//        addimageview3.setVisibility(View.GONE);
//        addimageview4.setVisibility(View.GONE);
//        addimageview5.setVisibility(View.GONE);
//        addimageview6.setVisibility(View.GONE);
//        addimageview7.setVisibility(View.GONE);
//        addimageview8.setVisibility(View.GONE);
//        addimageview9.setVisibility(View.GONE);
//        addimageview10.setVisibility(View.GONE);
        back.setOnClickListener(this);
        title_top_bar.setText("客户资料");
        refresh.setText("修改");
        refresh.setOnClickListener(this);

    }
    public void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String userid = getIntent().getStringExtra("userid");
                String operid = MyApplication.getInstance().getUserInfo().getUserId();
//                RequestParams requestParams = new RequestParams("http://139.129.9.221/mobile/CustomerHandle.ashx?action=getonecust&Userid="+userid+"&Operid="+operid);
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer)+"?action=getonecust&Userid="+userid+"&Operid="+operid);
//        requestParams.addBodyParameter("action","getonecust");
//        requestParams.addBodyParameter("Userid ",userid);
//        requestParams.addBodyParameter("Operid ", operid);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Add_UmInformationEntity()));
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if("".equals(isShow)||isShow==null){
        }else if("disShow".equals(isShow)){
            gethttp();
        }else{
            isShow ="disShow";
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Add_UmInformationEntity add = (Add_UmInformationEntity) object;
        userid = add.getDetailInfo().getUserId();
         name1 = add.getDetailInfo().getUserName();//姓名
         tel1 = add.getDetailInfo().getPhone();//移动电话
         birthday1 = add.getDetailInfo().getBirthDate();//生日
         origine1 = add.getDetailInfo().getOrigin();//客户来源
         sex1 = add.getDetailInfo().getUserSex();//性别
         address1 = add.getDetailInfo().getAddress();//地址
         certifiyType1 = add.getDetailInfo().getCertifiyType()+"";//证件类型
         certifiyNO1 = add.getDetailInfo().getCertifiyNO()+"";//证件号码
         purpose1 = add.getDetailInfo().getUserGradeName();//意向程度
//         invest1 = add.getDetailInfo().getTZNL();//投资能力
         marriage1 = add.getDetailInfo().getMarriType();//婚姻状况
         email1 = add.getDetailInfo().getEmail();//电子邮箱
         profession1 = add.getDetailInfo().getTradeName();//职业

         contact1 = add.getDetailInfo().getContactName();//联系人
         phone1 = add.getDetailInfo().getContactTel();//联系人电话
         relation1 = add.getDetailInfo().getRelaType();//关系
         named1 = add.getDetailInfo().getCall();//称呼
//        String remark1 = add.getDetailInfo().getRemark();//备注
        name.setText(name1);
        tel.setText(tel1);
        birthday.setText(birthday1);

        if("LY001".equals(origine1)){
            origine.setText("讲座");
        }else if("LY002".equals(origine1)){
            origine.setText("陌电");

        }else if("LY003".equals(origine1)){
            origine.setText("发单");

        }else if("LY004".equals(origine1)){
            origine.setText("答谢会");

        }else if("LY005".equals(origine1)){
            origine.setText("转介绍");

        }else if("LY006".equals(origine1)){
            origine.setText("社区活动");

        }else if("LY007".equals(origine1)){
            origine.setText("渠道");

        }else if("LY008".equals(origine1)){
            origine.setText("自有资源");

        }else if("LY0011".equals(origine1)){
            origine.setText("其他途径");
        }

        if("ZJ01".equals(certifiyType1)){
            certificate.setText("身份证");
        }else if("ZJ02".equals(certifiyType1)){
            certificate.setText("军官证");

        }else if("ZJ03".equals(certifiyType1)){
            certificate.setText("驾驶证");

        }else if("ZJ04".equals(certifiyType1)){
            certificate.setText("其他");

        }
        if ("XBFL01".equals(sex1)){
            sex.setText("男");
        }else if ("XBFL02".equals(sex1)){
            sex.setText("女");
        }
//        sex.setText(sex1);

        address.setText(address1);
//        certificate.setText(certifiyType1);
        IDcard.setText(certifiyNO1);
        if ("HY001".equals(marriage1)){
            marriage.setText("已婚");
        }else if ("HY002".equals(marriage1)){
            marriage.setText("未婚");
        }else if ("HY003".equals(marriage1)){
            marriage.setText("离异");
        }else if ("HY004".equals(marriage1)){
            marriage.setText("垂偶");
        }
        address.setText(address1);
        IDcard.setText(certifiyNO1);
        purpose.setText(purpose1);
//        invest.setText(invest1);
        email.setText(email1);
        profession.setText(profession1);

        contact.setText(contact1);
        phone.setText(phone1);
        if ("RYGX01".equals(relation1)){
            relation.setText("朋友");
        }else if ("RYGX02".equals(relation1)){
            relation.setText("配偶");
        }
        named.setText(named1);
//        remark.setText(remark1);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(this,"您的网络不稳定，请稍后重试~",Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(Seas_Look.this,Seas_Customers.class);
//        startActivity(intent);
//        finish();
//        super.onBackPressed();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back://返回
                onBackPressed();
                break;
            case R.id.refresh://修改
                Intent intent = new Intent(this,Edit_Activity.class);
                intent.putExtra("name",name1);
                intent.putExtra("userid",userid);
                intent.putExtra("tel1",tel1);
                intent.putExtra("birthday1",birthday1);
                intent.putExtra("origine1",origine1);
                intent.putExtra("sex1",sex1);
                intent.putExtra("address1",address1);
                intent.putExtra("certifiyType1",certifiyType1);
                intent.putExtra("certifiyNO1",certifiyNO1);
                intent.putExtra("purpose1",purpose1);
//                intent.putExtra("invest1",invest1);
                intent.putExtra("email1",email1);
                intent.putExtra("profession1",profession1);
                intent.putExtra("contact1",contact1);
                intent.putExtra("marriage1",marriage1);
                intent.putExtra("phone1",phone1);
                intent.putExtra("relation1",relation1);
                intent.putExtra("named1",named1);
                startActivity(intent);
                finish();
                break;
        }
    }
}
