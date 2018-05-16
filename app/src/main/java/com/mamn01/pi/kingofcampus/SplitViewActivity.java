package com.mamn01.pi.kingofcampus;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SplitViewActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitview);
    }

    public void statClick(View view){
        try{
            Class c = Class.forName("com.mamn01.pi.kingofcampus.ResultActivity");
            Intent intent = new Intent(this, c);
            startActivity(intent);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void mapClick(View view){
        try{
            Class c = Class.forName("com.mamn01.pi.kingofcampus.MapActivity");
            Intent intent = new Intent(this, c);
            startActivity(intent);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
