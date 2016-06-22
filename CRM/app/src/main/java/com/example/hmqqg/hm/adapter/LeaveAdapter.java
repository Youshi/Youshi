package com.example.hmqqg.hm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hmqqg.hm.fragment.Meet_Fragment;
import com.example.hmqqg.hm.fragment.Out_Fragment;
import com.example.hmqqg.hm.ui.My_ApplyOut_Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/7.
 */
public class LeaveAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public final int COUNT = 3;
    private String[] titles = new String[]{"我的申请","申请结果","下级申请"};

    public LeaveAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new Leave_frag());//
        fragmentList.add(new My_ApplyOut_Result());//申请结果
        fragmentList.add(new Out_Fragment());//
    }
    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
