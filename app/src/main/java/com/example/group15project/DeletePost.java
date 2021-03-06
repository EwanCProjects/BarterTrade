package com.example.group15project;

import android.content.Context;
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

public class DeletePost {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String userID = HomeActivity.currUser;
    public static String postID;
    public Post postToRemove;
    android.content.Context context;

    public DeletePost(Context context) {
        this.context = context;
    }

    /** call this method on the corresponding post once the trade has been accepted */
    public void removePost(Post postToRemove) {
        postToRemove.setTradeCompleted(true);
    }

    public String getPostID() {
        return postToRemove.getPostId(); // from viewing post page,
    }

    public void deletePost(Post post) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage("Are you sure you want to permanently delete this post?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realTimeDatabase.child(post.getPostId()).removeValue();
            }
        });
        alert.setNegativeButton("No", null);
        alert.create();
        alert.show();
    }
}
