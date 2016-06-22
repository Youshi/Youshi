package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.FileEntity;

import java.util.List;

/**
 * 公司文件下载适配器
 * Created by Administrator on 2016/1/7.
 */
public class File_Adapter extends BaseAdapter{
    private List<FileEntity.DetailInfoEntity> data;
    Context context;
    public File_Adapter(List<FileEntity.DetailInfoEntity> list, Context context){
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
//        TextView title;//标题
//        TextView time;//时间
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
        FileEntity.DetailInfoEntity file = data.get(position);
        holder.title.setText(file.getFolderName());
        holder.time.setText(file.getCreateDate());
        return convertView;
    }
    public class ViewHolder{
        //        private ImageView imagehead;
        private TextView title;
        private TextView time;
    }

}
