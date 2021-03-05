package com.example.group15project;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
// NEED HELP

public class TestSearchBarActivity {


    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference titleGiven = storageRef.child(title); // both need to be changed to strings...
    StorageReference CategoryGiven = storageRef.child(postCategory);


    public boolean isEmptyInput(String testString){
        return testString.isEmpty();
    }

    public boolean checkPostExistence(String title, String category){
        boolean titleIsAvailable = false;
        boolean categoryIsAvailable = false;
        if(title == titleGiven){
            titleAvailable = true;
        }
        if(category == CategoryGiven){
            categoryIsAvailable = true;
        }
        return (titleIsAvailable && categoryIsAvailable);
    }

}
