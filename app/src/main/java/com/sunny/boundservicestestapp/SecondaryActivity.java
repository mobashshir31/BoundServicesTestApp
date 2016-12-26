package com.sunny.boundservicestestapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondaryActivity extends AppCompatActivity {
    MyService myService;
    boolean isBound = false;

    public void showTime(View view){
        String currentTime = myService.getCurrentTime();
        TextView myText = (TextView) findViewById(R.id.myText);
        myText.setText(currentTime);

        TextView countText = (TextView) findViewById(R.id.countText);
        String currentCount = "" + myService.getCount();
        countText.setText(currentCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        Intent i = new Intent(this, MyService.class);
        bindService(i, myConnection, Context.BIND_AUTO_CREATE);
        // this shows that the bindService does not happen until a certain time has passed
        // so all the service dependent code must be done in onServiceConnected after the getService;
        if(myService!=null){
            Toast.makeText(this, "binded",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "not binded",Toast.LENGTH_LONG).show();
        }
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyLocalBinder binder = (MyService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
