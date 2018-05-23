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
    private int currentHolder;
    private int currentColor;

    private boolean shouldCapture;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CapturePoint(String name, GoogleMap map, LatLng point, Long currentHolderLong){
        this.name = name;
        currentColor = Color.BLACK;
        currentHolder = Math.toIntExact(currentHolderLong);
        setColor();
        shouldCapture = true;
        area = map.addCircle(new CircleOptions()
                .center(point)
                .radius(20)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(currentColor));
        area.setClickable(true);
        hasBeenCaptured = false;
        isBeingCaptured = false;
    }

    public void setHolder(int value){
        currentHolder = value;
        setColor();
    }

    public void setShouldNotCapture(boolean value){
        shouldCapture = value;
    }

    public boolean shouldCapture(){
        return shouldCapture;
    }

    public int getHolderValue(){
        return currentHolder;
    }

    private void setColor(){
        switch (currentHolder){
            case 1:
                // Data
                currentColor = Color.MAGENTA;
                break;
            case 2:
                // Maskin
                currentColor = Color.RED;
                break;
            case 3:
                // K
                currentColor = Color.YELLOW;
                break;
            case 4:
                // F
                currentColor = Color.BLACK;
                break;
            case 5:
                // W
                currentColor = Color.CYAN;
                break;
            case 6:
                // E
                currentColor = Color.WHITE;
                break;
            case 7:
                // V
                currentColor = Color.BLUE;
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                currentColor = Color.BLACK;
                break;

        }
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

    public boolean equals(CapturePoint p1){
        if(p1.getName().equals(name)){
            return true;
        }
        return false;
    }

}
