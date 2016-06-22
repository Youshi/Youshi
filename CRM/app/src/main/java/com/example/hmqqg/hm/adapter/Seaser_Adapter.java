package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomerEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/16.
 */
public class Seaser_Adapter extends BaseAdapter {
    private List<CustomerEntity.DetailInfoEntity> data;
    Context context;
    public Seaser_Adapter(List<CustomerEntity.DetailInfoEntity> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.seaser_adapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.number=(TextView)convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        CustomerEntity.DetailInfoEntity cd = data.get(position);
        holder.name.setText(cd.getUserName()+"");
        holder.number.setText(cd.getPhone()+"");
        return convertView;
    }
    public class ViewHolder{
        private TextView name;
        private TextView number;


    }
}
