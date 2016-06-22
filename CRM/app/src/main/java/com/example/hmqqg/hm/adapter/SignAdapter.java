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
public class SignAdapter extends BaseAdapter {
    private List<String> data;
    Context context;
    public SignAdapter(List<String> list,Context context){
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
        TextView from;
//        ViewHolder holder=null;
//        if (convertView==null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.time_adapter,null);
        from=(TextView)convertView.findViewById(R.id.from);
//            convertView.setTag(holder);
//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
        from.setText("烟台市芝罘区青年南路123456号");
        return convertView;
    }
    public class ViewHolder{
        private TextView from;
    }
}
