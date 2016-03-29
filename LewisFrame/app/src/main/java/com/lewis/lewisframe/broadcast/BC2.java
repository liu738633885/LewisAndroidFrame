package com.lewis.lewisframe.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by lewis on 16/3/16.
 */
public class BC2 extends BroadcastReceiver {
    public static final String ACTION = "BC1";
    public final String TAG=this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("msg");
        Bundle bundle=new Bundle();
        bundle.putString("test","向下传输的数据");
        setResultExtras(bundle);
        abortBroadcast();
    }


}
