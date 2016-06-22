package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.hmqqg.hm.R;

import java.util.List;

/**
 * 市场活动列表适配器
 * Created by Administrator on 2016/1/3.
 */
public class Marketing_Adapter extends BaseAdapter {
    private List<MarketEntity.DetailInfoEntity> data;
    Context context;
    public Marketing_Adapter(List<MarketEntity.DetailInfoEntity> list,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.market_adapter,null);
            holder.market_name=(TextView)convertView.findViewById(R.id.market_name);
            holder.market_time= (TextView) convertView.findViewById(R.id.market_time);
//            holder.market_approval= (TextView) convertView.findViewById(R.id.market_approval);//审批状态
            holder.market_substance= (TextView) convertView.findViewById(R.id.market_substance);//审批简介
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        MarketEntity.DetailInfoEntity me =data.get(position);
        holder.market_name.setText(me.getActTitle());
        holder.market_time.setText(me.getActDate());
        holder.market_substance.setText(me.getActPurpose());
        return convertView;
    }
    public class ViewHolder{
        private TextView market_name;//活动主题
        private TextView market_substance;//审批简介
        private TextView market_time;//时间

    }
}
