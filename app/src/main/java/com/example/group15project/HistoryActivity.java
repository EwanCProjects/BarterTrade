package com.example.group15project;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import currentUserProperties.CurrentUser;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView histView;
    HistoryAdapter histAdapter;

    public static String currUser = CurrentUser.getInstance().currUserString;
    DatabaseReference realTimeDatabase = HomeActivity.realTimeDatabase;
    List<Post> extractedPosts = new ArrayList<>();
    List<String> postTitles = new ArrayList<>();
    List<String> postOPs = new ArrayList<>();
    List<String> postCategories = new ArrayList<>();
    List<String> postImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        histView = findViewById(R.id.histPostsView);

        histAdapter = new HistoryAdapter(this, extractedPosts, postTitles, postOPs, postCategories, postImages);
        histView.setAdapter(histAdapter);
        histView.setLayoutManager(new LinearLayoutManager(this));

        databaseRead(realTimeDatabase);

    }

    public void databaseRead(DatabaseReference db) {
        //code for database initialization and grabbing all Posts
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.child("Posts").getChildren()) {
                    Post extractedPost = postSnapshot.getValue(Post.class);
                    if (extractedPost.getAuthor().equals(currUser) && extractedPost.getTradeCompleted()) {
                        extractedPosts.add(extractedPost);
                        postTitles.add(extractedPost.getPostTitle());
                        postOPs.add(extractedPost.getAuthor());
                        postCategories.add(extractedPost.getPostCategory());
                        postImages.add(extractedPost.getimage());
                        histAdapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        };
        db.addValueEventListener(postListener);
    }

    @Override
    public void onClick(View view) {

    }
}