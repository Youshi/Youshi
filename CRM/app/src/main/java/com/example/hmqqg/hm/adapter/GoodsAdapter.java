package com.example.hmqqg.hm.adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hmqqg.hm.R;

import java.util.List;


public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;
    private MyOnItemClickListener myOnItemClickListener;
    private List<String> dataList;

    public GoodsAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

//    @Override
//    public int getItemViewType(int position) {
//        // 最后一个item设置为footerView
//        if (position + 1 == getItemCount()) {
//            return TYPE_FOOTER;
//        } else {
//            return TYPE_ITEM;
//        }
//    }

    /**
     * 当 view 被复用的时候调用,将传回来的 holder 传递给了 onbindview, 做到了复用的功能,这个时候 onCreateViewHolder不会再执行
     *
     * @param holder
     */
    @Override
    public void onViewRecycled(GoodsAdapter.MyViewHolder holder) {
//        Log.e("recycler", "onViewRecycled");
        super.onViewRecycled(holder);
    }

    /**
     * 创建 viewhoder, 返回值是我们自己的内部类MyViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.e("recycler", "onCreateViewHolder");
//        if (viewType == TYPE_ITEM) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.goods_item, null);


//        itemView.setOnClickListener(this);
        return new MyViewHolder(itemView);
//        } else if (viewType == TYPE_FOOTER) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.footerview, null);
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT));
//            return new FooterViewHolder(view);
//        }

    }


    /**
     * 绑定视图,将内容显示出来的方法
     *
     * @param holder   onCreateViewHolder 返回的对象,传递到这里来 显示内容
     * @param position
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.iv_goods = (ImageView) holder.itemView.findViewById(R.id.iv_goods);
        holder.tv_goods = (TextView) holder.itemView.findViewById(R.id.tv_goods);

        holder.iv_goods.setImageResource(R.mipmap.sousuo);
//        holder.tv_goods.setText("iphone6s");
//        Log.e("data",data+"");
        if (dataList != null) {

//                holder1.tv_goods


//                    PicUtil.changePic(holder1.imageView, pic.getUrl(), context);
//            Log.e("recycler","数据设置完成");
        }
//            FooterViewHolder holder2 = (FooterViewHolder) holder;
    }

    /**
     * 返回数据源的长度
     *
     * @return
     */
    @Override
    public int getItemCount() {
        if (dataList == null || dataList.size() == 0) {
            return 6;
        }
        return dataList.size();
    }

    //不能在 adapter 中给 view 设置点击事件,因为拿不到位置,位置需要在 holder 中获取,所有点击事件应该在 holder 中设置
//    @Override
//    public void onClick(View v) {
//        Log.e("recycler", "点击事件执行");
//        if (myOnItemClickListener != null) {
//         //   myOnItemClickListener.myOnItemClick();
//        }
//    }
//    class FooterViewHolder extends RecyclerView.ViewHolder {
//
//        public FooterViewHolder(View view) {
//            super(view);
//        }
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * @param itemView 每一条 item 的 view
         */
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);//给 每一条 item设置点击事件
        }

        TextView tv_goods;
        ImageView iv_goods;

        @Override
        public void onClick(View v) {//每一条 item 点击事件的具体执行内容
            if (myOnItemClickListener != null) {
                myOnItemClickListener.myOnItemClick(v, getLayoutPosition());
            }
        }
    }

    public interface MyOnItemClickListener {//自己声明的点击事件对象

        void myOnItemClick(View view, int postion);//点击事件具体执行的方法
    }
}
