package com.example.group15project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistChatAdapter extends RecyclerView.Adapter<HistChatAdapter.HistChatViewHolder> {
    public static Conversation currConversation;
    List<Conversation> conversations;
    List<String> chatPartners;
    Context context;

    public HistChatAdapter(Context ct, List<Conversation> conversations, List<String> chatPartners) {
        context = ct;
        this.conversations = conversations;
        this.chatPartners = chatPartners;
    }

    @NonNull
    @Override
    public HistChatAdapter.HistChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.hist_chat_cardview, parent, false);
        return new HistChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistChatAdapter.HistChatViewHolder holder, int position) {
        holder.chatPartner.setText(chatPartners.get(position));

        holder.chatLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, HistChatActivity.class);
            currConversation = conversations.get(position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public class HistChatViewHolder extends RecyclerView.ViewHolder {
        TextView chatPartner;
        ConstraintLayout chatLayout;

        public HistChatViewHolder(@NonNull View itemView) {
            super(itemView);
            chatPartner = itemView.findViewById(R.id.chatPartnerName);
            chatLayout = itemView.findViewById(R.id.chatCardLayout);
        }
    }
}
