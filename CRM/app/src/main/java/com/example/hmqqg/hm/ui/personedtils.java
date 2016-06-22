package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.common.Constant;
import com.example.hmqqg.hm.entity.AddjobEntity;
import com.example.hmqqg.hm.entity.PassEntity;
import com.example.hmqqg.hm.entity.PersonEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by Administrator on 2016/5/10.
 */
public class personedtils extends BaseRequestActivity implements View.OnClickListener {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private BufferedInputStream in;
    private Intent intent;
    private Bitmap bitmap;


    private ImageView back;//返回
    private TextView title_top_bar;
    private ImageView mIv_head;

    private TextView mUsername;
    private TextView mNeed;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mQq;
    private EditText address;
    private String str;
    private String realPath1;
    private TextView refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personedtils);
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back.setOnClickListener(this);
        title_top_bar.setText("个人信息");
        bindViews();


    }
    private void bindViews() {

        mIv_head = (ImageView) findViewById(R.id.portrait);
        mUsername = (TextView) findViewById(R.id.username);
        mNeed = (TextView) findViewById(R.id.need);
        mEmail = (EditText) findViewById(R.id.email);
        mPhone = (EditText) findViewById(R.id.phone);
        refresh = (TextView) findViewById(R.id.refresh);
        mQq = (EditText) findViewById(R.id.qq);
//        mWeixin = (EditText) findViewById(R.id.weixin);
        address = (EditText) findViewById(R.id.address);
        refresh.setOnClickListener(this);
        mIv_head.setOnClickListener(this);
        Intent intent = getIntent();
        mUsername.setText(intent.getStringExtra("name")+"");
        mNeed.setText(intent.getStringExtra("Sex")+"");
        mEmail.setText(intent.getStringExtra("Email")+"");
        mPhone.setText(intent.getStringExtra("Mobile")+"");
        mQq.setText(intent.getStringExtra("Qq")+"");
        str = intent.getStringExtra("str");
        address.setText(intent.getStringExtra("Address")+"");
        if ("".equals(str)||str == null){
            mIv_head.setImageResource(R.mipmap.person12);
        }else {
//            x.image().bind(mIv_head,getResources().getString(R.string.http_image)+str);
            ImageLoader.getInstance().loadImage(getResources().getString(R.string.http_image)+str, new SimpleImageLoadingListener()
                    {
                        public void onLoadingComplete(String imageUri, android.view.View view, android.graphics.Bitmap loadedImage) {
                            loadedImage=toRoundBitmap(loadedImage);
                            mIv_head.setImageBitmap(loadedImage);
                        };
                        public void onLoadingFailed(String imageUri, android.view.View view, com.nostra13.universalimageloader.core.assist.FailReason failReason) {
                        };
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {

                        }
                        @Override
                        public void onLoadingCancelled(String imageUri, View view) {
                        }
                    }
            );
        }

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.refresh:
                progressD();
                gethttp();
                break;
            case R.id.portrait:
                getImageView();
                break;
        }
    }
    //点击触发弹框
    public void getImageView() {
        final CharSequence[] items = {"照相", "相册"};
        android.app.AlertDialog dlg = new android.app.AlertDialog.Builder(
                personedtils.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        // 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法

                        if (item == 0) {
                            takepicture();

                        }
                        if (item == 1) {
                            takepicture2();

                        }else {
                            Log.e("cramer", "请确认已经插入SD卡");
                        }
                    }

                }).create();
        dlg.show();
    }

    private void takepicture2() {
//        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
//        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
//        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//            startActivityForResult(pickIntent, 2);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"), 2);
    }

    public String bitmaptoString(Bitmap bitmap){
        //将Bitmap转换成字符串
        String string=null;
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,10,bStream);
        byte[]bytes=bStream.toByteArray();
        string= Base64.encodeToString(bytes,Base64.DEFAULT);
        return string;
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


            if (resultCode == RESULT_OK) {
                //Log.e(tag, "获取图片成功，path="+realPath);
                    setImageView(realPath1, mIv_head.getWidth(), mIv_head.getHeight());

            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
            } else {
                // 图像捕获失败，提示用户
                //Log.e(tag, "拍照失败");
            }
            if (requestCode == 2 && resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                        realPath1 = getRealPathFromURI(uri);
                        //Log.e(tag, "获取图片成功，path=" + realPath);
                        setImageView(realPath1, mIv_head.getWidth(), mIv_head.getHeight());
                }
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
        bitmap = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, widthx, heightx);
                //把bitmap转成圆形
                mIv_head.setImageBitmap(bitmap);
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
                realPath1 = outFile.getAbsolutePath();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Log.e("cramer", "请确认已经插入SD卡");
        }
    }
    private void progressD() {
        pd = new ProgressDialog(personedtils.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
        pd.setMessage("正在保存,请稍候...");
        pd.show();
    }
    public void gethttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_service));
                params.addBodyParameter("action","edit");
                params.addBodyParameter("userid", MyApplication.getInstance().getUserInfo().getUserId().toString());
                params.addBodyParameter("email", mEmail.getText().toString());
                params.addBodyParameter("Sex", mNeed.getText().toString());
                params.addBodyParameter("Mobile", mPhone.getText().toString());
                params.addBodyParameter("qq", mQq.getText().toString());
                params.addBodyParameter("address", address.getText().toString());
                if("".equals(realPath1)||null==realPath1){

                }else{
                    params.addBodyParameter("headimg", bitmaptoString(bitmap));
                }
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        if (result == null||"".equals(result)){
                            pd.dismiss();
                            Toast.makeText(personedtils.this, R.string.ToastString, Toast.LENGTH_LONG).show();
                            return;
                        }
                        Gson gson = new Gson();
                        PassEntity addjob =gson.fromJson(result,PassEntity.class);
                        pd.dismiss();
                        try {
                            String stav = addjob.getStatus().get(0).getStaval();
                            if("1".equals(stav)){
                                Toast.makeText(personedtils.this, "修改个人信息成功", Toast.LENGTH_LONG).show();
                                finish();
                            }else{
                                Toast.makeText(personedtils.this, R.string.ToastString, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
