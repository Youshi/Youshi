package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomerEntity;
import com.example.hmqqg.hm.entity.SubmitSuperior_Entity;

import java.util.List;

/**
 * 传阅上级审核适配器
 * Created by Administrator on 2016/5/9.
 */
public class Submit_Adapter extends BaseAdapter {
    private List<SubmitSuperior_Entity.DetailInfoEntity> data;
    Context context;
    public Submit_Adapter(List<SubmitSuperior_Entity.DetailInfoEntity> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.submit_adapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.liner_adapter= (LinearLayout) convertView.findViewById(R.id.liner_adapter);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        SubmitSuperior_Entity.DetailInfoEntity cd = data.get(position);
        holder.name.setText(cd.getUserName()+"");
        holder.liner_adapter.setFocusable(true);
        holder.liner_adapter.setFocusableInTouchMode(true);
        holder.liner_adapter.requestFocus();
        return convertView;
    }
    public class ViewHolder{
        private TextView name;
        private LinearLayout liner_adapter;

    }
}
