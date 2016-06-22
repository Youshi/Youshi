package com.example.hmqqg.hm.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;

import java.util.ArrayList;

/**
 * 引导页面
 * Created by Administrator on 2016/1/25.
 */
public class WelcomeActivity extends BaseActivity {
    private ViewPager mViewPager;
    private LinearLayout liner;
    private ImageView mPage0;
    private ImageView mPage1;
    private ImageView mPage2;
    private ImageView mPage3;

    SharedPreferences spf;
    private int currIndex = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //存入一个状态是导航界面第一次显示以后不再显示
        spf = getSharedPreferences("count", MODE_WORLD_READABLE);
        int count = spf.getInt("count", 0);
        if (count != 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(WelcomeActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                }
            }, 0);
        }

        SharedPreferences.Editor editor = spf.edit();
        //存入数据
        editor.putInt("count", ++count);
        //提交
        editor.commit();
        setContentView(R.layout.welcome);



        mViewPager = (ViewPager)findViewById(R.id.whatsnew_viewpager);
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());


        mPage0 = (ImageView)findViewById(R.id.page0);
        mPage1 = (ImageView)findViewById(R.id.page1);
        mPage2 = (ImageView)findViewById(R.id.page2);
        mPage3 = (ImageView)findViewById(R.id.page3);

        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.welcome1, null);
        View view2 = mLi.inflate(R.layout.welcome2, null);
        View view3 = mLi.inflate(R.layout.welcome3, null);
        View view4 = mLi.inflate(R.layout.welcome4, null);
        //每个页面的view数据
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }



            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };

        mViewPager.setAdapter(mPagerAdapter);
    }




    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 1:
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 2:
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
                case 3:
                    mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
                    mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
                    break;
//			case 4:
//				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
//				mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
//				mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page));
//				break;
//			case 5:
//				mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
//				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
//				break;
            }
            currIndex = arg0;
            //animation.setFillAfter(true);// True:图片停在动画结束位置
            //animation.setDuration(300);
            //mPageImg.startAnimation(animation);
        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
    public void startbutton(View v) {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this,LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

}




