package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;

import java.util.List;

/**
 * 主页面fragment适配器
 * Created by iwalking11 on 2015/12/26.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public final int COUNT = 5;
    private String[] titles = new String[]{"首页", "消息", "签到", "管理", "应用"};
    private Context context;


    public MyFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
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

    /**
     * 设置自定义布局
     *
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(titles[position]);

        Resources resources = context.getResources();
        Drawable drawable1 = resources.getDrawable(R.drawable.home_icon_selector);
        Drawable drawable2 = resources.getDrawable(R.drawable.list_icon_selector);
        Drawable drawable3 = resources.getDrawable(R.drawable.search_icon_selector);
        Drawable drawable4 = resources.getDrawable(R.drawable.shopcar_icon_selector);
        Drawable drawable5 = resources.getDrawable(R.drawable.vip_icon_selector);
        Drawable[] drawables = {drawable1, drawable2, drawable3, drawable4, drawable5};
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageDrawable(drawables[position]);
        return view;
    }
}
