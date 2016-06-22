package com.example.hmqqg.hm.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by bona on 2016/3/19.
 */
public class Work_Adapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public final int COUNT = 4;
    private String[] titles = new String[]{"日报","周报","月报","审批结果"};

    public Work_Adapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new Daily_Fragment1());
        fragmentList.add(new Weekly_Fragment1());
        fragmentList.add(new Monthly_Fragment1());
        fragmentList.add(new Results_Fragment1());
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
