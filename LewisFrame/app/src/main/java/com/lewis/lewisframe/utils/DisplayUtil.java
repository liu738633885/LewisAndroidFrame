package com.lewis.lewisframe.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.lewis.lewisframe.MainApplication;

/**
 * Created by lewis on 16/4/19.
 */
public class DisplayUtil {
    /**测量屏幕大小*/
    public static Point getSizePoint(){
        WindowManager wm= (WindowManager) MainApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display=wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
    public static int getWindowWidth(){
        return getSizePoint().x;
    }
    public static int getWindowHeight(){
        return getSizePoint().y;
    }
}
