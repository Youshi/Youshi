package com.example.hmqqg.hm.entity;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * 添加Acitivity到此容器中，以便退出时全部关闭
 * Created by Administrator on 2016/4/26.
 */
public class FinishApplication extends Application {
    private List<Activity> activityList = new LinkedList<Activity>();
    private static FinishApplication instance;

    public static FinishApplication getInstance() {
        if(instance==null){
            instance = new FinishApplication();
        }
        return instance;
    }

    public static void setInstance(FinishApplication instance) {
        FinishApplication.instance = instance;
    }

    //添加Activity到容器
    public  void addAcitivity(Activity activity) {
        activityList.add(activity);
    }

    //遍历所有activity，并关闭
    public void exit(){
        for(Activity activity :activityList){
            activity.finish();
        }
    }


}
