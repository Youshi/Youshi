package com.example.hmqqg.hm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hmqqg.hm.ui.Customer_Remind;
import com.example.hmqqg.hm.ui.Product_Remind;
import com.example.hmqqg.hm.ui.Staff_Remind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bona on 2016/3/19.
 */
public class Remind_Adapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    public final int COUNT = 3;
    private String[] titles = new String[]{"员工生日提醒","客户生日提醒","投资产品提醒"};
    public Remind_Adapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new Staff_Remind());
        fragmentList.add(new Customer_Remind());
        fragmentList.add(new Product_Remind());
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

