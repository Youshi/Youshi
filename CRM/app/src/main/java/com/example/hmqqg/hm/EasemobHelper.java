package com.example.hmqqg.hm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.easemob.EMEventListener;
import com.easemob.EMNotifierEvent;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;

import java.util.List;

/**
 * Created by iwalking11 on 2016/1/13.
 */
public class EasemobHelper {
    private static EasemobHelper instance = null;
    private Context appContext;

    private EasemobHelper() {
    }

    public synchronized static EasemobHelper getInstance() {
        if (instance == null) {
            instance = new EasemobHelper();
        }
        return instance;
    }

    public void init(Context context) {
        appContext = context;

        EMChat.getInstance().init(context);
/**
 * debugMode == true 时为打开，sdk 会在log里输入调试信息
 * @param debugMode
 * 在做代码混淆的时候需要设置成false
 */
        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，避免消耗不必要的资源

//        //只有注册了广播才能接收到新消息，目前离线消息，在线消息都是走接收消息的广播（离线消息目前无法监听，在登录以后，接收消息广播会执行一次拿到所有的离线消息）
//        NewMessageBroadcastReceiver msgReceiver = new NewMessageBroadcastReceiver();
//        IntentFilter intentFilter = new IntentFilter(EMChatManager.getInstance().getNewMessageBroadcastAction());
//        intentFilter.setPriority(3);
//        context.registerReceiver(msgReceiver, intentFilter);
//
//        EMChatManager.getInstance().getChatOptions().setRequireAck(true);
////如果用到已读的回执需要把这个flag设置成true
//        IntentFilter ackMessageIntentFilter = new IntentFilter(EMChatManager.getInstance().getAckMessageBroadcastAction());
//        ackMessageIntentFilter.setPriority(3);
//        context.registerReceiver(ackMessageReceiver, ackMessageIntentFilter);


        EMChatManager.getInstance().registerEventListener(new EMEventListener() {

            @Override
            public void onEvent(EMNotifierEvent event) {

                switch (event.getEvent()) {
                    case EventNewMessage: // 接收新消息
                    {
                        EMMessage message = (EMMessage) event.getData();
                        break;
                    }
                    case EventDeliveryAck: {//接收已发送回执

                        break;
                    }

                    case EventNewCMDMessage: {//接收透传消息

                        break;
                    }

                    case EventReadAck: {//接收已读回执

                        break;
                    }

                    case EventOfflineMessage: {//接收离线消息
                        List<EMMessage> messages = (List<EMMessage>) event.getData();
                        break;
                    }

                    case EventConversationListChanged: {//通知会话列表通知event注册（在某些特殊情况，SDK去删除会话的时候会收到回调监听）

                        break;
                    }

                    default:
                        break;
                }
            }
        }


    );
}

/**
 * 注册接收新消息的监听广播
 */
private class NewMessageBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 注销广播
        abortBroadcast();

        // 消息id（每条消息都会生成唯一的一个id，目前是SDK生成）
        String msgId = intent.getStringExtra("msgid");
        //发送方
        String username = intent.getStringExtra("from");
        // 收到这个广播的时候，message已经在db和内存里了，可以通过id获取mesage对象
        EMMessage message = EMChatManager.getInstance().getMessage(msgId);
        EMConversation conversation = EMChatManager.getInstance().getConversation(username);
        // 如果是群聊消息，获取到group id
        if (message.getChatType() == EMMessage.ChatType.GroupChat) {
            username = message.getTo();
        }
        if (!username.equals(username)) {
            // 消息不是发给当前会话，return
            return;
        }
    }

}

    /**
     * 注册接收ack回执消息的BroadcastReceiver
     */
    private BroadcastReceiver ackMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            abortBroadcast();
            String msgid = intent.getStringExtra("msgid");
            String from = intent.getStringExtra("from");
            EMConversation conversation = EMChatManager.getInstance().getConversation(from);
            if (conversation != null) {
                // 把message设为已读
                EMMessage msg = conversation.getMessage(msgid);
                if (msg != null) {
                    msg.isAcked = true;
                }
            }

        }
    };


}
