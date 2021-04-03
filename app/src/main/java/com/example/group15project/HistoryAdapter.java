package com.example.group15project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    public static Post currPost;
    List<Post> posts;
    List<String> titles, OPs, categories, images;
    Context context;


    public HistoryAdapter(Context ct, List<Post> extractedPosts, List<String> postTitles, List<String> postOPs, List<String> postCategories, List<String> postImages) {
        context = ct;
        posts = extractedPosts;
        titles = postTitles;
        OPs = postOPs;
        categories = postCategories;
        images = postImages;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_post_cardview, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.histTitle.setText(titles.get(position));
        holder.histOP.setText(OPs.get(position));
        holder.histCategory.setText(categories.get(position));

        if(images.get(position) != null){

            Glide.with(this.context).load(images.get(position)).into(holder.histImage);


        }
        else {
            Glide.with(this.context).load("https://firebasestorage.googleapis.com/v0/b/barter-trade-app.appspot.com/o/fire.webp?alt=media&token=492d3655-5d76-45c4-9ffd-adef06a38f12").into(holder.histImage);
        }

        holder.postLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, HistViewPostActivity.class);
            currPost = posts.get(position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView histImage;
        TextView histTitle, histOP, histCategory;
        ConstraintLayout postLayout;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            histTitle = itemView.findViewById(R.id.homePostTitle);
            histOP = itemView.findViewById(R.id.homePostOP);
            histCategory = itemView.findViewById(R.id.homePostCategory);
            postLayout = itemView.findViewById(R.id.postCardLayout);
            histImage = itemView.findViewById(R.id.homePostImage);
        }
    }
}
