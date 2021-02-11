package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class PostActivity extends AppCompatActivity implements View.OnClickListener {


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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(this);

        initializeDatabase();

    }


    protected void initializeDatabase() {
        db = FirebaseDatabase.getInstance();
        postTitleRef = db.getReference("Posts/postTitle");
        postCategoryRef = db.getReference("Posts/postCategory");
        postDescriptionRef = db.getReference("Posts/postDesc");
    }


    protected String getPostTitle() {
        EditText userName = findViewById(R.id.titleTextField);
        return userName.getText().toString().trim();
    }

    protected String getPostDescription() {
        EditText emailAddress = findViewById(R.id.descriptionTextField);
        return emailAddress.getText().toString().trim();
    }

    protected String getPostCategory() {
        EditText emailAddress = findViewById(R.id.categoryTextField);
        return emailAddress.getText().toString().trim();
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

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    protected void addDataToFirebase(String postTitle, String postCategory, String postDescription){
        postTitleRef.setValue(postTitle);
        postCategoryRef.setValue(postCategory);
        postDescriptionRef.setValue(postDescription);
    }

    @Override
    public void onClick(View view) {
        String postTitle = getPostTitle();
        String postDescription = getPostDescription();
        String postCategory = getPostCategory();
        String errorMessage = new String();

        if (isEmptyTitle(postTitle)){
            errorMessage = getResources().getString(R.string.empty_title);
        }
        if(isEmptyCategory(postCategory)){
            errorMessage = getResources().getString(R.string.empty_category);
        }
        if(isEmptyDescription(postDescription)){
            errorMessage = getResources().getString(R.string.empty_description);
        }

        if (errorMessage.isEmpty()){
            addDataToFirebase(postTitle, postCategory, postDescription);
            //viewPostWindow()
        } else {
            setStatusMessage(errorMessage);
        }

    }

    protected void move2PostScreen(String postTitle, String postCategory, String postDescription){

    }

    public void postOnButtonClick(View view) {
    }
}
