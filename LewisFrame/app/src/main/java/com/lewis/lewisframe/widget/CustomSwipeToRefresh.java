package com.lewis.lewisframe.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.lewis.lewisframe.utils.TouchEventUtil;

/**
 * Created by lewis on 16/3/3.
 */
public class CustomSwipeToRefresh extends SwipeRefreshLayout {
    String TAG = this.getClass().getSimpleName();

    private int mTouchSlop;
    private float mPrevX;

    public CustomSwipeToRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
/*
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
         Log.e(TAG, b + "---dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        Log.i(TAG, b + "---onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean b = super.onTouchEvent(ev);
        Log.d(TAG, b + "---onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return b;
    }*/


    /*private View hview;

    public void setViewPager(View view) {
        this.hview = view;
    }*/

   /* @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (TouchEventUtil.inRangeOfView(hview, event)) {
                    mPrevX = MotionEvent.obtain(event).getX();
                } else {
                    mPrevX = -1;
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if (mPrevX != -1) {
                    final float eventX = event.getX();
                    float xDiff = Math.abs(eventX - mPrevX);

                    if (xDiff > mTouchSlop) {
                        this.setEnabled(false);
                        return false;
                    }
                }


                break;
            case MotionEvent.ACTION_UP:
                if (!isEnabled()&&mPrevX!=-1) {
                    this.setEnabled(true);
                }
                break;
        }

        return super.onInterceptTouchEvent(event);
    }*/
}
