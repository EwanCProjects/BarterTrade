package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button close = findViewById(R.id.closeButton);
        close.setOnClickListener(this);
        Button refresh = findViewById(R.id.refreshButton);
        refresh.setOnClickListener(this);
    }


    protected void switchToHomeWindow() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.refreshButton){
            realTimeDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //get trade
                    // need to add maybe an EditText or something in the activity_history to populate it with a trade/multiple trades
                    //get post
                    // need to add maybe an EditText or something in the activity_history to populate it with a post/multiple posts
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("Database read failed: "+ error.getMessage());
                }
            });

        }else if (view.getId() == R.id.closeButton){
            switchToHomeWindow();
        }
    }

}
