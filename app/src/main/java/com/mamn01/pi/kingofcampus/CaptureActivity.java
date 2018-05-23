package com.mamn01.pi.kingofcampus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;


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
    private CapturePoint capturePoint;
    private Random rand;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_activity_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.shake);
        textView.setText("SHAKE YOUR PHONE!");
        isShaking = false;
        rand = new Random();


        progressBar.getProgressDrawable().setColorFilter(
                Color.MAGENTA, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar.setScaleY(15f);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.capt);
        mp.setLooping(false);

        // Get a sensor manager to listen for shakes
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Listen for shakes
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        for(CapturePoint p : GameSettings.capturePointList){
            if(p.isBeingCaptured){
                capturePoint = p;
            }
        }

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
            textView.setText("AREA CAPTURED!");
            mp.start();
            while (mp.isPlaying()){

            }
            Class c = null;
            capturePoint.setHolder(1);
            GameSettings.addValue(capturePoint);
            try {
                c = Class.forName("com.mamn01.pi.kingofcampus.MapActivity");
                Intent intent = new Intent(this, c);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

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
