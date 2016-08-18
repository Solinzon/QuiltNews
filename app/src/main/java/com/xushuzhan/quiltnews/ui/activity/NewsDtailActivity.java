package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.presenter.NewsDetailPresenter;
import com.xushuzhan.quiltnews.ui.iview.INewsDetailView;

public class NewsDtailActivity extends AppCompatActivity implements INewsDetailView {
    public static final String TAG = "NewsDtailActivity";
    String url;
    WebView webView;
    NewsDetailPresenter newsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dtail);
        Intent intent = getIntent();
        Log.d(TAG, "onCreate: "+intent.getStringExtra("url"));
        url = intent.getStringExtra("url");
        newsDetailPresenter = new NewsDetailPresenter(this);
        initView();
        initData();
    }

    private void initData() {
        newsDetailPresenter.showNewsDetaile(url);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.web_view_news_dtail);
        webView.getSettings().setJavaScriptEnabled(false);
        //webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String
                    url) {
                //view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return true; // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setTextZoom(100);
    }

    @Override
    public void showNewsDetail(String url) {
        webView.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsDetailPresenter = null;
    }
}
