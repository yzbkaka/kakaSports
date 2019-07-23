package com.example.yzbkaka.kakasports.Soccer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yzbkaka.kakasports.R;

public class LinkActivity extends AppCompatActivity {
    private WebView webView;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        webView = (WebView)findViewById(R.id.webview);
        Intent intent = getIntent();
        link = intent.getStringExtra("link");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("www.baidu.com");
    }
}
