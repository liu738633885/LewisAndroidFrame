package com.lewis.lewisframe.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.View;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.animation.MaterialDesignAnimation;
import com.lewis.lewisframe.widget.MultiStateView;

public class MainActivity extends BaseActivity {
    private MultiStateView mMultiStateView;
    private View view1, view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        //setMultiSateView();
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(getWindow().get);
                setViewWidth(squareRed, 500);
                setViewWidth(squareBlue, 500);
                setViewWidth(squareGreen, 500);
                setViewWidth(squareYellow, 500);
            }
        });
    }

    private void setMultiSateView() {
        mMultiStateView = (MultiStateView) findViewById(R.id.multiStateView);
        mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
                    }
                });
        mMultiStateView.getView(MultiStateView.VIEW_STATE_EMPTY)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                    }
                });
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
            }
        }, 3000);
    }

    public void gotoAnimation(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);
        MaterialDesignAnimation.jump(this, location[0] + (v.getWidth() / 2), location[1] + (v
                .getHeight() / 2));
    }
}
