package com.example.group15project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
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
}
