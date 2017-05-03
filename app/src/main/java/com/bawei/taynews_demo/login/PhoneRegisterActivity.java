package com.bawei.taynews_demo.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.blankj.utilcode.util.RegexUtils;


public class PhoneRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_number_phone_register;
    private TextView tv_agreement_phone_register;
    private Button btn_login_phone_register;
    private int theme = 0;
    private ImageView mPgone_register_title_image;
    private CheckBox mCheck_yes_phone_register;
    private TextView mTv_log_phon_register;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_phone_register);

        initView();
        initData();
    }

    private void initView() {
        mPgone_register_title_image = (ImageView) findViewById(R.id.image_phone_register);
        mPgone_register_title_image.setOnClickListener(this);
        et_number_phone_register = (EditText) findViewById(R.id.et_number_phone_register);
        et_number_phone_register.setOnClickListener(this);
        mCheck_yes_phone_register = (CheckBox) findViewById(R.id.check_yes_phone_register);
        tv_agreement_phone_register = (TextView) findViewById(R.id.tv_agreement_phone_register);
        tv_agreement_phone_register.setOnClickListener(this);
        btn_login_phone_register = (Button) findViewById(R.id.btn_login_phone_register);
        //btn_login_phone_register.setOnClickListener(this);
        mTv_log_phon_register = (TextView) findViewById(R.id.tv_log_phon_register);
        mTv_log_phon_register.setOnClickListener(this);
        mCheck_yes_phone_register.setChecked(false);
        flag = false;
        //判断checkbox
        mCheck_yes_phone_register.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    flag = true;
                }
            }
        });
        btn_login_phone_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!flag) {
                    btn_login_phone_register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submit();
                        }
                    });
                } else {
                    Toast.makeText(PhoneRegisterActivity.this, "不同意此协议无法注册", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.btn_login_phone_register:
                if (flag){
                    submit();
                }else{
                    Toast.makeText(PhoneRegisterActivity.this, "不同意此协议无法注册", Toast.LENGTH_SHORT)
                    .show();
                }

                break;*/
            case R.id.tv_agreement_phone_register:
                startActivity(new Intent(this, WebAgreement.class));
                break;
            case R.id.image_phone_register:
                finish();
                break;
            case R.id.tv_log_phon_register:
                startActivity(new Intent(this, PhoneLoginAcitivity.class));
                break;
        }
    }

    private void submit() {
        // validate
        String code = et_number_phone_register.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean mobileExact = RegexUtils.isMobileExact(code);
        if (mobileExact) {

            Intent intent = new Intent(this, CAPTCHAActivity.class);
            intent.putExtra("phone", code);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }

    }
}
