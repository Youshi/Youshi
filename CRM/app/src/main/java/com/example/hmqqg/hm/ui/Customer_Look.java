package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.Add_UmInformationEntity;
import com.example.hmqqg.hm.entity.DeptDetailEntity;
import com.example.hmqqg.hm.entity.LinkmanEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 查看客户资料
 * Created by Administrator on 2016/1/11.
 */
public class Customer_Look extends BaseRequestActivity implements View.OnClickListener{
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

    private TextView contact;//联系人
    private TextView phone;//联系人电话
    private TextView relation;//和客户的关系
    private TextView named;//称呼
    private LinearLayout add;
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
    private String marriage1;
    private String email1;
    private String profession1;

    private String contact1;//紧急联系人
    private String phone1;//紧急联系人电话
    private String relation1;//与客户的关系
    private String named1;//称呼

    private String userid;
    private String username;
    int a =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_look);
        initView();
        gethttp();
        userid = getIntent().getStringExtra("userid");
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
            Add_UmInformationEntity add = (Add_UmInformationEntity) object;
            name1 = add.getDetailInfo().getUserName();//姓名
            tel1 = add.getDetailInfo().getPhone();//移动电话
            birthday1 = add.getDetailInfo().getBirthDate();//生日
            origine1 = add.getDetailInfo().getOrigin();//客户来源
            sex1 = add.getDetailInfo().getUserSex();//性别
            address1 = add.getDetailInfo().getAddress();//地址
            certifiyType1 = add.getDetailInfo().getCertifiyType() + "";//证件类型
            certifiyNO1 = add.getDetailInfo().getCertifiyNO() + "";//证件号码
            purpose1 = add.getDetailInfo().getUserGradeName();//意向程度
            marriage1 = add.getDetailInfo().getMarriType();//婚姻状况
            email1 = add.getDetailInfo().getEmail();//电子邮箱
            profession1 = add.getDetailInfo().getTradeName();//职业
            contact1 = add.getDetailInfo().getContactName();//联系人
            phone1 = add.getDetailInfo().getContactTel();//联系人电话
            relation1 = add.getDetailInfo().getRelaType();//关系
            named1 = add.getDetailInfo().getCall();//称呼
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

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
    }

    private void initView() {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        add = (LinearLayout) findViewById(R.id.add);
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

        contact = (TextView) findViewById(R.id.contact);
        phone = (TextView) findViewById(R.id.phone);
        relation = (TextView) findViewById(R.id.relation);
        named = (TextView) findViewById(R.id.named);

        back.setOnClickListener(this);
        add.setOnClickListener(this);
        title_top_bar.setText("客户资料");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back://返回
                onBackPressed();
                break;
            case R.id.add://添加客户投资信息
                Intent intent = new Intent(this,Add_Invest_InformationActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("username", username);
                startActivity(intent);
                break;
        }
    }

    public void gethttp() {
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
    }
}
