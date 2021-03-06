package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomerEntity;

import java.util.List;

/**
 * 客户资料浏览适配器
 * Created by Administrator on 2016/1/11.
 */
public class Seas_Adapter extends BaseAdapter{
    private List<CustomerEntity.DetailInfoEntity> data;
    Context context;
    public Seas_Adapter(List<CustomerEntity.DetailInfoEntity> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.customer_adapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.number=(TextView)convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.name.setText("赵宜");
        holder.number.setText("15166866385");
        return convertView;
    }
    public class ViewHolder{
        private TextView name;
        private TextView number;
    }
}
