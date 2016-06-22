package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.PassEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 修改密码
 * Created by Administrator on 2016/5/9.
 */
public class Password_Activity extends BaseRequestActivity implements View.OnClickListener{
    private ImageView back;//返回
    private TextView title_top_bar;
    private EditText edittext_old_pwd;//旧密码
    private EditText edittext_new_pwd1;//新密码
    private EditText edittext_new_pwd2;//新密码
    private Button button_save;//确认修改

    private String Sold_pwd;
    private String Snew_pwd1;
    private String Snew_pwd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        edittext_old_pwd = (EditText) findViewById(R.id.edittext_old_pwd);
        edittext_new_pwd1 = (EditText) findViewById(R.id.edittext_new_pwd);
        edittext_new_pwd2 = (EditText) findViewById(R.id.edittext_new_pwd2);
        button_save = (Button) findViewById(R.id.button_save);
        button_save.setOnClickListener(this);
        title_top_bar.setText("修改密码");
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.button_save:
                Sold_pwd = edittext_old_pwd.getText().toString();
                Snew_pwd1 = edittext_new_pwd1.getText().toString();
                Snew_pwd2 = edittext_new_pwd2.getText().toString();
                if (null == Sold_pwd||"".equals(Sold_pwd)){
                    Toast.makeText(this,"旧密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (null == Snew_pwd1||"".equals(Snew_pwd1)){
                    Toast.makeText(this,"新密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (null == Snew_pwd2||"".equals(Snew_pwd2)){
                    Toast.makeText(this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Snew_pwd1.equals(Snew_pwd2)){
                    Toast.makeText(this,"两次密码不一致，请重新确认",Toast.LENGTH_SHORT).show();
                    return;
                }
                gethttp();
                break;
        }
    }

    private void gethttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestParams params = new RequestParams(getResources().getString(R.string.http_service));
                params.addBodyParameter("action","updatepwd");
                params.addBodyParameter("userid", MyApplication.getInstance().getUserInfo().getUserId().toString());
                params.addBodyParameter("pwd",Sold_pwd);
                params.addBodyParameter("newpwd",Snew_pwd1);
            x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new PassEntity()));
            }
        }).start();
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        PassEntity pass = (PassEntity) object;
        String str = pass.getStatus().get(0).getStaval().toString();
        if("1".equals(str)){
            Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
            finish();
        }else if("0".equals(str)){
            Toast.makeText(this, "修改失败,请稍后重试！", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(this, "您的网络不稳定,请稍后重试！", Toast.LENGTH_SHORT).show();
    }
}
