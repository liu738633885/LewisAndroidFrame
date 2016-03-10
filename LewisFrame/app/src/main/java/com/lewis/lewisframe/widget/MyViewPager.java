package com.lewis.lewisframe.widget;


import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.lewis.lewisframe.utils.TouchEventUtil;

public class MyViewPager extends ViewPager {
	String TAG = this.getClass().getSimpleName();
	private float mPrevX;
	private int mTouchSlop;
	public MyViewPager(Context context) {
		super(context);
	}
	private SwipeRefreshLayout swipeRefreshLayout;
	public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout){
		this.swipeRefreshLayout=swipeRefreshLayout;
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						mPrevX = MotionEvent.obtain(event).getX();
						break;
					case MotionEvent.ACTION_MOVE:
						final float eventX = event.getX();
						float xDiff = Math.abs(eventX - mPrevX);
						if (xDiff > mTouchSlop && swipeRefreshLayout != null && swipeRefreshLayout.isEnabled()) {
							swipeRefreshLayout.setEnabled(false);
						}
						break;
					case MotionEvent.ACTION_UP:
						if (!swipeRefreshLayout.isEnabled()) {
							swipeRefreshLayout.setEnabled(true);
						}
						break;
				}
				return false;
			}
		});
	}

}
