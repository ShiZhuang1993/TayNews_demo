package com.bawei.taynews_demo.httrack.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.taynews_demo.R;

public class Menu_Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_back_include_head_login;
    private TextView tv_back_include_head_login;
    private LinearLayout next_linear_image;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        initView();
        initData();
    }


    private void initView() {
        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
        iv_back_include_head_login.setOnClickListener(this);
        tv_back_include_head_login = (TextView) findViewById(R.id.tv_back_include_head_login);
        tv_back_include_head_login.setText("活动");
        next_linear_image = (LinearLayout) findViewById(R.id.next_linear_image);
        next_linear_image.setVisibility(View.GONE);
        webView = (WebView) findViewById(R.id.webView);
        webView.setOnClickListener(this);
    }

    private void initData() {
//脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://isub.snssdk.com/2/wap/activity/?iid=9484834698&device_id=35792575301&ac=wifi&channel=jiawo2&aid=13&app_name=news_article&version_code=499&version_name=4.9.9&device_platform=android&device_type=Lenovo+P70-t&os_api=19&os_version=4.4.2&uuid=864394010501239&openudid=507B9D27D65A0000&manifest_version_code=500");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_include_head_login:
                finish();
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
