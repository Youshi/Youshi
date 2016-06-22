package com.example.hmqqg.hm.fragment.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/1/25.
 */
public abstract class BaseRequestFragment extends BaseFragment {
    public ProgressDialog pd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        //初始化进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(

                new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        pd.dismiss();
                    }
                });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public abstract void onRequestSuccess(Object object);

    public abstract void onRequestError(Throwable ex);

}
