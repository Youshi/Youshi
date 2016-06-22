package com.example.hmqqg.hm.ui;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.common.Constant;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 外勤签到
 * Created by Administrator on 2015/12/31.
 */
public class Outside_Activity extends BaseRequestActivity implements View.OnClickListener {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int TwoNextPage = 1000;
    private int a;
    private ImageView addimageview1;//上传图片
    private ImageView addimageview2;//上传图片
    private ImageView addimageview3;//上传图片
    private BufferedInputStream in;
    private Intent intent;
    private String realPath1;//图片路径
    private String realPath2;//图片路径
    private String realPath3;//图片路径

    private ImageView back;//返回
    private TextView refresh;//确认
    private LocationClient mLocationClient;
    //    private EditText frequence;
    public MyLocationListener mMyLocationListener;

    public TextView mLocationResult, mLocationResult2, logMsg;
    public TextView trigger, exit;
    public Vibrator mVibrator;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor = "gcj02";
    private LinearLayout location;

    private EditText mEdit_team;//所在团队
    private EditText mEdit_name;//姓名
    private EditText mEdit_position;//职位
    private EditText mEdit_absence_time;//外出时间
    private EditText mEdit_absence_cause;//外出事由
    private EditText mEdit_result;//预期得到成果
    private EditText mEdit_absence_feedback;//外出反馈
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fieldsignin_main);
        initView();
        if (realPath1 == null) {
//            addimageview1.setVisibility(View.VISIBLE);//显示
            addimageview2.setVisibility(View.GONE);//隐藏
            addimageview3.setVisibility(View.GONE);//隐藏
        }
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        addimageview1 = (ImageView) findViewById(R.id.addimageview1);
        addimageview2 = (ImageView) findViewById(R.id.addimageview2);
        addimageview3 = (ImageView) findViewById(R.id.addimageview3);
        refresh = (TextView) findViewById(R.id.refresh);
        mLocationResult = (TextView) findViewById(R.id.diqu);
        mLocationResult2 = (TextView) findViewById(R.id.didian);
        location = ((LinearLayout) findViewById(R.id.ll_location));
        mLocationResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        mEdit_team = (EditText) findViewById(R.id.edit_team);
        mEdit_name = (EditText) findViewById(R.id.edit_name);
        mEdit_position = (EditText) findViewById(R.id.edit_position);
        mEdit_absence_time = (EditText) findViewById(R.id.edit_absence_time);
        mEdit_absence_cause = (EditText) findViewById(R.id.edit_absence_cause);
        mEdit_result = (EditText) findViewById(R.id.edit_result);
        mEdit_absence_feedback = (EditText) findViewById(R.id.edit_absence_feedback);

        initLocation();
        mLocationClient.start();

        location.setOnClickListener(this);
        back.setOnClickListener(this);
        addimageview1.setOnClickListener(this);
        addimageview2.setOnClickListener(this);
        addimageview3.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.refresh:
                gethttp();
//                finish();
                break;
            case R.id.ll_location:
                intent = new Intent(this, LocationActivity.class);
                startActivityForResult(intent, Constant.REQUEST_LOCATION);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case R.id.addimageview1:
                a = 1;
                getImageView();
                break;
            case R.id.addimageview2:
                a = 2;
                getImageView();
                break;
            case R.id.addimageview3:
                a = 3;
                getImageView();
                break;
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType(tempcoor);//可选，默认gcj02，设置返回的定位结果坐标系，
//        int span = 100000;
//        try {
//            span = Integer.valueOf(frequence.getText().toString());
//        } catch (Exception e) {
//        }
//        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//        option.setIsNeedAddress(checkGeoLocation.isChecked());//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setLocationNotify(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedAddress(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
    }

    //点击触发弹框
    public void getImageView() {
        final CharSequence[] items = {"照相"};
        AlertDialog dlg = new AlertDialog.Builder(
                Outside_Activity.this)
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
                }


            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
            } else {
                // 图像捕获失败，提示用户
                //Log.e(tag, "拍照失败");
            }
        }else if (requestCode == Constant.REQUEST_LOCATION){
            if (resultCode == Constant.RESULT_LOCATION){
                String name = data.getStringExtra("name");
                String address = data.getStringExtra("address");
                mLocationResult.setText(name);
                mLocationResult2.setText(address);
            }
        }
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
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
        }
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    /**
     * 实现实时位置回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            SharedPreferences sp = getSharedPreferences("location", MODE_PRIVATE);
            sp.edit().putString("latitude", "" + latitude).putString("longitude", "" + longitude).commit();

            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            StringBuffer sb2 = new StringBuffer(256);
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb2.append("(").append(location.getLocationDescribe()).append(")");
//                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb2.append("(").append(location.getLocationDescribe()).append(")");
                //运营商信息
//                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("定位失败,请检查您的手机是否联网");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                sb.append(location.getProvince()).append(location.getCity()).append(location.getDistrict());
//                sb2.append(location.getStreet()).append(location.getStreetNumber());
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            logMsg(sb.toString(), sb2.toString());
        }

    }


    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(String str, String str2) {
        try {
            if (mLocationResult != null)
                mLocationResult.setText(str);
            if (mLocationResult2 != null) {
                mLocationResult2.setText(str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        mLocationClient.stop();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        LoginEntity log = (LoginEntity) object;
        String str = log.getStatus().get(0).getStaval();
        if ("1".equals(str)){
            Toast.makeText(Outside_Activity.this, "签到成功~", Toast.LENGTH_SHORT).show();
            finish();
        }else if ("0".equals(str)){
            Toast.makeText(Outside_Activity.this, "签到请求失败，已经签过了~", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {

    }

    private void gethttp() {
        final String address = mLocationResult.getText().toString()+mLocationResult2.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action","signinadd");
                params.addBodyParameter("UserID", MyApplication.getInstance().getUserInfo().getUserId());
                params.addBodyParameter("SignAddress",address);
                x.http().request(HttpMethod.POST, params , new MyCommonCallStringRequest(new LoginEntity()));
            }
        }).start();
    }
}
