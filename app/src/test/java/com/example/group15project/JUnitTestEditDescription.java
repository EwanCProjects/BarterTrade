package com.example.group15project;

import android.app.Activity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTestEditDescription {
    static EditPostActivity edActivity;
    private final static String author = "CS STUDENT EXAMPLE";
    public static String postId = "567890"; //HomeActivity.currUser;
    private final static String postTitle = "CHAIR";
    private final static String postDescription = "Original description of a chair...";
    private final static String postCategory = "FURNITURE";
    private final static String image = "    https://firebasestorage.googleapis.com/v0/b/barter-trade-app.appspot.com/o/1615742712551?alt=media&token=3dd86e49-8800-41ac-918d-b5ce9ad84699\n";

    private final static Post exOriginalPost = new Post( author,  postId,  postTitle,  postDescription, postCategory, image);

    // do not mock the post object using mockito


    @BeforeClass
    public static void setup() {
        //DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();// mock
        // Mockito.mock//
        //edActivity = new EditDescriptionActivity();
        // exUpdatedPost = new EditDescriptionActivity();
        edActivity = new EditPostActivity();
    }


    @Test
    public void changeIsEmpty() {
        assertTrue(edActivity.isEmptyDescription(""));
    }

    @Test
    public void changeIsNotEmpty(){
        assertFalse(edActivity.isEmptyDescription("bla bla"));
    }


    // HELP 1
    @Test
    public void descriptionIsUpdated(){
        // Post exUpdatedPost = exOriginalPost; // equal
        // = new Post (author, postId, postTitle, postDescription,  postCategory);
        // FirebaseDatabase instance = FirebaseDatabase.getInstance();
        // instance.getReference("Posts");
        // Post edited = exUpdatedPost.editedPost;
        Post updatedPost = exOriginalPost;
        String updatedDescriptionStr = "The updated description of a chair...";
        updatedPost.setPostDescription(updatedDescriptionStr);
        assertEquals(updatedPost.getPostDescription(),updatedDescriptionStr);
    }


    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
