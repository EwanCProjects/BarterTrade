package com.example.group15project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.String;

public class JUnitTestDeletePost {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    static Post testPost;
    static DeletePostActivity deletePostActivity;

    @BeforeClass
    public static void setup() {
        testPost = new Post();
        realTimeDatabase.child("Posts").child("00test00").setValue(testPost);
    }

    @Test
    public void checkPostIsDeleted() {
        deletePostActivity.deletePost(testPost);
        //call delete post

        final Boolean[] postExists = new Boolean[1];
        realTimeDatabase.child("Posts").orderByChild("postId").equalTo(testPost.getPostId())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    //post exists
                    postExists[0] = true;
                }else {
                    //post does not exist
                    postExists[0] = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        assertFalse(postExists[0]);

        //check post exists in database, assert false
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
