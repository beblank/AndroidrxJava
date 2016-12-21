package com.example.adityahadiwijaya.androidrxjava.part4.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adityahadiwijaya.androidrxjava.R;
import com.example.adityahadiwijaya.androidrxjava.part4.model.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityahadiwijaya on 12/20/16.
 */

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.RepoHolder>{

    List<GitHubRepo> repos = new ArrayList<>();

    public void addRepo(GitHubRepo repo){
        repos.add(repo);
        notifyItemInserted(repos.size()-1);
    }

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.part4_detail_element, viewGroup, false);
        return new RepoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int i) {
        holder.bindTo(repos.get(i));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public static class RepoHolder extends RecyclerView.ViewHolder{
        private final TextView firstLine;
        private final TextView secondLine;

        public RepoHolder(View itemView){
            super(itemView);
            firstLine = (TextView)itemView.findViewById(R.id.firstLine);
            secondLine = (TextView)itemView.findViewById(R.id.secondLine);
        }

        public void bindTo(GitHubRepo repo){
            firstLine.setText(String.format("%s (%s)", repo.getFull_name(), repo.getLanguage()));
            secondLine.setText(repo.getDescription());
        }
    }
}
