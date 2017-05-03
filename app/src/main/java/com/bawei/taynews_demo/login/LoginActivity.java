package com.bawei.taynews_demo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.personalcenter.ProtogenesisRegister;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.bawei.taynews_demo.utils.TopTitle;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_login_top_fanhui;
    private Button btn_login_login;
    private Button btn_register_login, btn_register_eail;
    private ImageView iv_qq_login;
    private ImageView iv_wb_login;
    private ImageView iv_qb_login;
    private ImageView iv_rr_login;
    private int theme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        initView();
        initData();
    }

    private void initView() {
        //左上角返回键
        image_login_top_fanhui = (ImageView) findViewById(R.id.image_login_top_fanhui);
        image_login_top_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_login_login = (Button) findViewById(R.id.btn_login_login);
        btn_login_login.setOnClickListener(this);
        btn_register_login = (Button) findViewById(R.id.btn_register_login);
        btn_register_login.setOnClickListener(this);
        btn_register_eail = (Button) findViewById(R.id.btn_register_logins);
        btn_register_eail.setOnClickListener(this);
        iv_qq_login = (ImageView) findViewById(R.id.iv_qq_login);
        iv_qq_login.setOnClickListener(this);
        iv_wb_login = (ImageView) findViewById(R.id.iv_vb_login);
        iv_wb_login.setOnClickListener(this);
        iv_qb_login = (ImageView) findViewById(R.id.iv_qb_login);
        iv_qb_login.setOnClickListener(this);
        iv_rr_login = (ImageView) findViewById(R.id.iv_rr_login);
        iv_rr_login.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                startActivity(new Intent(this, PhoneLoginAcitivity.class));
                break;
            case R.id.btn_register_login:
                startActivity(new Intent(this, PhoneRegisterActivity.class));
                break;
            case R.id.btn_register_logins:
                startActivity(new Intent(this, ProtogenesisRegister.class));
                break;
            case R.id.image_login_top_fanhui:
                startActivity(new Intent(this, PhoneRegisterActivity.class));
                break;
            case R.id.iv_qq_login:
                startActivity(new Intent(this, PhoneLoginAcitivity.class));
                break;
            case R.id.iv_qb_login:
                startActivity(new Intent(this, PhoneLoginAcitivity.class));
                break;
            case R.id.iv_rr_login:
                startActivity(new Intent(this, PhoneLoginAcitivity.class));
                break;
            case R.id.iv_vb_login:
                startActivity(new Intent(this, PhoneLoginAcitivity.class));
                break;
        }
    }
}
