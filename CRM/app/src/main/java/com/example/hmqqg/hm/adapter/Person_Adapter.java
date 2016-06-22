package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.DeptDetailEntity;
import com.parse.gdata.Escaper;

import java.util.List;

/**
 * 部门人员适配器
 * Created by Administrator on 2016/1/22.
 */
public class Person_Adapter extends BaseAdapter {
    private List<DeptDetailEntity.StaffersEntity> data;
    Context context;

    public Person_Adapter(List<DeptDetailEntity.StaffersEntity> list, Context context) {
        super();
        this.data = list;
        this.context = context;
    }

    @Override
    public int getCount() {
//        return data.size();
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
        TextView name;//姓名
        TextView job;//职务
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.person_adapter, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.job = (TextView) convertView.findViewById(R.id.job);
            holder.imagehead = (ImageView) convertView.findViewById(R.id.imagehead);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(data.get(position).getUsername());
        holder.job.setText(data.get(position).getMobile());

        return convertView;
    }

    public class ViewHolder {
        private ImageView imagehead;
        private TextView name;
        private TextView job;
    }
}
