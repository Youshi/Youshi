package com.example.hmqqg.hm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.DemoActivity2;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.ui.Application_Memo;
import com.example.hmqqg.hm.ui.ApplyforActivity;
import com.example.hmqqg.hm.ui.ApprovalActivity;
import com.example.hmqqg.hm.ui.CustomLook_Activity;
import com.example.hmqqg.hm.ui.File_Activity;
import com.example.hmqqg.hm.ui.HelpActivity;
import com.example.hmqqg.hm.ui.Lookreport_Activity;
import com.example.hmqqg.hm.ui.MarketPriceActivity;
import com.example.hmqqg.hm.ui.Meet_Activity;
import com.example.hmqqg.hm.ui.Notice_Activity;
import com.example.hmqqg.hm.ui.Outalone_Activity;
import com.example.hmqqg.hm.ui.Person_details;
import com.example.hmqqg.hm.ui.Remind_Activity;
import com.example.hmqqg.hm.ui.VersionActivity;

/**
 * 应用
 */
public class ApplyFragment extends BaseFragment implements View.OnClickListener {
    private TextView title_top_bar;
    private ImageView iv_module1;//公告
    private ImageView iv_module4;//文件下载
    private ImageView iv_module5;//备忘
    private ImageView iv_module7;//提醒
    private ImageView iv_module6;//帮助
    private ImageView iv_module11;//市场活动
    private ImageView iv_module2;//版本信息

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        title_top_bar = (TextView) view.findViewById(R.id.title_top_bar);
        title_top_bar.setText("应用");

        iv_module1 = (ImageView) view.findViewById(R.id.iv_module1);
        iv_module4 = (ImageView) view.findViewById(R.id.iv_module4);
        iv_module7 = (ImageView) view.findViewById(R.id.iv_module7);
        iv_module6 = (ImageView) view.findViewById(R.id.iv_module6);
        iv_module11 = (ImageView) view.findViewById(R.id.iv_module11);
        iv_module2 = (ImageView) view.findViewById(R.id.iv_module2);
        iv_module1.setOnClickListener(this);
        iv_module4.setOnClickListener(this);
        iv_module7.setOnClickListener(this);
        iv_module6.setOnClickListener(this);
        iv_module11.setOnClickListener(this);
        iv_module2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_module1://公告
                intent = new Intent(getActivity(), Notice_Activity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_module4://文件下载
                intent = new Intent(getActivity(), File_Activity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_module7://提醒
                intent = new Intent(getActivity(), Remind_Activity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_module11://市场活动
                intent = new Intent(getActivity(), MarketPriceActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_module6://帮助
                intent = new Intent(getActivity(), HelpActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.iv_module2://版本信息
                intent = new Intent(getActivity(), VersionActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
