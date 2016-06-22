package com.example.hmqqg.hm.ui;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 完善客户资料（添加紧急联系人）
 * Created by bona on 2016/3/4.
 */
public class ImprovementActivity extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;//返回
    private TextView save;//完成
    private TextView title;
    private EditText product;//投资产品
    private EditText money;//金额
    private EditText retum;//利率返还日
    private EditText gate;//投资回报率
    private EditText pact;//合同编号
    private EditText bank;//银行账号
    private EditText bankname;//银行名称
    private EditText contact1;//紧急联系人1
    private EditText phone1;//紧急联系人电话1
    private EditText contact2;//紧急联系人2
    private EditText phone2;//紧急联系人电话2
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

    private String Sproduct;//投资产品
    private String Smoney;//金额
    private String Sretum;//利率返还日
    private String Sgate;//投资回报率
    private String Spact;//合同编号
    private String Sbank;//银行账号
    private String Sbankname;//银行名称

    private String Scontact1;//紧急联系人1
    private String Sphone1;//紧急联系人电话1
    private String Scontact2;//紧急联系人2
    private String Sphone2;//紧急联系人电话2

    private String realPath1;//图片路径
    private String realPath2;//图片路径
    private String realPath3;//图片路径
    private String realPath4;//图片路径
    private String realPath5;//图片路径
    private String realPath6;//图片路径
    private String realPath7;//图片路径
    private String realPath8;//图片路径
    private String realPath9;//图片路径
    private String realPath10;//图片路径

    private int a;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private BufferedInputStream in;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contacts);
        iniview();//初始化
        ImagePth();//隐藏显示
    }



    private void iniview() {
        back = (ImageView) findViewById(R.id.back);//返回键
        save = (TextView) findViewById(R.id.save);//完成保存按钮
        title = (TextView) findViewById(R.id.title_top_bar);//标题
        title.setText("完善客户资料");

        contact1 = (EditText) findViewById(R.id.contact1);//紧急联系人1的姓名
        phone1 = (EditText) findViewById(R.id.phone1);//紧急联系人1的电话
        contact2 = (EditText) findViewById(R.id.contact2);
        phone2 = (EditText) findViewById(R.id.phone2);


        product = (EditText) findViewById(R.id.product);//产品
        money = (EditText) findViewById(R.id.money);//金额
        retum = (EditText) findViewById(R.id.retum);//利率返还日
        gate = (EditText) findViewById(R.id.gate);//投资回报率
        pact = (EditText) findViewById(R.id.pact);//合同编号
        bank = (EditText) findViewById(R.id.bank);//银行账号
        bankname = (EditText) findViewById(R.id.bankname);//银行名称


        addimageview1 = (ImageView) findViewById(R.id.addimageview1);
        addimageview2 = (ImageView) findViewById(R.id.addimageview2);
        addimageview3 = (ImageView) findViewById(R.id.addimageview3);
        addimageview4 = (ImageView) findViewById(R.id.addimageview4);
        addimageview5 = (ImageView) findViewById(R.id.addimageview5);
        addimageview6 = (ImageView) findViewById(R.id.addimageview6);
        addimageview7 = (ImageView) findViewById(R.id.addimageview7);
        addimageview8 = (ImageView) findViewById(R.id.addimageview8);
        addimageview9 = (ImageView) findViewById(R.id.addimageview9);
        addimageview10 = (ImageView) findViewById(R.id.addimageview10);

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        addimageview1.setOnClickListener(this);
        addimageview2.setOnClickListener(this);
        addimageview3.setOnClickListener(this);
        addimageview4.setOnClickListener(this);
        addimageview5.setOnClickListener(this);
        addimageview6.setOnClickListener(this);
        addimageview7.setOnClickListener(this);
        addimageview8.setOnClickListener(this);
        addimageview9.setOnClickListener(this);
        addimageview10.setOnClickListener(this);
    }
    private void ImagePth() {
        if (realPath1 == null) {
//          addimageview1.setVisibility(View.VISIBLE);//显示
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
    @Override
    public void onClick(View v) {
        final Bitmap myBitmap;
        switch(v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.save://完成保存
                getsave();
                getsave1();
                break;
            case R.id.addimageview1:
                myBitmap = BitmapFactory.decodeFile(realPath1);
                if(realPath1!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 1;
                    getImageView();
                }
                break;
            case R.id.addimageview2:
                myBitmap = BitmapFactory.decodeFile(realPath2);

                if(realPath2!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 2;
                    getImageView();
                }
                break;
            case R.id.addimageview3:
                myBitmap = BitmapFactory.decodeFile(realPath3);

                if(realPath3!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 3;
                    getImageView();
                }
                break;
            case R.id.addimageview4:
                myBitmap = BitmapFactory.decodeFile(realPath4);

                if(realPath4!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 4;
                    getImageView();
                }
                break;
            case R.id.addimageview5:
                myBitmap = BitmapFactory.decodeFile(realPath5);

                if(realPath5!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 5;
                    getImageView();
                }
                break;
            case R.id.addimageview6:
                myBitmap = BitmapFactory.decodeFile(realPath6);

                if(realPath6!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 6;
                    getImageView();
                }
                break;
            case R.id.addimageview7:
                myBitmap = BitmapFactory.decodeFile(realPath7);

                if(realPath7!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 7;
                    getImageView();
                }
                break;
            case R.id.addimageview8:
                myBitmap = BitmapFactory.decodeFile(realPath8);

                if(realPath8!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 8;
                    getImageView();
                }
                break;
            case R.id.addimageview9:
                myBitmap = BitmapFactory.decodeFile(realPath9);

                if(realPath9!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 9;
                    getImageView();
                }
                break;
            case R.id.addimageview10:
                myBitmap = BitmapFactory.decodeFile(realPath10);

                if(realPath10!=null){
                    getBigPicture(myBitmap);
                }else{
                    a = 10;
                    getImageView();
                }
                break;

        }
    }



    private void getBigPicture(Bitmap b) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View imgEntryView = inflater.inflate(R.layout.imagebig, null); // 加载自定义的布局文件
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(ImprovementActivity.this).create();
        ImageView img = (ImageView) imgEntryView.findViewById(R.id.w);
        if (b != null) {
            Display display = ImprovementActivity.this.getWindowManager()
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

    //点击触发弹框
    public void getImageView() {
        final CharSequence[] items = {"照相"};
        android.app.AlertDialog dlg = new android.app.AlertDialog.Builder(
                ImprovementActivity.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        // 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法

                        if (item == 0) {
                            takepicture();

                        } else {
                            Log.e("cramer", "请确认已经插入SD卡");
                        }
                    }

                }).create();
        dlg.show();
    }

    public void takepicture() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File outDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
            if (a == 1) {
                realPath1 = outFile.getAbsolutePath();
            }
            if (a == 2) {
                realPath2 = outFile.getAbsolutePath();
            }
            if (a == 3) {
                realPath3 = outFile.getAbsolutePath();
            }
            if (a == 4) {
                realPath4 = outFile.getAbsolutePath();
            }
            if (a == 5) {
                realPath5 = outFile.getAbsolutePath();
            }
            if (a == 6) {
                realPath6 = outFile.getAbsolutePath();
            }
            if (a == 7) {
                realPath7 = outFile.getAbsolutePath();
            }
            if (a == 8) {
                realPath8 = outFile.getAbsolutePath();
            }
            if (a == 9) {
                realPath9 = outFile.getAbsolutePath();
            }
            if (a == 10) {
                realPath10 = outFile.getAbsolutePath();
            }

            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Log.e("cramer", "请确认已经插入SD卡");
        }
    }

    /**
     * 接受从LocationActivit中传过来的poiInfo(name,address)
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Outside_Activity.TwoNextPage && resultCode == RESULT_OK) {
            setResult(RESULT_OK, intent);
            finish();
            return;
        }
        /**
         * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
         * 所以为了区别到底选择了那个方式获取图片要进行判断，
         * 这里的requestCode跟startActivityForResult里面第二个参数对应
         */

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                //Log.e(tag, "获取图片成功，path="+realPath);
                if (a == 1) {
                    setImageView(realPath1, addimageview1.getWidth(), addimageview1.getHeight());
                    addimageview2.setVisibility(View.VISIBLE);
                }
                if (a == 2) {
                    setImageView(realPath2, addimageview2.getWidth(), addimageview2.getHeight());
                    addimageview3.setVisibility(View.VISIBLE);
                }
                if (a == 3) {
                    setImageView(realPath3, addimageview3.getWidth(), addimageview3.getHeight());
                    addimageview4.setVisibility(View.VISIBLE);
                }
                if (a == 4) {
                    setImageView(realPath4, addimageview4.getWidth(), addimageview4.getHeight());
                    addimageview5.setVisibility(View.VISIBLE);
                }
                if (a == 5) {
                    setImageView(realPath5, addimageview5.getWidth(), addimageview5.getHeight());
                    addimageview6.setVisibility(View.VISIBLE);
                }
                if (a == 6) {
                    setImageView(realPath6, addimageview6.getWidth(), addimageview6.getHeight());
                    addimageview7.setVisibility(View.VISIBLE);
                }
                if (a == 7) {
                    setImageView(realPath7, addimageview7.getWidth(), addimageview7.getHeight());
                    addimageview8.setVisibility(View.VISIBLE);
                }
                if (a == 8) {
                    setImageView(realPath8, addimageview8.getWidth(), addimageview8.getHeight());
                    addimageview9.setVisibility(View.VISIBLE);
                }
                if (a == 9) {
                    setImageView(realPath9, addimageview9.getWidth(), addimageview9.getHeight());
                    addimageview10.setVisibility(View.VISIBLE);
                }
                if (a == 10) {
                    setImageView(realPath10, addimageview10.getWidth(), addimageview10.getHeight());
                }

            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
            } else {
                // 图像捕获失败，提示用户
                //Log.e(tag, "拍照失败");
            }
        }
    }

    private void setImageView(String realPath, int widthx, int heightx) {
        try {
            in = new BufferedInputStream(
                    new FileInputStream(new File(realPath)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 加载图像尺寸而不是图像本身
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true; // bitmap为null
        bmpFactoryOptions.outHeight = heightx;
        bmpFactoryOptions.outWidth = widthx;
        bmpFactoryOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;
        bmpFactoryOptions.inJustDecodeBounds = false;
        bmpFactoryOptions.inPurgeable = true;
        bmpFactoryOptions.inInputShareable = true;
        Bitmap bitmap = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, widthx, heightx);
        if (bitmap != null) {
            if (a == 1) {
                //把bitmap转成圆形
                addimageview1.setImageBitmap(bitmap);
            }
            if (a == 2) {
                addimageview2.setImageBitmap(bitmap);
            }
            if (a == 3) {
                addimageview3.setImageBitmap(bitmap);
            }
            if (a == 4) {
                addimageview4.setImageBitmap(bitmap);
            }
            if (a == 5) {
                addimageview5.setImageBitmap(bitmap);
            }
            if (a == 6) {
                addimageview6.setImageBitmap(bitmap);
            }
            if (a == 7) {
                addimageview7.setImageBitmap(bitmap);
            }
            if (a == 8) {
                addimageview8.setImageBitmap(bitmap);
            }
            if (a == 9) {
                addimageview9.setImageBitmap(bitmap);
            }
            if (a == 10) {
                addimageview10.setImageBitmap(bitmap);
            }
        }
    }

    public void getsave() {
        Intent in = getIntent();
        final String userid = in.getStringExtra("userid");
        final String username = in.getStringExtra("username");
        Scontact1 = contact1.getText().toString();
        Sphone1 = phone1.getText().toString();
        Scontact2 = contact2.getText().toString();
        Sphone2 = phone2.getText().toString();

        Sproduct = product.getText().toString();
        Smoney = money.getText().toString();
        Sretum = retum.getText().toString();
        if(Scontact1==null||Scontact1.equals("")){
            Toast.makeText(this,"请输入紧急联系人",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Sphone1==null||Sphone1.equals("")){
            Toast.makeText(this,"请输入紧急联系人手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isMobileNO(Sphone1)){
            Toast.makeText(this,"请输入正确电话格式",Toast.LENGTH_SHORT).show();
            return;
        }

//        if(Sproduct==null||Sproduct.equals("")){
//            Toast.makeText(this,"请输入投资产品",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if(Smoney==null||Smoney.equals("")){
//            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if(Sretum==null||Sretum.equals("")){
//            Toast.makeText(this,"请输入利率返还日",Toast.LENGTH_SHORT).show();
//            return;
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","linkadd" );
                requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());//id
                requestParams.addBodyParameter("userId", userid);
                requestParams.addBodyParameter("username", username);
                requestParams.addBodyParameter("manName", Scontact1);
                requestParams.addBodyParameter("moveTel", Sphone1);
                x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new LoginEntity()));
            }
        }).start();

    }
    private void getsave1() {
        Intent in = getIntent();
        final String userid = in.getStringExtra("userid");
        final String username = in.getStringExtra("username");
        Sproduct = product.getText().toString();
        Smoney = money.getText().toString();
        Sretum = retum.getText().toString();
        Sgate = gate.getText().toString();
        Spact = pact.getText().toString();
        Sbank = bank.getText().toString();
        Sbankname = bankname.getText().toString();
        if(Sproduct==null||Sproduct.equals("")){
            Toast.makeText(this,"请输入投资产品",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Smoney==null||Smoney.equals("")){
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Sretum==null||Sretum.equals("")){
            Toast.makeText(this,"请输入利率返还日",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Sgate==null||Sgate.equals("")){
            Toast.makeText(this,"请输入投资回报率",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Spact==null||Spact.equals("")){
            Toast.makeText(this,"请输入合同编号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Sbank==null||Sbank.equals("")){
            Toast.makeText(this,"请输入银行卡号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Sbankname==null||Sbank.equals("")){
            Toast.makeText(this,"请输入银行名称",Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","Investadd" );
                requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());//id
                requestParams.addBodyParameter("userId", userid);
                requestParams.addBodyParameter("username", username);
                requestParams.addBodyParameter("InvestName", Sproduct);//产品名称
                requestParams.addBodyParameter("InvestSum", Smoney);//投资金额
                requestParams.addBodyParameter("InvestRate", Sgate);//投资回报率
                requestParams.addBodyParameter("ContactNo", Spact);//合同编号
                requestParams.addBodyParameter("BankAccount", Sbank);//银行账号
                requestParams.addBodyParameter("BankName", Sbankname);//银行名称
                requestParams.addBodyParameter("GetRateDate", Sretum);//利率返还日
                x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new LoginEntity()));
            }
        }).start();
    }



    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        LoginEntity l = (LoginEntity) object;
        String str = l.getStatus().get(0).getStaval();
        if(str.equals("1")){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }
    public boolean isMobileNO(String mobiles) {
        String xxx = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(xxx);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
}
