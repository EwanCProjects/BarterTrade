package com.example.group15project;


import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class PostNotificationRVAdapter extends RecyclerView.Adapter<PostNotificationRVAdapter.ListItem>{
    private ArrayList<Post> posts;
    private FirebaseStorage fbStorage;
    private ArrayList<String> newPostsIds;

    /**
     * constructor
     * @param posts array of Post objects to show on the list
     */
    public PostNotificationRVAdapter(ArrayList<Post> posts, FirebaseStorage fbStorage, ArrayList<String> newPostsIds) {
        this.posts = posts;
        this.fbStorage = fbStorage;
        this.newPostsIds = newPostsIds;
    }

    /**
     * Create new views (invoked by layout manager)
     */
    @Override
    public ListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.home_post_cardview,
                parent, false);

        return (new ListItem(v,posts,fbStorage));
    }

    /**
     * this method sets up UI fields for each item in the list
     * @param listItem one element or item in the list
     * @param position the position of that item on the list
     */
    @Override
    public void onBindViewHolder(ListItem listItem, int position){
        Post post = posts.get(position);
        listItem.homePostTitle.setText(post.getPostTitle());
        listItem.homePostCategory.setText(post.getPostCategory());
    }

    @Override
    public int getItemCount(){ return posts.size(); }

    public static class ListItem extends RecyclerView.ViewHolder {
        private FirebaseStorage fbStorage;
        LinearLayout postLayout;
        TextView homePostTitle;
        TextView homePostCategory;
        View mainView;
        ArrayList<Post> mPosts;

        public ListItem(View listItemview, ArrayList<Post> posts, FirebaseStorage fbStorage){
            super(listItemview);
            mPosts = posts;
            mainView = listItemview;
            homePostTitle = listItemview.findViewById(R.id.homePostTitle);
            homePostCategory = listItemview.findViewById(R.id.homePostCategory);
            this.fbStorage = fbStorage;

            postLayout.setOnClickListener((v) -> {
                goToViewPost();
            });
        }

        private void goToViewPost(){
            Intent intent = new Intent(mainView.getContext(), ViewPostActivity.class);
            intent.putExtra("postID", mPosts.get(getAdapterPosition()).getPostId());
            intent.putExtra("author", mPosts.get(getAdapterPosition()).getAuthor());
            mainView.getContext().startActivity(intent);
        }

    }

}

