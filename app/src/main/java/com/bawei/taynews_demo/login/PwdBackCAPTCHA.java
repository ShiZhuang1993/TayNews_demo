package com.bawei.taynews_demo.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.utils.Night_styleutils;

public class PwdBackCAPTCHA extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_phone_pwd_back_captch;
    private EditText et_captch_pwd_back_captch;
    private Button btn_resend_pwd_back_captch;
    private EditText et_pwd_pwd_back_captch;
    private Button btn_login_pwd_back_captch;
    private int theme = 0;
    private ImageView include_head_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pwd_back_captch);
        initView();
        initData();

    }

    private void initView() {
        tv_phone_pwd_back_captch = (TextView) findViewById(R.id.tv_phone_pwd_back_captch);
        et_captch_pwd_back_captch = (EditText) findViewById(R.id.et_captch_pwd_back_captch);
        btn_resend_pwd_back_captch = (Button) findViewById(R.id.btn_resend_pwd_back_captch);
        et_pwd_pwd_back_captch = (EditText) findViewById(R.id.et_pwd_pwd_back_captch);
        btn_login_pwd_back_captch = (Button) findViewById(R.id.btn_login_pwd_back_captch);
        include_head_login = (ImageView) findViewById(R.id.include_head_login);
        btn_resend_pwd_back_captch.setOnClickListener(this);
        btn_login_pwd_back_captch.setOnClickListener(this);
        include_head_login.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_resend_pwd_back_captch:

                break;
            case R.id.btn_login_pwd_back_captch:

                break;
            case R.id.include_head_login:
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String captch = et_captch_pwd_back_captch.getText().toString().trim();
        if (TextUtils.isEmpty(captch)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = et_pwd_pwd_back_captch.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
