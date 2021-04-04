package com.example.group15project;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.location.LocationListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import currentUserProperties.CurrentUser;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;



public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String userID = CurrentUser.getInstance().currUserString;
    public static final String LOCATION_PERMISSION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String LOCATION_PREF = "locationPref";

    String image = "";
    private LocationManager locationManager;
    private double latitude;
    private double longitude;


    private MyLocation myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(this);

        if(getIntent().hasExtra("postTitle") && getIntent().hasExtra("postDescription")&&
                getIntent().hasExtra("postCategory")){
            String postTitle = getIntent().getStringExtra("postTitle");
            String postDescription = getIntent().getStringExtra("postDescription");
            String postCategory = getIntent().getStringExtra("postCategory");

            EditText title = findViewById(R.id.originalPosterField);
            EditText description = findViewById(R.id.descriptionTextField);
            EditText category = findViewById(R.id.categoryTextField);
            title.setText(postTitle);
            description.setText(postDescription);
            category.setText(postCategory);
        }





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

    protected Post createPost(String author, String postID, String postTitle, String postDescription, String postCategory, String image, double latitude, double longitude) {
        return new Post(author, postID, postTitle, postDescription, postCategory, image, latitude, longitude);
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

        if (view.getId() == R.id.postimagebtn) {


            String postTitle = getPostTitle();
            String postDescription = getPostDescription();
            String postCategory = getPostCategory();

            Intent firstintent = new Intent(PostActivity.this, PostImageActivity.class);
            firstintent.putExtra("postTitle", postTitle);
            firstintent.putExtra("postDescription", postDescription);
            firstintent.putExtra("postCategory", postCategory);
            startActivity(firstintent);


        }
        else{

             image = getIntent().getStringExtra("image_url");


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
            Post post = createPost(userID, postID, postTitle, postDescription, postCategory, image, latitude, longitude);
            addPostToFirebase(realTimeDatabase, post, postID);
            switchToHomeWindow();
            //viewPostWindow()
        }

        else {
            setStatusMessage(errorMessage);
        }
    }}
}
