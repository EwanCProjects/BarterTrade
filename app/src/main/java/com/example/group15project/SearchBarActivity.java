package com.example.group15project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// NEED HELP
// StorageReference titleGiven = storageRef.child(title); // both need to be changed to strings...
// StorageReference CategoryGiven = storageRef.child(postCategory);

public class SearchBarActivity extends Activity {

    FirebaseDatabase realTimeDatabase;
    public static DatabaseReference databaseReference;// = FirebaseDatabase.getInstance().getReference();

    ArrayList<Post> postList= new ArrayList<Post>(); // empty array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbar);
        postList = ListOfAllPosts();

    }

    public void buttonClicked(android.view.View view) {
        //System.out.println("!!!!!!");
        EditText editedTextOfTitle = findViewById(R.id.titleSearch);
        EditText editedTextOfCategory = findViewById(R.id.hashTagSearch);

        String editTitleStr = editedTextOfTitle.getText().toString();
        String editCategoryStr = editedTextOfCategory.getText().toString();

       // DatabaseReference ref = realTimeDatabase.getReference();//////not useful

        List<Post> foundPostsList = new ArrayList<>();

            foundPostsList = foundPosts(editTitleStr, editCategoryStr); // need to run through page



        // display post
        System.out.println(foundPostsList.size());

        //// display home page
    }

    /*
     * @return filled lists with posts
     * @status DONE
     */
    public ArrayList<Post> ListOfAllPosts(){ // refra\

       // realTimeDatabase = FirebaseDatabase.getInstance();//.getReference();
        // fetch from database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts/");
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Post postRef;
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

                //postRef = dataSnapshot.getValue(Post.class);
                //System.out.println(postRef.getPostTitle());
                //postList.add(postRef);
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

    /*
     * iterate over the posts until found post if maching title
     * @return filled lists with posts
     * @status MIGHT NEED HELP, not sure if being accessed
     */
    public List<Post> foundPosts(String title, String category){
        //postList = ListOfAllPosts();
        List<Post> matchedPosts = new ArrayList<>();

        for (int i =0; i < postList.size(); i++){
            Post postObj = postList.get(i);
            String titleAtIndex = postObj.getPostTitle();
            String categoryAtIndex = postObj.getPostCategory();
            if(checkPostExistence(titleAtIndex, categoryAtIndex, postList.get(i))){
                matchedPosts.add(postList.get(i));

            }
        }
        return matchedPosts;

    }

    /*
     * @return boolean if there exists a post
     * @status DONE
     */
    public boolean checkPostExistence(String title, String category, Post post){
        boolean titleIsAvailable = false;
        boolean categoryIsAvailable = false;

        // iterate over posting list
        //for(Post post: postList){

        ///// !!!!! first CHEKC IF NOT NULL ,
        ////// then verify searching is correct (i.e. .mathes is correct)
        ///// then make sure non empty list when searching for a value string
        //// contact Issac
        if(post.getPostTitle() != null && post.getPostCategory() != null ){
            if(post.getPostTitle().matches(title)){
                titleIsAvailable = true;
            }
            if(post.getPostCategory().matches(category)){
                categoryIsAvailable = true;
            }
        }

        return (titleIsAvailable && categoryIsAvailable); // Works , returns correctly
    }

    public boolean isEmptyInput(String testString){
        return testString.isEmpty();
    }

}
