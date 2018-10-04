package com.tutorial.nura.newsapp.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tutorial.nura.newsapp.Constants;
import com.tutorial.nura.newsapp.R;
import com.tutorial.nura.newsapp.fragments.AbstractFragment;
import com.tutorial.nura.newsapp.fragments.DetailFragment;
import com.tutorial.nura.newsapp.models.NewsModel;

import java.util.List;

public class StarterAdapter extends RecyclerView.Adapter<StarterAdapter.NewsViewHolder> {

    private List<NewsModel.News> data;
    private AbstractFragment fragment;

    public StarterAdapter(AbstractFragment fragment, List<NewsModel.News> data) {
        this.data = data;
        this.fragment = fragment;
    }

    public void update(List<NewsModel.News> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_starter, parent, false);
        itemView.setOnClickListener(this::openDetailFragment);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, desc, author, time, url;

        private NewsViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.title = itemView.findViewById(R.id.tv_title);
            this.desc = itemView.findViewById(R.id.tv_desc);
            this.author = itemView.findViewById(R.id.tv_author);
            this.time = itemView.findViewById(R.id.tv_time);
            this.url = itemView.findViewById(R.id.tv_url);
        }

        private void bind(NewsModel.News news) {
            title.setText(news.getTitle());
            desc.setText(news.getDescription());
            author.setText(news.getAuthor());
            time.setText(news.getPublishDate());
            url.setText(news.getUrl());

            if (TextUtils.isEmpty(news.getImageUrl())) {
                image.setVisibility(View.GONE);
            } else {
                Picasso.get()
                        .load(news.getImageUrl())
                        .resize(300, 200)
                        .into(image);
            }
        }
    }

    private void openDetailFragment(View v) {
        String url = ((TextView) v.findViewById(R.id.tv_url)).getText().toString();

        Bundle args = new Bundle();
        args.putString(Constants.URL, url);
        DetailFragment destFragment = new DetailFragment();
        destFragment.setArguments(args);
        fragment.changeFragment(destFragment, true);
    }

}
