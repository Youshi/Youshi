package com.example.hmqqg.hm.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.swipeback.SwipeBackLayout;

import de.greenrobot.event.EventBus;

/**
 * Created by iwalking11 on 2016/1/19.
 */
public abstract class BaseRequestActivity extends BaseActivity {
    public ProgressDialog pd;

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
        EventBus.getDefault().register(this);

        //初始化进度条对话框
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        pd.dismiss();
                    }
                });
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

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public abstract void onRequestSuccess(Object object) ;

    public abstract void onRequestError(Throwable ex) ;
}
