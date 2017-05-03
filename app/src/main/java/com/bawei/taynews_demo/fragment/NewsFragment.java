package com.bawei.taynews_demo.fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.bawei.taynews_demo.R;
import com.bawei.taynews_demo.activity.MainActivity;
import com.bawei.taynews_demo.adapter.FragmentAdapter;
import com.bawei.taynews_demo.bean.NewsBean;
import com.bawei.taynews_demo.httrack.NextActivity;
import com.bawei.taynews_demo.url.Url;
import com.bawei.taynews_demo.utils.GsonUtils;
import com.bawei.taynews_demo.utils.MySQLite;
import com.bawei.taynews_demo.utils.XUtils_util;
import com.bawei.xlistviewlibrary.XListView;
import com.zaaach.citypicker.CityPickerActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/10 19:55
 */

@SuppressLint("ValidFragment")
public class NewsFragment extends Fragment {
    private String url;
    private XListView mListView;
    private ArrayList<NewsBean.ResultBean.DataBean> arrayList;
    private Handler handler = new Handler();
    private boolean flag = true;
    private boolean flag1 = true;
    private MySQLite sqLite;
    private LinearLayout top;

    public NewsFragment(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment, null);
        //头布局

        if (url.equals("bd")) {
            top = (LinearLayout) view.findViewById(R.id.te_xlist_top);
            top.setVisibility(View.VISIBLE);
            top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //启动
                    startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),

                            REQUEST_CODE_PICK_CITY);

                }
            });
        }

        mListView = (XListView) view.findViewById(R.id.frag_list);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
        sqLite = new MySQLite(getActivity());
        mListView.setPullLoadEnable(true);
        mListView.setPullRefreshEnable(true);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        mListView.setRefreshTime(format);
        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                if (flag) {
                    flag = false;
                    arrayList.clear();
                    getData();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mListView.stopRefresh();
                            flag = true;
                        }
                    }, 2000);
                }
            }

            @Override
            public void onLoadMore() {
                if (flag1) {
                    flag1 = false;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mListView.stopLoadMore();
                            flag1 = true;
                        }
                    }, 2000);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {

                Intent intent = new Intent(getActivity(), NextActivity.class);//换webview
                intent.putExtra("title", arrayList.get(position - 1).getTitle());
                intent.putExtra("pic", arrayList.get(position - 1).getThumbnail_pic_s() + "");
                intent.putExtra("content", arrayList.get(position - 1).getAuthor_name());
                intent.putExtra("url", arrayList.get(position - 1).getUrl());
                startActivity(intent);
            }
        });
     /*   mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupWindow(view);
                return true;
            }
        });*/

    }

    private void getData() {

        Map<String, String> map = new HashMap<>();
        map.put("uri", url);
        XUtils_util.getInstance().getCache(Url.urla, map, true, 99999999, new XUtils_util.XCallBack() {

            private FragmentAdapter adapter;

            @Override
            public void onResponse(String result) {
                NewsBean newsBean = GsonUtils.GsonToBean(result, NewsBean.class);
                List<NewsBean.ResultBean.DataBean> beanList = newsBean.getResult().getData();
                arrayList = new ArrayList<>();
                arrayList.addAll(beanList);
                Log.e("--------------", arrayList.size() + "");
                if (adapter == null) {
                    adapter = new FragmentAdapter(getActivity(), arrayList);
                    mListView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String result) {

            }
        });
    }


    private static final int REQUEST_CODE_PICK_CITY = 0;

    //重写onActivityResult方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                //resultTV.setText("当前选择：" + city);
                SQLiteDatabase database = sqLite.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", city);
                database.update("channel", values, "url=?", new String[]{"bd"});
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();

            }
        }
    }


}
