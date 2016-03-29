package com.lewis.lewisframe.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Checkable;
import android.widget.ImageView;

import com.lewis.lewisframe.R;

/**
 * Created by lewis on 16/3/24.
 */
public class AvatarView extends ImageView implements Checkable, View.OnClickListener {


    public AvatarView(Context context) {
        super(context);
        initView();
    }

    public AvatarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AvatarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        setOnClickListener(this);
    }

    public void setAvatarImage(@DrawableRes int resId) {
       /* Drawable drawable = ResourcesCompat.getDrawable(getResources(), resId,
                getContext().getTheme());
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        @SuppressWarnings("ConstantConditions")
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                bitmapDrawable.getBitmap());
        roundedDrawable.setCircular(true);
        setImageDrawable(roundedDrawable);*/
        Bitmap src = BitmapFactory.decodeResource(getResources(), resId); //获取Bitmap图片
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), src); //创建RoundedBitmapDrawable对象
        //roundedBitmapDrawable.setCornerRadius(30); //设置圆角半径（根据实际需求）
        roundedBitmapDrawable.setCircular(true);//设置圆形图片
        roundedBitmapDrawable.setAntiAlias(true); //设置反走样
        setImageDrawable(roundedBitmapDrawable); //显示圆角图片
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        if (w > 0 && h > 0) {
            setOutlineProvider(new RoundOutlineProvider(Math.min(w, h)));
        }
    }

    private boolean mChecked;

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
        invalidate();
    }


    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mChecked) {
            Drawable border = ContextCompat.getDrawable(getContext(), R.drawable.selector_avatar);
            border.setBounds(0, 0, getWidth(), getHeight());
            border.draw(canvas);
        }
    }

    @Override
    public void onClick(View v) {
        toggle();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public class RoundOutlineProvider extends ViewOutlineProvider {

        private final int mSize;

        public RoundOutlineProvider(int size) {
            if (0 > size) {
                throw new IllegalArgumentException("size needs to be > 0. Actually was " + size);
            }
            mSize = size;
        }

        @Override
        public final void getOutline(View view, Outline outline) {
            outline.setOval(0, 0, mSize, mSize);
        }

    }

}
