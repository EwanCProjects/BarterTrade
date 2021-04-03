package com.example.group15project;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView homeView;
    HomeAdapter homeAdapter;
    HomeAdapter searchedAdapter; // might not need to be global

    public static String currUser = RegistrationActivity.currUser;
    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    List<Post> extractedPosts = new ArrayList<>();
    List<String> postTitles = new ArrayList<>();
    List<String> postOPs = new ArrayList<>();
    List<String> postCategories = new ArrayList<>();
    SearchBar searchBar;
    ArrayList<Post> postArrayList = new ArrayList<>();
    List<String> postImages = new ArrayList<>();


    ArrayList<Post> postListFound = new ArrayList<Post>(); // aka postList
    public static DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseRead(realTimeDatabase);

        searchBar = new SearchBar();
        postArrayList = searchBar.ListOfAllPosts();

        if (currUser == null) {
            currUser = LoginActivity.currUser;
        }

        homeView = findViewById(R.id.homePostsView);

        homeAdapter = new HomeAdapter(this, extractedPosts, postTitles, postOPs, postCategories, postImages);

        homeView.setAdapter(homeAdapter);
        homeView.setLayoutManager(new LinearLayoutManager(this));

        Button newPostButton = findViewById(R.id.newPostButton);
        newPostButton.setOnClickListener(this);
        Button histButton = findViewById(R.id.histButton);
        histButton.setOnClickListener(this);
        Button searchBarMainBtn = findViewById(R.id.searchBarMainBtn);
        searchBarMainBtn.setOnClickListener(this);


        /// searching:
        searchedAdapter = new  HomeAdapter(this, extractedPosts, postTitles, postOPs, postCategories, postImages);
        //postListFound = ListOfAllPosts(); //? still need

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

    ////////////// update with search activity
    protected void switchToSearchResults() {
        //Intent intent = new Intent(this, SearchBarActivity.class);
        //startActivity(intent);
        EditText searchText = findViewById(R.id.searchText);
        List<Post> foundPostList = searchBar.foundPosts(searchText.getText().toString(), postArrayList);

        System.out.println(foundPostList.size());

        List<String> postTitles = new ArrayList<>();
        List<String> postOPs = new ArrayList<>();
        List<String> postCategories = new ArrayList<>();
        List<String> postImages = new ArrayList<>();

        for(int i = 0; i < foundPostList.size(); i++){
            postTitles.add(foundPostList.get(i).getPostTitle());
            postOPs.add(foundPostList.get(i).getAuthor()); /// username? must be email
            postCategories.add(foundPostList.get(i).getPostCategory());
            // postImages.add(foundPostList.get(i).); ??????
            // !!!! Talk to Ewan, need to add list of images to show in search results!
        }


        searchedAdapter = new HomeAdapter(this, foundPostList, postTitles, postOPs, postCategories, postImages);
        homeView.setAdapter(searchedAdapter);
        homeView.setLayoutManager(new LinearLayoutManager(this));


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

            case R.id.searchBarMainBtn:
                switchToSearchResults();
                break;

            default:
                break;
        }
        //Toast.makeText(MainActivity.this,"Firebase connection success", Toast.LENGTH_LONG).show();
    }

    /**
     * runs when Filter button is clicked
     */
    public void goToFilterActivity(View v){
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }




    /// SEARCH BAR ACTIVITY ADDED:




}


