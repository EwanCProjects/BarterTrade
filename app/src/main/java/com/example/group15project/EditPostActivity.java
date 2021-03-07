package com.example.group15project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditPostActivity extends AppCompatActivity {

    FirebaseDatabase realTimeDatabase;
    String postID = "510fa197-1421-4ec3-a57a-f52ae4e76afd"; // get in contact w history
    Post globalPost;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        realTimeDatabase = FirebaseDatabase.getInstance();//.getReference();

        //DatabaseReference postNode = realTimeDatabase.getReference("Posts");
        Post fetchedPost = getPost(postID);

    }


    public Post getPost(String postID){
        DatabaseReference ref = realTimeDatabase.getReference("Posts/"+postID);
        //dataRef.child(postID);
        Post postTwo = new Post();
        ref.addValueEventListener(new ValueEventListener() {
            //Post post;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                globalPost = dataSnapshot.getValue(Post.class);
                //System.out.println(post.getPostDescription());
                EditText editText = findViewById(R.id.editDesciption);
                editText.setText(globalPost.getPostDescription());
                //globalPost = post;
                //return post;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        postTwo = globalPost;
        return postTwo;

    }

    public void buttonClicked(android.view.View view) {
        EditText editText = findViewById(R.id.editDesciption);
        String editedText = editText.getText().toString();
        globalPost.setPostDescription(editedText);

        DatabaseReference ref = realTimeDatabase.getReference();


        Map<String, Object> postValues = globalPost.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Posts/" + postID, postValues);

        ref.updateChildren(childUpdates);

    }




    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    protected boolean isEmptyTitle(String title) { return title.isEmpty(); }
    protected boolean isEmptyCategory(String category) {
        return category.isEmpty();
    }
    protected boolean isEmptyDescription(String description) {
        return description.isEmpty();
    }

    public void setUpdatedDescription(String newDescription){
        globalPost.setPostDescription(newDescription);
    }

    public String getUpdatedDescription(){
        return globalPost.getPostDescription();
    }


    protected void addUPDATEDPostToFirebase(DatabaseReference mDatabase, Post editedPost, String postID){
        mDatabase.child("Posts").child(postID).setValue(editedPost);
    }

}