package com.tutorial.nura.newsapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tutorial.nura.newsapp.Constants;
import com.tutorial.nura.newsapp.R;

public class DetailFragment extends AbstractFragment {

    private ProgressBar progressBar;
    private WebView webView;

    private static final int LAYOUT = R.layout.fragment_detail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String url = getArguments().getString(Constants.URL);

        progressBar = getView().findViewById(R.id.progress_bar);
        webView = getView().findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebClient());
        webView.loadUrl(url);
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            System.out.println("URL PROGRESS " + view.getProgress());
            progressBar.setVisibility(view.getProgress() == 100 ? View.GONE : View.VISIBLE);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
