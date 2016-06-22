package com.example.hmqqg.hm.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.fragment.base.BaseFragment;
import com.example.hmqqg.hm.ui.CustomLook_Activity;
import com.example.hmqqg.hm.ui.Lookreport_Activity;
import com.example.hmqqg.hm.ui.Organization_Activity;
import com.example.hmqqg.hm.ui.Outalone_Activity;

/**
 * 首页
 */
public class ManageFragment extends BaseFragment implements View.OnClickListener{
    private LinearLayout linearlayout1;//组织架构管理
    private LinearLayout linearlayout2;//上传文件
    private LinearLayout linearlayout3;//发布公告
    private LinearLayout linearlayout4;//浏览员工客户
    private LinearLayout linearlayout5;//浏览员工工作汇报
    private LinearLayout linearlayout6;//员工约见单/外出单
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearlayout1 = (LinearLayout) view.findViewById(R.id.linearlayout1);
        linearlayout2 = (LinearLayout) view.findViewById(R.id.linearlayout2);
        linearlayout3 = (LinearLayout) view.findViewById(R.id.linearlayout3);
        linearlayout4 = (LinearLayout) view.findViewById(R.id.linearlayout4);
        linearlayout5 = (LinearLayout) view.findViewById(R.id.linearlayout5);
        linearlayout6 = (LinearLayout) view.findViewById(R.id.linearlayout6);
        linearlayout1.setOnClickListener(this);
        linearlayout2.setOnClickListener(this);
        linearlayout3.setOnClickListener(this);
        linearlayout4.setOnClickListener(this);
        linearlayout5.setOnClickListener(this);
        linearlayout6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()){
            case R.id.linearlayout1://组织架构管理
                intent = new Intent(getActivity(), Organization_Activity.class);
                startActivity(intent);
                break;
            case R.id.linearlayout4://浏览员工客户
                intent = new Intent(getActivity(), CustomLook_Activity.class);
                startActivity(intent);
                break;
            case R.id.linearlayout5://浏览员工工作汇报
                intent = new Intent(getActivity(), Lookreport_Activity.class);
                startActivity(intent);
                break;
            case R.id.linearlayout6://员工约见单/外出单
                intent = new Intent(getActivity(), Outalone_Activity.class);
                startActivity(intent);
                break;

            default:
            break;
        }
    }


}
