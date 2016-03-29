package com.lewis.lewisframe.broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class SendBroadcastService extends Service {

    private Handler handler;

    public SendBroadcastService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                sendBC3();
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendBC() {
        Intent intent1=new Intent();
        intent1.putExtra("msg","无序广播");
        intent1.setAction(BC1.ACTION);
        sendBroadcast(intent1);
    }
    private void sendBC3() {
        Intent intent1=new Intent();
        intent1.setAction("BC3");
        sendBroadcast(intent1);
    }
}
