package com.example.adityahadiwijaya.androidrxjava.part4.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adityahadiwijaya.androidrxjava.R;
import com.example.adityahadiwijaya.androidrxjava.part4.model.GitHubUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityahadiwijaya on 12/20/16.
 */

public class GitHubUsersAdapter extends RecyclerView.Adapter<GitHubUsersAdapter.DataHolder> {

    private List<GitHubUser> users = new ArrayList<>();
    private final OnRVClickListener listener;

    public GitHubUsersAdapter(OnRVClickListener listener){
        this.listener = listener;
    }

    public void addUser(GitHubUser user){
        users.add(user);
        notifyItemInserted(users.size() -1);
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.part4_element, viewGroup, false);
        return  new DataHolder(itemView, listener);

    }

    @Override
    public void onBindViewHolder(DataHolder holder, int i) {
        holder.bindTo(users.get(i));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView userPicture;
        private final TextView userName;
        private final TextView userLogin;
        private final TextView userPage;
        private final OnRVClickListener listener;


        public DataHolder(View itemView, OnRVClickListener listener){
            super(itemView);
            userPicture = (ImageView)itemView.findViewById(R.id.userPicture);
            userName = (TextView)itemView.findViewById(R.id.userName);
            userLogin = (TextView)itemView.findViewById(R.id.userLogin);
            userPage = (TextView)itemView.findViewById(R.id.userPage);
            this.listener = listener;
            itemView.setOnClickListener(this);

        }

        public void bindTo(GitHubUser user){
            userName.setText(user.getName());
            userLogin.setText(user.getLogin());
            userPage.setText(user.getRepos_url());

            Picasso.with(userPicture.getContext())
                    .load(user.getAvatar_url())
                    .placeholder(R.drawable.ic_placeholder)
                    .into(userPicture);

        }

        @Override
        public void onClick(View v) {
            final String requestedUser = userLogin.getText().toString();

            listener.onItemClicked(requestedUser);
        }
    }
}
