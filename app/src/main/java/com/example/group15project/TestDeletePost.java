package com.example.group15project;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestDeletePost extends AppCompatActivity implements View.OnClickListener {

    //static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    static Post testPost;



    @Override
    public void onClick(View v) {


    }

    public Post removePost(Post p) {
        p.setPostCategory("");
        p.setAuthor("");
        p.setPostDescription("");
        p.setPostId("");
        p.setPostTitle("");
        return p;
    }
}
