package com.example.group15project;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.security.acl.Permission;
import java.util.Random;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;



public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String userID = HomeActivity.currUser;

    private MyLocation myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(this);
    }

    private void showDbDataUi(Post dataFromDb) {
        String address = latLongStringToAddress(dataFromDb);

        ((TextView) findViewById(R.id.GPSLocation)).setText(address);
    }

    private String latLongStringToAddress(Post dataFromDb) {
        List<Address> addresses = null;
        try {
            Geocoder geocoder = new Geocoder(this);
            Double lat = Double.parseDouble(dataFromDb.getLatLonLocation().split(",")[0]);
            Double lon = Double.parseDouble(dataFromDb.getLatLonLocation().split(",")[1]);
            addresses = geocoder.getFromLocation(lat, lon, 1);
            myLocation = new MyLocation(null) {
                @Override
                public void LocationResult(Location location) {
                }
            };
            LongLatLocation latLocation = new LongLatLocation(lat, lon);
            myLocation.setLastLocation(latLocation);
        } catch (IOException e) {
            getLocation();
            return "";
        }

        return addresses.get(0).getAddressLine(0);
    }

    private void getLocation() {
        myLocation = new MyLocation(this) {
            @Override
            public void LocationResult(Location location)
            {
                showMyLocationOnUI(location);
            }
        };
    }

    private void showMyLocationOnUI(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        try {
            //get the address
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);
            String address = addresses.get(0).getAddressLine(0);

            ((TextView) findViewById(R.id.GPSLocation)).setText(address);

        } catch (IOException e) {
            ((TextView) findViewById(R.id.GPSLocation)).setText("unable to get address");
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 99 && grantResults[0] == PERMISSION_GRANTED) {
            getLocation();
        } else {
            Toast.makeText(this, "This app requires permissions to get Location data"
                    , Toast.LENGTH_SHORT).show();
        }
    }

    protected String generatePostID() {
        return UUID.randomUUID().toString();
    }

    protected String getPostTitle() {
        EditText title = findViewById(R.id.originalPosterField);
        return title.getText().toString().trim();
    }

    protected String getPostDescription() {
        EditText description = findViewById(R.id.descriptionTextField);
        return description.getText().toString().trim();
    }

    protected String getPostCategory() {
        EditText category = findViewById(R.id.categoryTextField);
        return category.getText().toString().trim();
    }

    protected boolean isEmptyTitle(String title) {
        return title.isEmpty();
    }

    protected boolean isEmptyDescription(String description) {
        return description.isEmpty();
    }

    protected boolean isEmptyCategory(String category) {
        return category.isEmpty();
    }

    protected Post createPost(String author, String postID, String postTitle, String postDescription, String postCategory) {
        return new Post(author, postID, postTitle, postDescription, postCategory);
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    protected void addPostToFirebase(DatabaseReference mDatabase, Post post, String postID){
        mDatabase.child("Posts").child(postID).setValue(post);
    }

    // We need to implement this later for adding a list of posts to a User
    protected void addPostIDToUser(DatabaseReference mDatabase, String postID, String userID){

    }

    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        String postID = generatePostID();
        String postTitle = getPostTitle();
        String postDescription = getPostDescription();
        String postCategory = getPostCategory();
        String errorMessage = getResources().getString(R.string.empty_string);

        if(isEmptyDescription(postDescription)) {
            errorMessage = getResources().getString(R.string.empty_description);
        }

        if(isEmptyCategory(postCategory)) {
            errorMessage = getResources().getString(R.string.empty_category);
        }

        if (isEmptyTitle(postTitle)) {
            errorMessage = getResources().getString(R.string.empty_title);
        }

        if (errorMessage.isEmpty()) {
            Post post = createPost(userID, postID, postTitle, postDescription, postCategory);
            addPostToFirebase(realTimeDatabase, post, postID);
            switchToHomeWindow();
            //viewPostWindow()
        }

        else {
            setStatusMessage(errorMessage);
        }
    }
}
