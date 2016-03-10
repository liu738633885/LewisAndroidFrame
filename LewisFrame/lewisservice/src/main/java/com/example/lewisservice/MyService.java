package com.example.lewisservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //只要一绑定 返回的就是 aidl接口的 binder
        return iBinder;
    }
    private IBinder iBinder=new LewisAidlInterface.Stub(){
        @Override
        public int add(int num1, int num2) throws RemoteException {
            Log.d("TAG","收到了远程的请求");
            return num1+num2;
        }
    };
}
