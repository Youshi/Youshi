package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.MarketingEntity2;

import java.util.List;

/**
 * 市场审批结果
 * Created by bona on 2016/3/8.
 */
public class Marketing_Adapter2 extends BaseAdapter {
    private List<MarketingEntity2.DetailInfoEntity> data;
    Context context;
    public Marketing_Adapter2(List<MarketingEntity2.DetailInfoEntity> list,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.market_adapter2,null);
            holder.market_name=(TextView)convertView.findViewById(R.id.market_name);
            holder.market_time= (TextView) convertView.findViewById(R.id.market_time);
            holder.market_approval= (TextView) convertView.findViewById(R.id.market_approval);//审批状态
            holder.market_substance= (TextView) convertView.findViewById(R.id.market_substance);//审批简介
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        MarketingEntity2.DetailInfoEntity me =data.get(position);
        String state = me.getState().toString();
//        if(state.equals("N")){
//            holder.market_approval.setTextColor(convertView.getResources().getColor(R.color.orange));
//            holder.market_approval.setText("未审核");
//        }else
        if(state.equals("已通过")){
            holder.market_approval.setTextColor(convertView.getResources().getColor(R.color.green));
            holder.market_approval.setText("通过");
        }else if(state.equals("未通过")){
            holder.market_approval.setTextColor(convertView.getResources().getColor(R.color.red));
            holder.market_approval.setText("驳回");
        }

        holder.market_name.setText(me.getActTitle());
        holder.market_time.setText(me.getActDate());
        holder.market_substance.setText(me.getActPlan());
        return convertView;
    }
    public class ViewHolder{
        private TextView market_name;//活动主题
        private TextView market_approval;//审批状态
        private TextView market_substance;//审批目的
        private TextView market_time;//时间

    }
}
