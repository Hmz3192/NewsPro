package com.zjnu.newspro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjnu.newspro.R;
import com.zjnu.newspro.utils.Constant;

public class ArticleActivity extends Activity implements View.OnClickListener {

    private ImageView btn_back;
    private TextView txt_title;
    private ImageView btn_top;
    private ImageView btn_refresh;
    private WebView wView;
    private long exitTime = 0;
    private String articleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent = getIntent();
        articleId = intent.getStringExtra("articleId");
        bindViews();
    }

    private void bindViews() {
        btn_back =  findViewById(R.id.btn_back);
        txt_title =  findViewById(R.id.txt_title);
        btn_top =  findViewById(R.id.btn_top);
        btn_refresh =  findViewById(R.id.btn_refresh);
        wView = (WebView) findViewById(R.id.wView);

        btn_back.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
        btn_top.setOnClickListener(this);

        wView.loadUrl(Constant.LOADARTICLE + articleId);
        wView.getSettings().setJavaScriptEnabled(true);//启用js
        wView.getSettings().setBlockNetworkImage(false);//解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wView.setWebChromeClient(new WebChromeClient() {
            //这里设置获取到的网站title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                txt_title.setText(title);
            }
        });


        wView.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();          //关闭当前Activity
                break;
            case R.id.btn_refresh:
                wView.reload();    //刷新当前页面
                break;
            case R.id.btn_top:
                wView.setScrollY(0);   //滚动到顶部
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (wView.canGoBack()) {
            wView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次返回",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

        }
    }

}
