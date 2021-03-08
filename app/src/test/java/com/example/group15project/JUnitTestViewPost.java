package com.example.group15project;

import android.widget.TextView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
@RunWith(MockitoJUnitRunner.Silent.class)
public class JUnitTestViewPost {
    static ViewPostActivity viewPostActivity;

    @Mock
    static Post mockPost = null;

    @Test public void checkIfPosted() {
        when(mockPost.getAuthor()).thenReturn("Phone-obsessed teen");
        when(mockPost.getPostTitle()).thenReturn("Two year old phone");
        when(mockPost.getPostCategory()).thenReturn("Electronics");
        when(mockPost.getPostDescription()).thenReturn("This is my phone. There are many like it, " +
                "but this one is mine. " +
                "My phone is my best friend. It is my life.");

        // viewPostActivity.displayPost(mockPost);

        assertEquals("Phone-obsessed teen", mockPost.getAuthor());
        Mockito.verify(mockPost, Mockito.atLeastOnce()).getAuthor();

        assertEquals("Two year old phone", mockPost.getPostTitle());
        Mockito.verify(mockPost, Mockito.atLeastOnce()).getPostTitle();

        assertEquals("Electronics", mockPost.getPostCategory());
        Mockito.verify(mockPost, Mockito.atLeastOnce()).getPostCategory();

        assertEquals("This is my phone. There are many like it, " +
                "but this one is mine. " +
                "My phone is my best friend. It is my life.", mockPost.getPostDescription());
        Mockito.verify(mockPost, Mockito.atLeastOnce()).getPostDescription();
    }



    

}
