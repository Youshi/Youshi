package com.example.hmqqg.hm.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.DataResult2;
import com.example.hmqqg.hm.entity.LoginEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;
import com.facebook.drawee.view.SimpleDraweeView;

import org.xutils.common.util.LogUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 测试网络加载数据示例
 * 继承父类BaseRequestActivity
 * 会要求重写两个方法onRequestSuccess(Object object),onRequestError(Throwable ex)
 * 在这两个方法里面进行ui更新
 */
public class DemoActivity2 extends BaseRequestActivity implements View.OnClickListener {

    private TextView textView;
    private SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.textView);

//        String userName = MyApplication.getInstance().getUserInfo().getUserName();
//        textView.setText(userName);

        draweeView = ((SimpleDraweeView) findViewById(R.id.image));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                requestData();//发起网络请求

                break;
        }
    }

    /**
     * 网络请求加载成功的时候调用的这方法
     * 重写父类方法的时候要记得加上这句注解
     *  @Subscribe(threadMode = ThreadMode.MainThread)
     *  其中ThreadMode提供了四个常量：
     *  MainThread 主线程
     *  BackgroundThread 后台线程
     *  Async 后台线程
     *  PostThread 发送线程（默认）
     * @param object
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        DataResult2 dataResult2 = (DataResult2) object;

        textView.setText(dataResult2.getResult().getTitle().toString());//设置电影标题

        /**
         * 图片记载示例
         */
        String image = dataResult2.getResult().getAct_s().get(0).getImage();
        dataResult2.getResult().getAct_s().get(0).getImage();//获取图片的url地址
        LogUtil.e(image);
        draweeView.setImageURI(Uri.parse(image));//通过网络请求设置图片

    }

    /**
     * 网络请求加载失败的时候回调这个方法
     * @param ex
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        final Snackbar snackbar = Snackbar.make(textView, "网络加载失败", Snackbar.LENGTH_SHORT);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).show();
    }

    /**
     * 网络请求具体操作示例
     */
    private void requestData() {
        pd.setMessage("网络数据加载中,请稍候...");
        pd.show();
        RequestParams requestParams = new RequestParams("http://op.juhe.cn/onebox/movie/video");
//        requestParams.addBodyParameter("upImage",new File("1.jpg"));
        requestParams.addBodyParameter("key", "8aedfab894a771b88f6257c36a7dc44e");
        requestParams.addBodyParameter("q", "寻龙诀");
        x.http().request(HttpMethod.GET, requestParams, new MyCommonCallStringRequest(new DataResult2(),pd));

    }
}
