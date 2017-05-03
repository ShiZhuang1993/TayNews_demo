package com.bawei.taynews_demo.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.utils.Night_styleutils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class UserInfoSetActivity extends AppCompatActivity {
    //处理本地相册图片的状态码
    private final static int LOCAL_IMAGE_CODE = 200;
    //处理拍照图片的状态码
    private final static int TAKE_IMAGE_CODE = 300;
    private ImageView mTouxiang;
    private int theme = 0;
    private EditText et_name_user_info_set;
    private TextView mUser_info_set_accomplish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_info_set);
        initView();
        initData();
    }

    private void initView() {
        mTouxiang = (ImageView) findViewById(R.id.iv_protrait_user_info_set_);
        et_name_user_info_set = (EditText) findViewById(R.id.et_name_user_info_set);
        mUser_info_set_accomplish = (TextView) findViewById(R.id.user_info_set_accomplish);
    }

    private void initData() {
        mUser_info_set_accomplish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        mTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();

            }
        });
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoSetActivity.this);
        builder.setItems(new String[]{"拍照", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //调用相机
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, TAKE_IMAGE_CODE);
                        break;
                    case 1:
                        //打开图片库
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent1, LOCAL_IMAGE_CODE);
                        break;
                }
            }
        });
        builder.create().show();
    }

    //回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断结果码
        if (resultCode == RESULT_OK) {
            //判断请求码 返回相册数据

            if (requestCode == LOCAL_IMAGE_CODE) {
                getLocalImage(data);
            } else if (requestCode == TAKE_IMAGE_CODE) {//判断请求码 返回拍照数据
                takeImage(data);
            }
        }
    }

    //返回拍照数据
    private void takeImage(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");

        mTouxiang.setImageBitmap(bitmap);

    }

    //图片显示前保存sdcard
    private File saveBitmap(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory(), "a.jpg");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    //获取到本地图片数据
    private void getLocalImage(Intent data) {

        Uri uri = data.getData();
        //把uri转换成bitmap
        Bitmap bitmap = getBitmapFromUri(uri);
        //图片显示前保存sdcard
        File file = saveBitmap(bitmap);
        //显示图片
        mTouxiang.setImageBitmap(bitmap);

    }

    //把uri转换成bitmap
    private Bitmap getBitmapFromUri(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void submit() {
        // validate
        String edit = et_name_user_info_set.getText().toString().trim();
        if (TextUtils.isEmpty(edit)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
      /*  DbManager dbManager = MyXUtils.dataBaseXUtils("TopNews.db", 1);
        UserBean userBean = new UserBean();
        try {
            userDB = (ArrayList<UserBean>) userBean.getUserDB(dbManager);
        } catch (DbException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        for (int i=0;i<userDB.size();i++){
            if (edit.equals(userDB.get(i).getName())){
                flag=true;
                break;
            }
        }
        if (flag){
            Toast.makeText(this, "此用户名已注册", Toast.LENGTH_SHORT).show();
        }*/
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String pwd = intent.getStringExtra("pwd");
    }
}
