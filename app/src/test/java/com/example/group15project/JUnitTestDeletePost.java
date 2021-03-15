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

    static TestDeletePost testerActivity;
    //static Post testPost;
    //static DeletePostActivity deletePostActivity;
    private final static String author = "example post";
    public static String postId = "005500"; //HomeActivity.currUser;
    private final static String postTitle = "--test--";
    private final static String postDescription = "description of post";
    private final static String postCategory = "TEST";
    private final static Post testPost = new Post( author,  postId,  postTitle,  postDescription, postCategory);

    @BeforeClass
    public static void setup() {
        //testPost = new Post("deleteTest", "00test00", "titletest", "description", "testCategory");
        //tester.sendPost(testPost);
        testerActivity = new TestDeletePost();
    }

    @Test
    public void postIdDeleted() {
        Post deletedPost = testerActivity.removePost(testPost);
        assertEquals("", deletedPost.getPostId());
        assertEquals("", deletedPost.getAuthor());
        assertEquals("", deletedPost.getPostCategory());
        assertEquals("", deletedPost.getPostDescription());
        assertEquals("", deletedPost.getPostTitle());

    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
