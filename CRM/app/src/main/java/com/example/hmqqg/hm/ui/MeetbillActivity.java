package com.example.hmqqg.hm.ui;

import android.app.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.hmqqg.hm.entity.AddjobEntity;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.LeaveEntity;
import com.example.hmqqg.hm.entity.MeetEntity;
import com.example.hmqqg.hm.entity.MyOutaloneEntity;
import com.example.hmqqg.hm.entity.OperateByEntity;
import com.example.hmqqg.hm.util.DateTimePickDialogUtil;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 请假单 录入
 * Created by Administrator on 2016/1/25.
 */
public class MeetbillActivity extends BaseRequestActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private ImageView back;//返回
    private TextView title_top_bar;
    private TextView save;//完成

    private EditText name;//姓名
    private EditText yuanyin;//请假原因
    private TextView start;//开始时间
    private TextView end;//结束时间
    private TextView mminute;//天
    private TextView hours;//小时
    private EditText perple;//紧急联系人
    private EditText phone;//联系电话
    private static TextView mday;//天
    private Spinner leaveadd;
    private Spinner Operate;//审核人

    //    private static TextView mhour;//时
//    private static TextView mminute;//分
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    private DatePicker datePicker;
    private DatePicker datePickerr;
    private TimePicker timePicker;
    private TimePicker timePickerr;
    private AlertDialog ad;
    private String sTime;
    private String eTime;

    private String Sname;
    private String Syuanyin;
    private String Smminute;
    private String Shours;
    private String Sresult;
    private String starttime;
    private String endtime;
    private String initDateTime;
    private String initDateTimer;
    private Activity activity;
    private List<String> mAppList = new ArrayList<String>();
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();
    OperateByEntity.DetailInfoEntity ob;
    private String OperateBy;
    private String[] str;
    private String initStartDateTime; // 初始化开始时间
    private String initEndDateTime = "2014年8月23日 17:44"; // 初始化结束时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.out_mian);
        initView();
        getOperateBy();
//        getspinner();
    }


    private void initView() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        initStartDateTime = year + "年" + month + "月" + day + "日 " + hour + ":" + minute;
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        name = (EditText) findViewById(R.id.name);
        mminute = (TextView) findViewById(R.id.minute);
        hours = (TextView) findViewById(R.id.hours);
        yuanyin = (EditText) findViewById(R.id.yuanyin);
//        mday = (TextView) findViewById(R.id.day);
        leaveadd = (Spinner) findViewById(R.id.leaveadd1);
        Operate = (Spinner) findViewById(R.id.Operate);

        start = (TextView) findViewById(R.id.start);
        end = (TextView) findViewById(R.id.end);
        start.setInputType(InputType.TYPE_NULL);
        end.setInputType(InputType.TYPE_NULL);
        save = (TextView) findViewById(R.id.save);
        title_top_bar.setText("请假单");
        back.setOnClickListener(this);
        save.setOnClickListener(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
//                        MeetbillActivity.this, initStartDateTime);
//                dateTimePicKDialog.dateTimePicKDialog(start);
                dateTimePicKDialog(start);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimePicKDialog(end);

            }
        });

    }

    public AlertDialog dateTimePicKDialog(final TextView inputDate) {
        LinearLayout dateTimeLayout = (LinearLayout) MeetbillActivity.this
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        init(datePicker, timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(MeetbillActivity.this);

        ad = new AlertDialog.Builder(MeetbillActivity.this)
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.save:
                gethttp();
                break;
        }
    }

    public void gethttp() {
        Sname = name.getText().toString();//标题
        Syuanyin = yuanyin.getText().toString();
        Smminute = mminute.getText().toString();
        starttime = start.getText().toString();
        endtime = end.getText().toString();
        Shours = hours.getText().toString();
        Sresult = yuanyin.getText().toString();
        if ("".equals(Sname) || Sname == null) {
            Toast.makeText(MeetbillActivity.this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        } else if ((Sname.length()) > 40) {
            Toast.makeText(MeetbillActivity.this, "标题字数超过限制", Toast.LENGTH_SHORT).show();
        }
        if ("".equals(Syuanyin) || Syuanyin == null) {
            Syuanyin = "";
        } else if ((Sresult.length()) > 400) {
            Toast.makeText(MeetbillActivity.this, "请假原因字数超过限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(starttime) || starttime == null) {
            Toast.makeText(MeetbillActivity.this, "请输入开始时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(endtime) || endtime == null) {
            Toast.makeText(MeetbillActivity.this, "请输入结束时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(Smminute) || Smminute == null) {
            Toast.makeText(MeetbillActivity.this, "请输入合计时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(Shours) || Shours == null) {
            Toast.makeText(MeetbillActivity.this, "请输入合计时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(Operate.getSelectedItem()) || null == Operate.getSelectedItem()) {
            OperateBy = "op10001";
//            Toast.makeText(MeetbillActivity.this, "请输入选择审核人", Toast.LENGTH_SHORT).show();
//            return;
        }
        if ("请选择审核人".equals(Operate.getSelectedItem().toString()) || null == Operate.getSelectedItem().toString()) {
            Toast.makeText(MeetbillActivity.this, "请输入选择审核人", Toast.LENGTH_SHORT).show();
            return;
        }
        progressD();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams requestParams = new RequestParams(getResources().getString(R.string.http_sign));
                requestParams.addBodyParameter("action", "leaveadd");
                requestParams.addBodyParameter("Operid", str);
                requestParams.addBodyParameter("ApprovalBy", str);
                requestParams.addBodyParameter("DeptId", MyApplication.getInstance().getUserInfo().getDeptCode());
                requestParams.addBodyParameter("AppType", leaveadd.getSelectedItem().toString());
                requestParams.addBodyParameter("AppTitle", Sname);
                requestParams.addBodyParameter("Days", Smminute);
                requestParams.addBodyParameter("Hours", Shours);
                requestParams.addBodyParameter("StartDate", starttime);
                requestParams.addBodyParameter("EndDate", endtime);
                requestParams.addBodyParameter("AppReason", Syuanyin);
                requestParams.addBodyParameter("OperateBy", OperateBy);
                requestParams.addBodyParameter("CreatedBy", str);
//                x.http().request(HttpMethod.POST, requestParams, new MyCommonCallStringRequest(new MeetEntity()));
                x.http().post(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        MeetEntity meetentity = gson.fromJson(result,MeetEntity.class);
                        pd.dismiss();
                        if ("1".equals(meetentity.getStatus().get(0).getStaval())) {
                            Toast.makeText(MeetbillActivity.this, "保存成功!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(MeetbillActivity.this, "保存失败，请稍后重试！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        pd.dismiss();
                        Toast.makeText(MeetbillActivity.this, getResources().getString(R.string.ToastString), Toast.LENGTH_SHORT).show();
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
//        MeetEntity ac = (MeetEntity) object;
//        pd.dismiss();
//        if ("1".equals(ac.getStatus().get(0).getStaval())) {
//            Toast.makeText(MeetbillActivity.this, "保存成功~", Toast.LENGTH_SHORT).show();
//            finish();
//        } else {
//            Toast.makeText(MeetbillActivity.this, "保存失败，请稍后重试！", Toast.LENGTH_SHORT).show();
//        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
//        pd.dismiss();
//        Toast.makeText(MeetbillActivity.this, "您的网络不稳定，请稍后重试！", Toast.LENGTH_SHORT).show();

    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            calendar = this.getCalendarByInintData(initDateTime);
        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "年"
                    + calendar.get(Calendar.MONTH) + "月"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), MeetbillActivity.this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
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

        String hourStr = spliteString(time, ":", "index", "front"); // 时
        String minuteStr = spliteString(time, ":", "index", "back"); // 分

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue();
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
        int currentHour = Integer.valueOf(hourStr.trim()).intValue();
        int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay, currentHour,
                currentMinute);
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


    public void gettime() {
        long starttimel = 0;
        long endtimel = 0;
        endtime = end.getText().toString();
        starttime = start.getText().toString();
        if ((!"".equals(endtime) && endtime != null) && (!"".equals(starttime) && starttime != null)) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            try {
                starttimel = sdf.parse(starttime).getTime();
                endtimel = sdf.parse(endtime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (starttimel > endtimel) {
                Toast.makeText(MeetbillActivity.this, "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
                return;
            } else {
                long time = endtimel - starttimel;
                long days = time / (1000 * 60 * 60 * 24);
                long hour = (time - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = (time - days * (1000 * 60 * 60 * 24) - hour * (1000 * 60 * 60)) / (1000 * 60);
                if (minutes > 1) {
                    hour += 1;
                }
                //            mday.setText(days+"天"+hours+"小时"+minutes+"分");
                mminute.setText(String.valueOf(days));
                hours.setText(String.valueOf(hour));
            }
        }
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        sTime = sdf.format(calendar.getTime());
        ad.setTitle(sTime);
    }


    public void getOperateBy() {
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
                                ArrayAdapter adapter = new ArrayAdapter(MeetbillActivity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                Operate.setAdapter(adapter);
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

                                ArrayAdapter adapter = new ArrayAdapter(MeetbillActivity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                Operate.setAdapter(adapter);
                            }
//                            Operate.setSelection(0, false);
                                Operate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void progressD() {
        pd = new ProgressDialog(MeetbillActivity.this);
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
}
