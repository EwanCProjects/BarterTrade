package com.example.group15project;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistChatActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView histChatView;
    HistChatAdapter histChatAdapter;

    public static String currUser = HomeActivity.currUser;
    DatabaseReference realTimeDatabase = HomeActivity.realTimeDatabase;
    List<Conversation> extractedConversations = new ArrayList<>();
    List<String> conversationPartners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);

        histChatView = findViewById(R.id.histChatView);

        histChatAdapter = new HistChatAdapter(this, extractedConversations, conversationPartners);
        histChatView.setAdapter(histChatAdapter);
        histChatView.setLayoutManager(new LinearLayoutManager(this));

        databaseRead(realTimeDatabase);

    }

    public void databaseRead(DatabaseReference db) {
        //code for database initialization and grabbing all Posts
        ValueEventListener chatListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot chatSnapshot : dataSnapshot.child("Conversations").getChildren()) {
                    Conversation extractedConversation = chatSnapshot.getValue(Conversation.class);
                    if (extractedConversation.getUser().equals(currUser)) {
                        extractedConversations.add(extractedConversation);
                        conversationPartners.add(extractedConversation.getOppositeUser());
                        histChatAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        };
        db.addValueEventListener(chatListener);
    }

    @Override
    public void onClick(View v) {

    }
}
