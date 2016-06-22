package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.Add_UmInformationEntity;
import com.example.hmqqg.hm.entity.CloseClientlook_Entity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 关单客户查看详情
 * Created by bona on 2016/3/17.
 */
public class ColseClient_Look extends BaseRequestActivity implements View.OnClickListener{

    /** 基本资料**/
    private ImageView back;//返回
    private TextView title_top_bar;
    private TextView mCl_name;//姓名
    private TextView mCl_sex;//性别
    private TextView mCl_tel;//手机号
    private TextView mCl_birthday;//出生日期
    private TextView mCl_certifitype;//证件类型
    private TextView mCl_IDcard;//证件号码
    private TextView mCl_origine;//客户来源
    private TextView mCl_address;//地址
    private TextView mCl_purpose;//意向程度
    private TextView mCl_invest;//投资能力
    private TextView mCl_marriage;//婚姻状况
    private TextView mCl_email;//电子邮箱
    private TextView mCl_profession;//职业

    /**紧急联系人**/
    private TextView contact;//联系人
    private TextView phone;//联系人电话
    private TextView relation;//与客户关系
    private TextView named;//称呼
//    private TextView remark;//备注

    /** 投资理财**/
    private TextView product;//投资产品
    private TextView money;//金额
    private TextView retum;//利率返还日
    private TextView gate;//投资回报率
    private TextView pact;//合同编号
    private TextView bank;//银行账号
    private TextView bankname;//银行名称

    /** 证件照片**/
    private ImageView addimageview1;
    private ImageView addimageview2;
    private ImageView addimageview3;
    private ImageView addimageview4;
    private ImageView addimageview5;
    private ImageView addimageview6;
    private ImageView addimageview7;
    private ImageView addimageview8;
    private ImageView addimageview9;
    private ImageView addimageview10;

    /** 投资信息**/
    private LinearLayout invest;


    private String Scontact;//紧急联系人
    private String Sphone;//紧急联系人电话
    private String Srelation;//关系
    private String Snamed;//称呼
//    private String Sremark;//备注

    private String realPath1 = null;//图片路径
    private String realPath2;//图片路径
    private String realPath3;//图片路径
    private String realPath4;//图片路径
    private String realPath5;//图片路径
    private String realPath6;//图片路径
    private String realPath7;//图片路径
    private String realPath8;//图片路径
    private String realPath9;//图片路径
    private String realPath10;//图片路径

    private String name1;//姓名
    private String tel1;//电话
    private String birthday1;//生日
    private String origine1;//客户来源
    private String sex1;//性别
    private String address1;//地址
    private String certifiyType1;//证件类型
    private String certifiyNO1;//证件号
    private String purpose1;//意向程度
    private String invest1;//投资能力
    private String marriage1;//婚姻状况
    private String email1;//电子邮箱
    private String profession1;//职业

    private String userid;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closeclient_look);
        iniView();//初始化
        gethttp();
//        ImagePth();//图片隐藏显示
    }

    private void iniView() {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        userid = getIntent().getStringExtra("userid");

        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("关单客户资料");
        mCl_name = (TextView) findViewById(R.id.cl_name);
        mCl_sex = (TextView) findViewById(R.id.cl_sex);
        mCl_tel = (TextView) findViewById(R.id.cl_tel);
        mCl_birthday = (TextView) findViewById(R.id.cl_birthday);
        mCl_certifitype = (TextView) findViewById(R.id.cl_certifitype);
        mCl_IDcard = (TextView) findViewById(R.id.cl_IDcard);
        mCl_origine = (TextView) findViewById(R.id.cl_origine);
        mCl_purpose = (TextView) findViewById(R.id.purpose);
//        mCl_invest = (TextView) findViewById(R.id.invest1);
        mCl_marriage = (TextView) findViewById(R.id.marriage);
        mCl_email = (TextView) findViewById(R.id.email);
        mCl_profession = (TextView) findViewById(R.id.profession);
        mCl_address = (TextView) findViewById(R.id.cl_address);

        contact = (TextView) findViewById(R.id.contact);//紧急联系人的姓名
        phone = (TextView) findViewById(R.id.phone);//紧急联系人的电话
        relation = (TextView) findViewById(R.id.relation);//关系
        named = (TextView) findViewById(R.id.named);//称呼
//        remark = (TextView) findViewById(R.id.remark);//备注

        invest = (LinearLayout) findViewById(R.id.invest);//投资信息

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

        back.setOnClickListener(this);
        invest.setOnClickListener(this);
    }

    private void ImagePth() {
        if (realPath1 == null) {
//          addimageview1.setVisibility(View.VISIBLE);//显示
            addimageview1.setVisibility(View.GONE);//隐藏
            addimageview2.setVisibility(View.GONE);//隐藏
            addimageview3.setVisibility(View.GONE);//隐藏
            addimageview4.setVisibility(View.GONE);//隐藏
            addimageview5.setVisibility(View.GONE);//隐藏
            addimageview6.setVisibility(View.GONE);//隐藏
            addimageview7.setVisibility(View.GONE);//隐藏
            addimageview8.setVisibility(View.GONE);//隐藏
            addimageview9.setVisibility(View.GONE);//隐藏
            addimageview10.setVisibility(View.GONE);//隐藏
        }
    }
    public void gethttp() {//获取基本信息的请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                String userid = getIntent().getStringExtra("userid");
                String operid = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","getonecust");
                requestParams.addBodyParameter("Userid",userid);
                requestParams.addBodyParameter("Operid", operid);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Add_UmInformationEntity()));
            }
        }).start();
//        getlink();
    }


    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Add_UmInformationEntity ccl = (Add_UmInformationEntity) object;
        name1 = ccl.getDetailInfo().getUserName();//姓名
        tel1 = ccl.getDetailInfo().getPhone();//移动电话
        birthday1 = ccl.getDetailInfo().getBirthDate();//生日
        origine1 = ccl.getDetailInfo().getOrigin();//客户来源
        sex1 = ccl.getDetailInfo().getUserSex();//性别
        address1 = ccl.getDetailInfo().getAddress();//地址
        certifiyType1 = ccl.getDetailInfo().getCertifiyType() + "";//证件类型
        certifiyNO1 = ccl.getDetailInfo().getCertifiyNO() + "";//证件号码
        purpose1 = ccl.getDetailInfo().getUserGradeName();//意向程度
//        invest1 = ccl.getDetailInfo().getTZNL();//投资能力
        marriage1 = ccl.getDetailInfo().getMarriType();//婚姻状况
        email1 = ccl.getDetailInfo().getEmail();//电子邮箱
        profession1 = ccl.getDetailInfo().getTradeName();//职业

        Scontact = ccl.getDetailInfo().getContactName();//联系人
        Sphone = ccl.getDetailInfo().getContactTel();//联系人电话
        Srelation = ccl.getDetailInfo().getRelaType();//关系
        Snamed = ccl.getDetailInfo().getCall();//称呼
//        Sremark = ccl.getDetailInfo().getRemark();//备注

        mCl_name.setText(name1);
        mCl_tel.setText(tel1);
        mCl_birthday.setText(birthday1);
        if("LY001".equals(origine1)){//客户来源
            mCl_origine.setText("讲座");
        }else if("LY002".equals(origine1)){
            mCl_origine.setText("陌电");

        }else if("LY003".equals(origine1)){
            mCl_origine.setText("发单");

        }else if("LY004".equals(origine1)){
            mCl_origine.setText("答谢会");

        }else if("LY005".equals(origine1)){
            mCl_origine.setText("转介绍");

        }else if("LY006".equals(origine1)){
            mCl_origine.setText("社区活动");

        }else if("LY007".equals(origine1)){
            mCl_origine.setText("渠道");

        }else if("LY008".equals(origine1)){
            mCl_origine.setText("自有资源");

        }else if("LY0011".equals(origine1)){
            mCl_origine.setText("其他途径");
        }

        if("ZJ01".equals(certifiyType1)){
            mCl_certifitype.setText("身份证");
        }else if("ZJ02".equals(certifiyType1)){
            mCl_certifitype.setText("军官证");

        }else if("ZJ03".equals(certifiyType1)){
            mCl_certifitype.setText("驾驶证");

        }else if("ZJ04".equals(certifiyType1)){
            mCl_certifitype.setText("其他");

        }

        if ("XBFL01".equals(sex1)){
            mCl_sex.setText("男");
        }else if ("XBFL02".equals(sex1)){
            mCl_sex.setText("女");
        }
        if ("HY001".equals(marriage1)){
            mCl_marriage.setText("已婚");
        }else if ("HY002".equals(marriage1)){
            mCl_marriage.setText("未婚");
        }else if ("HY003".equals(marriage1)){
            mCl_marriage.setText("离异");
        }else if ("HY004".equals(marriage1)){
            mCl_marriage.setText("垂偶");
        }
        mCl_address.setText(address1);
        mCl_IDcard.setText(certifiyNO1);
        mCl_purpose.setText(purpose1);
//        mCl_invest.setText(invest1);
        mCl_email.setText(email1);
        mCl_profession.setText(profession1);
        contact.setText(Scontact);
        phone.setText(Sphone);
        if ("RYGX01".equals(Srelation)){
            relation.setText("朋友");
        }else if ("RYGX02".equals(Srelation)){
            relation.setText("配偶");
        }
        named.setText(Snamed);
//        remark.setText(Sremark);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(ColseClient_Look.this,"服务器出错了！",Toast.LENGTH_SHORT).show();
    }

//    private void getlink(){//获取紧急联系人的请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String userid = getIntent().getStringExtra("userid");
//                String operid = MyApplication.getInstance().getUserInfo().getUserId();
//                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
//                requestParams.addBodyParameter("action","linklist");
//                requestParams.addBodyParameter("Userid",userid);
//                requestParams.addBodyParameter("Operid", operid);
//                x.http().get(requestParams, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        try {
//                            JSONArray json = new JSONObject(result).getJSONArray("DetailInfo");
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//            }
//        }).start();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.invest:
                Intent intent = new Intent(this,Invest_Avtivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("username", username);
                startActivity(intent);
        }
    }



}
