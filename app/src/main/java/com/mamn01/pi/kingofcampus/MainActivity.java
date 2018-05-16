package com.mamn01.pi.kingofcampus;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ListActivity {

    String tests[] = { "MapActivity", "ResultActivity","CaptureActivity"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tests));
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id){
        super.onListItemClick(list,view,position,id);
        String testName = tests[position];
        try{
            Class c = Class.forName("com.mamn01.pi.kingofcampus." + testName);
            Intent intent = new Intent(this, c);
            startActivity(intent);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }



}