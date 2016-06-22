package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.view.PercentLinearLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.net.URLDecoder;

/**
 * 外出签到显示图片界面
 * Created by bona on 2016/5/16.
 */
public class My_ApplyOut_show extends BaseActivity implements View.OnClickListener {
    private ImageView back;//返回
    private TextView title_top_bar;//标题
    private TextView mTv_title;//标题
    private TextView mTv_outtype;//外出类型
    private TextView mTv_startime;//开始时间
    private TextView mTv_endtime;//结束时间
    private TextView mTv_days;//时长 天
    private TextView mTv_hours;//时长  小时
    private TextView mTv_absence_cause;//外出事由
    private TextView mTv_absence_idea;//审批意见
    private TextView mTv_name;//审批人
    private TextView mTv_ditu;//地点
    private ImageView mTv_addimageview;//图片

    private String title;
    private String ApprovalType;
    private String Reason;
    private String StartDate;
    private String EndDate;
    private String days;
    private String hours;
    private String flag;
    private String ApprovalID;
    private String imageurl;
    private String address;
    private String str;
    private String name;

    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_show);
        iniView();
        getintent();
    }

    private void iniView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mTv_outtype = (TextView) findViewById(R.id.tv_outtype);
        mTv_startime = (TextView) findViewById(R.id.tv_startime);
        mTv_endtime = (TextView) findViewById(R.id.tv_endtime);
        mTv_days = (TextView) findViewById(R.id.tv_days);
        mTv_hours = (TextView) findViewById(R.id.tv_hours);
        mTv_absence_cause = (TextView) findViewById(R.id.tv_absence_cause);
        mTv_absence_idea = (TextView) findViewById(R.id.tv_absence_idea);
        mTv_name = (TextView) findViewById(R.id.tv_name);
        mTv_ditu = (TextView) findViewById(R.id.ditu);
        mTv_addimageview = (ImageView) findViewById(R.id.addimageview);
        title_top_bar.setText("外出签到");
        back.setOnClickListener(this);
        mTv_addimageview.setOnClickListener(this);
    }
    private void getintent() {
        title = getIntent().getStringExtra("title");
        ApprovalID = getIntent().getStringExtra("ApprovalID");
        ApprovalType = getIntent().getStringExtra("ApprovalType");
        Reason = getIntent().getStringExtra("Reason");
        StartDate = getIntent().getStringExtra("StartDate");
        EndDate = getIntent().getStringExtra("EndDate");
        days = getIntent().getStringExtra("days");
        hours = getIntent().getStringExtra("hours");
        flag = getIntent().getStringExtra("flag");
        imageurl = getIntent().getStringExtra("imageurl");
        address = getIntent().getStringExtra("address");
        mTv_title.setText(title+"");
        mTv_outtype.setText(ApprovalType+"");
        mTv_startime.setText(StartDate+"");
        mTv_endtime.setText(EndDate+"");
        if(("".equals(days)||days==null)&&(!"".equals(hours)||hours!=null)){
            mTv_hours.setText(hours);

        }else if(("".equals(hours)||hours==null)&&(!"".equals(days)||days!=null)){
            mTv_days.setText(days);
        }else if(("".equals(days)||days==null)&&("".equals(hours)||hours==null)){
            mTv_days.setText("");
            mTv_hours.setText("");
        }else {
            mTv_days.setText(days);
            mTv_hours.setText(hours);
        }
        mTv_absence_cause.setText(Reason+"");
        if("".equals(flag)||flag==null){
            mTv_absence_idea.setText("");
        }else if("N".equals(flag)){
            mTv_absence_idea.setTextColor(getResources().getColor(R.color.orange));
            mTv_absence_idea.setText("审核中");
        }else if("Y".equals(flag)){
            mTv_absence_idea.setTextColor(getResources().getColor(R.color.green));
            mTv_absence_idea.setText("同意");
        }else if("R".equals(flag)){
            mTv_absence_idea.setTextColor(getResources().getColor(R.color.red));
            mTv_absence_idea.setText("不同意");
        }
        mTv_ditu.setText(address);
        String struri = imageurl.replaceAll("\\\\","/");
        str = getResources().getString(R.string.http_image)+struri;
        ImageOptions i = new ImageOptions.Builder().setUseMemCache(true).setSize(300,300).build();
        x.image().bind(mTv_addimageview,str,i);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                x.image().bind(mTv_addimageview,str);
//                ImageLoader.getInstance().loadImage(str, new SimpleImageLoadingListener()
//                        {
//                            public void onLoadingComplete(String imageUri, android.view.View view, android.graphics.Bitmap loadedImage) {
//                                loadedImage=toRoundBitmap(loadedImage);
//                                mTv_addimageview.setImageBitmap(loadedImage);
//                            };
//                            public void onLoadingFailed(String imageUri, android.view.View view, com.nostra13.universalimageloader.core.assist.FailReason failReason) {
//                            };
//                            @Override
//                            public void onLoadingStarted(String imageUri, View view) {
//
//                            }
//                            @Override
//                            public void onLoadingCancelled(String imageUri, View view) {
//                            }
//                        }
//                );
//            }
//        }).start();
    }
//    public Bitmap toRoundBitmap(Bitmap bitmap){
//        int width=104;
//        int height=104;
//        int r=0;
//        //取最短边做边长
//        if(width<height){
//            r=width;
//        }else{
//            r=height;
//        }
//        //构建一个bitmap
//        Bitmap backgroundBm=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
//        //new一个Canvas，在backgroundBmp上画图
//        Canvas canvas=new Canvas(backgroundBm);
//        Paint p=new Paint();
//        //设置边缘光滑，去掉锯齿
//        p.setAntiAlias(true);
//        RectF rect=new RectF(0, 0, r, r);
//        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
//        //且都等于r/2时，画出来的圆角矩形就是圆形
//        canvas.drawRoundRect(rect, r/2, r/2, p);
//        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
//        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        //canvas将bitmap画在backgroundBmp上
//        canvas.drawBitmap(bitmap, null, rect, p);
//        return backgroundBm;
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.addimageview://点击查看大图
                if(mTv_addimageview.getDrawable()!=null){
                    mTv_addimageview.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(mTv_addimageview.getDrawingCache());
                    getBigPicture(bitmap);
                    mTv_addimageview.setDrawingCacheEnabled(false);
                }
                break;
        }
    }
    //点击放大
    private void getBigPicture(Bitmap b) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View imgEntryView = inflater.inflate(R.layout.imagebig, null); // 加载自定义的布局文件
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(My_ApplyOut_show.this).create();
        ImageView img = (ImageView) imgEntryView.findViewById(R.id.w);
        if (b != null) {
            Display display = My_ApplyOut_show.this.getWindowManager()
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

    private void progressD() {
        pd = new ProgressDialog(My_ApplyOut_show.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
        pd.setMessage("正在上获取图片,请稍候...");
        pd.show();
    }
}
