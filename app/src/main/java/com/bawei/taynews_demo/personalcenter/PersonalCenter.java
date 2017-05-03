package com.bawei.taynews_demo.personalcenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.bumptech.glide.Glide;

public class PersonalCenter extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImage_accont_top;
    private int theme = 0;
    private ImageView home_pager_image;
    private TextView home_pager_text;
    private CheckBox home_pager_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        mImage_accont_top = (ImageView) findViewById(R.id.image_accont_top);
        mImage_accont_top.setOnClickListener(this);

        home_pager_image = (ImageView) findViewById(R.id.home_pager_image);
        home_pager_text = (TextView) findViewById(R.id.home_pager_text);

        home_pager_check = (CheckBox) findViewById(R.id.home_pager_check);
        home_pager_check.setOnClickListener(this);
    }

    private void initData() {
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        if (config.getBoolean("isfrit", false)) {
            String mPic = config.getString("imageview", "");
            String mName1 = config.getString("name", "");
            Glide.with(this).load(mPic).into(home_pager_image);
            home_pager_text.setText(mName1);
        } else {
            String mPic = config.getString("imageview", "");
            Glide.with(this).load(mPic).placeholder(R
                    .mipmap.default_round_head).into(home_pager_image);
            home_pager_text.setText("");
            finish();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_accont_top:
                finish();
                break;
            case R.id.home_pager_check:
                startActivity(new Intent(PersonalCenter.this, AccountActivity.class));
                break;
        }
    }
}
