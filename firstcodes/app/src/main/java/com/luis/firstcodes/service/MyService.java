package com.luis.firstcodes.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = MyService.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy executed");

    }

    class DownlodBinder extends Binder{
        public void startDownload(){
            Log.i(TAG,"startDownload execute");
        }
        public void getProgress(){
            Log.i(TAG,"getProgress execute");
        }
    }
}
