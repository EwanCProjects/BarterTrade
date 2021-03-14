package com.example.group15project;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView homeView;
    HomeAdapter homeAdapter;

    public static String currUser = RegistrationActivity.currUser;
    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    List<Post> extractedPosts = new ArrayList<>();
    List<String> postTitles = new ArrayList<>();
    List<String> postOPs = new ArrayList<>();
    List<String> postCategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseRead(realTimeDatabase);

        if (currUser == null) {
            currUser = LoginActivity.currUser;
        }

        homeView = findViewById(R.id.homePostsView);

        homeAdapter = new HomeAdapter(this, extractedPosts, postTitles, postOPs, postCategories);
        homeView.setAdapter(homeAdapter);
        homeView.setLayoutManager(new LinearLayoutManager(this));

        Button newPostButton = findViewById(R.id.newPostButton);
        newPostButton.setOnClickListener(this);
        Button histButton = findViewById(R.id.histButton);
        histButton.setOnClickListener(this);
    }


    public void databaseRead(DatabaseReference db) {
        //code for database initialization and grabbing all Posts
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.child("Posts").getChildren()) {
                    Post extractedPost = postSnapshot.getValue(Post.class);
                    extractedPosts.add(extractedPost);
                    postTitles.add(extractedPost.getPostTitle());
                    postOPs.add(extractedPost.getAuthor());
                    postCategories.add(extractedPost.getPostCategory());
                    homeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        };
        db.addValueEventListener(postListener);
    }

    protected void switchToHistoryWindow() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    protected void switchToPostWindow() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newPostButton:
                switchToPostWindow();
                break;

            case R.id.histButton:
                switchToHistoryWindow();
                break;

            default:
                break;
        }
        //Toast.makeText(MainActivity.this,"Firebase connection success", Toast.LENGTH_LONG).show();
    }
}


