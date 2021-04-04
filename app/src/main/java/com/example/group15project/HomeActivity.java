package com.example.group15project;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import currentUserProperties.CurrentUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;
    public static final int MAX_LOCAL_DISTANCE = 50000; //local is defined as 50km max in this app
    RecyclerView homeView;
    HomeAdapter homeAdapter;
    HomeAdapter searchedAdapter; // might not need to be global

    public static String currUser = CurrentUser.getInstance().currUserString;
    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    List<Post> extractedPosts = new ArrayList<>();
    List<String> postTitles = new ArrayList<>();
    List<String> postOPs = new ArrayList<>();
    List<String> postCategories = new ArrayList<>();
    SearchBar searchBar;
    ArrayList<Post> postArrayList = new ArrayList<>();
    List<String> postImages = new ArrayList<>();

    Context context;
    private MyLocation myLocation;

    ArrayList<Post> postListFound = new ArrayList<>(); // aka postList
    public static DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseRead(realTimeDatabase);

        searchBar = new SearchBar();
        postArrayList = searchBar.ListOfAllPosts();

        homeView = findViewById(R.id.homePostsView);

        homeAdapter = new HomeAdapter(this, extractedPosts, postTitles, postOPs, postCategories, postImages);

        homeView.setAdapter(homeAdapter);
        homeView.setLayoutManager(new LinearLayoutManager(this));

        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        getLocation();

        Button newPostButton = findViewById(R.id.newPostButton);
        newPostButton.setOnClickListener(this);
        Button histButton = findViewById(R.id.histButton);
        histButton.setOnClickListener(this);
        Button searchBarMainBtn = findViewById(R.id.searchBarMainBtn);
        searchBarMainBtn.setOnClickListener(this);

        Button tradeButton = findViewById(R.id.tradeHistory);
        tradeButton.setOnClickListener(this);
        Button chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener(this);

        /// searching:
        searchedAdapter = new  HomeAdapter(this, extractedPosts, postTitles, postOPs, postCategories, postImages);
    }

    /** useful when showing main page with default or prev saved preferences of user */

    private void setFilterPrefsToDefaultIfNeeded() {
        FilterPreferences filterPrefs = UserStatusData.getUserFilterPrefs(this);
        if (filterPrefs != null){

        }else{
            filterPrefs = new FilterPreferences();
            filterPrefs.setMaxDistance(MAX_LOCAL_DISTANCE/1000);
            UserStatusData.saveUserFilterPrefsData(filterPrefs,this);
        }
    }

    private void checkLocationPermission(final Activity activity, final Context context, final String Permission, final String prefName) {

        PermissionUtil.checkPermission(activity, context, Permission, prefName,
                new PermissionUtil.PermissionAskListener() {
                    @Override
                    public void onPermissionAsk() {
                        ActivityCompat.requestPermissions(HomeActivity.this,
                                new String[]{Permission},
                                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                    }

                    @Override
                    public void onPermissionPreviouslyDenied() {
                        //show a dialog explaining is permission denied previously , but app require it and then request permission

                        showToast("Permission previously Denied.");

                        ActivityCompat.requestPermissions(HomeActivity.this,
                                new String[]{Permission},
                                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                    }

                    @Override
                    public void onPermissionDisabled() {
                        // permission check box checked and permission denied previously .
                        askUserToAllowPermissionFromSetting();
                    }

                    @Override
                    public void onPermissionGranted() {
                        showToast("Permission Granted.");
                    }
                });
    }

    private void askUserToAllowPermissionFromSetting() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Permission Required:");

        // set dialog message
        alertDialogBuilder
                .setMessage("Kindly allow Permission from App Setting, without this permission app could not show maps.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        showToast("Permission forever Disabled.");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                //permission was granted! Do what we need to do.
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    // Permission Denied
                    Toast.makeText(this, "Permission denied, please change your permission in your settings if needed", Toast.LENGTH_SHORT)
                            .show();
                }
                return;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //Get location
    public void getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (myLocation == null)
        {
            myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

        }
    }


    public void databaseRead(DatabaseReference db) {
        //code for database initialization and grabbing all Posts
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.child("Posts").getChildren()) {
                    Post extractedPost = postSnapshot.getValue(Post.class);
                    if (!extractedPost.getTradeCompleted()) {
                        extractedPosts.add(extractedPost);
                        postTitles.add(extractedPost.getPostTitle());
                        postOPs.add(extractedPost.getAuthor());
                        postCategories.add(extractedPost.getPostCategory());
                        homeAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        };
        db.addValueEventListener(postListener);
    }

    protected void switchToHistoryWindow() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    protected void switchToPostWindow() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

    protected void switchToTradeWindow() {
        Intent intent = new Intent(this, HistTradeActivity.class);
        startActivity(intent);
    }

    protected void switchToChatWindow() {
        Intent intent = new Intent(this, HistChatActivity.class);
        startActivity(intent);
    }

    ////////////// update with search activity
    protected void switchToSearchResults() {
        //Intent intent = new Intent(this, SearchBarActivity.class);
        //startActivity(intent);
        EditText searchText = findViewById(R.id.searchText);
        List<Post> foundPostList = searchBar.foundPosts(searchText.getText().toString(), postArrayList);

        System.out.println(foundPostList.size());

        List<String> postTitles = new ArrayList<>();
        List<String> postOPs = new ArrayList<>();
        List<String> postCategories = new ArrayList<>();
        List<String> postImages = new ArrayList<>();

        for(int i = 0; i < foundPostList.size(); i++){
            postTitles.add(foundPostList.get(i).getPostTitle());
            postOPs.add(foundPostList.get(i).getAuthor()); /// username? must be email
            postCategories.add(foundPostList.get(i).getPostCategory());
            // postImages.add(foundPostList.get(i).); ??????
            // !!!! Talk to Ewan, need to add list of images to show in search results!
        }


        searchedAdapter = new HomeAdapter(this, foundPostList, postTitles, postOPs, postCategories, postImages);
        homeView.setAdapter(searchedAdapter);
        homeView.setLayoutManager(new LinearLayoutManager(this));


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newPostButton:
                switchToPostWindow();
                break;

            case R.id.histButton:
                switchToHistoryWindow();
                break;

            case R.id.searchBarMainBtn:
                switchToSearchResults();
                break;

            case R.id.tradeHistory:
                switchToTradeWindow();
                break;

            case R.id.chatButton:
                switchToChatWindow();
                break;

            default:
                break;
        }
        //Toast.makeText(MainActivity.this,"Firebase connection success", Toast.LENGTH_LONG).show();
    }

    /**
     * runs when Filter button is clicked
     */
    public void goToFilterActivity(View v){
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }

}


