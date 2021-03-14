package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.UUID;


public class TestPostActivity extends AppCompatActivity implements View.OnClickListener {

    //public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String userID = "3130_student_working_late";

    String image = "";

    // functions for Iteration 2
    /*
    FirebaseDatabase db = null;
    private DatabaseReference database;
    DatabaseReference postTitleRef = null;
    DatabaseReference postCategoryRef = null;
    DatabaseReference postDescriptionRef = null;

    public void dbRead(DatabaseReference db){
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
                List<String> post = new ArrayList();
                while (itr.hasNext()){
                    Post p = itr.next().getValue(Post.class);
                    String dbValue = p.getPostId();
                    post.add(dbValue);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    */

    /*protected void move2PostScreen(String postTitle, String postCategory, String postDescription){

    }

    public void postOnButtonClick(View view) {
    }*/

    /*
    protected void initializeDatabase() {
        db = FirebaseDatabase.getInstance();
        postTitleRef = db.getReference("Posts/postTitle");
        postCategoryRef = db.getReference("Posts/postCategory");
        postDescriptionRef = db.getReference("Posts/postDesc");
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(this);

        Button postimageButton = findViewById(R.id.postimagebtn);
        postimageButton.setOnClickListener(this);

        //initializeDatabase();

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

    protected Post createPost(String author, String postID, String postTitle, String postDescription, String postCategory) {
        return new Post(author, postID, postTitle, postDescription, postCategory, image);
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    protected void addPostToFirebase(DatabaseReference mDatabase, Post post, String postID){
        mDatabase.child("Posts").child(postID).setValue(post);
    }

    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.postimagebtn) {

            System.out.println("the method is calledAAAAAAAAAAAAAAAA");
            Intent firstintent = new Intent(TestPostActivity.this, PostImageActivity.class);
            startActivity(firstintent);


        }
        else if(view.getId() == R.id.postButton) {
            System.out.println("kill meuebfhhoeriufierhfierhefierhfjker");
            String postID = generatePostID();
            String postTitle = getPostTitle();
            String postDescription = getPostDescription();
            String postCategory = getPostCategory();
            String errorMessage = getResources().getString(R.string.empty_string);

            if (isEmptyDescription(postDescription)) {
                errorMessage = getResources().getString(R.string.empty_description);
            }

            if (isEmptyCategory(postCategory)) {
                errorMessage = getResources().getString(R.string.empty_category);
            }

            if (isEmptyTitle(postTitle)) {
                errorMessage = getResources().getString(R.string.empty_title);
            }

            if (errorMessage.isEmpty()) {
                Post post = createPost(userID, postID, postTitle, postDescription, postCategory);
                //addPostToFirebase(realTimeDatabase, post, postID);
                switchToHomeWindow();
                //viewPostWindow()
            } else {
                setStatusMessage(errorMessage);
            }
        }


    }
    }
