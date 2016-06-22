package com.example.hmqqg.hm.util.http;

import android.app.ProgressDialog;

import com.google.gson.Gson;

import org.xutils.common.Callback;

import de.greenrobot.event.EventBus;

/**
 * Created by iwalking11 on 2016/1/19.
 */
public class MyCommonCallStringRequest implements Callback.CommonCallback<String> {
    private Object object;//用于解析 json 数据的对象,需要什么就传递什么
    private ProgressDialog pd;

    public MyCommonCallStringRequest(Object object, ProgressDialog pd) {
        this.object = object;
        this.pd = pd;
    }

    public MyCommonCallStringRequest(Object object) {
        this.object = object;
    }

    @Override
    public void onSuccess(String result) {
        object = new Gson().fromJson(result, object.getClass());//解析最终生成的对象类型 是由object.getClass() 来决定的,也就是说你传递进来了什么类型最终就解析成什么类型
        EventBus.getDefault().post(object);
        if (pd != null) {
            pd.dismiss();
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        if (pd != null) {
            pd.dismiss();
        }
//        if (ex instanceof HttpException) { // 网络错误
//            HttpException httpEx = (HttpException) ex;
//            int responseCode = httpEx.getCode();
//            String responseMsg = httpEx.getMessage();
//            String errorResult = httpEx.getResult();
//            // ...
//        } else { // 其他错误
//            // ...
//        }
        EventBus.getDefault().post(ex);
    }

    @Override
    public void onCancelled(CancelledException cex) {
        if (pd != null) {
            pd.dismiss();
        }
    }

    @Override
    public void onFinished() {
        if (pd != null) {
            pd.dismiss();
        }
    }
}
