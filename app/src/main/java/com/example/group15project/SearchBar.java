package com.example.group15project;

import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchBar {



    public List<Post> foundPosts(String searched, ArrayList<Post> postList){
        //postList = ListOfAllPosts();
        List<Post> matchedPosts = new ArrayList<>();

        for (int i =0; i < postList.size(); i++){
            Post postObj = postList.get(i);
            // String titleAtIndex = postObj.getPostTitle();

            if(checkPostExistence(searched, postList.get(i))){
                matchedPosts.add(postList.get(i));

            }
        }

        //homeadapter.notifyDataSetChange;
        return matchedPosts;

    }

    public ArrayList<Post> ListOfAllPosts(){

        ArrayList<Post> postList = new ArrayList<Post>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts/");
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Post postRef;
                String snapshotStr = dataSnapshot.getKey();
                // postRef = dataSnapshot.child(snapshotStr).getValue(Post.class);
                System.out.println(snapshotStr);
                // postRef = dataSnapshot.child(snapshotStr).getValue(Post.class);

                Map<String, Post> mapPosts = new HashMap<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    //System.out.println(snapshot.getKey());
                    Post newPostObj = snapshot.getValue(Post.class);
                    System.out.println(newPostObj.getPostTitle());
                    postList.add(newPostObj);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


/*
        for (int i =0; i < postList.size(); i++){
            Post insertPost = new Post(currAuthour.toString(), currID.toString(),
                    currTitle.toString(), currDescription.toString(), currCategory.toString());
            postList.add(i, insertPost);
        }

 */
        return postList;
    }


    public boolean checkPostExistence(String searchString, Post post){
        boolean titleIsAvailable = false;
        boolean categoryIsAvailable = false;

        if(searchString == null || post == null){
            return false; // ie null
        }
        if (post.getPostTitle() == null || post.getPostCategory() == null){
            return false;
        }
        if(post.getPostTitle() != null){
            if(post.getPostTitle().toLowerCase().matches(searchString.toLowerCase())){
                titleIsAvailable = true;
            }
        }
        if(post.getPostCategory() != null){
            if(post.getPostCategory().toLowerCase().matches(searchString.toLowerCase())){
                categoryIsAvailable = true;
            }
        }
        return (titleIsAvailable || categoryIsAvailable);
    }

}
