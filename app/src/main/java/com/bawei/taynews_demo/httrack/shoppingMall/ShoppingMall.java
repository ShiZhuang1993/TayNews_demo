package com.bawei.taynews_demo.httrack.shoppingMall;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.url.Url;
import com.bawei.xlistviewlibrary.XListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingMall extends AppCompatActivity implements View.OnClickListener {

    private XListView main_list;
    private int index = 0;
    private SQLiteDatabase mDatabase;
    private ArrayList<NewsBean.DataBean> mList = new ArrayList<>();
    private boolean flag = true;
    private boolean flags = true;
    private Map<String, Integer> map;
    private NewsAdapter mAdapter;
    private ImageView iv_back_include_head_login;
    private TextView tv_back_include_head_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingmall);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
        initView();
        xianshi();

    }

    private void initView() {
        map = new HashMap<>();
        map.put("channelId", 0);

        main_list = (XListView) findViewById(R.id.main_list);
        iv_back_include_head_login = (ImageView) findViewById(R.id.iv_back_include_head_login);
        iv_back_include_head_login.setOnClickListener(this);
        tv_back_include_head_login = (TextView) findViewById(R.id.tv_back_include_head_login);
        tv_back_include_head_login.setText("商店");
        main_list.setPullRefreshEnable(true);
        main_list.setPullLoadEnable(true);
        main_list.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                if (flag) {
                    flag = false;
                    index = 0;
                    mList.clear();
                    xianshi();
                    main_list.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            main_list.stopRefresh();
                            flag = true;
                        }
                    }, 2000);
                }
            }

            @Override
            public void onLoadMore() {
                if (flags) {
                    flags = false;
                    index++;
                    xianshi();
                    main_list.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            main_list.stopLoadMore();
                        }
                    }, 2000);
                    flags = true;
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void xianshi() {
        map.put("startNum", index);
        XUtils_utils.getInstance().getCache(Url.url, map, true, 999999, new XUtils_utils.XCallBack() {
            @Override
            public void onResponse(String result) {
                //解析result
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(result, NewsBean.class);
                List<NewsBean.DataBean> dataBeanList = newsBean.getData();
                mList.addAll(dataBeanList);


                if (mAdapter == null) {
                    mAdapter = new NewsAdapter(ShoppingMall.this, mList);
                    main_list.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String result) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_include_head_login:
                finish();
                break;
        }
    }
}