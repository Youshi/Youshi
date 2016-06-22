package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.LeaveEntity;

import java.util.List;

/**
 * 我的请假单 适配器
 * Created by BONA on 2016/1/5.
 */
public class MeetAdapter extends BaseAdapter {
    private List<LeaveEntity.DetailInfoEntity> data;
    Context context;
    public MeetAdapter(List<LeaveEntity.DetailInfoEntity> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.approval_adapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            holder.approval=(TextView)convertView.findViewById(R.id.approval);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        LeaveEntity.DetailInfoEntity me =data.get(position);
        holder.name.setText(me.getApprovalTitle().toString());
        holder.time.setText(me.getCreatedDate()+"");
        if ("空值".equals(me.getFlag().toString())){
            holder.approval.setText("提交");
        }else {
            holder.approval.setText(me.getFlag().toString());
        }
        return convertView;
    }
    public class ViewHolder{
        private TextView name;//姓名
        private TextView approval;//审核状态
        private TextView time;//时间
    }
}
