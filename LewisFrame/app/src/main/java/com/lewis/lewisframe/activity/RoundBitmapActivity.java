package com.lewis.lewisframe.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.widget.AvatarView;

public class RoundBitmapActivity extends AppCompatActivity {

    private ImageView img1, img2;
    private AvatarView av1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_bitmap);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        av1= (AvatarView) findViewById(R.id.av1);
        img1.setClipToOutline(true);
        img1.setOutlineProvider(new RoundOutlineProvider());
        av1.setAvatarImage(R.mipmap.avatar_14_raster);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    class RoundOutlineProvider extends ViewOutlineProvider {

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setOval(0, 0, view.getWidth(), view.getHeight());
        }
    }

    public void myClick(View view) {
        img1.setDrawingCacheEnabled(true);
        Bitmap b = Bitmap.createBitmap(img1.getDrawingCache());
        //RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), Bitmap.createBitmap(img1.getDrawingCache()));
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), ((BitmapDrawable) img1.getDrawable()).getBitmap());
        //img2.setImageBitmap(b);
        roundedBitmapDrawable.setCircular(true);
        img2.setImageDrawable(roundedBitmapDrawable);
        img1.setDrawingCacheEnabled(false);
        av1.setChecked(true);
    }
}
