package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;

import java.util.List;

/**
 * 消息适配器
 * Created by Administrator on 2016/1/2.
 */
public class MessageAdapter extends BaseAdapter {
    private List<String> data;
    Context context;
    public MessageAdapter(List<String> list,Context context){
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
         TextView substance;
         TextView name;
         TextView time;
//        ViewHolder holder=null;
//        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.message_adapter,null);
            substance=(TextView)convertView.findViewById(R.id.substance);
            name=(TextView)convertView.findViewById(R.id.name);
            time=(TextView)convertView.findViewById(R.id.time);
//            convertView.setTag(holder);
//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
            substance.setText("呵呵呵呵");
            name.setText("万权");
            time.setText("1-3");
        return convertView;
    }
    public class ViewHolder{
//        private ImageView imagehead;
        private TextView substance;
        private TextView name;
        private TextView time;
    }
}
