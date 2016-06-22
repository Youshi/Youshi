package com.example.hmqqg.hm.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/6/1.
 */
public class ScrovllViewForDate extends ScrollView {
    float lastX;
    float lastY;

    public ScrovllViewForDate(Context context) {
        super(context);
    }

    public ScrovllViewForDate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrovllViewForDate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                lastX = ev.getX();
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceX =(int) Math.abs( ev.getX() - lastX);
                int distanceY = (int) Math.abs(ev.getY()-lastY);

                if(distanceX>distanceY && distanceX>10){
                    result = true;
                }else{
                    result = false;
                }
                break;

            default:
                break;
        }

        return result;
    }
}
