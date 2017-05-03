package com.bawei.taynews_demo.personalcenter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    private Button acc_button;
    private int theme = 0;
    private ImageView image_off_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
    }

    private void initView() {
        acc_button = (Button) findViewById(R.id.acc_button);
        acc_button.setOnClickListener(this);
        image_off_top = (ImageView) findViewById(R.id.image_off_top);
        image_off_top.setOnClickListener(this);
    }

    private void setExit() {
        UMShareAPI.get(AccountActivity.this).deleteOauth(this, SHARE_MEDIA.QQ, new UMAuthListener
                () {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(AccountActivity.this, "注销成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acc_button:
                setExit();
                SharedPreferences preferences = getSharedPreferences("config", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.clear();
                edit.commit();
                finish();
                break;
            case R.id.image_off_top:
                finish();
                break;
        }
    }
}
