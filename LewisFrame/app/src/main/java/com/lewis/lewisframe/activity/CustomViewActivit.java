package com.lewis.lewisframe.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.SeekBar;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.widget.MyView1;

public class CustomViewActivit extends AppCompatActivity {
    private SeekBar seekbar;
    private MyView1 myView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        seekbar= (SeekBar) findViewById(R.id.seekbar);
        myView1 = (MyView1) findViewById(R.id.myView1);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myView1.setViewAlpha((float)progress/(float)100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
