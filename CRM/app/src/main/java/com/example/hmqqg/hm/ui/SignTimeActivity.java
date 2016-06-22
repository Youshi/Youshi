package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.adapter.SignAdapter;
import com.example.hmqqg.hm.util.KCalendar;
import com.example.hmqqg.hm.util.KCalendar.OnCalendarClickListener;
import com.example.hmqqg.hm.util.KCalendar.OnCalendarDateChangedListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 签到天数
 */

public class SignTimeActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private ProgressDialog mypDialog;//进度条对话框
    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
    private TextView popupwindow_calendar_month;
    private KCalendar calendar;
    private List<String> list = new ArrayList<String>(); //设置标记列表
    private SignAdapter messageAdapter;
    private PullToRefreshListView lstv;
    private ImageView back;//返回
    private List<String> list1 = new ArrayList<String>();
    private PopupWindow popupWindow;
    private boolean isSigned = false;//是否签到过

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.time_main);

        ImageView lastMonth = (ImageView) findViewById(R.id.iv_left);
        ImageView nextMonth = (ImageView) findViewById(R.id.iv_right);
        lastMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        popupwindow_calendar_month = (TextView) findViewById(R.id.popupwindow_calendar_month);
        calendar = (KCalendar) findViewById(R.id.popupwindow_calendar);
        popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
                + calendar.getCalendarMonth() + "月");
        if (null != date) {

            int years = Integer.parseInt(date.substring(0,
                    date.indexOf("-")));
            int month = Integer.parseInt(date.substring(
                    date.indexOf("-") + 1, date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "年" + month + "月");

            calendar.showCalendar(years, month);
            calendar.setCalendarDayBgColor(date,
                    R.mipmap.calendar_date_focused);
        }


        list.add("2016-01-01");
        list.add("2016-01-02");
        list.add("2016-01-03");
//        calendar.addMarks(list, 0);
        calendar.setCalendarDaysBgColor(list, R.mipmap.calendar_date_focused);

        //监听所选中的日期
        calendar.setOnCalendarClickListener(new OnCalendarClickListener() {

            public void onCalendarClick(int row, int col, String dateFormat) {
                int month = Integer.parseInt(dateFormat.substring(
                        dateFormat.indexOf("-") + 1,
                        dateFormat.lastIndexOf("-")));
                int day = Integer.parseInt(dateFormat.substring(dateFormat.lastIndexOf("-") + 1));
                int year = Integer.parseInt(dateFormat.substring(0, dateFormat.indexOf("-")));

                if (calendar.getCalendarMonth() - month == 1//跨年跳转
                        || calendar.getCalendarMonth() - month == -11) {
                    calendar.lastMonth();

                } else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
                        || month - calendar.getCalendarMonth() == -11) {
                    calendar.nextMonth();

                } else if (calendar.getCalendarYear() == year && calendar.getCalendarMonth() == month && calendar.getThisday().getDate() == day) {
//                    list.add(dateFormat);
//                    calendar.addMarks(list, 0);
//                    calendar.removeAllBgColor();
                    calendar.setCalendarDayBgColor(dateFormat,
                            R.mipmap.calendar_date_focused);
                    date = dateFormat;//最后返回给全局 date
                    showPopUp(calendar);//弹窗提示签到成功
                    isSigned = true;
                }
            }
        });

        //监听当前月份
        calendar.setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month
                        .setText(year + "年" + month + "月");
            }
        });
        lstv = (PullToRefreshListView) findViewById(R.id.lstv);
        messageAdapter = new SignAdapter(list1, this);
        lstv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        list1.add(0, "哈哈");

        messageAdapter.notifyDataSetChanged();
        lstv.setAdapter(messageAdapter);

        lstv.setOnItemClickListener(this);
        lstv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            // 下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list1.add(1, "哈哈");
                messageAdapter.notifyDataSetChanged();
                stopRefresh();
            }

            // 上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                list1.add(0, "哈哈");

                messageAdapter.notifyDataSetChanged();
                stopRefresh();

            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void stopRefresh() {
        lstv.postDelayed(new Runnable() {
            @Override
            public void run() {
                lstv.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_left:
                calendar.lastMonth();
                break;
            case R.id.iv_right:
                calendar.nextMonth();
                break;
        }
    }

    /**
     * 弹窗
     *
     * @param v
     */
    private void showPopUp(View v) {
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.DKGRAY);
        TextView tv = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,20,20,20);
        tv.setLayoutParams(params);
        if (isSigned) {
            tv.setText("您今天已经签到过了^-^");
        } else {
            tv.setText("恭喜您签到成功!");
        }
        tv.setTextColor(Color.WHITE);
        layout.addView(tv);

        popupWindow = new PopupWindow(layout, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
    }

}