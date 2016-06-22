package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.Invest_Entity;
import com.example.hmqqg.hm.entity.TouZiEntity;

import java.util.List;

/**
 * 产品提醒列表
 * Created by bona on 2016/3/19.
 */
public class Product_Adapter extends BaseAdapter {
    private List<Invest_Entity.DetailInfoEntity> data;
    Context context;
    public Product_Adapter(List<Invest_Entity.DetailInfoEntity> list, Context context){
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
        ViewHolder holder ;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.product_adapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.money=(TextView)convertView.findViewById(R.id.money);
            holder.invest_name=(TextView)convertView.findViewById(R.id.invest_name);
            holder.endTime=(TextView)convertView.findViewById(R.id.endTime);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        Invest_Entity.DetailInfoEntity cd = data.get(position);
        holder.name.setText(cd.getUserName());
        holder.invest_name.setText(cd.getInvestName());
        holder.money.setText(String.valueOf(cd.getInvestSum()));
        holder.endTime.setText(cd.getEndDate());
        return convertView;
    }
    public class ViewHolder{
        private TextView name;//投资人姓名
        private TextView money;//投资产品金额
        private TextView invest_name;//投资产品名称
        private TextView endTime;//产品到期时间

    }
}
