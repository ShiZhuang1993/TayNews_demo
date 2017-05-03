package com.bawei.taynews_demo.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.url.IP;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.bawei.taynews_demo.utils.VolleyUtils;

import java.util.HashMap;


public class PhoneLoginAcitivity extends AppCompatActivity implements View.OnClickListener {


    private int theme = 0;
    private ImageView include_head_login;
    private EditText et_number_phone_login;
    private ImageView imageView;
    private EditText et_pwd_phone_login;
    private TextView tv_register_phone_login;
    private TextView tv_pwd_phone_login;
    private Button btn_login_phone_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_login_acitivity);

        initView();
        initData();
    }

    private void initView() {

        include_head_login = (ImageView) findViewById(R.id.include_head_login);
        include_head_login.setOnClickListener(this);
        et_number_phone_login = (EditText) findViewById(R.id.et_number_phone_login);
        et_number_phone_login.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        et_pwd_phone_login = (EditText) findViewById(R.id.et_pwd_phone_login);
        et_pwd_phone_login.setOnClickListener(this);
        tv_register_phone_login = (TextView) findViewById(R.id.tv_register_phone_login);
        tv_register_phone_login.setOnClickListener(this);
        tv_pwd_phone_login = (TextView) findViewById(R.id.tv_pwd_phone_login);
        tv_pwd_phone_login.setOnClickListener(this);
        btn_login_phone_login = (Button) findViewById(R.id.btn_login_phone_login);
        btn_login_phone_login.setOnClickListener(this);
    }


    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pwd_phone_login:
                startActivity(new Intent(this, PwdBackActivity.class));
                break;
            case R.id.tv_register_phone_login:
                startActivity(new Intent(this, PhoneRegisterActivity.class));
                break;
            case R.id.include_head_login:
                finish();
                break;
            case R.id.btn_login_phone_login:
                submit();

                break;
        }
    }

    private void submit() {
        // validate
        String login = et_number_phone_login.getText().toString().trim();
        if (TextUtils.isEmpty(login)) {
            Toast.makeText(this, "请输入帐号", Toast.LENGTH_SHORT).show();
            return;
        }

        String logins = et_pwd_phone_login.getText().toString().trim();
        if (TextUtils.isEmpty(login)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", login);
        hashMap.put("password", logins);
        hashMap.put("client", "android");
        VolleyUtils.Send(this, IP.LINK_MOBILE + "login", hashMap);

    }
}
