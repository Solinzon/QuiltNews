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
    TextView newsTitile;
    TextView newsTime;
    String url;
    WebView webView;
    NewsDetailPresenter newsDetailPresenter;
   // public static final String APP_CSS = "<style> html, body{ margin:0; padding:1px; color:#666666; font-style:normal; font-weight:normal; font-size:13px;} div, dl, dt, dd, h1, h2, h3, h4, h5, h6, pre, code, form, fieldset, legend, input, button, textarea, select, p, blockquote, th, td, ol, ul, li{ width:auto; margin:0; padding:0; color:#666666; font-style:normal; font-weight:normal; font-size:13px;} fieldset, img{ border:0;} address, button, caption, cite, code, dfn, em, input, optgroup, option, select, strong, textarea, th, var, span{  font : inherit; color:#666666; font-style:normal; font-weight:normal;} del, ins{ text-decoration:none;} ol, ul, dl{margin-left : 15px;} ol{ list-style:decimal outside;} ul{ list-style:disc outside;} li{ margin:0; padding:0; width:auto; color:#666666; font-style:normal; font-weight:normal; text-indent:-3px; font-size:13px;} h1, h2, h3, h4, h5, h6{ width:auto; font-size:100%; font-weight:normal;} abbr, acronym{ border:0; font-variant:normal;} a, a:link, a:visited, a:hover, a:active{ text-decoration:none; color:#666666} table{ border-collapse:collapse; border-spacing:0; width:auto;} table{ width:100%; border:1px solid #666; } table td, table th, tbody td, tbody th{ width:auto; padding:3px; border:1px solid #c1c1c1; } strong{font-weight:bold} </style>";
    public String picc= "<p><img src=\\\"http://02.imgmini.eastday.com/mobile/20160816/20160816173026_591c45d7d61ef0bec7bb43cf96d196d2_1.jpeg\\\" /></p>";
    public String pic_url ;
    public String pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dtail);
        Intent intent = getIntent();
        Log.d(TAG, "onCreate: "+intent.getStringExtra("url"));
        url = intent.getStringExtra("url");
//        pic_url = intent.getStringExtra("pic_url");
        Log.d("998998", "onCreate: "+pic_url);
        newsDetailPresenter = new NewsDetailPresenter(this);
        initView();
        initData();
    }

    private void initData() {
        newsDetailPresenter.showNewsDetaile(url);
    }

    private void initView() {
//        newsTitile = (TextView) findViewById(R.id.tv_news_detail_title);
//        newsTime = (TextView) findViewById(R.id.tv_news_detail_time);
        webView = (WebView) findViewById(R.id.web_view_news_dtail);
        webView.getSettings().setJavaScriptEnabled(true);
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
        //settings.setUseWideViewPort(false);
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    }

    @Override
    public void showNewsDetail(String content) {
    //    webView.loadDataWithBaseURL(null,pic+content,"text/html", "utf-8", null);
    webView.loadUrl(content);

    }

    @Override
    public void setTitle(String title) {
        newsTitile.setText(title);
    }

    @Override
    public void setTime(String time) {
        newsTime.setText(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsDetailPresenter = null;
    }
}
