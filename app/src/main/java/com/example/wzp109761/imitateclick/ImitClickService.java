package com.example.wzp109761.imitateclick;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import java.io.IOException;


public class ImitClickService extends IntentService {

    boolean flag;
    String x;
    String y;
    String testCount;
    String testPerTime;
    String TAG="IMITCLICKSERVICE";
    public ImitClickService() {
        super("ClickService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: alarm start");
        x = intent.getStringExtra("x");
        y = intent.getStringExtra("y");
        flag = intent.getBooleanExtra("flag", false);
        testCount = intent.getStringExtra("test_count");
        testPerTime = intent.getStringExtra("test_per_time");
        int perTime=Integer.parseInt(testPerTime)*1000;
        Log.d("====", "onStartCommand: " + x + "  " + y + "  " + flag + "  " + testCount + "  " + testPerTime);
        for (int i = Integer.parseInt(testCount); i > 0; i--) {
            //打印当前线程的id
            Log.d(TAG, "IntentService线程的id是：" + Thread.currentThread().getId());
            try {

                Log.e("----", "点击" + DateUtils.parseDate(System.currentTimeMillis()));
                // 利用ProcessBuilder执行shell命令
                String[] order;
                if (flag) {
                    order = new String[]{"input", "tap", "" + Math.random() * 1200, "" + Math.random() * 1000};
                } else {
                    order = new String[]{"input", "tap", "" + x, "" + y};
                }
                Log.d(TAG, "onHandleIntent: "+flag+"   "+order[1]+"  "+ order[2]+"   "+order[3]+"  "+order[0]);
                Thread.sleep(perTime);
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Destroy");
    }
}

