package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AcceptanceActivity extends AppCompatActivity{

    ListView listview;

    ArrayList<String> arraylist = new ArrayList<>();
    DatabaseReference mRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptance);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AcceptanceActivity.this, android.R.layout.simple_list_item_1,arraylist);

        listview = (ListView) findViewById(R.id.listRequests);

        listview.setAdapter(arrayAdapter);

        mRef = FirebaseDatabase.getInstance().getReference();

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                arraylist.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }
            //change from arraylist to list for flexibility,
            // dont merge w/o fixing android xml changes,
            // String value --to-> Post or User or whatever class data type
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
