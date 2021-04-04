package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistViewPostActivity extends AppCompatActivity implements View.OnClickListener {
    public static Post currPost;
    DeletePost postDeleter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist_view_post);

        getPost();
        displayPost(currPost);

        Button editPostButton = findViewById(R.id.contactButton);
        editPostButton.setOnClickListener(this);
    }

    private void getPost() { currPost = HistoryAdapter.currPost; }

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

    protected void switchToEditPostWindow() {
        Intent intent = new Intent(this, EditPostActivity.class);
        startActivity(intent);
    }

    //delete post implementation changed
    /*protected void switchToDeletePostWindow() {
        Intent intent = new Intent(this, DeletePostActivity.class);
        startActivity(intent);
    }*/

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactButton:
                switchToEditPostWindow();
                break;

            case R.id.deleteButton:
                postDeleter.deletePost(currPost);
                break;

            default:
                break;
        }
    }
}
