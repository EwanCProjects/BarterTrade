package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AcceptanceActivity extends AppCompatActivity {
    private ArrayList<Singular_Item> arraylist;
    private RecyclerView mRecyclerView;
    private AdapterAcceptance mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseReference dRef;
    int accept_flags = 0;
    int decline_flags = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_acceptance);
        createList();
        buildRecyclerView();
    }

    public void removeItem(int position) {
        arraylist.remove(position);
        mAdapter.notifyItemRemoved(position);
        decline_flags++;
    }

    public void switchActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void showMessageIcon(ImageView ig) {
        ig.setVisibility(View.VISIBLE);
        accept_flags++;
    }
    public int getAcceptance(){
        return accept_flags;
    }
    public int getDeclines(){
        return decline_flags;
    }

    public void createList() {
        arraylist = new ArrayList<>();
        dRef = FirebaseDatabase.getInstance().getReference().child("Trades");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    String value = ds.child("title").getValue(String.class);
                    String value2 = ds.child("description").getValue(String.class);
                    arraylist.add(new Singular_Item(R.id.imageView,value,value2));
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        };
        dRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterAcceptance(arraylist);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterAcceptance.OnItemClickListener() {
            @Override
            public void onAcceptClick(ImageView ig) {
                showMessageIcon(ig);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }

            @Override
            public void onMessageClick() {
                switchActivity();
            }

        });
    }

}


