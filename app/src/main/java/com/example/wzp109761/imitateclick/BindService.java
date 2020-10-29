package com.example.wzp109761.imitateclick;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BindService extends Service {

    /**
     * 测试验证
     */
    private String TAG="BindService";
    LocalBinder localBinder=new LocalBinder();
    private  int count;
    public  class LocalBinder extends Binder{
        public BindService getService() {
            return BindService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    count++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public int getCount(){
        return count;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }
}
