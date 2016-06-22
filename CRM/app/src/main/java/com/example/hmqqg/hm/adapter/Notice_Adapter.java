package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.NoticeEntity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公告浏览适配器
 * Created by Administrator on 2016/1/3.
 */
public class Notice_Adapter extends BaseAdapter {
    private List<NoticeEntity.DetailInfoEntity> data;
    Context context;
    public Notice_Adapter(List<NoticeEntity.DetailInfoEntity> list,Context context){
        super();
        this.data = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.notices_adapter,null);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            holder.content=(TextView)convertView.findViewById(R.id.content);
//            holder.title=(TextView)convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        NoticeEntity.DetailInfoEntity not = data.get(position);
        holder.time.setText(not.getSendTime());
//        holder.title.setText(delHtml(not.getMContent()).replaceAll("\\s",""));
        holder.content.setText(not.getTitle());
//        time.setText("上午8:30");
//        content.setText("明天早上8点公司全员开会");
//        title.setText("重要通知");

        return convertView;
    }
    public class ViewHolder{
        //        private LinearLayout imagehead;
        private TextView time;
        private TextView content;
//        private TextView title;
    }
    public static String delHtml(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            String regEx_script = "<[/s]*?script[^>]*?>[/s/S]*?<[/s]*?//[/s]*?script[/s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[/s/S]*?<//script>

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }
}
