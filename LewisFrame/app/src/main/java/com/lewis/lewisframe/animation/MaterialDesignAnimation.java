package com.lewis.lewisframe.animation;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.activity.BaseActivity;

public class MaterialDesignAnimation extends BaseActivity {
    private Intent intent;
    private View contentView;
    private int touchX = 0;
    private int touchY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        touchX = getIntent().getIntExtra("touchX", 0);
        touchY = getIntent().getIntExtra("touchY", 0);
        setContentView(R.layout.activity_material_design_transitions);
        contentView = findViewById(android.R.id.content);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    contentView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(
                            contentView,
                            touchX,
                            touchY,
                            0,
                            (float) Math.hypot(contentView.getWidth(), contentView.getHeight()));
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(1000);
                    animator.start();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    // 设置不同动画效果
    public void explode(View view) {
        intent = new Intent(this, ToActivity.class);
        intent.putExtra("flag", 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this)
                            .toBundle());
        }

    }

    // 设置不同动画效果
    public void slide(View view) {
        intent = new Intent(this, ToActivity.class);
        intent.putExtra("flag", 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this)
                            .toBundle());
        }
    }

    // 设置不同动画效果
    public void fade(View view) {
        intent = new Intent(this, ToActivity.class);
        intent.putExtra("flag", 2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this)
                            .toBundle());
        }
    }

    // 设置不同动画效果
    public void share(View view) {
        View fab = findViewById(R.id.fab_button);
        intent = new Intent(this, ToActivity.class);
        intent.putExtra("flag", 3);
        // 创建单个共享元素
//        startActivity(intent,
//                ActivityOptions.makeSceneTransitionAnimation(
//                        this, view, "share").toBundle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(
                            this,
                            // 创建多个共享元素
                            Pair.create(view, "toolBar"),
                            Pair.create(fab, "fab")).toBundle());
        }
    }

    public void circularReveal(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = ViewAnimationUtils.createCircularReveal(
                    contentView,
                    touchX,
                    touchY, 0,
                    (float) Math.hypot(contentView.getWidth(), contentView.getHeight()));
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(1000);
            animator.start();
        }
    }

    public static void jump(Context context, int touchX, int touchY) {
        Intent intent = new Intent(context, MaterialDesignAnimation.class);
        intent.putExtra("touchX", touchX);
        intent.putExtra("touchY", touchY);
        context.startActivity(intent);
    }
}
