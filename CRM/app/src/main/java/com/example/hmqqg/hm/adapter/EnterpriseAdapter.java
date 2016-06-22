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
 * 好友企业适配器
 * Created by Administrator on 2016/1/6.
 */
public class EnterpriseAdapter extends BaseAdapter {
    private List<String> data;
    Context context;
    public EnterpriseAdapter(List<String> list,Context context){
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
        TextView companyname;//公司名
        TextView introduction;//简介
        ViewHolder holder=null;
//        if (convertView==null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.company_adapter,null);
        companyname=(TextView)convertView.findViewById(R.id.companyname);
        introduction=(TextView)convertView.findViewById(R.id.introduction);
//            convertView.setTag(holder);
//        }else {
//            holder=(ViewHolder)convertView.getTag();
//        }
        companyname.setText("BONA网络科技有限公司");
        introduction.setText("软件开发、网站开发、微信开发");
        return convertView;
    }
    public class ViewHolder{
        //        private ImageView imagehead;
        private TextView companyname;
        private TextView introduction;
    }
}
