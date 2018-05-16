package com.mamn01.pi.kingofcampus;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

/**
 * Created by Assar on 2018-05-02.
 */

public class CapturePoint {

    private String name;
    private Circle area;
    private boolean hasBeenCaptured;
    public boolean isBeingCaptured;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CapturePoint(String name, GoogleMap map, LatLng point){
        this.name = name;
        area = map.addCircle(new CircleOptions()
                .center(point)
                .radius(20)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.rgb(0,0,255)));
        area.setClickable(true);
        hasBeenCaptured = false;
        isBeingCaptured = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void chaneColor(){
        Random rand = new Random();
        area.setFillColor(Color.rgb((int)(rand.nextFloat()*255),(int)(rand.nextFloat()*255),(int)(rand.nextFloat()*255)));
    }

    public void setPinkColor(){
        area.setFillColor(Color.rgb(225,35,157));
    }

    public void setHasBeenCaptured(boolean value){
        hasBeenCaptured = value;
    }

    public boolean hasBeenCaptured(){
        return hasBeenCaptured;
    }

    public void setIsBeingCaptured(boolean value){
        isBeingCaptured = value;
    }

    public boolean isBeingCaptured(){
        return isBeingCaptured;
    }

    public Circle getCircle(){
        return area;
    }
    public String getName(){
        return name;
    }
}
