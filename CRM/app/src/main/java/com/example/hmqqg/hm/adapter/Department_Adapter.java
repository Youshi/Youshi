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
 * 部门详情适配器
 * Created by Administrator on 2016/1/14.
 */
public class Department_Adapter extends BaseAdapter{
    private List<String> data;
    Context context;
    public Department_Adapter(List<String> list,Context context){
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
        TextView name;//姓名
        TextView identity;//身份
        ViewHolder holder=null;
//        if (convertView==null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.department_adapter,null);
        name=(TextView)convertView.findViewById(R.id.name);
        identity=(TextView)convertView.findViewById(R.id.identity);
//            convertView.setTag(holder);
//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
        name.setText("田霞");
        identity.setText("管理员");
        return convertView;
    }
    public class ViewHolder{
        //        private ImageView imagehead;
        private TextView name;
        private TextView identity;
    }
}
