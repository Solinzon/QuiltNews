package com.xushuzhan.quiltnews.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.xushuzhan.quiltnews.R;
import com.xushuzhan.quiltnews.modle.network.config.UserInfo;
import com.xushuzhan.quiltnews.presenter.NewsDetailPresenter;
import com.xushuzhan.quiltnews.ui.iview.INewsDetailView;
import com.xushuzhan.quiltnews.utils.DialogPopup;

public class NewsDtailActivity extends AppCompatActivity implements INewsDetailView, View.OnClickListener {
    public static final String TAG = "NewsDtailActivity";
    String url;
    String title;
    String picUrl;
    String uniqueKey;
    WebView webView;
    NewsDetailPresenter newsDetailPresenter;
    RelativeLayout rlNewsDetailDiscuss;
    DialogPopup dialogPopup;
    Button allNews;
    TextView allNewsCount;
    ImageButton ReadMode;
    ImageButton back;
    TextView titleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dtail);
        Intent intent = getIntent();
        Log.d(TAG, "onCreate: " + intent.getStringExtra("url"));
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        picUrl = intent.getStringExtra("pic_url");
        uniqueKey = intent.getStringExtra("uniquekey");
        Log.d(TAG, "onCreate: " + title + ">>>" + picUrl + ">>>" + uniqueKey);
        newsDetailPresenter = new NewsDetailPresenter(this);
        initView();
        initData();
    }

    private void initData() {
        newsDetailPresenter.showNewsDetail(url);
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

        rlNewsDetailDiscuss = (RelativeLayout) findViewById(R.id.rl_write_discuss);
        rlNewsDetailDiscuss.setOnClickListener(this);
        allNews = (Button) findViewById(R.id.bt_news_detail_discuss);
        allNews.setOnClickListener(this);
        allNewsCount = (TextView) findViewById(R.id.tv_news_detail_discuss_count);
        allNewsCount.setOnClickListener(this);
        ReadMode = (ImageButton) findViewById(R.id.ib_toobar_read_mode);
//        ReadMode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(MainActivity.this,LoginActivity.class));
//                startActivity(new Intent(MainActivity.this,TestActivity.class));
//            }
//        });
        ReadMode.setVisibility(View.INVISIBLE);

        back= (ImageButton) findViewById(R.id.ib_toolbar_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        titleToolbar.setVisibility(View.INVISIBLE);


        newsDetailPresenter.showDiscussCount();
    }

    @Override
    public void showNewsDetail(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void showPopupWindow() {
        dialogPopup = new DialogPopup(NewsDtailActivity.this);
        dialogPopup.showPopupWindow();
    }

    @Override
    public void sendDiscussContent() {
        if (UserInfo.userName != null) {
            dialogPopup.setOnItemClickListener(new DialogPopup.OnSendButtonClickListener() {
                @Override
                public void onSendClick(View view, String content) {
                    Log.d(TAG, "onSendClick: " + content);
                    if (content != null) {
                        AVObject news = new AVObject("comment");// 构建对象
                        news.put("user_name", UserInfo.userName);
                        news.put("news_uniquekey", uniqueKey);
                        news.put("discuss_content", content);
                        news.put("news_title", title);
                        news.put("url", url);
                        news.put("pic_url", picUrl);
                        news.saveInBackground();// 保存到服务端
                        dialogPopup.dismiss();
                    } else {
                        Toast.makeText(NewsDtailActivity.this, "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(NewsDtailActivity.this, "请先登录！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void intentToAllDiscuss() {
        Intent intent = new Intent(NewsDtailActivity.this, AllDiscussActivity.class);
        intent.putExtra("news_uniquekey", uniqueKey);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("pic_url", picUrl);
        startActivity(intent);
    }

    @Override
    public void setDiscussCount(String count) {
        allNewsCount.setText(count);

    }

    @Override
    public String getNewsUniqueKey() {
        return uniqueKey;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsDetailPresenter = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_write_discuss:
                newsDetailPresenter.showDiscussWindow();
                newsDetailPresenter.sendDiscuss();
                break;
            case R.id.bt_news_detail_discuss:
            case R.id.tv_news_detail_discuss_count:
                newsDetailPresenter.intentToAllDiscuss();
                break;
            default:
                break;
        }
    }
}
