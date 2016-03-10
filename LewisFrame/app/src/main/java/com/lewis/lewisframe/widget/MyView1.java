package com.lewis.lewisframe.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import com.lewis.lewisframe.R;

/**
 * Created by lewis on 16/3/7.
 */
public class MyView1 extends View {
    private int backcolor;
    private Bitmap bitmap1;
    private Rect mRect;
    private float mAlpha;

    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView1);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.MyView1_src:
                    bitmap1 = ((BitmapDrawable) array.getDrawable(attr)).getBitmap();
                    break;
                case R.styleable.MyView1_backcolor:
                    backcolor = array.getColor(attr, 0xFFc94f0d);
                    break;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //判断图片最大的宽高能为多少(取最小值)
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop
                () - getPaddingBottom());
        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - iconWidth / 2;
        mRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(mAlpha*((float)getMeasuredWidth()/2),0);
        canvas.drawBitmap(bitmap1, null, mRect, null);
        int alpha = (int) Math.ceil(255 * mAlpha);
        setupTargetBitmap(alpha);
        canvas.drawBitmap(bitmap2, 0, 0, null);


    }

    /**
     * 在内存中绘制可变色的Icon
     */
    private Canvas mCanvas;
    private Bitmap bitmap2;
    private Paint mPaint;

    private void setupTargetBitmap(int alpha) {
        bitmap2 = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(bitmap2);
        mCanvas.rotate(360*mAlpha,getMeasuredWidth()/2,getMeasuredHeight()/2);
        mPaint = new Paint();
        mPaint.setColor(backcolor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        //在图片的范围上绘制纯色
        mCanvas.drawRect(mRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(bitmap1, null, mRect, mPaint);

    }
    public void setViewAlpha(float alpha)
    {
        this.mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }


}
