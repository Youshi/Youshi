package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.OrganizationEntity;


import java.util.List;


/**
 * 部门列表
 * Created by Administrator on 2016/1/4.
 */
public class Organization_Adapter extends BaseAdapter {
    private List<OrganizationEntity.DetailInfoEntity> data;
    Context context;

    public Organization_Adapter(List<OrganizationEntity.DetailInfoEntity> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.organization_adapter, null);
            holder.section = (TextView) convertView.findViewById(R.id.section);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        OrganizationEntity.DetailInfoEntity oe = data.get(position);
        holder.section.setText(oe.getDeptName());
        holder.number.setText(oe.getAllHuman()+"");
        return convertView;
    }

    public class ViewHolder {
        private TextView section;
        private TextView number;
    }
}
