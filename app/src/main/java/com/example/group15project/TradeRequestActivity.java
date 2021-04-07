package com.example.group15project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import currentUserProperties.CurrentUser;

public class TradeRequestActivity extends AppCompatActivity implements View.OnClickListener {
    DataSnapshot conversationsTree;
    public static DatabaseReference realTimeDatabase = FirebaseDatabase.getInstance().getReference();
    public static String provider = ViewPostActivity.currPost.getAuthor();
    public static String receiver = CurrentUser.getInstance().currUserString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_request);

        databaseRead(realTimeDatabase);

        Button sendRequest = findViewById(R.id.sendRequestButton);
        sendRequest.setOnClickListener(this);
        Button cancelRequest = findViewById(R.id.cancelRequestButton);
        cancelRequest.setOnClickListener(this);
    }

    public void databaseRead(DatabaseReference db) {
        //code for database initialization and accessing the credentials
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                conversationsTree = dataSnapshot.child("Conversations");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        };
        db.addValueEventListener(userListener);
    }

    protected String generateTradeID(){ return UUID.randomUUID().toString();}

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.tradeRequestStatusLabel);
        statusLabel.setText(message);
    }

    protected String getItemTitle(){
        EditText title = findViewById(R.id.itemTitle);
        return title.getText().toString().trim();
    }

    protected String getItemDescription(){
        EditText description = findViewById(R.id.itemDescription);
        return description.getText().toString().trim();

    }

    protected boolean isTitleEmpty(String title){ return title.isEmpty();}

    protected boolean isDescriptionEmpty(String description){ return description.isEmpty();}

    protected Trade createTrade(String tradeID, String title, String description, String userProvider, String userReceiver){
        return new Trade(tradeID, title, description, userProvider, userReceiver);
    }

    protected void addTradeToDatabase(DatabaseReference mDatabase, Trade trade, String tradeID){
        mDatabase.child("Trades").child(tradeID).setValue(trade);
    }

    protected void addConversationToDatabase(Conversation conversation){
        if (conversationsTree.hasChild(conversation.getConversationName())) {
            Toast.makeText(getApplicationContext(), "A new trade has been made, but you are already chatting with this user!", Toast.LENGTH_LONG).show();
        }
        else {
            Map<String, String> message = new HashMap<>();
            message.put("message", "placeholder");
            message.put("user", "-placeholder-user-");
            realTimeDatabase.child("Conversations").child(conversation.getConversationName()).setValue(conversation);
            realTimeDatabase.child("Chats").child(conversation.getConversationName()).push().setValue(message);
        }
    }

    protected void switchToHomeWindow(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        String tradeID = generateTradeID();
        String title = getItemTitle();
        String description = getItemDescription();
        String errorMessage = "";

        if(v.getId() == R.id.sendRequestButton){
            if(isTitleEmpty(title)){
                errorMessage = "Title is Empty";
            }

            if(isDescriptionEmpty(description)){
                errorMessage = "Description is Empty";
            }

            if(errorMessage.isEmpty()){
                Trade trade = createTrade(tradeID, title, description, provider, receiver);
                addTradeToDatabase(realTimeDatabase, trade, tradeID);

                // add code to create conversations here
                Conversation userConversation = new Conversation(receiver, provider);
                Conversation oppositeConversation = new Conversation(provider, receiver);

                addConversationToDatabase(userConversation);
                addConversationToDatabase(oppositeConversation);

                switchToHomeWindow();

            }

            else{
                setStatusMessage(errorMessage);
            }

        }
        else if (v.getId() == R.id.cancelRequestButton){
           switchToHomeWindow();
        }
    }
}
