package com.example.group15project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collections;

public class NewPostNotifications extends AppCompatActivity{
    private final FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseDatabase realTimeDatabase;
    private FirebaseStorage fbStorage;
    private ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post_notifications);

        //create instance of FirebaseStorage
        fbStorage = FirebaseStorage.getInstance();

    }

    private void getNotifications(){
        final ArrayList<Post> posts = new ArrayList<>();
        String path = "users/";
        final String userPath = UserStatusData.getUserID(this) + "/Notifications/NewPosts/";
        DatabaseReference ref = realTimeDatabase.getReference(userPath);
        DataSnapshot dataFromDb;
        //getPostsFromDBSnapShot(posts, dataFromDb);

    }

    public void createRecyclerView(ArrayList<Post> posts, ArrayList<String> newPostIds){
        if(posts == null) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.TaskPostsList);

        //using a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new PostNotificationRVAdapter(posts, fbStorage, newPostIds);
        recyclerView.setAdapter(mAdapter);

    }

    private void getPostsFromDBSnapShot(ArrayList<PostNotification> postNotifications, DataSnapshot dataFromdb){
        posts = new ArrayList<>();
        final ArrayList<String> postIds = new ArrayList();
        final ArrayList<String> newPostIds = new ArrayList<>();

        for(PostNotification p: postNotifications){
            if(p.isNew()){
                newPostIds.add(p.getPostID());
            }
            postIds.add(p.getPostID());
            p.setNew(false);
        }

        for(DataSnapshot userdata : dataFromdb.getChildren()) {
            for(DataSnapshot post : userdata.child("Posts").getChildren()){
                Post posts = post.getValue(Post.class);
                if(posts != null && postIds.contains(posts.getPostId())){
                    // posts.add(post);
                    System.out.println(posts.getPostTitle());
                }

            }
        }
        createRecyclerView(posts, newPostIds);
        setNotificationStatusToViewed(postNotifications);
    }

    private void setNotificationStatusToViewed(ArrayList<PostNotification> postNotifications){
        for(PostNotification p: postNotifications) {
            String path = "users/" + UserStatusData.getUserID(this ) + "Notifications/NewPosts/"
                    + p.getPostID();

        }
    }


}
