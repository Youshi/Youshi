package com.example.hmqqg.hm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hmqqg.hm.ui.Apply_Fragment;
import com.example.hmqqg.hm.ui.Apply_frag;
import com.example.hmqqg.hm.ui.Apply_result;
import com.example.hmqqg.hm.ui.Daily_Fragment;
import com.example.hmqqg.hm.ui.Monthly_Fragment;
import com.example.hmqqg.hm.ui.Weekly_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请的 审批的 adapter
 */
public class Approvaladapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public final int COUNT = 3;
    private String[] titles = new String[]{"外出单","请假单","申请结果"};

    public Approvaladapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new Apply_Fragment());
        fragmentList.add(new Apply_frag());
        fragmentList.add(new Apply_result());
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
