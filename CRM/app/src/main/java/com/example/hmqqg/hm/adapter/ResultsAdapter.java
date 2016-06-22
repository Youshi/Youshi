package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.MonthlyEntity;
import com.example.hmqqg.hm.entity.ResultsEntity;
import com.example.hmqqg.hm.entity.UndlingMonthEntity;

import java.util.List;

/**
 * 下属员工月报适配器
 * Created by Administrator on 2016/4/20.
 */
public class ResultsAdapter extends BaseAdapter {
    private List<ResultsEntity.DetailInfoEntity> data;
    Context context;

    public ResultsAdapter(List<ResultsEntity.DetailInfoEntity> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.resultsadapter, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.approval = (TextView) convertView.findViewById(R.id.approval);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResultsEntity.DetailInfoEntity rd = data.get(position);
        holder.name.setText(rd.getUserName() + "");
        if ("day".equals(rd.getWtype())) {

            holder.approval.setText("日报");
        }
        if ("week".equals(rd.getWtype())) {

            holder.approval.setText("周报");
        }
        if ("month".equals(rd.getWtype())) {
            holder.approval.setText("月报");

        }
        holder.time.setText((rd.getStartDate()).replace("T00:00:00","") + "");
        return convertView;
    }

    public class ViewHolder {
        private TextView name;//姓名
        private TextView approval;//类型
        private TextView time;//时间
    }
}
