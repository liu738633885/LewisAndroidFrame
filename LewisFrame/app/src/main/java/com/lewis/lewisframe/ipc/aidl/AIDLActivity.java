package com.lewis.lewisframe.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lewisservice.LewisAidlInterface;
import com.lewis.lewisframe.R;

public class AIDLActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private TextView tv_over;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        editText1 = (EditText) findViewById(R.id.edt1);
        editText2 = (EditText) findViewById(R.id.edt2);
        tv_over = (TextView) findViewById(R.id.tv_over);
        bindService();
    }

    LewisAidlInterface lewisAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        //绑定上服务的时候
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            lewisAidlInterface = LewisAidlInterface.Stub.asInterface(iBinder);
        }

        //与服务断开的时候
        @Override
        public void onServiceDisconnected(ComponentName name) {
            lewisAidlInterface=null;
        }
    };

    public void myOnClick(View view) {
        int num1=Integer.parseInt(editText1.getText().toString());
        int num2=Integer.parseInt(editText2.getText().toString());
        try {
            int numover=lewisAidlInterface.add(num1,num2);
            tv_over.setText("结果"+numover);
        } catch (RemoteException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void bindService() {
        //获取到服务端  绑定服务
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.lewisservice", "com.example.lewisservice.MyService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
