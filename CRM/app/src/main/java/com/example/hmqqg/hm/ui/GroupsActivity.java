/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hmqqg.hm.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.chat.EMGroup;
import com.easemob.chat.EMGroupManager;
import com.easemob.exceptions.EaseMobException;
import com.easemob.util.EMLog;
import com.example.hmqqg.hm.DemoHelper;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.activity.BaseActivity;
import com.example.hmqqg.hm.activity.MainActivity;
import com.example.hmqqg.hm.activity.chat.ChatActivity;
import com.example.hmqqg.hm.adapter.GroupAdapter;
import com.example.hmqqg.hm.entity.FinishApplication;

import java.util.List;

public class GroupsActivity extends BaseActivity implements View.OnClickListener{
	public static final String TAG = "GroupsActivity";
	private ListView groupListView;
	protected List<EMGroup> grouplist;
	private GroupAdapter groupAdapter;
	private InputMethodManager inputMethodManager;
	public static GroupsActivity instance;
	private SyncListener syncListener;
	private View progressBar;
	private SwipeRefreshLayout swipeRefreshLayout;
	Handler handler = new Handler();
	private ImageView back;//返回
	private TextView title_top_bar;
	private ProgressDialog pd;


	class SyncListener implements DemoHelper.HXSyncListener {
		@Override
		public void onSyncSucess(final boolean success) {
			EMLog.d(TAG, "onSyncGroupsFinish success:" + success);
			runOnUiThread(new Runnable() {
				public void run() {
					swipeRefreshLayout.setRefreshing(false);
					if (success) {
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {
								refresh();
								progressBar.setVisibility(View.GONE);
							}
						}, 1000);
					} else {
						if (!GroupsActivity.this.isFinishing()) {
							String s1 = getResources()
									.getString(
											R.string.Failed_to_get_group_chat_information);
							Toast.makeText(GroupsActivity.this, s1, Toast.LENGTH_LONG).show();
							progressBar.setVisibility(View.GONE);
						}
					}
				}
			});
		}
	}
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_groups);
        FinishApplication.getInstance().addAcitivity(GroupsActivity.this);
		instance = this;
		inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//		grouplist = EMGroupManager.getInstance().getAllGroups();//本地获得群组列表
		iniView();
		progressD();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					grouplist = EMGroupManager.getInstance().getGroupsFromServer();//获得群组列表
					if(grouplist!=null){
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								setGList();
								pd.dismiss();
							}
						});
					}
				} catch (EaseMobException e) {
					e.printStackTrace();
				}
			}
		}).start();
//		refresh();//添加数据到内存中
	}

	private void progressD() {
		pd = new ProgressDialog(GroupsActivity.this);
		pd.setCanceledOnTouchOutside(false);
		pd.setOnCancelListener(

				new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {
					}
				});
		pd.setMessage("正在加载,请稍候...");
		pd.show();
	}

	private void iniView() {
		groupListView = (ListView) findViewById(R.id.list);//群组
		syncListener = new SyncListener();
		title_top_bar = (TextView) findViewById(R.id.title_top_bar);
		back = (ImageView) findViewById(R.id.back);
		title_top_bar.setText("群聊");
		progressBar = (View)findViewById(R.id.progress_bar);
		back.setOnClickListener(this);

		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);//谷歌自带的刷新方法。暂时不使用
		swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				MainActivity.asyncFetchGroupsFromServer();
				progressBar.setVisibility(View.GONE);
			}
		});
//		DemoHelper.getInstance().addSyncGroupListener(syncListener);
		if (!DemoHelper.getInstance().isGroupsSyncedWithServer()) {
			progressBar.setVisibility(View.VISIBLE);
		} else {
			progressBar.setVisibility(View.GONE);
		}

	}

	private void setGList() {
		groupAdapter = new GroupAdapter(this, 1, grouplist);
		groupListView.setAdapter(groupAdapter);
		groupListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == 1) {
					// 新建群聊
					startActivityForResult(new Intent(GroupsActivity.this, NewGroupActivity.class), 0);
				} else if (position == 2) {
					// 添加公开群
					startActivityForResult(new Intent(GroupsActivity.this, PublicGroupsActivity.class), 0);
				} else {
					// 进入群聊
					Intent intent = new Intent(GroupsActivity.this, ChatActivity.class);
					// it is group chat
					intent.putExtra("chatType", ChatActivity.CHATTYPE_GROUP);
					String groupId = groupAdapter.getItem(position - 3).getGroupId();
					intent.putExtra("userId", groupId);
					startActivityForResult(intent, 0);
				}
			}

		});
		groupListView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
					if (getCurrentFocus() != null)
						inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				}
				return false;
			}
		});
	}

	/**
	 * 进入公开群聊列表
	 */
	public void onPublicGroups(View view) {
		startActivity(new Intent(this, PublicGroupsActivity.class));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onResume() {
		super.onResume();
		grouplist = EMGroupManager.getInstance().getAllGroups();
		groupAdapter = new GroupAdapter(this, 1, grouplist);
		groupListView.setAdapter(groupAdapter);
		groupAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onDestroy() {
		if (syncListener != null) {
			DemoHelper.getInstance().removeSyncGroupListener(syncListener);
			syncListener = null;
		}
		super.onDestroy();
		instance = null;
	}
	
	public void refresh() {
		if (groupListView != null && groupAdapter != null) {
			grouplist = EMGroupManager.getInstance().getAllGroups();
			groupAdapter = new GroupAdapter(GroupsActivity.this, 1,
					grouplist);
			groupListView.setAdapter(groupAdapter);
			groupAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 返回
	 *
	 * @param view
	 */
	public void back(View view) {
		finish();
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
