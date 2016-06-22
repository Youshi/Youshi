package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hmqqg.hm.MyApplication;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseRequestActivity;
import com.example.hmqqg.hm.entity.PassEntity;
import com.example.hmqqg.hm.entity.PersonEntity;
import com.example.hmqqg.hm.util.http.MyCommonCallStringRequest;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class ChangePwdActivity extends BaseRequestActivity implements View.OnClickListener{
    private EditText edittext_old_pwd;
    private EditText edittext_new_pwd;
    private EditText edittext_new_pwd2;
    private Button button_save;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        edittext_old_pwd = (EditText) findViewById(R.id.edittext_old_pwd);
        edittext_new_pwd = (EditText) findViewById(R.id.edittext_new_pwd);
        edittext_new_pwd2 = (EditText) findViewById(R.id.edittext_new_pwd2);
        button_save = (Button) findViewById(R.id.button_save);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        button_save.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestSuccess(Object object) {
        PassEntity pe = (PassEntity) object ;
        String str = pe.getStatus().get(0).getStaval();
        if(str.equals("1")){
        Toast.makeText(ChangePwdActivity.this,"密码修改成功~",Toast.LENGTH_SHORT).show();
        finish();
        }if(str.equals("0")){
            Toast.makeText(ChangePwdActivity.this,"原密码错误~",Toast.LENGTH_SHORT).show();
        }

    }
    @Subscribe(threadMode = ThreadMode.MainThread)
    @Override
    public void onRequestError(Throwable ex) {
        Toast.makeText(ChangePwdActivity.this,"网络不稳定，请重试~",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.button_save:
                String old_pwd = edittext_old_pwd.getText().toString();
                String new_pwd = edittext_new_pwd.getText().toString();
                String new_pwd2 = edittext_new_pwd2.getText().toString();
                if(old_pwd==null||old_pwd.equals("")){
                    Toast.makeText(ChangePwdActivity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                String qqzheng = "[0-9][a-z][A-Z]{6,12}";
                Pattern pattern = Pattern.compile(qqzheng);
                Matcher matcher = pattern.matcher(new_pwd);

                if(new_pwd==null||new_pwd.equals("")){
                    Toast.makeText(ChangePwdActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (!matcher.matches()) {
//                    Toast.makeText(ChangePwdActivity.this,"密码必须是6到12位",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(!new_pwd.equals(new_pwd2)){
                    Toast.makeText(ChangePwdActivity.this,"两次密码不相同~",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    RequestParams params = new RequestParams("http://139.129.9.221/mobile/accounthandle.ashx");
                    params.addBodyParameter("action","updatepwd");
                    params.addBodyParameter("userid", MyApplication.getInstance().getUserInfo().getUserId().toString());
                    params.addBodyParameter("pwd",old_pwd);
                    params.addBodyParameter("newpwd",new_pwd);
                    x.http().request(HttpMethod.POST,params,new MyCommonCallStringRequest(new PassEntity()));
                }

                break;
        }
    }
}
