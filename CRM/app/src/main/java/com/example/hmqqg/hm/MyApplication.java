package com.example.hmqqg.hm;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;

import com.baidu.mapapi.SDKInitializer;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

import java.util.Iterator;
import java.util.List;

/**
 * Created by iwalking11 on 2015/12/26.
 */
public class MyApplication extends Application {
    //在内存中存储用户信息
    private LoginEntity.DetailInfoEntity userInfo;
    private Context context;
    private static MyApplication myApplication;

    public static MyApplication getInstance()

    {
        return myApplication;
    }

    // login user name
    public final String PREF_USERNAME = "username";

    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .enableLogging() // Not necessary in common
                .build();
        ImageLoader.getInstance().init(config);



        Fresco.initialize(this);

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        myApplication = this;

    //在使用 百度地图SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
    // 如果app启用了远程的service，此application:onCreate会被调用2次
    // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
    // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase("com.example.hmqqg.crm")) {
            //"com.easemob.chatuidemo"为demo的包名，换到自己项目中要改成自己包名

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        DemoHelper.getInstance().init(this);


    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    public LoginEntity.DetailInfoEntity getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(LoginEntity.DetailInfoEntity userInfo) {
        this.userInfo = userInfo;
    }
}
