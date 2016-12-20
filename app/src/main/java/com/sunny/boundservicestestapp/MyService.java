package com.sunny.boundservicestestapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {

    private final IBinder myBinder = new MyLocalBinder();

    private int count = 0;
    public int getCount(){return count;}

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getCurrentTime(){
        count++;
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss", Locale.US);
        return (df.format(new Date()));
    }

    public class MyLocalBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
}
