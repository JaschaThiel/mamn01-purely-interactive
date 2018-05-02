package com.mamn01.pi.kingofcampus;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Assar on 2018-05-02.
 */

public class CapturePoint {

    private String name;
    private Circle area;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public CapturePoint(String name, GoogleMap map, LatLng point){
        this.name = name;
        area = map.addCircle(new CircleOptions()
                .center(point)
                .radius(5)
                .strokeColor(Color.TRANSPARENT)
                .fillColor(Color.argb(0.5f,0,0,255)));
        area.setClickable(true);
    }

    

    public Circle getCircle(){
        return area;
    }
    public String getName(){
        return name;
    }
}
