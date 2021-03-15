package com.example.group15project;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class ViewPostActivity extends AppCompatActivity implements View.OnClickListener {
    public static Post currPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        Button contactSeller = findViewById(R.id.contactButton);
        contactSeller.setOnClickListener(this);

        getPost();
        displayPost(currPost);
    }

    private void getPost() { currPost = HomeAdapter.currPost; }

    public void displayPost(Post postToDisplay) {
        TextView postTitle = findViewById(R.id.postTitle);
        TextView originalPoster = findViewById(R.id.originalPosterField);
        TextView postCategory = findViewById(R.id.categoryTextField);
        TextView postDescription = findViewById(R.id.descriptionTextField);

        setTextField(postTitle, postToDisplay.getPostTitle());
        setTextField(originalPoster, postToDisplay.getAuthor());
        setTextField(postCategory, postToDisplay.getPostCategory());
        setTextField(postDescription, postToDisplay.getPostDescription());
    }

    protected void setTextField(TextView field, String strValue) {
        field.setText(strValue);
    }

    protected String getDisplayedTitle() {
        TextView postTitle = findViewById(R.id.postTitle);
        return postTitle.getText().toString().trim();
    }

    protected String getDisplayedAuthor() {
        TextView originalPoster = findViewById(R.id.originalPosterField);
        return originalPoster.getText().toString().trim();
    }

    protected String getDisplayedCategory() {
        TextView postCategory = findViewById(R.id.categoryTextField);
        return postCategory.getText().toString().trim();
    }

    protected String getDisplayedDescription() {
        TextView postDescription = findViewById(R.id.descriptionTextField);
        return postDescription.getText().toString().trim();
    }

    public void onClick(View view) {
        // add code for pressing contact button and integrating that contact button US
        Intent intent = new Intent(this, TradeRequestActivity.class);
        startActivity(intent);
    }

    private String getAddressFromLonLatOnUI(String latLon){
        double lat = Double.parseDouble(latLon.split(",")[0]);
        double lon = Double.parseDouble(latLon.split(",")[1]);

        try {
            //get the address
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocation(lat,lon,1);
            String address = addresses.get(0).getAddressLine(0);

            return address;
        } catch (IOException e) {
            return "unable to get address";
        }
    }

}
