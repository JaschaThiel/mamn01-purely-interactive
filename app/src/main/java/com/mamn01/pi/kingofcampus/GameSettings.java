package com.mamn01.pi.kingofcampus;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Assar on 2018-05-03.
 */

public class GameSettings {
    private static final String TAG = "GameSettings";
    private static StorageReference mStorageReference;
    private static FirebaseDatabase fDatabase;
    private static DatabaseReference dataRef;

    public static List<CapturePoint> capturePointList = new ArrayList<>();
    private static List<LatLng> capturePointLatLngList = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void init(final GoogleMap mMap){

        capturePointLatLngList.add(new LatLng(55.713597, 13.213089)); // entrance
        capturePointLatLngList.add(new LatLng(55.713431, 13.213732)); // bottom right
        capturePointLatLngList.add(new LatLng(55.713663, 13.213504)); // middle
        capturePointLatLngList.add(new LatLng(55.715135, 13.212273)); // IKDC

        capturePointLatLngList.add(new LatLng(55.713846, 13.213207)); // top left

        capturePointLatLngList.add(new LatLng(55.712386, 13.209087)); // Karhuset

        capturePointLatLngList.add(new LatLng(55.710929, 13.210208)); // LED


        mStorageReference = FirebaseStorage.getInstance().getReference();
        fDatabase = FirebaseDatabase.getInstance();
        dataRef = fDatabase.getReference("");
        // Read from the database
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                HashMap<String, Long> nodes;
                nodes = (HashMap<String, Long>) dataSnapshot.getValue();
                System.out.println("Size of the map: " + nodes.size());
                System.out.println("received nodes from database");
                addValuesToList(nodes, mMap);
                Log.d(TAG, "Value is: " + nodes.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

//        capturePointList.add(new CapturePoint("Kårhuset",mMap, new LatLng(55.712386, 13.209087)));
//      capturePointList.add(new CapturePoint("IKDC",mMap, new LatLng(55.715135, 13.212273)));
//    capturePointList.add(new CapturePoint("LED",mMap, new LatLng(55.710929, 13.210208)));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void addValuesToList(HashMap<String, Long> capturePoints, GoogleMap map) {
        capturePointList = new ArrayList<>();
        map.clear();
        int i = 0;
        System.out.println(capturePoints.keySet());
            for (String key : capturePoints.keySet()) {
                capturePointList.add(new CapturePoint(key, map, capturePointLatLngList.get(i), capturePoints.get(key)));
            i ++;

        }
        System.out.println("The list contains " + capturePointList.size() + " Elements");
    }

}
