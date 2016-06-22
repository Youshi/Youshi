package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomLookEntity;
import com.example.hmqqg.hm.entity.UnderEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class Underadapter extends BaseAdapter{
    private List<UnderEntity.DetailInfoEntity> data;
    Context context;
    public Underadapter(List<UnderEntity.DetailInfoEntity> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_lookadapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.number= (TextView) convertView.findViewById(R.id.number);
            holder.intention=(TextView)convertView.findViewById(R.id.intention);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        UnderEntity.DetailInfoEntity cd = data.get(position);
        holder.name.setText(cd.getUserName()+"");
        holder.number.setText(cd.getName()+"");
        holder.intention.setText(cd.getUserGradeName()+"");
        return convertView;
    }
    public class ViewHolder{
        private TextView name;//客户名字
        private TextView number;//员工名字
        private TextView intention;//意向程度
    }
}
