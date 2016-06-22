package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomLookEntity;

import java.util.List;

/**
 * 外出事由适配器
 * Created by bona on 2016/4/10.
 */
public class OutListAdapter extends BaseAdapter{
    private List<String> data;
    Context context;
    public OutListAdapter(List<String> list, Context context){
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
//        ViewHolder holder=null;
//        if (convertView==null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.memo_adapter,null);
        title=(TextView)convertView.findViewById(R.id.title);
        time=(TextView)convertView.findViewById(R.id.time);
//            convertView.setTag(holder);
//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
        title.setText("去万达广场");
        time.setText("2016-5-5");
        return convertView;
    }
    public class ViewHolder{
        //        private ImageView imagehead;
        private TextView title;
        private TextView time;
    }
}
