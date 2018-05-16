package com.mamn01.pi.kingofcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private StorageReference mStorageReference;
    private FirebaseDatabase fDatabase;
    private DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
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
                System.out.println("received nodes from database");
                addValuesToSOmething(nodes);
                Log.d(TAG, "Value is: " + nodes.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public void addValuesToSOmething(HashMap<String, Long> nodeMap) {
        System.out.println(nodeMap.toString());
    }
}
