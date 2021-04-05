package com.example.group15project;

public class PostNotification {
    private String postID;
    private boolean isNew;

    public PostNotification(){
    }

    public PostNotification(String postID, boolean isNew) {
        this.postID = postID;
        this.isNew = isNew;
    }

    public String getPostID() { return postID; }

    public void setPostID(String postID){ this.postID = postID; }

    public boolean isNew(){ return isNew; }

    public void setNew(boolean aNew) { this.isNew = aNew; }
}
