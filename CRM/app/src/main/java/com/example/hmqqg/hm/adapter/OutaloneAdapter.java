package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.MyOutaloneEntity;

import java.util.List;

/**
 * 我的外出反单 适配器
 * Created by Administrator on 2016/1/5.
 */
public class OutaloneAdapter extends BaseAdapter {
    private List<MyOutaloneEntity.DetailInfoEntity> data;
    Context context;
    public OutaloneAdapter(List<MyOutaloneEntity.DetailInfoEntity> list,Context context){
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
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.approval_adapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            holder.approval=(TextView)convertView.findViewById(R.id.approval);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        MyOutaloneEntity.DetailInfoEntity myoutalone = data.get(position);
        holder.name.setText(myoutalone.getApprovalTitle());
        holder.time.setText(myoutalone.getCreatedDate()+"");
        holder.approval.setText(myoutalone.getFlag());
        return convertView;
    }
    public class ViewHolder{
        TextView name;//姓名
        TextView time;//时间日期
        TextView approval;//审核状态
    }
}
