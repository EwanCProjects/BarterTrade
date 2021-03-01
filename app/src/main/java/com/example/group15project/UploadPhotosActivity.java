package com.example.group15project;
//package com.google.firebase.referencecode.storage; // ?

import android.app.Activity;

import java.io.FileNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class UploadPhotosActivity extends Activity {


    public void includesForUploadFiles() throws FileNotFoundException {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a root reference
        StorageReference storageRef = storage.getReference();


        // Create a reference to 'chair.jpg'
        StorageReference chairRef = storageRef.child('chair.jpg');

        // Create a reference to 'images/chair.jpg'
        StorageReference chairImagesRef = storageRef.child('images/chair.jpg');

        // While the file names are the same, the references point to different files
        chairRef.getName().equals(chairImagesRef.getName());    // true
        chairRef.getPath().equals(chairImagesRef.getPath());    // false

    }



}

