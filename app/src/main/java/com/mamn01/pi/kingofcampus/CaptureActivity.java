package com.mamn01.pi.kingofcampus;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by Assar on 2018-05-16.
 */

public class CaptureActivity extends Activity implements SensorEventListener {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

    private static final float SHAKE_THRESHOLD = 15f; // m/S**2
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 2000;
    private static final int SHAKE_DURATION = 1000;
    private long mLastShakeTime;
    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private boolean isShaking;
    private long shakeTimer1, shakeTimer2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_activity_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);
        isShaking = false;
        progressBar.setScaleY(5f);

        // Get a sensor manager to listen for shakes
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Listen for shakes
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        // Start long running operation in a background thread
//        new Thread(new Runnable() {
//            public void run() {
//                while (progressStatus < 100) {
//                    //progressStatus += 1;
//                    // Update the progress bar and display the
//                    //current value in the text view
//                    handler.post(new Runnable() {
//                        public void run() {
//                            progressBar.setProgress(progressStatus);
//                            //textView.setText(progressStatus+"/"+progressBar.getMax());
//                        }
//                    });
//                    try {
//                        // Sleep for 200 milliseconds.
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                vibrate();
//            }
//        }).start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                if (acceleration > SHAKE_THRESHOLD) {
                    updateProgressBar();
                    doShake();
                }else{
                    isShaking = false;

                }
            }
        }
    }

     private void updateProgressBar(){
        progressStatus += 5;
        if(progressStatus % 20 == 0){
            vibrate();
        }
        if(progressStatus > 100){
            progressStatus = 0;
        }
         progressBar.setProgress(progressStatus);
     }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    private void doShake(){
        if(!isShaking){
            isShaking = true;
            shakeTimer1 = System.currentTimeMillis();
        }else{
            shakeTimer2 = System.currentTimeMillis();
            if((shakeTimer2 - shakeTimer1) > SHAKE_DURATION){
                isShaking = false;
                mLastShakeTime = shakeTimer2;
                vibrate();
            }
        }
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
