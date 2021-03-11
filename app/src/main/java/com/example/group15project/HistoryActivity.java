package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    Post post;
    Trade trade;

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

                    post = snapshot.getValue(Post.class);
                    EditText postInfo = findViewById(R.id.postMultiLine);
                    postInfo.setText(post.getPostTitle());

                    trade = snapshot.getValue(Trade.class);
                    EditText tradeInfo = findViewById(R.id.tradeMultiLine);
                    tradeInfo.setText("Traded " + trade.getTitle + " with " + "");
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
