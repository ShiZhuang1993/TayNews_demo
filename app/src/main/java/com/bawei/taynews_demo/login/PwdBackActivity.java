package com.bawei.taynews_demo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.blankj.utilcode.util.RegexUtils;

public class PwdBackActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_pwdback_xiayibu;
    private int theme = 0;
    private EditText et_number_pwd_back;
    private ImageView iv_clear_pwd_back;
    private ImageView mInclude_head_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pwd_back);

        initView();
        initData();
    }

    private void initView() {
        et_number_pwd_back = (EditText) findViewById(R.id.et_number_pwd_back);
        iv_clear_pwd_back = (ImageView) findViewById(R.id.iv_clear_pwd_back);
        mBt_pwdback_xiayibu = (Button) findViewById(R.id.btn_login_pwd_back);
        mBt_pwdback_xiayibu.setOnClickListener(this);
        mInclude_head_login = (ImageView) findViewById(R.id.include_head_login);
        mInclude_head_login.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_pwd_back:
                submit();
                break;
            case R.id.include_head_login:
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String back = et_number_pwd_back.getText().toString().trim();
        boolean mobileExact = RegexUtils.isMobileExact(back);
        if (!TextUtils.isEmpty(back)) {
            if (mobileExact) {
                startActivity(new Intent(this, PwdBackCAPTCHA.class));

            } else {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        }


    }
}
