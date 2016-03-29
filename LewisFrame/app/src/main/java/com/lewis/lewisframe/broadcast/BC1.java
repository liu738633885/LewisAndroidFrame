package com.lewis.lewisframe.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lewis.lewisframe.R;

/**
 * Created by lewis on 16/3/16.
 */
public class BC1 extends BroadcastReceiver {
    public static final String ACTION = "BC1";
    public final String TAG=this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("msg");
        Log.e(TAG,"静态接收器收到"+msg);
       /* Bundle bundle=getResultExtras(true);
        String test=bundle.getString("test");
        Log.e(TAG,"动态接收器收到"+msg+"-test---"+test);*/
    }



}
