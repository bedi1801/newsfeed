package com.example.dashmesh.newsfeeder;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
@SuppressLint("SetJavaScriptEnabled")
public class Web extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        WebView mainWebView = (WebView) findViewById(R.id.webview1);
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //mainWebView.loadData(link,"text/html; charset=utf-8", "utf-8");

        ProgressDialog waitingDialog = new ProgressDialog(Web.this);
        waitingDialog.setMessage("Loading...");
        waitingDialog.show();
        mainWebView.loadUrl(link);
        waitingDialog.dismiss();
    }
    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {

            view.loadUrl(url);

            return true;
        }
    }
}
