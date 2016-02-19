package com.lewis.lewisframe;

import android.app.Application;

import com.lewis.lewisframe.utils.crashUtils.CrashHandler;

/**
 * Created by lewis on 16/1/6.
 */
public class MainApplication extends Application {
    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //在这里为应用设置异常处理程序，然后我们的程序才能捕获未处理的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    public static MainApplication getInstance() {
        return instance;
    }
}
