package com.example.hmqqg.hm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hmqqg.hm.ui.Apply_Fragment;
import com.example.hmqqg.hm.ui.Apply_frag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/21.
 */
public class Applyforadapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public final int COUNT = 2;
    private String[] titles = new String[]{"我的申请","申请结果"};

    public Applyforadapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new Apply_Fragment());
        fragmentList.add(new Apply_frag());
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
