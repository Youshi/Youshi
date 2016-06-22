package com.example.hmqqg.hm.common;


import easeui.EaseConstant;

/**
 * 存放一些公共常量
 * Created by iwalking11 on 2016/1/7.
 */
public class Constant extends EaseConstant{

    public static final String HOST = "http://139.129.9.221/mobile/accounthandle.ashx";

    /**
     * 跳转到LocationActivity页面startActivityForResult传的参数
     */
    public static final int REQUEST_LOCATION = 1;
    /**
     * setResult的参数
     */
    public static final int RESULT_LOCATION = 2;

    /**
     * 加载更多
     */
    public static final int LOADMORE = 3;
    /**
     * 刷新数据
     */
    public static final int REFRESH = 4;

    public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
    public static final String GROUP_USERNAME = "item_groups";
    public static final String CHAT_ROOM = "item_chatroom";
    public static final String ACCOUNT_REMOVED = "account_removed";
    public static final String ACCOUNT_CONFLICT = "conflict";
    public static final String CHAT_ROBOT = "item_robots";
    public static final String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
    public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
    public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";

}
