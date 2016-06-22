package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.hmqqg.hm.R;
import com.example.hmqqg.hm.entity.CustomLookEntity;

import java.util.List;

import easeui.domain.EaseUser;

/**
 * 联系人列表的适配器
 * Created by Administrator on 2016/4/25.
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {

    private List<EaseUser> data;
    Context context;
    public ContactAdapter(List<EaseUser> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_adapter,null);
            holder.show_letter=(TextView)convertView.findViewById(R.id.show_letter);
            holder.name=(TextView)convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        EaseUser eu = data.get(position);
        String nike = eu.getNick();
        holder.name.setText(nike);
        //获得当前position是属于哪个分组
        int sectionForPosition = getSectionForPosition(position);
        //获得该分组第一项的position
        int positionForSection = getPositionForSection(sectionForPosition);
        //查看当前position是不是当前item所在分组的第一个item
        //如果是，则显示showLetter，否则隐藏
        if (position == positionForSection) {
            holder.show_letter.setVisibility(View.VISIBLE);
            holder.show_letter.setText(eu.getInitialLetter());
        } else {
            holder.show_letter.setVisibility(View.GONE);
        }
        return convertView;
    }


    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    //传入一个分组值[A....Z],获得该分组的第一项的position
    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getInitialLetter().charAt(0) == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    //传入一个position，获得该position所在的分组
    @Override
    public int getSectionForPosition(int position) {
        return data.get(position).getInitialLetter().charAt(0);
    }

    public class ViewHolder{
        private TextView show_letter,name;
    }
}
