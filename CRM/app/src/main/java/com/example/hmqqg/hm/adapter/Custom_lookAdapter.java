package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomLookEntity;
import com.example.hmqqg.hm.entity.CustomerEntity;

import java.util.List;

/**
 * 客户浏览适配器
 * Created by Administrator on 2016/1/3.
 */
public class Custom_lookAdapter extends BaseAdapter {
    private List<CustomLookEntity.DetailInfoEntity> data;
    Context context;
    public Custom_lookAdapter(List<CustomLookEntity.DetailInfoEntity> list, Context context){
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
        convertView = LayoutInflater.from(context).inflate(R.layout.custom_lookadapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.number= (TextView) convertView.findViewById(R.id.number);
            holder.intention=(TextView)convertView.findViewById(R.id.intention);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        CustomLookEntity.DetailInfoEntity cd = data.get(position);
        holder.name.setText(cd.getUserName()+"");
        holder.number.setText(cd.getPhone()+"");
        holder.intention.setText(cd.getUserGradeName()+"");
        return convertView;
    }
        public class ViewHolder{
            private TextView name;
            private TextView number;
            private TextView intention;
        }
}
