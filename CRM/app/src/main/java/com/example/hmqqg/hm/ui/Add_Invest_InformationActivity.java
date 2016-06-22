package com.example.hmqqg.hm.ui;

import android.app.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.Add_invest;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 添加投资信息界面
 * Created by baotao on 2016/3/28.
 */
public class Add_Invest_InformationActivity extends BaseRequestActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private ImageView back;
    private TextView title_top_bar;
    private TextView refresh;

    private EditText mCl_product;//基金名称
    private Spinner mInvest_product_type;//基金类型
    private EditText mCl_money;//投资金额
    private EditText mInvest_date;//投资日期
    private TextView mCl_retum;//返利时间
    private Spinner mInvest_rebate_period;//返利周期
    private EditText mCl_gate;//回报率
    private TextView mStart_time;//开始时间
    private TextView mEnd_time;//结束时间
    private EditText mCl_pact;//合同编号
    private EditText mCl_banknumber;//银行帐号
    private EditText mCl_bankname;//银行名称
    private EditText mInvest_account_name;//账户所有人
    private EditText mInvest_bank_information;//银行详细
    private EditText mInvest_sign;//购买年限
    private ImageView mAdd_bankimageview1;//添加银行卡照片1
    private ImageView mAdd_bankimageview2;//
    private ImageView mAdd_idcardimageview1;//添加身份证照片
    private ImageView mAdd_idcardimageview2;//

    private String realPath1;//图片路径
    private String realPath2;//图片路径
    private String realPath3;//图片路径
    private String realPath4;//图片路径

    private Intent intent;
    private int a;
    private BufferedInputStream in;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private String userid;//客户编号
    private String username;//客户姓名
    private String type;//基金类型
    private String rebate;//返利周期
    String istime = "[0-9]{4}-[0-9]{2}-[0-9]{2}";//时间格式
    private String image1;
    private String image2;
    private String image3;
    private String image4;

    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;

    private ProgressDialog pd;
    private boolean progressShow;

    private DatePicker datePicker;
    private AlertDialog ad;
    private String sTime;
    private String initDateTime;
    private String starttime;
    private String endtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_invest_information);
        iniView();
    }

    private void iniView() {
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        username = intent.getStringExtra("username");
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        refresh = (TextView) findViewById(R.id.refresh);

        title_top_bar.setText("添加投资信息");
        back.setOnClickListener(this);
        mCl_product = (EditText) findViewById(R.id.cl_product);
        mInvest_product_type = (Spinner) findViewById(R.id.invest_product_type);
        mCl_money = (EditText) findViewById(R.id.cl_money);
        mCl_retum = (TextView) findViewById(R.id.cl_retum);
        mInvest_rebate_period = (Spinner) findViewById(R.id.invest_rebate_period);
        mCl_gate = (EditText) findViewById(R.id.cl_gate);
        mStart_time = (TextView) findViewById(R.id.start_time);
        mEnd_time = (TextView) findViewById(R.id.end_time);
        mCl_pact = (EditText) findViewById(R.id.cl_pact);
        mCl_banknumber = (EditText) findViewById(R.id.cl_banknumber);
        mCl_bankname = (EditText) findViewById(R.id.cl_bankname);
        mInvest_account_name = (EditText) findViewById(R.id.invest_account_name);
        mInvest_bank_information = (EditText) findViewById(R.id.invest_bank_information);
        mInvest_sign = (EditText) findViewById(R.id.invest_sign);
        mAdd_bankimageview1 = (ImageView) findViewById(R.id.add_bankimageview1);
        mAdd_bankimageview2 = (ImageView) findViewById(R.id.add_bankimageview2);
        mAdd_idcardimageview1 = (ImageView) findViewById(R.id.add_idcardimageview1);
        mAdd_idcardimageview2 = (ImageView) findViewById(R.id.add_idcardimageview2);

        mAdd_bankimageview1.setVisibility(View.VISIBLE);
        mAdd_bankimageview2.setVisibility(View.VISIBLE);
        mAdd_idcardimageview1.setVisibility(View.VISIBLE);
        mAdd_idcardimageview2.setVisibility(View.VISIBLE);

        refresh.setOnClickListener(this);
        mAdd_bankimageview1.setOnClickListener(this);
        mAdd_bankimageview2.setOnClickListener(this);
        mAdd_idcardimageview1.setOnClickListener(this);
        mAdd_idcardimageview2.setOnClickListener(this);
        mStart_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimePicKDialog(mStart_time);
            }
        });
        mEnd_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimePicKDialog(mEnd_time);
            }
        });
        mCl_retum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimePicKDialog(mCl_retum);

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.add_bankimageview1:
                    a = 1;
                    getImageView();
                break;
            case R.id.add_bankimageview2:
                    a = 2;
                    getImageView();
                break;
            case R.id.add_idcardimageview1:
                    a = 3;
                    getImageView();
                break;
            case R.id.add_idcardimageview2:
                    a = 4;
                    getImageView();
                break;
            case R.id.refresh://完成

                if (mCl_product.getText().toString() == null || "".equals(mCl_product.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写基金名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCl_money.getText().toString() == null || "".equals(mCl_money.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写基金金额", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mCl_retum.getText().toString() == null ||"".equals(mCl_retum.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写返利时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCl_gate.getText().toString() == null || "".equals(mCl_gate.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写回报率", Toast.LENGTH_SHORT).show();
                    return;
                }else if((mCl_gate.getText().toString()).indexOf("%")>=0){
                    Toast.makeText(Add_Invest_InformationActivity.this, "回报率不需要填写%", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mStart_time.getText().toString() == null || "".equals(mStart_time.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写开始时间", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!mStart_time.getText().toString().matches(istime)) {
                    Toast.makeText(this, "请输入正确的时间日期格式", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mEnd_time.getText().toString() == null || "".equals(mEnd_time.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写开始时间", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!mEnd_time.getText().toString().matches(istime)) {
                    Toast.makeText(this, "请输入正确的时间日期格式", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCl_pact.getText().toString() == null ||"".equals(mCl_pact.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写合同编号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCl_banknumber.getText().toString() == null || "".equals(mCl_banknumber.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写银行账户", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mCl_bankname.getText().toString() == null || "".equals(mCl_bankname.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写银行名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mInvest_account_name.getText().toString() == null || "".equals(mInvest_account_name.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写账户所有人", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mInvest_bank_information.getText().toString() == null || "".equals(mInvest_bank_information.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写银行所有人", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mInvest_sign.getText().toString() == null || "".equals(mInvest_sign.getText().toString())) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请填写购买年限", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (realPath1 == null||"".equals(realPath1)) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请上传银行卡照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (realPath2 == null||"".equals(realPath2)) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请上传银行卡照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (realPath3 == null||"".equals(realPath3)) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请上传身份证照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (realPath4 == null||"".equals(realPath4)) {
                    Toast.makeText(Add_Invest_InformationActivity.this, "请上传身份证照片", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ("收益型".equals(mInvest_product_type.getSelectedItem().toString()) ) {
                    type = "CPLX01";
                }else if("成长型".equals(mInvest_product_type.getSelectedItem().toString())) {
                    type = "CPLX02";
                }else if("平衡型".equals(mInvest_product_type.getSelectedItem().toString()) ) {
                    type = "CPLX03";
                }
                if (mInvest_rebate_period.getSelectedItem().toString().equals("周")) {
                    rebate = "HBZQ01";
                }
                if (mInvest_rebate_period.getSelectedItem().toString().equals("月")) {
                    rebate = "HBZQ02";
                }
                if (mInvest_rebate_period.getSelectedItem().toString().equals("季")) {
                    rebate = "HBZQ03";
                }
                if (mInvest_rebate_period.getSelectedItem().toString().equals("周")) {
                    rebate = "HBZQ04";
                }
                if (mInvest_rebate_period.getSelectedItem().toString().equals("周")) {
                    rebate = "HBZQ05";
                }

                gethttp();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Add_invest ai = (Add_invest) object;
        if ("0".equals(ai.getStatus().get(0).getStaval())) {
            progressShow = false;
            pd.dismiss();
            Toast.makeText(Add_Invest_InformationActivity.this,"添加投资信息失败", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressShow = false;
            pd.dismiss();
            Toast.makeText(Add_Invest_InformationActivity.this, "添加投资信息成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        progressShow = false;
        pd.dismiss();
        Toast.makeText(Add_Invest_InformationActivity.this,getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();
    }

    public String bitmaptoString(Bitmap bitmap) {
        //将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }
    private void progressD() {
        pd = new ProgressDialog(Add_Invest_InformationActivity.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
        pd.setMessage("正在上传,请稍候...");
        pd.show();
    }

    public void gethttp() {
        progressD();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams =
                        new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action", "investadd");
                requestParams.addBodyParameter("Operid", MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("userid", userid);
                requestParams.addBodyParameter("username", username);
                requestParams.addBodyParameter("InvestName", mCl_product.getText().toString());
                requestParams.addBodyParameter("InvestType", type);
                requestParams.addBodyParameter("InvestSum", mCl_money.getText().toString());
                requestParams.addBodyParameter("INvestRate", mCl_gate.getText().toString());
                requestParams.addBodyParameter("ContactNo", mCl_pact.getText().toString());
                requestParams.addBodyParameter("BankAccount", mCl_banknumber.getText().toString());
                requestParams.addBodyParameter("BankName", mCl_bankname.getText().toString());
                requestParams.addBodyParameter("BankMaster", mInvest_account_name.getText().toString());
                requestParams.addBodyParameter("BankDetail", mInvest_bank_information.getText().toString());
                requestParams.addBodyParameter("BuyYears", mInvest_sign.getText().toString());
                requestParams.addBodyParameter("StartDate", mStart_time.getText().toString());
                requestParams.addBodyParameter("EndDate", mEnd_time.getText().toString());
                requestParams.addBodyParameter("GetRateDate", mCl_retum.getText().toString());
                requestParams.addBodyParameter("GetPeriod", rebate);
                requestParams.addBodyParameter("file1", bitmaptoString(bitmap1));
                requestParams.addBodyParameter("file2", bitmaptoString(bitmap2));
                requestParams.addBodyParameter("file3", bitmaptoString(bitmap3));
                requestParams.addBodyParameter("file4", bitmaptoString(bitmap4));
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (result == null||"".equals(result)){
                            pd.dismiss();
                            Toast.makeText(Add_Invest_InformationActivity.this,"空",Toast.LENGTH_LONG).show();
                            return;
                        }
                        Gson gson = new Gson();
                        Add_invest addjob =gson.fromJson(result,Add_invest.class);
                        pd.dismiss();
                        try {
                            String stav = addjob.getStatus().get(0).getStaval();
                            if("1".equals(stav)){
                                Toast.makeText(Add_Invest_InformationActivity.this, "添加投资信息成功", Toast.LENGTH_LONG).show();
                                finish();
                            }else{
                                Toast.makeText(Add_Invest_InformationActivity.this, "网络不稳定，请稍后重试", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
                        Toast.makeText(Add_Invest_InformationActivity.this, "网络不稳定，请稍后", Toast.LENGTH_LONG).show();

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

    //点击触发弹框
    public void getImageView() {
        final CharSequence[] items = {"照相", "相册"};
        android.app.AlertDialog dlg = new android.app.AlertDialog.Builder(
                Add_Invest_InformationActivity.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        // 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
                        if (item == 0) {
                            takepicture();
                        }
                        if (item == 1) {
                            takepicture2();
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
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Log.e("cramer", "请确认已经插入SD卡");
        }
    }

    private void takepicture2() {
//        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
//        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
//        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//            startActivityForResult(pickIntent, 2);
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"), 2);
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
        /**
         * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
         * 所以为了区别到底选择了那个方式获取图片要进行判断，
         * 这里的requestCode跟startActivityForResult里面第二个参数对应
         */
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE&&resultCode == RESULT_OK) {
            //Log.e(tag, "获取图片成功，path="+realPath);
            if (a == 1) {
                setImageView(realPath1, mAdd_bankimageview1.getWidth(), mAdd_bankimageview1.getHeight());
                mAdd_bankimageview2.setVisibility(View.VISIBLE);
            }
            if (a == 2) {
                setImageView(realPath2, mAdd_bankimageview2.getWidth(), mAdd_bankimageview2.getHeight());
            }
            if (a == 3) {
                setImageView(realPath3, mAdd_idcardimageview1.getWidth(), mAdd_idcardimageview1.getHeight());
                mAdd_idcardimageview2.setVisibility(View.VISIBLE);
            }
            if (a == 4) {
                setImageView(realPath4, mAdd_idcardimageview2.getWidth(), mAdd_idcardimageview2.getHeight());
            }

        } else if (resultCode == RESULT_CANCELED) {
            // 用户取消了图像捕获
        } else {
            // 图像捕获失败，提示用户
            //Log.e(tag, "拍照失败");
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                if (a == 1) {
                    realPath1 = getRealPathFromURI(uri);
                    //Log.e(tag, "获取图片成功，path=" + realPath);
                    setImageView(realPath1, mAdd_bankimageview1.getWidth(), mAdd_bankimageview1.getHeight());
                }
                if (a == 2) {
                    realPath2 = getRealPathFromURI(uri);
                    //Log.e(tag, "获取图片成功，path=" + realPath);
                    setImageView(realPath2, mAdd_bankimageview2.getWidth(), mAdd_bankimageview2.getHeight());
                }
                if (a == 3) {
                    realPath3 = getRealPathFromURI(uri);
                    //Log.e(tag, "获取图片成功，path=" + realPath);
                    setImageView(realPath3, mAdd_idcardimageview1.getWidth(), mAdd_idcardimageview1.getHeight());
                }
                if (a == 4) {
                    realPath4 = getRealPathFromURI(uri);
                    //Log.e(tag, "获取图片成功，path=" + realPath);
                    setImageView(realPath4, mAdd_idcardimageview2.getWidth(), mAdd_idcardimageview2.getHeight());
                }

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
        if (a == 1) {
            bitmap1 = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
            bitmap1 = ThumbnailUtils.extractThumbnail(bitmap1, widthx, heightx);
            //把bitmap转成圆形
            mAdd_bankimageview1.setImageBitmap(bitmap1);
        }
        if (a == 2) {
            bitmap2 = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
            bitmap2 = ThumbnailUtils.extractThumbnail(bitmap2, widthx, heightx);
            mAdd_bankimageview2.setImageBitmap(bitmap2);
        }
        if (a == 3) {
            bitmap3 = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
            bitmap3 = ThumbnailUtils.extractThumbnail(bitmap3, widthx, heightx);
            mAdd_idcardimageview1.setImageBitmap(bitmap3);
        }
        if (a == 4) {
            bitmap4 = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
            bitmap4 = ThumbnailUtils.extractThumbnail(bitmap4, widthx, heightx);
            mAdd_idcardimageview2.setImageBitmap(bitmap4);
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            // Do not call Cursor.close() on a cursor obtained using this
            // method,
            // because the activity will do that for you at the appropriate time
            Cursor cursor = this.managedQuery(contentUri, proj, null, null,
                    null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return contentUri.getPath();
        }
    }

    //点击放大
    private void getBigPicture(Bitmap b) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View imgEntryView = inflater.inflate(R.layout.imagebig, null); // 加载自定义的布局文件
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(Add_Invest_InformationActivity.this).create();
        ImageView img = (ImageView) imgEntryView.findViewById(R.id.w);
        if (b != null) {
            Display display = Add_Invest_InformationActivity.this.getWindowManager()
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

    public android.app.AlertDialog dateTimePicKDialog(final TextView inputDate) {
        LinearLayout dateTimeLayout = (LinearLayout) Add_Invest_InformationActivity.this
                .getLayoutInflater().inflate(R.layout.common_date, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        init(datePicker, null);

        ad = new android.app.AlertDialog.Builder(Add_Invest_InformationActivity.this)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText(sTime);
                        gettime();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText("");
                    }
                }).show();
        onDateChanged(null, 0, 0, 0);
        return ad;
    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            calendar = this.getCalendarByInintData(initDateTime);
        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "年"
                    + calendar.get(Calendar.MONTH) + "月"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "日"
            ;
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), Add_Invest_InformationActivity.this);
    }

    private Calendar getCalendarByInintData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();

        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
        String date = spliteString(initDateTime, "日", "index", "front"); // 日期
        String time = spliteString(initDateTime, "日", "index", "back"); // 时间

        String yearStr = spliteString(date, "年", "index", "front"); // 年份
        String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

        String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日
        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue();
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        calendar.set(currentYear, currentMonth, currentDay);
        return calendar;
    }

    public static String spliteString(String srcStr, String pattern,
                                      String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        sTime = sdf.format(calendar.getTime());
        ad.setTitle(sTime);
    }

    public void gettime() {
        long starttimel = 0;
        long endtimel = 0;
        starttime = mStart_time.getText().toString();
        endtime = mEnd_time.getText().toString();
        if ((!"".equals(endtime) && endtime != null) && (!"".equals(starttime) && starttime != null)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                starttimel = sdf.parse(starttime).getTime();
                endtimel = sdf.parse(endtime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (starttimel > endtimel) {
                Toast.makeText(Add_Invest_InformationActivity.this, "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
