package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.ApplyEntity;
import com.example.hmqqg.hm.entity.Apply_Eneity;

import java.util.List;

/**
 * Created by bona on 2016/5/5.
 */
public class Applyout_Adapter extends BaseAdapter {

    private List<Apply_Eneity.DetailInfoEntity> data;
    Context context;
    public Applyout_Adapter(List<Apply_Eneity.DetailInfoEntity> list,Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.approval_adapter,null);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            holder.approval=(TextView)convertView.findViewById(R.id.approval);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        Apply_Eneity.DetailInfoEntity me =data.get(position);
        holder.name.setText(me.getAppRovalByName());
        holder.time.setText(me.getCreatedDate()+"");

        return convertView;
    }
    public class ViewHolder{
        private TextView name;//姓名
        private TextView approval;//审核状态
        private TextView time;//时间
    }
}
