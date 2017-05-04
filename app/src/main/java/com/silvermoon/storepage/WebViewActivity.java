package com.silvermoon.storepage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.silvermoon.cashkaro.R;

/*
* Activity to display an url in WebView*/

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String urlValue;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView)findViewById(R.id.storePageWebView);
        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        urlValue = getIntent().getStringExtra("URL");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(urlValue);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                progressBar.setVisibility(View.VISIBLE);
                view.loadUrl(urlValue);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });


    }
}
