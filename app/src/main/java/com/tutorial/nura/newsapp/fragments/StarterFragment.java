package com.tutorial.nura.newsapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tutorial.nura.newsapp.R;
import com.tutorial.nura.newsapp.adapters.StarterAdapter;
import com.tutorial.nura.newsapp.models.NewsModel;
import com.tutorial.nura.newsapp.rest.ApiInterface;
import com.tutorial.nura.newsapp.rest.ApiClient;
import com.tutorial.nura.newsapp.utils.ConnectionUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarterFragment extends AbstractFragment {

    private static final int LAYOUT = R.layout.fragment_starter;
    private static final String TAG = StarterFragment.class.getSimpleName();

    private StarterAdapter adapter;
    private List<NewsModel.News> news;
    private Unbinder unbinder;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private static final String API_KEY = "7455c8857d8e4803bb1c3c93b516cdc1";
    private static final String SOURCES = "national-geographic";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        if (ConnectionUtil.isOnline()) {
            loadNews();
        } else {
            Toast.makeText(getContext(), R.string.no_inet_connection, Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        news = new ArrayList<>();
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StarterAdapter(this, news);
        recyclerView.setAdapter(adapter);
    }

    private void loadNews() {
        ApiInterface apiInterface = ApiClient.getApiService();
        Call<NewsModel> call = apiInterface.getNews(SOURCES, API_KEY);
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                if (response.isSuccessful()) {
                    news = response.body().getNews();
                    adapter.update(news);
                    Log.d(TAG, String.valueOf(news.size()));
                } else {
                    try {
                        Log.d(TAG, String.valueOf("error " + response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Log.d(TAG, "fail " + t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
