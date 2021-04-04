package com.example.group15project;

import android.widget.TextView;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class JUnitTestViewPost {
    static ViewPostActivity viewPostActivity;

    String author = "Phone-obsessed teen";
    String testID = "abc123";
    String title = "Two year old phone";
    String category = "Electronics";
    String description = "This is my phone. There are many like it, " +
            "but this one is mine. " +
            "My phone is my best friend. It is my life.";
    String image = "https://firebasestorage.googleapis.com/v0/b/barter-trade-app.appspot.com/o/1615742712551?alt=media&token=3dd86e49-8800-41ac-918d-b5ce9ad84699";
    Post testPost = new Post(author, testID, title, description, category, image, 0 , 0 );

    @BeforeClass
    public static void setup() {
        viewPostActivity = new ViewPostActivity();
    }

    // AT 2 - ET 2
    @Test
    public void checkIfPosted() {

        viewPostActivity.currPost = testPost;
        assertEquals(viewPostActivity.currPost.getAuthor(), testPost.getAuthor());
        assertEquals(viewPostActivity.currPost.getPostTitle(), testPost.getPostTitle());
        assertEquals(viewPostActivity.currPost.getPostDescription(), testPost.getPostDescription());
        assertEquals(viewPostActivity.currPost.getPostCategory(), testPost.getPostCategory());
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
