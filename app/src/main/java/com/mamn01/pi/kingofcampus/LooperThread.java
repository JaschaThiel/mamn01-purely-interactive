package com.mamn01.pi.kingofcampus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by Assar on 2018-05-16.
 */

public class LooperThread extends Thread {
    public Handler mHandler;

    @Override
    public void run() {
        Looper.prepare();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // process incoming messages here
            }
        };
        System.out.println("LOWDOLDWOLD");
        Looper.loop();
    }

}
