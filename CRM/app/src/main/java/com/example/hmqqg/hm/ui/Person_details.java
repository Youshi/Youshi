package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.PersonEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 显示修改名片界面
 * Created by Android-Wq on 2016/1/22.
 */
public class Person_details extends BaseRequestActivity implements View.OnClickListener {
    private ImageView back;//返回
    private TextView title_top_bar;
    private ImageView mIv_head;
    private TextView mUsername;
    private TextView mNeed;
    private TextView mEmail;
    private TextView mPhone;
    private TextView mQq;
    private TextView refresh;
//    private TextView mWeixin;
    private TextView mAddress;
    private LinearLayout logout;//修改密码

    private String name;
    private String Sex;
    private String Email;
    private String Mobile;
    private String Qq;
    private String Address;
    private String image;
    private String str;
    // End Of Content View Elements


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back.setOnClickListener(this);
        title_top_bar.setText("个人信息");
        bindViews();
        getdetails();

    }
    private void bindViews() {

        mIv_head = (ImageView) findViewById(R.id.portrait);
        mUsername = (TextView) findViewById(R.id.username);
        mNeed = (TextView) findViewById(R.id.need);
        mEmail = (TextView) findViewById(R.id.email);
        mPhone = (TextView) findViewById(R.id.phone);
        refresh = (TextView) findViewById(R.id.refresh);
        refresh.setText("修改");
        mQq = (TextView) findViewById(R.id.qq);
//        mWeixin = (TextView) findViewById(R.id.weixin);
        mAddress = (TextView) findViewById(R.id.address);
        logout = (LinearLayout) findViewById(R.id.logout);
        logout.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {

        PersonEntity pe = (PersonEntity) object;
         name = pe.getDetailInfo().get(0).getUserName().toString();
         Sex = pe.getDetailInfo().get(0).getSex().toString();
         Email = pe.getDetailInfo().get(0).getEmail().toString();
         Mobile = pe.getDetailInfo().get(0).getMobile().toString();
         Qq = pe.getDetailInfo().get(0).getQq().toString();
         Address = pe.getDetailInfo().get(0).getAddress().toString();
        mUsername.setText(pe.getDetailInfo().get(0).getUserName().toString()+"");//姓名
        mNeed.setText(pe.getDetailInfo().get(0).getSex().toString()+"");//性别
        mEmail.setText(pe.getDetailInfo().get(0).getEmail().toString()+"");//邮箱
        mPhone.setText(pe.getDetailInfo().get(0).getMobile().toString()+"");//手机
        mQq.setText(pe.getDetailInfo().get(0).getQq().toString()+"");//qq
        mAddress.setText(pe.getDetailInfo().get(0).getAddress().toString()+"");//地址
        str = pe.getDetailInfo().get(0).getHeadimg();
        image = getResources().getString(R.string.http_image)+str;
        ImageOptions imageoption = new ImageOptions.Builder().setUseMemCache(true).setCircular(true).setSize(200,200).build();
        x.image().bind(mIv_head,image,imageoption);
    }
    public Bitmap toRoundBitmap(Bitmap bitmap){
        int width=104;
        int height=104;
        int r=0;
        //取最短边做边长
        if(width<height){
            r=width;
        }else{
            r=height;
        }
        //构建一个bitmap
        Bitmap backgroundBm=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas=new Canvas(backgroundBm);
        Paint p=new Paint();
        //设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect=new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, p);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);
        return backgroundBm;
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(Person_details.this,R.string.ToastString,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.logout:
                Intent intent = new Intent(this,Password_Activity.class);
                startActivity(intent);
                break;
            case R.id.refresh:
                Intent intent1 = new Intent(this,personedtils.class);
                intent1.putExtra("name",name);
                intent1.putExtra("Sex",Sex);
                intent1.putExtra("Email",Email);
                intent1.putExtra("Mobile",Mobile);
                intent1.putExtra("Qq",Qq);
                intent1.putExtra("Address",Address);
                intent1.putExtra("str",str);
                startActivity(intent1);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getdetails();
    }

    public void getdetails() {
        RequestParams params = new RequestParams(getResources().getString(R.string.http_service));
        params.addBodyParameter("action","details");
        params.addBodyParameter("userid", MyApplication.getInstance().getUserInfo().getUserId().toString());
        x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new PersonEntity()));
    }
}
