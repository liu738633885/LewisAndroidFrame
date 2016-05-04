package com.lewis.lewisframe.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lewis.lewisframe.R;
import com.lewis.lewisframe.ipc.aidl.AIDLActivity;

public class BroadcastActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        initBroadCast();
    }

    /**
     * 动态注册广播
     */
    private void initBroadCast() {
        registerReceiver(BC3,new IntentFilter("BC3"));
        LocalBroadcastManager.getInstance(this).registerReceiver(BC3, new IntentFilter("BC3_Local"));
    }

    public void myOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent1=new Intent();
                intent1.putExtra("msg","无序广播");
                intent1.setAction(BC1.ACTION);
                sendBroadcast(intent1);
                break;
            case R.id.btn2:
                Intent intent2=new Intent();
                intent2.putExtra("msg","有序广播");
                intent2.setAction(BC1.ACTION);
                sendOrderedBroadcast(intent2,null);
                break;
            case R.id.btn3:

                Intent intent3=new Intent(BroadcastActivity.this,SendBroadcastService.class);
                startService(intent3);
                break;
            case R.id.btn4:
                Intent intent4=new Intent();
                intent4.setAction("BC3");
               sendBroadcast(intent4);
                break;
            case R.id.btn5:
               startActivity(new Intent(BroadcastActivity.this, AIDLActivity.class));
                break;
            case R.id.btn6:
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("BC3_Local"));
                break;
            default:
                break;
        }
    }
    BroadcastReceiver BC3=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(),"hhhhh",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(BC3);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(BC3);
    }
}
