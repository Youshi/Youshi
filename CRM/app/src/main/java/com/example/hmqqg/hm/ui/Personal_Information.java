package com.example.hmqqg.hm.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.entity.Person;
import com.example.hmqqg.hm.entity.PersonEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.jivesoftware.smack.util.Base64;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Decoder.BASE64Encoder;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 个人资料
 * Created by Administrator on 2015/12/31.
 */
public class Personal_Information extends BaseRequestActivity implements View.OnClickListener {
    private ImageView back;//返回
    private TextView save;//保存
    private EditText email;//邮箱
    private EditText phone;//手机
    private EditText address;//家庭住址
    private EditText qq;//QQ
    private EditText weixin;//微信
    private String gender;//性别
    private String realPath;//图片路径
    private RadioGroup rs_gender;
    private RadioButton rs_gender_man;//男
    private RadioButton rs_gender_woman;//女
    private ImageView iv_head;//头像
    private Bitmap bitmap;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE =100 ;
    private BufferedInputStream in;
    private Intent intent;
    private MyApplication app;
    private String w = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$\n";
    private String q = "([\\w\\-]+\\@[\\w\\-]+\\.[\\w\\-]+)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_main);
        String wq = MyApplication.getInstance().getUserInfo().getUserId().toString();
        initView();
        rs_gender.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        if (checkedId == rs_gender_man.getId()) {
                            gender = rs_gender_man.getText().toString();
                            return;
                        } else if (checkedId == rs_gender_woman.getId()) {
                            gender = rs_gender_woman.getText().toString();
                            return;

                        }
                    }
                });
    }
    //网络请求
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        Person pe = (Person)object;
        String str = pe.getStatus().get(0).getStaval();
        if(str.equals("1")) {
            Toast.makeText(Personal_Information.this, "修改成功~", Toast.LENGTH_SHORT).show();
            finish();
        }if(str.equals("0")){
            Toast.makeText(Personal_Information.this,"修改失败，请重试~",Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(Personal_Information.this,"网络不稳定，请重试~",Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        save = (TextView) findViewById(R.id.save);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        qq= (EditText) findViewById(R.id.qq);
        weixin = (EditText) findViewById(R.id.weixin);
        phone = (EditText) findViewById(R.id.phone);
        rs_gender = (RadioGroup) findViewById(R.id.rs_gender);
        rs_gender_man = (RadioButton) findViewById(R.id.rs_gender_man);
        rs_gender_woman = (RadioButton) findViewById(R.id.rs_gender_woman);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        rs_gender.setOnClickListener(this);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
        iv_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.iv_head:
                getImage();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.save:
                gethttp();
                break;
        }
    }
    //发送请求
    public void gethttp() {
        String email1 = email.getText().toString();
        String number = phone.getText().toString();
        String address1 = address.getText().toString();
        String qq1 = qq.getText().toString();
        String weixin1 = weixin.getText().toString();
        if (gender == null || gender.equals("")) {
            Toast.makeText(this, "请输入选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email1 == null || email1.equals("")) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern p = Pattern.compile(q);
        Matcher m = p.matcher(email1);
        if (!m.matches()) {
            Toast.makeText(this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (number==null||number.equals("")) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isMobileNO(number)) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (qq1 == null || qq1.equals("")) {
            Toast.makeText(this, "请输入QQ号", Toast.LENGTH_SHORT).show();
            return;
        }
        String qqzheng = "[1-9][0-9]{5,9}";
        Pattern pattern = Pattern.compile(qqzheng);
        Matcher matcher = pattern.matcher(qq1);
        if (!matcher.matches()) {
            Toast.makeText(this, "请输入正确的QQ号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (weixin1 == null || weixin1.equals("")) {
            Toast.makeText(this, "请输入微信号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (address1 == null || address1.equals("")) {
            Toast.makeText(this, "请输入家庭住址", Toast.LENGTH_SHORT).show();
            return;
        }
//        InputStream str = Bitmap2IS(bitmap);
        if (realPath == null || realPath.equals("")) {
            Toast.makeText(this, "请上传头像", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            String ID= MyApplication.getInstance().getUserInfo().getUserId().toString();
            String img = GetImageStr(realPath);
            int a = img.length();
            RequestParams requestParams = new RequestParams("http://139.129.9.221/mobile/accounthandle.ashx");
            requestParams.setMultipart(true);
            requestParams.addBodyParameter("action","edit");
            requestParams.addBodyParameter("userId", ID);
            requestParams.addBodyParameter("email",email1);
            requestParams.addBodyParameter("sex",gender);
            requestParams.addBodyParameter("mobile",number);
            requestParams.addBodyParameter("address",address1);
            requestParams.addBodyParameter("qq",qq1);
            requestParams.addBodyParameter("wx",weixin1);
//            requestParams.addBodyParameter("headimg",img);
            x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new Person()));
        }
    }

    public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;

        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
}

    private void getImage() {
        final CharSequence[] items = { "照相" ,"本地相册"};
        AlertDialog dlg = new AlertDialog.Builder(
                Personal_Information.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        // 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
                        if (item == 0) {//拍照
                            takepicture();
                        }else if(item==1){//本地相册
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

    private void takepicture() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File outDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!outDir.exists()) {
                outDir.mkdirs();
            }
            File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
            realPath = outFile.getAbsolutePath();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Log.e("cramer", "请确认已经插入SD卡");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == File_Activity.TwoNextPage && resultCode == RESULT_OK){
            setResult(RESULT_OK, intent);
            finish();
            return;
        }
        /**
         * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
         * 所以为了区别到底选择了那个方式获取图片要进行判断，
         * 这里的requestCode跟startActivityForResult里面第二个参数对应
         */
            if (requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE&&resultCode == RESULT_OK) {
                //Log.e(tag, "获取图片成功，path="+realPath);
                setImageView(realPath,iv_head.getWidth(),iv_head.getHeight());
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
            } else {
                // 图像捕获失败，提示用户
                //Log.e(tag, "拍照失败");
            }
            if (requestCode==2&&resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    realPath = getRealPathFromURI(uri);
                    //Log.e(tag, "获取图片成功，path=" + realPath);
                    setImageView(realPath,iv_head.getWidth(),iv_head.getHeight());
                } else {
                    //Log.e(tag, "从相册获取图片失败");
                }
                setImageView(realPath,iv_head.getWidth(),iv_head.getHeight());
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
        bmpFactoryOptions.outHeight= heightx;
        bmpFactoryOptions.outWidth=widthx;
        bmpFactoryOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;
        bmpFactoryOptions.inJustDecodeBounds = false;
        bmpFactoryOptions.inPurgeable = true;
        bmpFactoryOptions.inInputShareable = true;
        bitmap = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, widthx, heightx);
        bitmap=toRoundBitmap(bitmap);
        if (bitmap != null) {
            iv_head.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"获取图片失败",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 把bitmap转成圆形
     * */
    public Bitmap toRoundBitmap(Bitmap bitmap){
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
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


    public String getRealPathFromURI(Uri contentUri) {
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
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
    public boolean isMobileNO(String mobiles) {
        String xxx = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(xxx);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


}
