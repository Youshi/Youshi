package com.example.hmqqg.hm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 重写ListView
 * Created by Ella on 2016/1/25.
 */
public class Anew_Listview extends ListView {
    private Context context;
    public Anew_Listview(Context context) {
        super(context);
    }
    public Anew_Listview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public Anew_Listview(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
