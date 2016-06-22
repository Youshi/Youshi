package com.example.hmqqg.hm.ui;

import android.app.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.entity.OperateByEntity;
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
import java.util.List;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 我的外出单  录入
 * Created by Android-Wq on 2016/1/9.
 */
public class OutbillActivity extends BaseRequestActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private ImageView back;//返回
    private TextView title_top_bar;
    private TextView save;//完成

    private EditText mEdit_title;//标题
    private Spinner mEdit_outtype;//外出类型
    private TextView mEdit_startime;//开始时间
    private TextView mEdit_endtime;//结束时间
    private TextView mEdit_days;//时长 天
    private TextView mEdit_hours;//时长 小时
    private EditText mEdit_absence_cause;//外出事由
    private Spinner Operate;//审核人
    private com.example.hmqqg.hm.ui.ScrovllViewForDate scroll_datetime;//时间选择器的Scrollview
    private List<String> mAppList = new ArrayList<String>();
    private List<OperateByEntity.DetailInfoEntity> List = new ArrayList<OperateByEntity.DetailInfoEntity>();
    OperateByEntity.DetailInfoEntity ob;
    private String OperateBy;

    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;
    private String sTime;
    private String initDateTime;

    private String strTitle;
    private String strOuttype;
    private String strStartime;
    private String strEndtime;
    private String strdays;
    private String strhours;
    private String strAbsence_cause;
    private String starttime;
    private String endtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_applyout_result);
        initView();
        getOperateBy();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        save = (TextView) findViewById(R.id.save);
        title_top_bar.setText("外出单录入");
        save.setText("提交");

        mEdit_title = (EditText) findViewById(R.id.edit_title);
        mEdit_outtype = (Spinner) findViewById(R.id.sp_outtype);
        mEdit_startime = (TextView) findViewById(R.id.edit_startime);
        mEdit_endtime = (TextView) findViewById(R.id.edit_endtime);
        mEdit_days = (TextView) findViewById(R.id.edit_days);
        mEdit_hours = (TextView) findViewById(R.id.edit_hours);
        mEdit_absence_cause = (EditText) findViewById(R.id.edit_absence_cause);
        Operate = (Spinner) findViewById(R.id.edit_apply);

        back.setOnClickListener(this);
        save.setOnClickListener(this);
        mEdit_startime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimePicKDialog(mEdit_startime);
            }
        });
        mEdit_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateTimePicKDialog(mEdit_endtime);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.save:
                getDetails();//获取录入内容
                break;
        }
    }

    private void getDetails() {
        strTitle = mEdit_title.getText().toString();
        strOuttype = mEdit_outtype.getSelectedItem().toString();
        strStartime = mEdit_startime.getText().toString();
        strEndtime = mEdit_endtime.getText().toString();
        strdays = mEdit_days.getText().toString();
        strhours = mEdit_hours.getText().toString();
        strAbsence_cause = mEdit_absence_cause.getText().toString();

        if ("".equals(strTitle) || strTitle == null) {
            Toast.makeText(OutbillActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(strStartime) || strStartime == null) {
            Toast.makeText(OutbillActivity.this, "开始时间不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(strEndtime) || strEndtime == null) {
            Toast.makeText(OutbillActivity.this, "结束时间不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try {
            long Startime = sdf.parse(strStartime).getTime();
            long endtime = sdf.parse(strEndtime).getTime();
            if(Startime>endtime){
                Toast.makeText(OutbillActivity.this, "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (("".equals(strdays) || strdays == null) && ("".equals(strhours) || strhours == null)) {
            Toast.makeText(OutbillActivity.this, "时长不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("请选择审核人".equals(Operate.getSelectedItem().toString()) || null == Operate.getSelectedItem().toString()) {
            Toast.makeText(OutbillActivity.this, "请输入选择审核人", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(strAbsence_cause) || strAbsence_cause == null) {
            strAbsence_cause = "";
        } else if ((strAbsence_cause.length()) > 400) {
            Toast.makeText(OutbillActivity.this, "外出事由超出字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        progressD();
        getHttp();
    }
    private void getHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String opperid = MyApplication.getInstance().getUserInfo().getUserId();
                RequestParams params = new RequestParams(getResources().getString(R.string.http_sign));
                params.addBodyParameter("action", "outadd");
                params.addBodyParameter("Operid", opperid);//操作人
                params.addBodyParameter("ApprovalBy", opperid);//申请人编号
                params.addBodyParameter("OperateBy", OperateBy);//审核人编号
                params.addBodyParameter("DeptId", MyApplication.getInstance().getUserInfo().getDeptCode());//申请人部门ID
                params.addBodyParameter("AppTitle", strTitle);
                params.addBodyParameter("AppType", strOuttype);
                params.addBodyParameter("StartDate", strStartime);
                params.addBodyParameter("EndDate", strEndtime);
                params.addBodyParameter("Days", strdays);
                params.addBodyParameter("Hours", strhours);
                params.addBodyParameter("AppReason", strAbsence_cause);
                x.http().request(HttpMethod.POST, params, new MyCommonCallStringRequest(new LoginEntity()));
            }
        }).start();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        LoginEntity statu = (LoginEntity) object;
        String isSuccess = statu.getStatus().get(0).getStaval().toString();
        pd.dismiss();
        if ("1".equals(isSuccess)) {
            Toast.makeText(OutbillActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
            finish();
        } else if ("0".equals(isSuccess)) {
            Toast.makeText(OutbillActivity.this, "提交失败,请稍后重试！", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        pd.dismiss();
        Toast.makeText(OutbillActivity.this, "您的网络不稳定，请稍后重试！", Toast.LENGTH_SHORT).show();
    }

    public android.app.AlertDialog dateTimePicKDialog(final TextView inputDate) {
        LinearLayout dateTimeLayout = (LinearLayout) OutbillActivity.this
                .getLayoutInflater().inflate(R.layout.common_datetime, null);
        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
        scroll_datetime = (com.example.hmqqg.hm.ui.ScrovllViewForDate) findViewById(R.id.scroll_datetime);
        init(datePicker, timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(OutbillActivity.this);
        ad = new android.app.AlertDialog.Builder(OutbillActivity.this)
                .setTitle(initDateTime)
                .setView(dateTimeLayout)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        onDateChanged(null, 0, 0, 0);
                        inputDate.setText(sTime);
                        gettime();
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
                    + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), OutbillActivity.this);
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
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        sTime = sdf.format(calendar.getTime());
        ad.setTitle(sTime);
    }

    public void gettime() {
        long starttimel = 0;
        long endtimel = 0;
        starttime = mEdit_startime.getText().toString();
        endtime = mEdit_endtime.getText().toString();
        if ((!"".equals(endtime) && endtime != null) && (!"".equals(starttime) && starttime != null)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            try {
                starttimel = sdf.parse(starttime).getTime();
                endtimel = sdf.parse(endtime).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (starttimel > endtimel) {
                Toast.makeText(OutbillActivity.this, "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
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
                mEdit_days.setText(String.valueOf(days));
                mEdit_hours.setText(String.valueOf(hour));
            }
        }
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
                            OperateByEntity opby = gson.fromJson(result, OperateByEntity.class);
                            String staval = opby.getStatus().get(0).getStaval();
                            if ("0".equals(staval)) {
                                mAppList.add("请选择审核人");
                                ob = new OperateByEntity.DetailInfoEntity();
                                ob.setUserid("op10001");
                                ob.setUserName("审核人");
                                List.add(ob);
                                ArrayAdapter adapter = new ArrayAdapter(OutbillActivity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
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

                                ArrayAdapter adapter = new ArrayAdapter(OutbillActivity.this, android.R.layout.simple_spinner_dropdown_item, mAppList);
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
        pd = new ProgressDialog(OutbillActivity.this);
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
