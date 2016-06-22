package com.example.hmqqg.hm.ui;

import android.app.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMContactManager;
import com.easemob.exceptions.EaseMobException;
import com.example.hmqqg.hm.DemoHelper;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.view.CustomDialog;

import java.util.List;

/**
 * 添加好友
 * Created by Administrator on 2016/4/23.
 */
public class AddContactActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;//返回
    private TextView title_top_bar;//标题
    private TextView refresh;//完成按钮
    private EditText edit_note;//搜索输入框
    private LinearLayout searchedUserLayout;//显示查找到的好友的界面
    private ImageView avatar;//头像
    private TextView name;//用户名
    private Button indicator;//添加好友按钮

    private boolean progressShow;
    private ProgressDialog pd;
    String username;//获取查找的用户名称
    String mynames;
    CustomDialog.Builder builder;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        iniView();
    }

    private void iniView() {
        back = (ImageView) findViewById(R.id.back);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        title_top_bar.setText("添加好友");
        refresh = (TextView) findViewById(R.id.refresh);
        refresh.setText("查找");
        edit_note = (EditText) findViewById(R.id.edit_note);
        searchedUserLayout = (LinearLayout) findViewById(R.id.ll_user);
        avatar = (ImageView) findViewById(R.id.avatar);
        name = (TextView) findViewById(R.id.name);
        indicator = (Button) findViewById(R.id.indicator);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        indicator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back://返回
                onBackPressed();
                break;
            case R.id.refresh://查找
                search();
                break;
            case R.id.indicator://添加好友
                showAlertDialog1();
                break;
        }
    }
    private void search() {
        username = edit_note.getText().toString();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(AddContactActivity.this, "请输入用户账号", Toast.LENGTH_SHORT).show();
            return;
        }
        searchedUserLayout.setVisibility(View.VISIBLE);
        name.setText(username);
    }
    private void showAlertDialog1() {

        builder = new CustomDialog.Builder(this);
        builder.setMessage("");
        builder.setTitle("请输入添加理由");
        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = builder.getMessage();//获取弹框输入内容
                dialog.dismiss();
                addContact();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }
    private void addContact() {
//        try {
//            List<String> names = EMContactManager.getInstance().getContactUserNames();
//            mynames = names.get(0).toString();
//        } catch (EaseMobException e) {
//            e.printStackTrace();
//        }
//        if(mynames.equals(username)){
//            Toast.makeText(AddContactActivity.this, "不能添加您自己为好友！", Toast.LENGTH_SHORT).show();
//            return;
//        }
        //此处应写所添加的好友是否已经存在您的好友列表中。现在暂未实现



        progressShow = true;
        pd = new ProgressDialog(AddContactActivity.this);
        pd.setCanceledOnTouchOutside(false);
        pd.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
        pd.setMessage("正在添加,请稍候...");
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMContactManager.getInstance().addContact(username,result);
                    pd.dismiss();
                    progressShow=false;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddContactActivity.this, "发送添加好友请求成功，等待对方添加您为好友~", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (EaseMobException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
