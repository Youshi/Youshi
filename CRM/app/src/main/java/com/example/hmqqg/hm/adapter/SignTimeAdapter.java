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
import com.example.hmqqg.hm.entity.SignlistEntity;

import java.util.List;

/**
 * 消息适配器
 * Created by Administrator on 2016/1/2.
 */
public class SignTimeAdapter extends BaseAdapter {
    private List<SignlistEntity.DetailInfoEntity> data;
    Context context;

    public SignTimeAdapter(List<SignlistEntity.DetailInfoEntity> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.sign_adapter, null);
            holder.from = (TextView) convertView.findViewById(R.id.from);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SignlistEntity.DetailInfoEntity se = data.get(position);
        if ("".equals(se.getSignAddress().toString()) || se.getSignAddress().toString() == null) {
            holder.from.setText("");
        } else {
            holder.from.setText(se.getSignAddress().toString() + "");
        }
        if (!"".equals(se.getDateCode().toString())) {
            holder.date.setText(se.getDateCode().toString() + " ");
        } else {
            holder.date.setText("");
        }
        if (!"".equals(se.getSignTime().toString())) {
            holder.time.setText(se.getSignTime().toString() + " ");
        } else {
            holder.time.setText("");
        }
        return convertView;
    }

    public class ViewHolder {
        private TextView from;
        private TextView time;
        private TextView date;
    }
}
