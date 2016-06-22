package com.example.hmqqg.hm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.adapter.SignTimeAdapter;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.entity.SignlistEntity;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.fragment.base.BaseRequestFragment;
import com.example.hmqqg.hm.ui.ApprovalActivity;
import com.example.hmqqg.hm.ui.Joblogging_Activity;
import com.example.hmqqg.hm.ui.Lookreport_Activity;
import com.example.hmqqg.hm.ui.Meet_Activity;
import com.example.hmqqg.hm.ui.Outalone_Activity;
import com.example.hmqqg.hm.ui.OutsideList_Activity;
import com.example.hmqqg.hm.ui.Outside_Activity;
import com.example.hmqqg.hm.ui.Person_details;
import com.example.hmqqg.hm.ui.SignTimeActivity;
import com.example.hmqqg.hm.ui.Sign_in_off_Activity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 签到
 */
public class SignFragment extends BaseFragment implements View.OnClickListener,AdapterView.OnItemClickListener{
    private LinearLayout liner1;//我的名片
    private LinearLayout liner2;//工作汇报
    private LinearLayout liner3;//外出申请
    private LinearLayout liner4;//请假申请
    private LinearLayout liner5;//工作审批
    private LinearLayout liner6;//外出请假审批
    private LinearLayout liner7;//外出签到

    private TextView sign;
    private TextView week;//周
    private TextView time;//时间

    public SignFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.sign_fragment, container, false);
        initView(view);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = sdf.format(curDate);
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
//        mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
//        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
//        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));

        time.setText(str);
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        week.setText("星期"+mWay);
        return view;
    }
    private void initView(View view) {
        liner1 = (LinearLayout) view.findViewById(R.id.liner1);
        liner2 = (LinearLayout) view.findViewById(R.id.liner2);
        liner3 = (LinearLayout) view.findViewById(R.id.liner3);
        liner4 = (LinearLayout) view.findViewById(R.id.liner4);
        liner5 = (LinearLayout) view.findViewById(R.id.liner5);
        liner6 = (LinearLayout) view.findViewById(R.id.liner6);
        liner7 = (LinearLayout) view.findViewById(R.id.liner7);
        sign = (TextView) view.findViewById(R.id.sign);
        time = (TextView) view.findViewById(R.id.time);
        week = (TextView) view.findViewById(R.id.week);

        liner1.setOnClickListener(this);
        liner2.setOnClickListener(this);
        liner3.setOnClickListener(this);
        liner4.setOnClickListener(this);
        liner5.setOnClickListener(this);
        liner6.setOnClickListener(this);
        liner7.setOnClickListener(this);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.liner1://我的名片
                intent = new Intent(getActivity(),Person_details.class);
                startActivity(intent);
                break;
            case R.id.liner2://工作汇报
                intent = new Intent(getActivity(),Joblogging_Activity.class);
                startActivity(intent);
                break;
            case R.id.liner3://外出申请
                intent = new Intent(getActivity(),Outalone_Activity.class);
                startActivity(intent);
                break;
            case R.id.liner4://请假申请
                intent = new Intent(getActivity(),Meet_Activity.class);
                startActivity(intent);
                break;
            case R.id.liner5://工作审批
                intent = new Intent(getActivity(),Lookreport_Activity.class);
                startActivity(intent);
                break;
            case R.id.liner6://外出请假审批
                intent = new Intent(getActivity(),ApprovalActivity.class);
                startActivity(intent);
                break;
            case R.id.liner7://外出签到
                intent = new Intent(getActivity(),OutsideList_Activity.class);
                startActivity(intent);
                break;
            case R.id.sign://考勤签到
                intent = new Intent(getActivity(),Sign_in_off_Activity.class);
                startActivity(intent);
                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
