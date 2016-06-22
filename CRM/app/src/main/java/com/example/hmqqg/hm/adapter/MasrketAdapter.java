package com.example.hmqqg.hm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hmqqg.hm.ui.Apply_Fragment;
import com.example.hmqqg.hm.ui.Apply_frag;
import com.example.hmqqg.hm.ui.MarketWaitApplyFragment;
import com.example.hmqqg.hm.ui.MarketWaitApplyFragment2;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场活动（适配器）
 * Created by Administrator on 2016/3/8.
 */
public class MasrketAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public final int COUNT = 2;
    private String[] titles = new String[]{"我的审批","申批结果"};

    public MasrketAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new MarketWaitApplyFragment());
        fragmentList.add(new MarketWaitApplyFragment2());
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
