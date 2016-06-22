package com.example.hmqqg.hm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hmqqg.hm.ui.Daily_Fragment;
import com.example.hmqqg.hm.ui.Monthly_Fragment;
import com.example.hmqqg.hm.ui.Weekly_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by sojia on 2015/12/31.
 */
public class Vip_ChangePassAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public final int COUNT = 3;
    private String[] titles = new String[]{"日报","周报","月报"};

    public Vip_ChangePassAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new Daily_Fragment());
        fragmentList.add(new Weekly_Fragment());
        fragmentList.add(new Monthly_Fragment());
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
