package com.lewis.lewisframe.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.widget.CustomSwipeToRefresh;
import com.lewis.lewisframe.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouchFragment extends Fragment {

    List<View> viewList = new ArrayList<View>();

    public TouchFragment() {
        // Required empty public constructor
    }

    ViewGroup rootview;
    MyViewPager viewPager;
    CustomSwipeToRefresh swipeRefresh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = (ViewGroup) inflater.inflate(R.layout.fragment_touch, container, false);
        swipeRefresh = (CustomSwipeToRefresh) rootview.findViewById(R.id.swipeRefresh);
        viewPager = (MyViewPager) rootview.findViewById(R.id.viewpager2);
        //swipeRefresh.setViewPager(viewPager);
        viewPager.setSwipeRefreshLayout(swipeRefresh);
        LayoutInflater lf = getActivity().getLayoutInflater().from(getActivity());
        View view1 = lf.inflate(R.layout.touch_view_layout, viewPager, false);
        view1.setBackgroundColor(0xffffee22);
        View view2 = lf.inflate(R.layout.touch_view_layout, viewPager, false);
        view2.setBackgroundColor(0xffeeff22);
        View view3 = lf.inflate(R.layout.touch_view_layout, viewPager, false);
        view3.setBackgroundColor(0xff22eeff);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewPager.setAdapter(new ViewAdapter());
     /*   viewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x_tmp1 = x;
                        y_tmp1 = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        swipeRefresh.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                        x_tmp2 = x;
                        y_tmp2 = y;
                        Log.i("滑动", "滑动参值 x1=" + x_tmp1 + "; x2=" + x_tmp2);
                        if (x_tmp1 != 0 && y_tmp1 != 0) {
                            if (x_tmp1 - x_tmp2 > 8) {
                                Log.i("滑动", "向左滑动");
                            }
                            if (x_tmp2 - x_tmp1 > 8) {
                                Log.i("滑动", "向右滑动");
                            }

                        }

                        break;
                    case MotionEvent.ACTION_CANCEL:
                        swipeRefresh.setEnabled(true);
                        break;
                }
                return false;
            }
        });*/
        return rootview;
    }

    float x_tmp1, y_tmp1, x_tmp2, y_tmp2;

    class ViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    }


}
