package com.example.group15project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewPostActivity extends AppCompatActivity implements View.OnClickListener {
    public static Post currPost = null; // for integration, this will not be null but passed from HomeActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
    }

    public void displayPost(Post postToDisplay) {
        currPost = postToDisplay;

        TextView postTitle = findViewById(R.id.postTitle);
        TextView originalPoster = findViewById(R.id.originalPosterField);
        TextView postCategory = findViewById(R.id.categoryTextField);
        TextView postDescription = findViewById(R.id.descriptionTextField);

        setTextField(postTitle, currPost.getPostTitle());
        setTextField(originalPoster, currPost.getAuthor());
        setTextField(postCategory, currPost.getPostCategory());
        setTextField(postDescription, currPost.getPostDescription());
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
    }

}
