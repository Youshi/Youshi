package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;

import java.util.List;

/**
 * 备忘录适配器
 * Created by Administrator on 2016/1/22.
 */
public class Memo_Adapter extends BaseAdapter {
    private List<String> data;
    private Context context;
    public Memo_Adapter(List<String> list,Context context){
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
        TextView title;//标题
        TextView time;//时间
        ViewHolder holder=null;
//        if (convertView==null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.memo_adapter,null);
        title=(TextView)convertView.findViewById(R.id.title);
        time=(TextView)convertView.findViewById(R.id.time);
//            convertView.setTag(holder);
//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
        title.setText("和万达王经理见面");
        time.setText("2016-1-5");
        return convertView;
    }
    public class ViewHolder{
        private TextView title;
        private TextView time;
    }
}
