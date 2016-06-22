package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.Apply_Eneity;
import com.example.hmqqg.hm.entity.ApplyresxultEntity;

import java.util.List;

/**
 * Created by bona on 2016/5/6.
 */
public class Applyresult_Adapter extends BaseAdapter {
    private List<ApplyresxultEntity.DetailInfoEntity> data;
    Context context;
    public Applyresult_Adapter(List<ApplyresxultEntity.DetailInfoEntity> list, Context context){
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
        ApplyresxultEntity.DetailInfoEntity me =data.get(position);
        holder.name.setText(me.getApprovalByName());
        holder.time.setText(me.getOperatedDate()+"");
        String flag = me.getFlag();
        if("Y".equals(flag)){
            holder.approval.setTextColor(context.getResources().getColor(R.color.green));
            holder.approval.setText("同意");
        }else if("R".equals(flag)){
            holder.approval.setTextColor(context.getResources().getColor(R.color.red));
            holder.approval.setText("不同意");
        }else if("S".equals(flag)){
            holder.approval.setTextColor(context.getResources().getColor(R.color.blue));
            holder.approval.setText("传阅");
        }

        return convertView;
    }
    public class ViewHolder{
        private TextView name;//姓名
        private TextView approval;//审核状态
        private TextView time;//时间
    }
}
