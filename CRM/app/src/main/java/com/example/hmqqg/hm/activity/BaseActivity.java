package com.example.hmqqg.hm.activity;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.swipeback.SwipeBackLayout;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by iwalking11 on 2015/12/26.
 */
public class BaseActivity extends AppCompatActivity {
    public SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        overridePendingTransition(R.anim.activity_ani_enter, 0);
//
//        getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        getWindow().getDecorView().setBackgroundDrawable(null);
//        swipeBackLayout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
//                R.layout.base, null);
//        swipeBackLayout.attachToActivity(this);

        //解决软键盘遮挡界面
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.statusbar_bg);//通知栏所需颜色

    }

//    public void setSwipeBackEnable(boolean enable) {
//        swipeBackLayout.setEnableGesture(enable);
//    }
//
//    /**
//     * 返回键调成此方法
//     */
//    @Override
//    public void onBackPressed() {
//        // TODO Auto-generated method stub
//        super.onBackPressed();
//        overridePendingTransition(0, R.anim.activity_ani_exist);
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(0, R.anim.activity_ani_exist);
//    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}

