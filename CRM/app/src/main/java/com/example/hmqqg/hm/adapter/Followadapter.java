package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmqqg.hm.R;

import java.util.List;

/**
 * 消息适配器
 * Created by Administrator on 2016/1/2.
 */
public class Followadapter extends BaseAdapter  {
    private List<String> data;
    Context context;
    public Followadapter(List<String> list,Context context){
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
        TextView name;
        TextView phone;
        TextView origne;
        TextView time;
        ViewHolder holder=null;
//        if (convertView==null) {
        holder = new ViewHolder();
        convertView = LayoutInflater.from(context).inflate(R.layout.follow_adapter,null);
        holder.name = (TextView) convertView.findViewById(R.id.name);
        holder.phone = (TextView) convertView.findViewById(R.id.phone);
        holder.origne=(TextView)convertView.findViewById(R.id.origine);
        holder.intention= (TextView) convertView.findViewById(R.id.intention);
        holder.intention=(TextView)convertView.findViewById(R.id.intention);
        holder.time1=(TextView)convertView.findViewById(R.id.time1);
        holder.time2=(TextView)convertView.findViewById(R.id.time2);
        holder.money=(TextView)convertView.findViewById(R.id.money);
        holder.product=(TextView)convertView.findViewById(R.id.product);
//            convertView.setTag(holder);
//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
            return convertView;
    }
    public class ViewHolder{

        private TextView name;
        private TextView phone;
        private TextView origne;
        private TextView intention;
        private TextView time1;
        private TextView time2;
        private TextView money;
        private TextView product;
    }
}
