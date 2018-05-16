package com.mamn01.pi.kingofcampus;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Assar on 2018-05-03.
 */

public class GameSettings {

    public static List<CapturePoint> capturePointList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void init(GoogleMap mMap){
        capturePointList.add(new CapturePoint("kårhuset",mMap, new LatLng(55.712386, 13.209087)));
        capturePointList.add(new CapturePoint("IKDC",mMap, new LatLng(55.715135, 13.212273)));
        capturePointList.add(new CapturePoint("LED",mMap, new LatLng(55.710929, 13.210208)));
        capturePointList.add(new CapturePoint("test",mMap, new LatLng(55.711173, 13.210493)));
    }


}
