package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.TouZiEntity;

import java.util.List;

/**
 * 投资列表适配器
 * Created by bona on 2016/3/19.
 */
public class Invest_Adapter extends BaseAdapter {
    private List<TouZiEntity.DetailInfoEntity> data;
    Context context;
    public Invest_Adapter(List<TouZiEntity.DetailInfoEntity> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.invest_adapter,null);
            holder.product=(TextView)convertView.findViewById(R.id.product);
            holder.money=(TextView)convertView.findViewById(R.id.money);
            holder.day=(TextView)convertView.findViewById(R.id.day);
                convertView.setTag(holder);
            }else {
                holder=(ViewHolder)convertView.getTag();
        }
        TouZiEntity.DetailInfoEntity tou = data.get(position);
        holder.product.setText(tou.getInvestName()+"");
        holder.money.setText(tou.getInvestSum()+"");
        holder.day.setText(tou.getStartDate()+"");
        return convertView;
    }
    public class ViewHolder{
        private TextView product;
        private TextView money;
        private TextView day;
    }
}
