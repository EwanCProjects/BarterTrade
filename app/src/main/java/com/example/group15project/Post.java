package com.example.group15project;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


public class Post {
    private String author;
    private String postId;
    private String postTitle;
    private String postDescription;
    private String postCategory;
    private String image;

    public Post(String author, String postId, String postTitle, String postDescription,
                String postCategory, String image) {
        this.author = author;
        this.postId = postId;
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postCategory = postCategory;
        this.image = image;
    }

    public Post() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() { return postTitle; }

    public void setPostTitle(String postTitle) { this.postTitle = postTitle; }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) { this.postCategory = postCategory; }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) { this.postDescription = postDescription; }

    public String getimage() {
        return image;
    }

    public void setimage(String image) { this.image = image; }





    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("author", author);
        result.put("postCategory:", postCategory);
        result.put("postDescription:", postDescription);
        result.put("postID", postId);
        result.put("postTitle", postTitle);
        return result;
    }
}

