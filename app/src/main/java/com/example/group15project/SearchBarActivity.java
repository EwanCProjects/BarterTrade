package com.example.group15project;

import android.app.Activity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
// NEED HELP
//StorageReference titleGiven = storageRef.child(title); // both need to be changed to strings...
// StorageReference CategoryGiven = storageRef.child(postCategory);
// list of posts
// then iterate
//extractedUserPassword = userTree.child(Posts).child("password").getValue(String.class);

public class SearchBarActivity extends Activity {


    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();

    StorageReference postNode = storage.getReference("Posts"); // ?
    public static DataSnapshot currAuthour = null;
    public static DataSnapshot currID = null;
    public static DataSnapshot currTitle = null;
    public static DataSnapshot currDescription = null;
    public static DataSnapshot currCategory = null;
    ArrayList<Post> postList= new ArrayList<Post>(); // empty array



    /**
     * fill the lists with posts to iterate over
     * @return
     */

    public ArrayList<Post> ListOfAllPosts(){
        for (int i =0; i < postList.size(); i++){
            Post insertPost = new Post(currAuthour.toString(), currID.toString(),
                    currTitle.toString(), currDescription.toString(), currCategory.toString());
            postList.add(i, insertPost);
        }
        return postList;
    }


    public boolean isEmptyInput(String testString){
        return testString.isEmpty();
    }

    public boolean checkPostExistence(String title, String category){
        boolean titleIsAvailable = false;
        boolean categoryIsAvailable = false;

        // iterate over posting list
        for(Post post: postList){
            if(post.getPostTitle().matches(title)){
                titleIsAvailable = true;
            }
            if(category ==  post.getPostCategory()){
                categoryIsAvailable = true;
            }
        }


        return (titleIsAvailable && categoryIsAvailable);
    }

}
