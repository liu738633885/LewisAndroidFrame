package com.lewis.lewisframe.widget;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.lewis.lewisframe.utils.TouchEventUtil;

public class MyViewPager2 extends ViewPager {
    String TAG = this.getClass().getSimpleName();

    public MyViewPager2(Context context) {
        super(context);
    }

    public MyViewPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
       // Log.e(TAG, b + "---dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        //Log.i(TAG, b + "---onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean b = super.onTouchEvent(ev);
        //Log.d(TAG, b + "---onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return b;
    }

}
