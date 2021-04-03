package com.example.group15project;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    public static Post currPost;
    List<Post> posts;
    List<String> titles, OPs, categories, images;
    Context context;


    public HomeAdapter(Context ct, List<Post> extractedPosts, List<String> postTitles, List<String> postOPs, List<String> postCategories, List<String> postImages) {
        context = ct;
        posts = extractedPosts;
        titles = postTitles;
        OPs = postOPs;
        categories = postCategories;
        images = postImages;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_post_cardview, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.homeTitle.setText(titles.get(position));
        holder.homeOP.setText(OPs.get(position));
        holder.homeCategory.setText(categories.get(position));

        holder.postLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ViewPostActivity.class);
            currPost = posts.get(position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView homeTitle, homeOP, homeCategory;
        ConstraintLayout postLayout;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTitle = itemView.findViewById(R.id.homePostTitle);
            homeOP = itemView.findViewById(R.id.homePostOP);
            homeCategory = itemView.findViewById(R.id.homePostCategory);
            postLayout = itemView.findViewById(R.id.postCardLayout);
        }
    }
}
