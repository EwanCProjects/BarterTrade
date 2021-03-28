package com.example.group15project;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.UUID;

public class ChatActivity extends AppCompatActivity {

    String currUser = HomeActivity.currUser;
    Conversation currConversation;
    Conversation oppositeConversation;
    LinearLayout chatLayout1;
    RelativeLayout chatLayout2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView chatScrollView;
    Firebase conversation, dualConversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatLayout1 = findViewById(R.id.chatLayout1);
        chatLayout2 = findViewById(R.id.chatLayout2);
        sendButton = findViewById(R.id.chatSendButton);
        messageArea = findViewById(R.id.chatMessageArea);
        chatScrollView = findViewById(R.id.chatScrollView);

        currConversation = HistChatAdapter.currConversation;
        oppositeConversation = currConversation.getOppositeConversation();

        conversation = new Firebase("https://barter-trade-app-default-rtdb.firebaseio.com/Conversations/"+
                currConversation.getConversationName()+"/Messages");
        dualConversation = new Firebase("https://barter-trade-app-default-rtdb.firebaseio.com/Conversations/"+
                oppositeConversation.getConversationName()+"/Messages");

        
        sendButton.setOnClickListener(v -> {
            String messageText = messageArea.getText().toString();

            if(!messageText.equals("")){
                Message message = new Message(generateMessageID(), messageText, currUser);
                conversation.setValue(message);
                dualConversation.setValue(message);
                messageArea.setText("");
            }
        });

        conversation.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                String messageText = message.getMessage();
                String userName = message.getUser();

                if (userName.equals(currUser)) {
                    addMessageBox(messageText, 1);
                } else {
                    addMessageBox(messageText, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    protected String generateMessageID() {
        return UUID.randomUUID().toString();
    }

    public void addMessageBox(String message, int type) {
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 7.0f;

        if (type == 1) {
            lp2.gravity = Gravity.START;
            textView.setBackgroundResource(R.drawable.bubble_in);
        } else {
            lp2.gravity = Gravity.END;
            textView.setBackgroundResource(R.drawable.bubble_out);
        }
        textView.setLayoutParams(lp2);
        chatLayout1.addView(textView);
        chatScrollView.fullScroll(View.FOCUS_DOWN);
    }
}
