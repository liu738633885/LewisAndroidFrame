package com.lewis.lewisframe;

import android.app.Application;


import com.lewis.library.crash.CrashHandler;
import com.squareup.leakcanary.LeakCanary;

import org.xutils.x;


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
       /* CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);*/

        x.Ext.init(this);
        x.Ext.setDebug(true);

        LeakCanary.install(this);
    }

    public static MainApplication getInstance() {
        return instance;
    }
}
