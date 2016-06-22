package com.example.hmqqg.hm.ui;

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
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.AlertDialog;


import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 添加客户资料
 * Created by Administrator on 2016/1/11.
 */
public class Add_Customer extends BaseRequestActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private ProgressDialog pd;
    private ImageView back;//返回
    private TextView save;//完成
    private EditText name;//姓名
    private EditText tel;//电话
    private TextView birthday;//出生日期
    private EditText contact;//联系人
    private EditText phone;//联系人电话
    private Spinner relation;//与客户关系
    private EditText named;//称呼
    private Spinner origine;//客户来源
    private Spinner spinner_sex;//性别
    private Spinner spinner_certificate;//证件类型
    private Spinner usersort;//客户种类
    private EditText address;//地址
    private EditText IDcard;//身份证号
    private Spinner purpose;//意向程度
    private Spinner invest;//投资能力
    private Spinner marriage;//婚姻状况
    private EditText email;//电子邮箱
    private Spinner profession;//职业

    private String Sname;//姓名

    private String Sphone;//电话
    private String Sbirthday;//出生日期
    private String Scontact;//联系人
    private String Sphone1;//联系人电话
    private String Snamed;//称呼

    private String Saddress;//地址
    private String SIDcard;//证件号
    private String Sspinner_sex;//性别
    private String Sspinner_certificate;//证件类型
    private String Sprofession;//从事职业
    private String Semail;//电子邮箱
    private String Ssex;//性别
    private String  Scertificate;//证件类型
    private String Sorigine;//客户来源
    private String Susersort;//客户种类
    private String Spurpose;//意向程度
    private String Sinvest;//投资能力
    private String Smarriage;//婚姻状况
    private String Srelation;//与客户关系

    private DatePicker datePicker;
    private AlertDialog ad;
    private String sTime;
    private String initDateTime;

    String isIDcard = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";//身份证号格式
    String isCertificate  =" ^[a-zA-Z0-9]{7,21}$";//军官证格式
    String isBirth = "[0-9]{4}-[0-9]{2}-[0-9]{2}";//生日格式
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_main);
        initView();
    }



    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        save = (TextView) findViewById(R.id.save);
        name = (EditText) findViewById(R.id.name);
        spinner_sex = (Spinner) findViewById(R.id.spinner_sex);//性别
        tel = (EditText) findViewById(R.id.tel);
        birthday = (TextView) findViewById(R.id.birthday);
        spinner_certificate = (Spinner) findViewById(R.id.spinner_certificate);//证件类型
        IDcard = (EditText) findViewById(R.id.IDcard);//证件号
        origine = (Spinner) findViewById(R.id.origine);//客户来源
        usersort = (Spinner) findViewById(R.id.usersort);//客户种类
        purpose = (Spinner) findViewById(R.id.purpose);//意向程度
        invest = (Spinner) findViewById(R.id.invest);//投资能力
        marriage = (Spinner) findViewById(R.id.marriage);//婚姻状况
        email = (EditText) findViewById(R.id.email);//电子邮箱
        profession = (Spinner) findViewById(R.id.profession);//职业
        address = (EditText) findViewById(R.id.address);//地址

        contact = (EditText) findViewById(R.id.contact);//紧急联系人
        phone = (EditText) findViewById(R.id.phone);//电话
        relation = (Spinner) findViewById(R.id.relation);//与客户的关系
        named = (EditText) findViewById(R.id.named);//称呼
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimePicKDialog(birthday);
            }
        });
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back://返回
                Intent intent = new Intent(this,Seas_Customers.class);
                startActivity(intent);
                finish();
                break;
            case R.id.save://完成
                getsave();
                break;
            case R.id.image:
                Intent intent1 = new Intent(this,Seas_Customers.class);
                startActivity(intent1);
                finish();
                break;

        }
    }
    private void progressD() {
        pd = new ProgressDialog(Add_Customer.this);
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
    private void getBigPicture(Bitmap b) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View imgEntryView = inflater.inflate(R.layout.imagebig, null); // 加载自定义的布局文件
        final AlertDialog dialog = new AlertDialog.Builder(Add_Customer.this).create();
        ImageView img = (ImageView) imgEntryView.findViewById(R.id.w);
        if (b != null) {
            Display display = Add_Customer.this.getWindowManager()
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
                Add_Customer.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        // 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法

                        if (item == 0) {
//                            takepicture();

                        } else {
                            Log.e("cramer", "请确认已经插入SD卡");
                        }
                    }

                }).create();
        dlg.show();
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

    public void getsave() {
        Sname = name.getText().toString();//姓名
        Ssex = spinner_sex.getSelectedItem().toString();//性别
        Sphone = tel.getText().toString();//电话
        Sbirthday = birthday.getText().toString();//生日
        Scertificate = spinner_certificate.getSelectedItem().toString();//证件类型
        SIDcard = IDcard.getText().toString();//证件号
        Sorigine = origine.getSelectedItem().toString();//客户来源
        Susersort = usersort.getSelectedItem().toString();//客户种类
        Spurpose = purpose.getSelectedItem().toString();//意向程度
        Sinvest = invest.getSelectedItem().toString();//投资能力
        Smarriage = marriage.getSelectedItem().toString();//婚姻状况
        Semail = email.getText().toString();//电子邮箱
        Sprofession = profession.getSelectedItem().toString();//从事行业
        Saddress = address.getText().toString();//地址
        Scontact = contact.getText().toString();//紧急联系人
        Sphone1 = phone.getText().toString();//紧急联系人手机号
        Srelation = relation.getSelectedItem().toString();//与客户关系
        Snamed = named.getText().toString();//称呼

        if("男".equals(Ssex)){//性别
            Sspinner_sex= "XBFL01";//获取性别
        }else{
            Sspinner_sex="XBFL02" ;//获取性别
        }


        if("讲座".equals(Sorigine)){
            Sorigine = "LY001";//客户来源
        }else if("陌电".equals(Sorigine)){
            Sorigine = "LY002";//客户来源
        }else if("发单".equals(Sorigine)){
            Sorigine = "LY003";//客户来源
        }else if("答谢会".equals(Sorigine)){
            Sorigine = "LY004";//客户来源
        }else if("转介绍".equals(Sorigine)){
            Sorigine = "LY005";//客户来源
        }else if("社区活动".equals(Sorigine)){
            Sorigine = "LY006";//客户来源
        }else if("渠道".equals(Sorigine)){
            Sorigine = "LY007";//客户来源
        }else if("自有资源".equals(Sorigine)){
            Sorigine = "LY008";//客户来源
        }else if("其他途径".equals(Sorigine)){
            Sorigine = "LY0011";//客户来源
        }




        if("意向".equals(Susersort)){
            Susersort = "ZL001";//获取客户种类
        }else if("普通".equals(Susersort)){
            Susersort = "ZL002";//获取客户种类
        }else if("关单".equals(Susersort)){
            Susersort = "ZL003";//获取客户种类
        }else if("代理".equals(Susersort)){
            Susersort = "ZL004";//获取客户种类
        }else if("合作".equals(Susersort)){
            Susersort = "ZL005";//获取客户种类
        }else if("失效".equals(Susersort)){
            Susersort = "ZL006";//获取客户种类
        }

        if("强烈".equals(Spurpose)){//意向程度
            Spurpose = "DJ001";
        }else if("很强烈".equals(Spurpose)){
            Spurpose = "DJ002";
        }else if("一般".equals(Spurpose)){
            Spurpose = "DJ003";
        }else if("无意向".equals(Spurpose)){
            Spurpose = "DJ004";
        }

        if("低(50万以下)".equals(Sinvest)){//投资能力
            Sinvest = "tznl01";
        }else if("中(50万-200万)".equals(Sinvest)){
            Sinvest = "tznl02";
        }else if("高(200万-500万)".equals(Sinvest)){
            Sinvest = "tznl03";
        }else if("较高(500万以上)".equals(Sinvest)){
            Sinvest = "TZNL04";
        }

        if("已婚".equals(Smarriage)){//婚姻状况
            Smarriage = "HY001";
        }else if("未婚".equals(Smarriage)){
            Smarriage = "HY002";
        }else if("离异".equals(Smarriage)){
            Smarriage = "HY003";
        }else if("垂偶".equals(Smarriage)){
            Smarriage = "HY004";
        }

        if ("工业".equals(Sprofession)){//从事职业
            Sprofession = "KHHY001";
        }else if("服务业".equals(Sprofession)){
            Sprofession = "KHHY002";
        }else if("餐饮业".equals(Sprofession)){
            Sprofession = "KHHY003";
        }else if("通讯".equals(Sprofession)){
            Sprofession = "KHHY004";
        }else if("邮电".equals(Sprofession)){
            Sprofession = "KHHY005";
        }else if("社区服务".equals(Sprofession)){
            Sprofession = "KHHY006";
        }else if("批发零售".equals(Sprofession)){
            Sprofession = "KHHY007";
        }

        if("朋友".equals(Srelation)){
            Srelation = "RYGX01";//获取与联系人的关系
        }else if("家人".equals(Srelation)){
            Srelation = "RYGX02";//获取与联系人的关系
        }

        if(Sname==null||"".equals(Sname)){
            Toast.makeText(this,"姓名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Sphone==null||"".equals(Sphone)){
            Toast.makeText(this,"请输入电话",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isMobileNO(Sphone)){
            Toast.makeText(this,"请输入正确的电话格式",Toast.LENGTH_SHORT).show();
            return;
        }

        if(Sbirthday==null||"".equals(Sbirthday)){
            Toast.makeText(this,"出生日期不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else if(!Sbirthday.matches(isBirth)){
            Toast.makeText(this,"请输入正确的出生日期格式",Toast.LENGTH_SHORT).show();
            return;
        }
        if(SIDcard==null||"".equals(SIDcard)){
            Toast.makeText(this,"证件号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if("身份证".equals(Scertificate)){
            if(!SIDcard.matches(isIDcard)){
                Toast.makeText(this,"请输入正确的身份证号",Toast.LENGTH_SHORT).show();
                return;
            }
        }else if("驾驶证".equals(Scertificate)){
            if(!SIDcard.matches(isIDcard)){
                Toast.makeText(this,"请输入正确的驾驶证号",Toast.LENGTH_SHORT).show();
                return;
            }
        }else if("军官证".equals(Scertificate)){
            if(!SIDcard.matches(isCertificate)){
                Toast.makeText(this,"请输入正确的军官证号",Toast.LENGTH_SHORT).show();
            return;
            }
        }else if(Scertificate.equals("其他")){

        }
        if(Semail==null||"".equals(Semail)){
            Toast.makeText(this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isEmail(Semail)){
            Toast.makeText(this,"请输入正确格式的邮箱",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Saddress==null||"".equals(Saddress)){
            Toast.makeText(this,"请输入地址",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Scontact==null||"".equals(Scontact)){
            Toast.makeText(this,"紧急联系人不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Sphone1==null||"".equals(Sphone1)){
            Toast.makeText(this,"紧急联系人手机号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isMobileNO(Sphone1)){
            Toast.makeText(this,"请输入正确格式的紧急联系人手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Srelation==null||"".equals(Srelation)){
            Toast.makeText(this,"与客户关系不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Snamed==null||"".equals(Snamed)){
            Toast.makeText(this,"称呼不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if("身份证".equals(Scertificate)){
            Scertificate = "ZJ01";//获取证件类型
        }else if("军官证".equals(Scertificate)){
            Scertificate = "ZJ02";//获取证件类型
        }else if("驾驶证".equals(Scertificate)){
            Scertificate = "ZJ03";//获取证件类型
        }else if("其他".equals(Scertificate)){
            Scertificate = "ZJ04";//获取证件类型
        }
        progressD();
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_addCustomer));
                requestParams.addBodyParameter("action","custinfoadd");
                requestParams.addBodyParameter("operId", MyApplication.getInstance().getUserInfo().getUserId());//id
                requestParams.addBodyParameter("userName",Sname);//客户名
                requestParams.addBodyParameter("UserSex",Sspinner_sex);//性别
                requestParams.addBodyParameter("phone",Sphone);//电话
                requestParams.addBodyParameter("BirthDate",Sbirthday);//生日
                requestParams.addBodyParameter("CertifiyType",Scertificate);//证件类型
                requestParams.addBodyParameter("CertifiyNO",SIDcard);//证件号
                requestParams.addBodyParameter("origin",Sorigine);//客户来源
                requestParams.addBodyParameter("userSort",Susersort);//客户种类
                requestParams.addBodyParameter("userGrade",Spurpose);//意向程度
                requestParams.addBodyParameter("TZNL",Sinvest);//投资能力
                requestParams.addBodyParameter("HYZK",Smarriage);//婚姻状况
                requestParams.addBodyParameter("email",Semail);//电子邮箱
                requestParams.addBodyParameter("trade",Sprofession);//从事行业
                requestParams.addBodyParameter("address",Saddress);//地址
                requestParams.addBodyParameter("ContactName",Scontact);//紧急联系人姓名
                requestParams.addBodyParameter("ContactTel",Sphone1);//紧急联系人手机号
                requestParams.addBodyParameter("RYGX",Srelation);//与客户关系
                requestParams.addBodyParameter("Call",Snamed);//称呼

                x.http().request(HttpMethod.POST,requestParams,new MyCommonCallStringRequest(new LoginEntity()));
            }
        }).start();

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        pd.dismiss();
        LoginEntity l = (LoginEntity) object;
        String str = l.getStatus().get(0).getStaval();
        if(str.equals("1")){
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Seas_Customers.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        pd.dismiss();
        Toast.makeText(this,getResources().getString(R.string.ToastString),Toast.LENGTH_SHORT).show();

    }

    public boolean isMobileNO(String mobiles) {
        String xxx = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(xxx);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
    public android.app.AlertDialog dateTimePicKDialog(final TextView inputDate) {
        LinearLayout dateTimeLayout = (LinearLayout) Add_Customer.this
                .getLayoutInflater().inflate(R.layout.common_date, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        init(datePicker, null);
        ad = new android.app.AlertDialog.Builder(Add_Customer.this)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        onDateChanged(null, 0, 0, 0);
                        inputDate.setText(sTime);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        inputDate.setText("");
                    }
                }).show();
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
                calendar.get(Calendar.DAY_OF_MONTH), Add_Customer.this);
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
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }
    @Override
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

}
