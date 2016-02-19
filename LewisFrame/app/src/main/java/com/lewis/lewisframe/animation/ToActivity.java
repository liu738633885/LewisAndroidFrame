package com.lewis.lewisframe.animation;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import com.lewis.lewisframe.R;

public class ToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        int flag = getIntent().getExtras().getInt("flag");
        if (Build.VERSION.SDK_INT >= 21) {
            switch (flag) {
                case 0:
                    getWindow().setEnterTransition(new Explode());
                    getWindow().setExitTransition(new Explode());
                    break;
                case 1:
                    getWindow().setEnterTransition(new Slide());
                    getWindow().setExitTransition(new Slide());
                    break;
                case 2:
                    getWindow().setEnterTransition(new Fade());
                    getWindow().setExitTransition(new Fade());
                    break;
                case 3:
                    getWindow().setEnterTransition(new Explode());
                    getWindow().setExitTransition(new Explode());
                    break;
            }
        }
        setContentView(R.layout.activity_to);
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onBackPressed();
    }
}
