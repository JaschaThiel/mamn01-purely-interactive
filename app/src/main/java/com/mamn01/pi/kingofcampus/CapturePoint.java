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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CapturePoint(String name, GoogleMap map, LatLng point, Long currentHolderLong){
        this.name = name;
        int currentColor = Color.BLACK;
        int currentHolder = Math.toIntExact(currentHolderLong);
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
        area = map.addCircle(new CircleOptions()
                .center(point)
                .radius(20)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(currentColor));
        area.setClickable(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void chaneColor(){
        Random rand = new Random();
        area.setFillColor(Color.rgb((int)(rand.nextFloat()*255),(int)(rand.nextFloat()*255),(int)(rand.nextFloat()*255)));
    }

    public void setPinkColor(){
        area.setFillColor(Color.rgb(225,35,157));
    }

    public Circle getCircle(){
        return area;
    }
    public String getName(){
        return name;
    }
}
