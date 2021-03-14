package com.example.group15project;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.group15project.HomeActivity;
import com.example.group15project.PostActivity;
import com.example.group15project.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.sql.Timestamp;

public class PostImageActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    UploadTask uploadTask;
    Uri file = null;
    Uri transfer = null;
    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String userID = HomeActivity.currUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postimage);

        Button postButton = findViewById(R.id.btnChoose);
        postButton.setOnClickListener(this);


        Button uploadButton = findViewById(R.id.btnUpload);
        uploadButton.setOnClickListener(this);


        Button returnButton = findViewById(R.id.btnreturnpost);
        returnButton.setOnClickListener(this);


    }



    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    int check = 0;
    String errorMessage;
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnChoose) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
            check = 1;
        }





        if (view.getId() == R.id.btnUpload) {

            if(check == 0){
                errorMessage = "please choose an image";
                TextView statusLabel = findViewById(R.id.statusLabel);
                statusLabel.setText(errorMessage);
            }
            else{
                Toast.makeText(this, "currently uploading image, please wait", Toast.LENGTH_LONG).show();
                check = 2;
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                //insert system timestamp
                StorageReference riversRef = storageRef.child(String.valueOf(timestamp.getTime()));

                uploadTask = riversRef.putFile(file);
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        // Continue with the task to get the download URL
                        return riversRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            System.out.println(downloadUri);
                            transfer = downloadUri;
                        } else {
                            System.out.println("error couldnt get uri");

                        }
                    }
                });
                Toast.makeText(this, "image uploaded, click done", Toast.LENGTH_LONG).show();

            }}




        if (view.getId() == R.id.btnreturnpost) {
            if(check == 0){
                errorMessage = "please choose an image";
                TextView statusLabel = findViewById(R.id.statusLabel);
                statusLabel.setText(errorMessage);
            }
            else if(check == 1){
                errorMessage = "please upload an image";
                TextView statusLabel = findViewById(R.id.statusLabel);
                statusLabel.setText(errorMessage);
            }
            else{
                String postTitle = getIntent().getStringExtra("postTitle");
                String postDescription = getIntent().getStringExtra("postDescription");
                String postCategory = getIntent().getStringExtra("postCategory");

                Intent intent = new Intent(PostImageActivity.this, PostActivity.class);
                intent.putExtra("postTitle", postTitle);
                intent.putExtra("postDescription", postDescription);
                intent.putExtra("postCategory", postCategory);
                intent.putExtra("image_url", transfer.toString());
                startActivity(intent);
            }
        }

    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == 0) {
                    Uri selectedImageUri = data.getData();
                    // Get the path from the Uri
                    final String path = getPathFromURI(selectedImageUri);
                    if (path != null) {
                        File f = new File(path);
                        selectedImageUri = Uri.fromFile(f);

                    }
                    file = selectedImageUri;
                    System.out.println(selectedImageUri.getLastPathSegment());
                    // Set the image in ImageView
                    ImageView imageV = findViewById(R.id.imgView);
                    imageV.setImageURI(selectedImageUri);
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }


    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }




}
