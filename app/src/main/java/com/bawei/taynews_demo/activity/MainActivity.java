package com.bawei.taynews_demo.activity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.adapter.NewsAdapter;
import com.bawei.taynews_demo.bean.ChildInfo;
import com.bawei.taynews_demo.bean.NewsTitleBean;
import com.bawei.taynews_demo.fragment.NewsFragment;
import com.bawei.taynews_demo.httrack.FavoriteActivity;
import com.bawei.taynews_demo.httrack.Off_line_download;
import com.bawei.taynews_demo.httrack.SettingActivity;
import com.bawei.taynews_demo.httrack.activity.Menu_Activity;
import com.bawei.taynews_demo.httrack.shoppingMall.ShoppingMall;
import com.bawei.taynews_demo.login.LoginActivity;
import com.bawei.taynews_demo.login.PhoneLoginAcitivity;
import com.bawei.taynews_demo.personalcenter.PersonalCenter;
import com.bawei.taynews_demo.url.Url;
import com.bawei.taynews_demo.utils.GsonUtils;
import com.bawei.taynews_demo.utils.MySQLite;
import com.bawei.taynews_demo.utils.Night_styleutils;
import com.bawei.taynews_demo.utils.SQLiteXutils;
import com.bawei.taynews_demo.utils.TitleHttpUtils;
import com.bawei.taynews_demo.utils.UiUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToButton;
    private int mWidthPixels;
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private ProgressDialog progressDialog = null;
    private ImageView mImageView;
    private SlidingMenu mSm;
    private int theme = 0;
    private SQLiteDatabase mDb;
    private boolean flag = true;
    private RadioGroup mGroup;
    private ImageView mPhone;
    private SHARE_MEDIA mShare_media;
    private ImageView mQq;
    private ImageView mIconurl;
    private TextView mName;
    private LinearLayout mLin_menu_ic_na;
    private LinearLayout mLin_menu_sanfang;
    private SharedPreferences sp;
    private SharedPreferences.Editor mEdit;
    private String mPic;
    private String mName1;
    private Button mMenu_gengduodenglu;
    private Button mMenu_gengduodenglu1;
    private LinearLayout shoucang;
    private LinearLayout huodong;
    private LinearLayout shangcheng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        progressDialog = ProgressDialog.show(MainActivity.this, "请稍等..." + NetworkUtils
                .isWifiConnected(), "获取数据中...", true);

    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.main_jiahao);
        mTabLayout = (TabLayout) findViewById(R.id.main_tab);
        mPager = (ViewPager) findViewById(R.id.main_viewpager);
        mToButton = (ImageView) findViewById(R.id.topButton);
        mToButton.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mSm.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    // 当在其他位置的时候，设置不可以拖拽出来(SlidingMenu.TOUCHMODE_NONE)
                    // ，或只有在边缘位置才可以拖拽出来TOUCHMODE_MARGIN
                    mSm.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        //title数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                getFragment();
            }
        }).start();
        //侧滑
        ceHua();
    }

    //请求数据的方法
    private void getFragment() {

        TitleHttpUtils.getInstance().httpXUtilsPOST(Url.urlb, "uri", "title", new TitleHttpUtils
                .MyHttpCallback() {
            private List<NewsTitleBean.ResultBean.DateBean> mDate;
            private ChildInfo mInfo;

            @Override
            public void onSuccess(String result) {
                SharedPreferences preferences = getSharedPreferences("bawei", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                //解析result
                NewsTitleBean newsTitleBean = GsonUtils.GsonToBean(result, NewsTitleBean.class);
                //遍历集合来循环给表添加数据
                mDate = newsTitleBean.getResult().getDate();
                ArrayList<ChildInfo> childInfos = new ArrayList<>();
                ArrayList<String> strlist = new ArrayList<>();
                ArrayList<String> titList = new ArrayList<>();
                DbManager dbManager = SQLiteXutils.initDataBase();
                MySQLite mySQLlite = new MySQLite(MainActivity.this);
                mDb = mySQLlite.getReadableDatabase();

                boolean flag = preferences.getBoolean("flag", MainActivity.this.flag);
                ContentValues values = new ContentValues();
                if (flag) {
                    for (int i = 0; i < 11; i++) {

                        if (i % 2 == 0) {
                            values.put("name", mDate.get(i).getTitle());
                            values.put("url", mDate.get(i).getUri());
                            values.put("style", "1");
                            mDb.insert("channel", null, values);
                        }
                        if (i % 2 == 1) {
                            values.put("name", mDate.get(i).getTitle());
                            values.put("url", mDate.get(i).getUri());
                            values.put("style", "0");
                            mDb.insert("channel", null, values);
                        }
                        strlist.add(mDate.get(i).getTitle());
                    }
                    edit.putBoolean("flag", false);
                    edit.commit();
                }
                ArrayList<String> sList = new ArrayList<>();
                Cursor cursor = mDb.query("channel", new String[]{"name", "url"}, "style=?", new
                        String[]{"1"}, null, null, null);
                while (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    String url = cursor.getString(1);
                    sList.add(string);
                    titList.add(url);
                }

                for (NewsTitleBean.ResultBean.DateBean nd : mDate) {
                    int id = nd.getId();
                    String title = nd.getTitle();
                    String uri = nd.getUri();
                    mInfo = new ChildInfo(id, title, uri, 0);
                    childInfos.add(mInfo);
                    try {
                        dbManager.save(mInfo);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                ArrayList<Fragment> fragmentlist = new ArrayList<>();
                for (int i = 0; i < titList.size(); i++) {
                    Log.e("------", titList.size() + "");
                    NewsFragment fragment = new NewsFragment(titList.get(i));
                    Log.e("------------------", titList.toString());
                    fragmentlist.add(fragment);
                }

                NewsAdapter adapter = new NewsAdapter(getSupportFragmentManager(), fragmentlist,
                        sList);
                mPager.setAdapter(adapter);
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                mTabLayout.setupWithViewPager(mPager);
                mTabLayout.setTabsFromPagerAdapter(adapter);
            }

            @Override
            public void onError(String errorMsg) {

            }

            @Override
            public void onFinished() {
                progressDialog.dismiss();
            }
        });
    }


    private void ceHua() {
        // 设置左侧滑动菜单
     /*   setBehindContentView(R.layout.menu_frame_left);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.menu_frame, new LeftFragment()).commit();*/
        // 实例化滑动菜单对象
        mSm = new SlidingMenu(this);
        // 设置可以左右滑动的菜单
        mSm.setMode(SlidingMenu.LEFT);
        // 设置滑动菜单视图的宽度
        mWidthPixels = this.getResources().getDisplayMetrics().widthPixels;
        mSm.setBehindWidth(mWidthPixels / 4 * 3);
        // 设置渐入渐出效果的值
        mSm.setFadeDegree(0.35f);
        // 设置触摸屏幕的模式,这里设置为全屏
        mSm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSm.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        // 设置下方视图的在滚动时的缩放比例
        mSm.setBehindScrollScale(0.0f);
        if (!mSm.isSecondaryMenuShowing()) {
            mSm.showContent();
        } else {
            mSm.showSecondaryMenu();
        }
        mSm.setMenu(R.layout.layout_menu);
        mMenu_gengduodenglu1 = (Button) mSm.findViewById(R.id.menu_gengduodenglu);
        mMenu_gengduodenglu1.setOnClickListener(this);

        mPhone = (ImageView) mSm.findViewById(R.id.image_menu_phone);
        mQq = (ImageView) mSm.findViewById(R.id.image_menu_qq);
        //回传回来头像监听
        mIconurl = (ImageView) mSm.findViewById(R.id.image_menu_touxiang);
        mName = (TextView) mSm.findViewById(R.id.te_menu_name);
        mLin_menu_ic_na = (LinearLayout) mSm.findViewById(R.id.lin_menu_ic_na);
        mLin_menu_ic_na.setOnClickListener(this);
        mLin_menu_sanfang = (LinearLayout) mSm.findViewById(R.id.lin_menu_sanfang);
        mMenu_gengduodenglu = (Button) mSm.findViewById(R.id.menu_gengduodenglu);
        shoucang = (LinearLayout) mSm.findViewById(R.id.lin_menu_shoucang);
        shoucang.setOnClickListener(this);
        huodong = (LinearLayout) mSm.findViewById(R.id.lin_menu_huodong);
        huodong.setOnClickListener(this);
        shangcheng = (LinearLayout) mSm.findViewById(R.id.lin_menu_shangcheng);
        shangcheng.setOnClickListener(this);
        mQq.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        RadioButton yejian = (RadioButton) mSm.findViewById(R.id.ri_bt_yejian);
        RadioButton lixian = (RadioButton) mSm.findViewById(R.id.ri_bt_lixian);
        RadioButton shezhi = (RadioButton) mSm.findViewById(R.id.ri_bt_shezhi);
        yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.switchAppTheme(MainActivity.this);
                MainActivity.this.reload();
            }
        });
        lixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Off_line_download.class));
            }
        });
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.topButton:
                mSm.toggle();
                break;
            case R.id.main_jiahao:
                startActivity(new Intent(MainActivity.this, PinDaoguanli.class));
                finish();
                break;
            case R.id.menu_gengduodenglu:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.image_menu_phone:
                startActivity(new Intent(MainActivity.this, PhoneLoginAcitivity.class));
                break;
            case R.id.image_menu_qq:
                mShare_media = SHARE_MEDIA.QQ;
              /*  UMShareAPI.get(this).getPlatformInfo(this,
                        mShare_media.toSnsPlatform().mPlatform, umAuthListener);*/
                UMShareAPI mShareAPI = UMShareAPI.get(MainActivity.this);
                mShareAPI.getPlatformInfo(MainActivity.this, mShare_media.toSnsPlatform()
                        .mPlatform, umAuthListener);
               /* UMShareAPI.get(ShoppingMall.this).doOauthVerify(ShoppingMall.this, SHARE_MEDIA.QQ
                        .toSnsPlatform().mPlatform, umAuthListener);*/
                break;
            case R.id.lin_menu_ic_na:
                Intent intent = new Intent(this, PersonalCenter.class);
                startActivity(intent);
                break;
            case R.id.lin_menu_shoucang:
                startActivity(new Intent(this, FavoriteActivity.class));
                break;
            case R.id.lin_menu_huodong:
                startActivity(new Intent(this, Menu_Activity.class));
                break;
            case R.id.lin_menu_shangcheng:
                startActivity(new Intent(this, ShoppingMall.class));
                break;
            default:
                break;
        }
    }

    //QQ登录
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
            String name = data.get("name");
            String iconurl = data.get("iconurl");
            mName.setText(name);
            Glide.with(MainActivity.this).load(iconurl).error(R.mipmap
                    .ic_launcher)
                    .into(mToButton);
            Glide.with(MainActivity.this).load(iconurl).error(R.mipmap
                    .ic_launcher)
                    .into(mIconurl);
            mLin_menu_ic_na.setVisibility(View.VISIBLE);
            mLin_menu_sanfang.setVisibility(View.GONE);
            mMenu_gengduodenglu.setVisibility(View.GONE);
            mEdit.putBoolean("isfrit", true);
            mEdit.putString("name", data.get("name"));
            mEdit.putString("imageview", data.get("iconurl"));
            mEdit.commit();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show();
        }
    };

    //三方登录返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    //双击退出
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    //头像回传值

    @Override
    protected void onResume() {
        super.onResume();
        iclude();
    }

    private void iclude() {
        sp = getSharedPreferences("config", MODE_PRIVATE);
        mEdit = sp.edit();
        mPic = sp.getString("imageview", "");
        mName1 = sp.getString("name", "");
        boolean isfrit = sp.getBoolean("isfrit", false);
        if (isfrit) {
            Glide.with(MainActivity.this).load(sp.getString("imageview", "")).into(mToButton);
            mLin_menu_sanfang.setVisibility(View.GONE);
            mMenu_gengduodenglu.setVisibility(View.GONE);  //吧布局显示的在代码中隐藏
            mLin_menu_ic_na.setVisibility(View.VISIBLE);
            Glide.with(MainActivity.this).load(mPic).error(R.mipmap
                    .aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa).into(mIconurl);
            mName.setText(mName1);
        } else {
            mLin_menu_sanfang.setVisibility(View.VISIBLE);
            mMenu_gengduodenglu.setVisibility(View.VISIBLE);  //吧布局显示的在代码中隐藏
            mLin_menu_ic_na.setVisibility(View.GONE);
            Glide.with(MainActivity.this).load(sp.getString("imageview", "")).placeholder(R
                    .mipmap.qqqwwww).into(mToButton);
        }

    }
}
/*D/RegActivity: onClick: password=fan1991&password_confirm=fan1991&client=android&email=fan111
@qq.com&username=17600050562

public static final String LINK_MOBILE_LOGIN = LINK_MOBILE + "login";
                              //登录 POST
public static final String LINK_MOBILE_REG = LINK_MOBILE + "login&op=register";
                              //注册 POST

        password=fanyanlong&client=android&username=18611352750

        {"code":400,"datas":{"error":"\u8bf7\u586b\u5199\u7528\u6237\u540d"}}*/
