package com.example.group15project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Button postButton = findViewById(R.id.postButton);
        postButton.setOnClickListener(this);
    }

    protected String getPostTitle() {
        EditText userName = findViewById(R.id.titleTextField);
        return userName.getText().toString().trim();
    }

    protected String getPostDescription() {
        EditText emailAddress = findViewById(R.id.descriptionTextField);
        return emailAddress.getText().toString().trim();
    }

    protected String getPostCategory() {
        EditText emailAddress = findViewById(R.id.categoryTextField);
        return emailAddress.getText().toString().trim();
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

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    @Override
    public void onClick(View view) {
        String postTitle = getPostTitle();
        String postDescription = getPostDescription();
        String postCategory = getPostCategory();
    }
}
