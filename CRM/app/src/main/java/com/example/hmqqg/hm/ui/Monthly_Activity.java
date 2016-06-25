package com.example.hmqqg.hm.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.AddjobEntity;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 编写月报
 * Created by Administrator on 2016/1/19.
 */
public class Monthly_Activity extends BaseRequestActivity implements View.OnClickListener{
    private TextView title_top_bar;
    private ImageView back;//返回
    private TextView refresh;//保存按钮
    private final int DATE_DIALOG = 1;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE =100 ;
    public  static final int TwoNextPage = 1000;
//    private ImageView addimageview;//上传图片
    private BufferedInputStream in;
    private Intent intent;
    private String realPath;//图片路径
    private TextView time;//时间
    private EditText worktime;//工作时间
    private EditText amount1;//陌电数量
    private EditText amount2;//回访客户数量
    private EditText amount3;//意向客户数量
    private EditText condition1;//回访客户情况
    private EditText condition2;//关单情况
    private EditText condition3;//下周工作安排
    private Spinner spi_approver;//上级审核人Spinner
    private static int LENGTH=500;

    private int year;
    private int month;
    private int date;

    private String Stime;
    private String Sworktime;
    private String Samount1;
    private String Samount2;
    private String Samount3;
    private String Scondition1;
    private String Scondition2;
    private String Scondition3;
    private String Strapprover;
    private ArrayAdapter<String> approverAdapter;//系统默认adapter
    private List<String> approverList;//审核人的列表List
    private List<String> mAppList = new ArrayList<String>();
    private OperateByEntity.DetailInfoEntity ob;
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();
    private String OperateBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compile_job2);
        View.OnClickListener dateBtnListener =
                new BtnOnClickListener(DATE_DIALOG);
        time = (TextView) findViewById(R.id.time);
        time.setOnClickListener(dateBtnListener);
        initView();
        gethttpspi();//从服务器获取审核人
        //用来获取日期和时间的
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);
        time.setText( year + "-" +(month+1) + "-" + date );
    }



    private void initView() {
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        refresh = (TextView) findViewById(R.id.refresh);
        amount1 = (EditText) findViewById(R.id.amount1);
        amount2 = (EditText) findViewById(R.id.amount2);
        amount3 = (EditText) findViewById(R.id.amount3);
        condition1 = (EditText) findViewById(R.id.condition1);
        condition2 = (EditText) findViewById(R.id.condition2);
        condition3 = (EditText) findViewById(R.id.condition3);
        spi_approver = (Spinner) findViewById(R.id.spi_approver);
        approverList = new ArrayList<>();
        approverAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,approverList);
        //设置下拉风格
        approverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        title_top_bar.setText("工作记录录入");
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
//        addimageview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.refresh:

                gethttp();
                break;
        }
    }
    private void progressD() {
        pd = new ProgressDialog(Monthly_Activity.this);
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
    private void gethttp() {
        Stime = time.getText().toString();
        Samount1 = amount1.getText().toString();
        Samount2 = amount2.getText().toString();
        Samount3 = amount3.getText().toString();
        Scondition1 = condition1.getText().toString();
        Scondition2 = condition2.getText().toString();
        Scondition3 = condition3.getText().toString();
        Strapprover = spi_approver.getSelectedItem().toString();//获取Spinner的值
        if("".equals(Scondition1)||Scondition1==null){
            Scondition1="";
        }else if((Scondition1.length())>LENGTH){
            Toast.makeText(Monthly_Activity.this, "回访客户情况超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if("".equals(Scondition2)||Scondition2==null){
            Scondition2="";
        }else if((Scondition2.length())>LENGTH){
            Toast.makeText(Monthly_Activity.this, "关单情况超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if("".equals(Scondition3)||Scondition3==null){
            Scondition3="";
        }else if((Scondition3.length())>LENGTH){
            Toast.makeText(Monthly_Activity.this, "次日工作安排超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if("请选择上级审批人".equals(Strapprover)){
            Toast.makeText(Monthly_Activity.this, "请选择审核人", Toast.LENGTH_SHORT).show();
            return;
        }
        progressD();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_remindset));
                requestParams.addBodyParameter("action","monthlogadd");
                requestParams.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());
                requestParams.addBodyParameter("startDate",Stime);
                requestParams.addBodyParameter("endDate",Stime);
                requestParams.addBodyParameter("AlienCall",Samount1);//陌电数量
                requestParams.addBodyParameter("ReturnCall",Samount2);//回访数量
                requestParams.addBodyParameter("IntentNum",Samount3);//意向客户
                requestParams.addBodyParameter("ReturnCond",Scondition1);//回访情况
                requestParams.addBodyParameter("DealCond",Scondition2);//关单情况
                requestParams.addBodyParameter("invitnum","1");//邀请数量
                requestParams.addBodyParameter("operateby",OperateBy);//审核人
                requestParams.addBodyParameter("NextWork",Scondition3);//次日工作计划
                requestParams.addBodyParameter("userId",MyApplication.getInstance().getUserInfo().getUserId());
//                x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new AddjobEntity()));
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        pd.dismiss();
                        Gson gson = new Gson();
                        AddjobEntity addentity = gson.fromJson(result,AddjobEntity.class);
                        String str = addentity.getStatus().get(0).getStaval();
                        if("1".equals(str)){
                            Toast.makeText(Monthly_Activity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Monthly_Activity.this,Joblogging_Activity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Monthly_Activity.this,"保存失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
                        Toast.makeText(Monthly_Activity.this,R.string.ToastString,Toast.LENGTH_SHORT).show();
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
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
//        pd.dismiss();
//        AddjobEntity l = (AddjobEntity) object;
//        String str = l.getStatus().get(0).getStaval();
//        if(str.equals("1")){
//            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this,Joblogging_Activity.class);
//            startActivity(intent);
//            finish();
//        }else{
//            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
//        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
//        pd.dismiss();
//        Toast.makeText(this,R.string.ToastString,Toast.LENGTH_SHORT).show();
    }
    private void gethttpspi() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "getauditor");
                requestParams.addBodyParameter("operid", MyApplication.getInstance().getUserInfo().getUserId());
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            Gson gson = new Gson();
                            OperateByEntity opby =gson.fromJson(result,OperateByEntity.class);
                            String staval  = opby.getStatus().get(0).getStaval();
                            if ("0".equals(staval)) {
                                mAppList.add("请选择审核人");
                                ob = new OperateByEntity.DetailInfoEntity();
                                ob.setUserid("op10001");
                                ob.setUserName("审核人");
                                List.add(ob);
                                ArrayAdapter adapter = new ArrayAdapter(Monthly_Activity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spi_approver.setAdapter(adapter);
                            } else {
                                JSONArray json = new JSONObject(result).getJSONArray("DetailInfo");
                                mAppList.add("请选择审核人");
                                ob = new OperateByEntity.DetailInfoEntity();
                                ob.setUserid("op10001");
                                ob.setUserName("审核人");
                                List.add(ob);
                                for (int i = 0; i < json.length(); i++) {
                                    JSONObject j = (JSONObject) json.get(i);
                                    String userid = j.getString("userid");
                                    String username = j.getString("UserName");
                                    ob = new OperateByEntity.DetailInfoEntity();
                                    ob.setUserid(userid);
                                    ob.setUserName(username);
                                    List.add(ob);
                                    mAppList.add(username);
                                }

                                ArrayAdapter adapter = new ArrayAdapter(Monthly_Activity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spi_approver.setAdapter(adapter);
                            }
//                            Operate.setSelection(0, false);
                            spi_approver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                                           long id) {
                                    OperateBy = List.get((int) id).getUserid();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> arg0) {
                                    // TODO Auto-generated method stub

                                }
                            });

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
    //点击触发弹框
    public void getImageView() {
        final CharSequence[] items = { "照相" };
        AlertDialog dlg = new AlertDialog.Builder(
                Monthly_Activity.this)
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
    public void takepicture(){
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == AddJob_Activity.TwoNextPage && resultCode == RESULT_OK){
            setResult(RESULT_OK, intent);
            finish();
            return;
        }
        /**
         * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
         * 所以为了区别到底选择了那个方式获取图片要进行判断，
         * 这里的requestCode跟startActivityForResult里面第二个参数对应
         */
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    realPath = getRealPathFromURI(uri);
                    //Log.e(tag, "获取图片成功，path=" + realPath);
//                    setImageView(realPath,addimageview.getWidth(),addimageview.getHeight());
                } else {
                    //Log.e(tag, "从相册获取图片失败");
                }
            }
        }
        else{
            if (resultCode == RESULT_OK) {
                //Log.e(tag, "获取图片成功，path="+realPath);
//                setImageView(realPath,addimageview.getWidth(),addimageview.getHeight());
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消了图像捕获
            } else {
                // 图像捕获失败，提示用户
                //Log.e(tag, "拍照失败");
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
        bmpFactoryOptions.outHeight= heightx;
        bmpFactoryOptions.outWidth=widthx;
        bmpFactoryOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;
        bmpFactoryOptions.inJustDecodeBounds = false;
        bmpFactoryOptions.inPurgeable = true;
        bmpFactoryOptions.inInputShareable = true;
        Bitmap bitmap = BitmapFactory.decodeStream(in, null, bmpFactoryOptions);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, widthx, heightx);
        if (bitmap != null) {
//            addimageview.setImageBitmap(bitmap);
        }
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    /*
	     * 成员内部类,此处为提高可重用性，也可以换成匿名内部类
	     */
    private class BtnOnClickListener implements View.OnClickListener {

        private int dialogId = 0;   //默认为0则不显示对话框

        public BtnOnClickListener(int dialogId) {
            this.dialogId = dialogId;
        }
        @Override
        public void onClick(View view) {
            showDialog(dialogId);
        }

    }
}
