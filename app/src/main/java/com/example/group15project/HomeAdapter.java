package com.example.group15project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    List<String> titles, OPs, categories;
    Context context;


    public HomeAdapter(Context ct, List<String> postTitles, List<String> postOPs, List<String> postCategories) {
        context = ct;
        titles = postTitles;
        OPs = postOPs;
        categories = postCategories;
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
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView homeTitle, homeOP, homeCategory;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTitle = itemView.findViewById(R.id.homePostTitle);
            homeOP = itemView.findViewById(R.id.homePostOP);
            homeCategory = itemView.findViewById(R.id.homePostCategory);
        }
    }
}
