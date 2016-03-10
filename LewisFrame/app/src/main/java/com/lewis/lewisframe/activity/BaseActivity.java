package com.lewis.lewisframe.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lewis.lewisframe.R;

import org.xutils.x;

/**
 * Created by lewis on 16/1/18.
 * 这个activity实现了沉浸式activity
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
    }

    private void initWindow() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            // 很明显，这两货是新API才有的。
            // window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
            //window.setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

             window.setStatusBarColor(ContextCompat.getColor(this,android.R.color.transparent));
            // window.setNavigationBarColor(getResources().getColor(R.color.half_transparent));

        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //底部的控制栏透明
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
        /*老的用法*/
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/

    }

}
