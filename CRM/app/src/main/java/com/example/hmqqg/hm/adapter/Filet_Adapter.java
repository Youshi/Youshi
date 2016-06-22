package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.FiletEntity;

import java.util.List;

/**
 * 文件列表
 * Created by bona on 2016/3/10.
 */
public class Filet_Adapter extends BaseAdapter {
    private List<FiletEntity.DetailInfoEntity> data;
    Context context;
    public Filet_Adapter(List<FiletEntity.DetailInfoEntity> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.file_adapter,null);
            holder.title=(TextView)convertView.findViewById(R.id.title);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        FiletEntity.DetailInfoEntity filet = data.get(position);
        holder.title.setText(filet.getFileName());
        holder.time.setText(filet.getUploadDate());
        return convertView;
    }
    public class ViewHolder{
        //        private ImageView imagehead;
        private TextView title;
        private TextView time;
    }
}
