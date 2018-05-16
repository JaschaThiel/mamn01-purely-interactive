package com.mamn01.pi.kingofcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
    }

    public void changeLayout(View view){
        try{
            Class c = Class.forName("com.mamn01.pi.kingofcampus.MainActivity");
            Intent intent = new Intent(this, c);
            startActivity(intent);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}

