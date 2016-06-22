package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.MyMonentity;

import java.util.List;

/**
 * 工作审批---月报---评论列表的Adapter适配器
 * Created by Administrator on 2016/5/26.
 */
public class ApprovalMonthly_Adapter extends BaseAdapter {
    private List<MyMonentity.CommentsEntity> data;
    Context context;

    public ApprovalMonthly_Adapter(List<MyMonentity.CommentsEntity> list,Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.approvalmonth_adapter,null);
            holder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_comment=(TextView)convertView.findViewById(R.id.tv_comment);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        MyMonentity.CommentsEntity file = data.get(position);
        holder.tv_name.setText(file.getOperateByName()+"");
        if ("".equals(String.valueOf(file.getOperateMessage()))||String.valueOf(file.getOperateMessage())=="null"){
            holder.tv_comment.setText("暂未评论");
        }else {

            holder.tv_comment.setText(String.valueOf(file.getOperateMessage())+"");
        }
        return convertView;
    }
    public class ViewHolder{
        private TextView tv_name;//评论人姓名
        private TextView tv_comment;//评论内容
    }
}
