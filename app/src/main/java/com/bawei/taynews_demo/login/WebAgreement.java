package com.bawei.taynews_demo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.utils.Night_styleutils;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/13 21:33
 */
public class WebAgreement extends AppCompatActivity {


    private WebView webView;
    private int theme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recommend_webview);
        webView = (WebView) findViewById(R.id.re_web_view);
        //脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://mp.toutiao.com/agreement/");
        //缩放
        webView.getSettings().setBuiltInZoomControls(true);
        //可以访问的文件
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




}
