package com.bawei.taynews_demo.personalcenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.url.IP;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.bawei.taynews_demo.utils.VolleyUtils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProtogenesisRegister extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFanhui;
    private EditText ed_pr_id;
    private EditText ed_pr_pwd;
    private EditText ed_pr_pwd_too;
    private EditText ed_pr_email;
    private Button bt_pr_pr;
    private int theme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protogenesis_register);
        initView();

    }

    private void initView() {
        mFanhui = (ImageView) findViewById(R.id.image_pr_fahui);
        mFanhui.setOnClickListener(this);
        ed_pr_id = (EditText) findViewById(R.id.ed_pr_id);
        ed_pr_id.setOnClickListener(this);
        ed_pr_pwd = (EditText) findViewById(R.id.ed_pr_pwd);
        ed_pr_pwd.setOnClickListener(this);
        ed_pr_pwd_too = (EditText) findViewById(R.id.ed_pr_pwd_too);
        ed_pr_pwd_too.setOnClickListener(this);
        ed_pr_email = (EditText) findViewById(R.id.ed_pr_email);
        ed_pr_email.setOnClickListener(this);
        bt_pr_pr = (Button) findViewById(R.id.bt_pr_pr);
        bt_pr_pr.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_pr_fahui:
                finish();
                break;
            case R.id.bt_pr_pr:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String id = ed_pr_id.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "请输入帐号", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = ed_pr_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String too = ed_pr_pwd_too.getText().toString().trim();
        if (TextUtils.isEmpty(too)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (too.equals(pwd)) {

        } else {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        String email = ed_pr_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
            return;
        } else if (isEmailAddress(email)) {

        } else {
            Toast.makeText(this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
        }

        // TODO validate success, do something
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("username", id);
        hashMap.put("password", pwd);
        hashMap.put("password_confirm", too);
        hashMap.put("email", email);
        hashMap.put("client", "android");
        VolleyUtils.Send(this, IP.LINK_MOBILE_REG, hashMap);
    }

    public static boolean isEmailAddress(String url) {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)" +
                "[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(url);
        return matcher.matches();
    }
}
