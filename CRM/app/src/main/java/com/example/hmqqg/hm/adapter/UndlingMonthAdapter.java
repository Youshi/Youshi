package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.MonthlyEntity;
import com.example.hmqqg.hm.entity.UndlingMonthEntity;

import java.util.List;

/**
 * 下属员工月报适配器
 * Created by Administrator on 2016/4/20.
 */
public class UndlingMonthAdapter extends BaseAdapter {
    private List<UndlingMonthEntity.DetailInfoBean> data;
    Context context;
    public UndlingMonthAdapter(List<UndlingMonthEntity.DetailInfoBean> list,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.report_adapter,null);
            holder.title=(TextView)convertView.findViewById(R.id.title);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        UndlingMonthEntity.DetailInfoBean mo = data.get(position);
        holder.title.setText(mo.getUSERNAME()+"");
        holder.time.setText(mo.getStartDate()+"");
        return convertView;
    }
    public class ViewHolder{
        private TextView title;//公司名
        private TextView time;//时间
    }
}
