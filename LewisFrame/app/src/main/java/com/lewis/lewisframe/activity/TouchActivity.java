package com.lewis.lewisframe.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.fragment.Touch2Fragment;
import com.lewis.lewisframe.fragment.TouchFragment;
import com.lewis.lewisframe.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class TouchActivity extends AppCompatActivity {
    ViewPager viewPager1;
    List<Fragment> fragmentList;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color
                .holo_orange_light, android.R.color.holo_green_light);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        swipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        fragmentList = new ArrayList<Fragment>();

        fragmentList.add(new Touch2Fragment(Color.BLUE));
        fragmentList.add(new TouchFragment());
        fragmentList.add(new Touch2Fragment(Color.RED));
        fragmentList.add(new Touch2Fragment(Color.GREEN));
        viewPager1.setAdapter(new Adapter(getSupportFragmentManager()));
    }

    class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
