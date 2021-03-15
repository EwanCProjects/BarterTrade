package com.example.group15project;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;

public class DeletePostActivity extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String userID = HomeActivity.currUser;
    public static String postID;
    public Post postToRemove;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_hist_view_post);

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Are you sure you want to delete this post?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realTimeDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        postToRemove = dataSnapshot.getValue(Post.class); // retrieve post
                        //System.out.println(post);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

                deletePost(postToRemove);
                //Intent intent = new Intent(DeletePostActivity.this, HistViewPostActivity.class);
                //startActivity(intent);
                //Toast.makeText(getBaseContext(), "Post deleted.", Toast.LENGTH_LONG).show();
            }

        });
        alert.setNegativeButton("No", null);
        alert.create();
        alert.show();

    }

    /*protected String getPostID() {
        return "id"; // from viewing post page,
    }*/

    public void deletePost(Post post) {
        realTimeDatabase.child(post.getPostId()).removeValue();
    }
}
