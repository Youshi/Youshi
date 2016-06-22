package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.SubmitSuperior_Entity;
import com.example.hmqqg.hm.entity.WeekEntity_d;

import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class Reportde_adapter extends BaseAdapter {
    private List<WeekEntity_d.CommentsEntity> data;
    Context context;

    public Reportde_adapter(List<WeekEntity_d.CommentsEntity> list, Context context) {
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
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.reprotde_adapter, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WeekEntity_d.CommentsEntity cd = data.get(position);
        holder.name.setText(cd.getOperateByName()+"");
        if ("".equals(cd.getOperateMessage())||cd.getOperateMessage()==null){
            holder.number.setText("暂未评论");
        }else {
            holder.number.setText(cd.getOperateMessage()+"");
        }
        return convertView;
    }

    public class ViewHolder {
        private TextView name;
        private TextView number;


    }
}
