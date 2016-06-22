package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.Add_UmInformationEntity;
import com.example.hmqqg.hm.entity.InvestEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 投资信息详情
 * Created by bona on 2016/3/19.
 */
public class Invest_Details extends BaseRequestActivity implements View.OnClickListener{

    private ImageView back;
    private TextView title_top_bar;
    /** 投资理财**/
    private TextView product;//投资产品名称
    private TextView mInvest_product_type;//产品类型
    private TextView money;//金额
    private TextView retum;//返利时间
    private TextView mInvest_rebate_period;//返还周期
    private TextView gate;//投资回报率
    private TextView mStart_time;//开始时间
    private TextView mEnd_time;//结时间
    private TextView pact;//合同编号
    private TextView mCl_banknumber;//银行卡号
    private TextView bankname;//开户行
    private TextView mInvest_account_name;//开户名
    private TextView mInvest_bank_information;//银行信息
    private TextView mInvest_sign;//购买年限
    private ImageView mAdd_bankimageview1;//添加银行卡信息1
    private ImageView mAdd_bankimageview2;//添加银行卡信息2
    private ImageView mAdd_idcardimageview1;//添加身份证信息1
    private ImageView mAdd_idcardimageview2;//添加身份证信息2

    private String product1;
    private String mInvest_product_type1;
    private double money1;
    private String retum1;
    private String mInvest_rebate_period1;
    private double gate1;
    private String mStart_time1;
    private String mEnd_time1;
    private String pact1;
    private String mCl_banknumber1;
    private String bankname1;
    private String mInvest_account_name1;
    private String mInvest_bank_information1;
    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private int mInvest_sign1;
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    private String realPath1;//图片路径
    private String realPath2;//图片路径
    private String realPath3;//图片路径
    private String realPath4;//图片路径

    private String investid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invest_main);

        intiView();
        gethttp();
    }


    private void intiView() {
        product = (TextView) findViewById(R.id.cl_product);//投资产品名称
        mInvest_product_type = (TextView) findViewById(R.id.invest_product_type);//产品类型
        money = (TextView) findViewById(R.id.cl_money);//投资金额
        retum = (TextView) findViewById(R.id.cl_retum);//返利时间
        mInvest_rebate_period = (TextView) findViewById(R.id.invest_rebate_period);//返利周期
        gate = (TextView) findViewById(R.id.cl_gate);//投资回报率
        mStart_time = (TextView) findViewById(R.id.start_time);//开始时间
        mEnd_time = (TextView) findViewById(R.id.end_time);//结束时间
        pact = (TextView) findViewById(R.id.cl_pact);//合同编号
        mCl_banknumber = (TextView) findViewById(R.id.cl_banknumber);//银行卡号
        bankname = (TextView) findViewById(R.id.cl_bankname);//银行名称
        mInvest_account_name = (TextView) findViewById(R.id.invest_account_name);//账户所有人
        mInvest_bank_information = (TextView) findViewById(R.id.invest_bank_information);//银行详细
        mInvest_sign = (TextView) findViewById(R.id.invest_sign);//购买年限
        mAdd_bankimageview1 = (ImageView) findViewById(R.id.add_bankimageview1);
        mAdd_bankimageview2 = (ImageView) findViewById(R.id.add_bankimageview2);
        mAdd_idcardimageview1 = (ImageView) findViewById(R.id.add_idcardimageview1);
        mAdd_idcardimageview2 = (ImageView) findViewById(R.id.add_idcardimageview2);

        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);

        title_top_bar.setText("投资详情");
        back.setOnClickListener(this);
        mAdd_bankimageview1.setOnClickListener(this);
        mAdd_bankimageview2.setOnClickListener(this);
        mAdd_idcardimageview1.setOnClickListener(this);
        mAdd_idcardimageview2.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        InvestEntity inv = (InvestEntity) object;
        product1 = inv.getDetailInfo().getInvestName();//产品名称
        mInvest_product_type1 = inv.getDetailInfo().getInvestType();//产品类型
        money1 = inv.getDetailInfo().getInvestSum();//产品金额
        gate1 = inv.getDetailInfo().getInvestRate();//投资回报率
        pact1 = inv.getDetailInfo().getContactNo();//合同编号
        mCl_banknumber1 = inv.getDetailInfo().getBankAccount();//银行卡号
        bankname1 = inv.getDetailInfo().getBankName();//银行名称
        mInvest_account_name1 = (String) inv.getDetailInfo().getBankMaster();//账户所有人
        mInvest_bank_information1 = (String) inv.getDetailInfo().getBankDetail();//银行信息
        retum1 = inv.getDetailInfo().getGetRateDate();//返利时间
        mInvest_rebate_period1 = (String) inv.getDetailInfo().getGetPeriod();//返利周期
        mStart_time1 = inv.getDetailInfo().getStartDate();//开始时间
        mEnd_time1 = inv.getDetailInfo().getEndDate();//结束时间
        mInvest_sign1 = inv.getDetailInfo().getBuyYears();//购买年限
        pic1 = inv.getDetailInfo().getPic1Path();
        pic2 = inv.getDetailInfo().getPic2Path();
        pic3 = inv.getDetailInfo().getPic3Path();
        pic4 = inv.getDetailInfo().getPic4Path();
        String image1 = pic1.replaceAll("\\\\","/");
        String image2 = pic2.replaceAll("\\\\","/");
        String image3 = pic3.replaceAll("\\\\","/");
        String image4 = pic4.replaceAll("\\\\","/");

        product.setText(product1);
        mInvest_product_type.setText(mInvest_product_type1+"");
//        if ("CPLX01".equals(mInvest_product_type1)){
//            mInvest_product_type.setText("收益型");
//        }else if ("CPLX02".equals(mInvest_product_type1)){
//            mInvest_product_type.setText("成长型");
//        }else if ("CPLX03".equals(mInvest_product_type1)){
//            mInvest_product_type.setText("平衡型");
//        }


        money.setText(money1+"");
        gate.setText(gate1+"");
        pact.setText(pact1);
        mCl_banknumber.setText(mCl_banknumber1);
        bankname.setText(bankname1);
        mInvest_account_name.setText(mInvest_account_name1);
        mInvest_bank_information.setText(mInvest_bank_information1);
        retum.setText(retum1);
        if ("HBZQ01".equals(mInvest_rebate_period1)){
            mInvest_rebate_period.setText("周");
        }
        if ("HBZQ02".equals(mInvest_rebate_period1)){
            mInvest_rebate_period.setText("月");
        }
        if ("HBZQ03".equals(mInvest_rebate_period1)){
            mInvest_rebate_period.setText("季");
        }
        if ("HBZQ04".equals(mInvest_rebate_period1)){
            mInvest_rebate_period.setText("半年");
        }
        if ("HBZQ05".equals(mInvest_rebate_period1)){
            mInvest_rebate_period.setText("年");
        }
        mStart_time.setText(mStart_time1);
        mEnd_time.setText(mEnd_time1);
        mInvest_sign.setText(mInvest_sign1+"");
        x.image().bind(mAdd_bankimageview1,getResources().getString(R.string.http_image)+image1);
        x.image().bind(mAdd_bankimageview2,getResources().getString(R.string.http_image)+image2);
        x.image().bind(mAdd_idcardimageview1,getResources().getString(R.string.http_image)+image3);
        x.image().bind(mAdd_idcardimageview2,getResources().getString(R.string.http_image)+image4);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }
    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                investid = intent.getStringExtra("investid");
                String operid = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","getoneinvest");
                requestParams.addBodyParameter("investid",investid);
                requestParams.addBodyParameter("Operid", operid);
                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new InvestEntity()));

            }
        }).start();
    }


    @Override
    public void onClick(View v) {
        final Bitmap myBitmap;
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.add_bankimageview1:
                realPath1 = getResources().getString(R.string.http_image)+image1;
                myBitmap = BitmapFactory.decodeFile(realPath1);
                getBigPicture(myBitmap);
                break;
            case R.id.add_bankimageview2:
                realPath2 = getResources().getString(R.string.http_image)+image2;
                myBitmap = BitmapFactory.decodeFile(realPath2);
                getBigPicture(myBitmap);
                break;
            case R.id.add_idcardimageview1:
                realPath3 = getResources().getString(R.string.http_image)+image3;
                myBitmap = BitmapFactory.decodeFile(realPath3);
                getBigPicture(myBitmap);
                break;
            case R.id.add_idcardimageview2:
                realPath4 = getResources().getString(R.string.http_image)+image4;
                myBitmap = BitmapFactory.decodeFile(realPath4);
                getBigPicture(myBitmap);
                break;
        }
    }
    //点击放大
    private void getBigPicture(Bitmap b) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View imgEntryView = inflater.inflate(R.layout.imagebig, null); // 加载自定义的布局文件
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(Invest_Details.this).create();
        ImageView img = (ImageView) imgEntryView.findViewById(R.id.w);
        if (b != null) {
            Display display = Invest_Details.this.getWindowManager()
                    .getDefaultDisplay();
            int scaleWidth = display.getWidth();
            int height = b.getHeight();// 图片的真实高度
            int width = b.getWidth();// 图片的真实宽度
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) img.getLayoutParams();
            lp.width = scaleWidth;// 调整宽度
            lp.height = (height * scaleWidth) / width;// 调整高度
            img.setLayoutParams(lp);
            img.setImageBitmap(b);
            dialog.setView(imgEntryView); // 自定义dialog
            dialog.show();
        }
        // 点击布局文件（也可以理解为点击大图）后关闭dialog，这里的dialog不需要按钮
        imgEntryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramView) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        });
    }
}
