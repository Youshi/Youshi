package com.example.hmqqg.hm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.adapter.NewFriendsMsgAdapter;
import com.example.hmqqg.hm.common.Constant;
import com.example.hmqqg.hm.domain.InviteMessage;

import java.util.List;

import easeui.DemoHXSDKHelper;
import easeui.applib.controller.HXSDKHelper;
import easeui.db.InviteMessgeDao;

/**申请与通知详情页
 * Created by bona on 2016/4/23.
 */
public class NewFriendsMsgActivity extends BaseActivity implements View.OnClickListener{
    private ListView listView;
    private ImageView back;//返回
    private TextView title_top_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friends_msg);
        title_top_bar = (TextView) findViewById(R.id.title_top_bar);
        back = (ImageView) findViewById(R.id.back);
        title_top_bar.setText("申请与通知");
        back.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.list);
        InviteMessgeDao dao = new InviteMessgeDao(this);

        List<InviteMessage> msgs = dao.getMessagesList();
        //设置adapter
        NewFriendsMsgAdapter adapter = new NewFriendsMsgAdapter(this, 1, msgs);
        listView.setAdapter(adapter);
        dao.saveUnreadMessageCount(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
        }
    }
}
