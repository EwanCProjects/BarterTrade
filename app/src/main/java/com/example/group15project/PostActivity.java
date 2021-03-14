package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String userID = HomeActivity.currUser;


    String image = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(this);

        if(getIntent().hasExtra("postTitle") && getIntent().hasExtra("postDescription")&&
                getIntent().hasExtra("postCategory")){
            String postTitle = getIntent().getStringExtra("postTitle");
            String postDescription = getIntent().getStringExtra("postDescription");
            String postCategory = getIntent().getStringExtra("postCategory");

            EditText title = findViewById(R.id.originalPosterField);
            EditText description = findViewById(R.id.descriptionTextField);
            EditText category = findViewById(R.id.categoryTextField);
            title.setText(postTitle);
            description.setText(postDescription);
            category.setText(postCategory);
        }





    }

    protected String generatePostID() {
        return UUID.randomUUID().toString();
    }

    protected String getPostTitle() {
        EditText title = findViewById(R.id.originalPosterField);
        return title.getText().toString().trim();
    }

    protected String getPostDescription() {
        EditText description = findViewById(R.id.descriptionTextField);
        return description.getText().toString().trim();
    }

    protected String getPostCategory() {
        EditText category = findViewById(R.id.categoryTextField);
        return category.getText().toString().trim();
    }

    protected boolean isEmptyTitle(String title) {
        return title.isEmpty();
    }

    protected boolean isEmptyDescription(String description) {
        return description.isEmpty();
    }

    protected boolean isEmptyCategory(String category) {
        return category.isEmpty();
    }

    protected Post createPost(String author, String postID, String postTitle, String postDescription, String postCategory, String image) {
        return new Post(author, postID, postTitle, postDescription, postCategory, image);
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    protected void addPostToFirebase(DatabaseReference mDatabase, Post post, String postID){
        mDatabase.child("Posts").child(postID).setValue(post);
    }

    // We need to implement this later for adding a list of posts to a User
    protected void addPostIDToUser(DatabaseReference mDatabase, String postID, String userID){

    }

    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.postimagebtn) {


            String postTitle = getPostTitle();
            String postDescription = getPostDescription();
            String postCategory = getPostCategory();

            Intent firstintent = new Intent(PostActivity.this, PostImageActivity.class);
            firstintent.putExtra("postTitle", postTitle);
            firstintent.putExtra("postDescription", postDescription);
            firstintent.putExtra("postCategory", postCategory);
            startActivity(firstintent);


        }
        else{

             image = getIntent().getStringExtra("image_url");


            String postID = generatePostID();
        String postTitle = getPostTitle();
        String postDescription = getPostDescription();
        String postCategory = getPostCategory();
        String errorMessage = getResources().getString(R.string.empty_string);

        if(isEmptyDescription(postDescription)) {
            errorMessage = getResources().getString(R.string.empty_description);
        }

        if(isEmptyCategory(postCategory)) {
            errorMessage = getResources().getString(R.string.empty_category);
        }

        if (isEmptyTitle(postTitle)) {
            errorMessage = getResources().getString(R.string.empty_title);
        }

        if (errorMessage.isEmpty()) {
            Post post = createPost(userID, postID, postTitle, postDescription, postCategory, image);
            addPostToFirebase(realTimeDatabase, post, postID);
            switchToHomeWindow();
            //viewPostWindow()
        }

        else {
            setStatusMessage(errorMessage);
        }
    }}
}
