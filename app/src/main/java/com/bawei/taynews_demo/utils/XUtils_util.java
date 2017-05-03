package com.bawei.taynews_demo.utils;

import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/10 19:15
 */

/**
 * Created by acer on 2017/3/19.
 */
public class XUtils_util {

    private volatile static XUtils_util instance;

    //私有化构造器
    private XUtils_util() {
    }

    /**
     * 单例模式
     */
    public static XUtils_util getInstance() {
        if (instance == null) {
            synchronized (XUtils_util.class) {
                if (instance == null) {
                    instance = new XUtils_util();
                }
            }
        }
        return instance;
    }

    /**
     * 带缓存数据的异步get请求
     *
     * @param url
     * @param maps
     * @param ifCache   是否缓存
     * @param cacheTime 缓存存活时间
     */
    public void getCache(String url, Map<String,String> maps, boolean ifCache,
                         long cacheTime, XCallBack callback) {
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(cacheTime);
        if (null != maps && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue() + "");
            }
        }
        httpGet(ifCache, callback, params);
    }

    //带缓存的get请求的方法
    private void httpGet(final boolean ifCache, final XCallBack callback,
                         RequestParams params) {

        x.http().get(params, new Callback.CacheCallback<String>() {

            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) {
                if (ifCache && null != result) {
                    this.result = result;
                }
                // true: 信任缓存数据, 不在发起网络请求; false不信任缓存数据.
                return ifCache;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    this.result = result;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                hasError = true;
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                callback.onFail("失败了");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    callback.onResponse(result);
                }
            }
        });
    }

    //接口回调
    public interface XCallBack {
        void onResponse(String result);

        void onFail(String result);
    }
}


/*public class HttpXutis {

    private static ArrayList<NewsBean.ResultBean.DataBean> arrayList;

    public static void getCache(String url, final XListView listView, final Context context) {
        RequestParams params = new RequestParams(Url.urla);
        params.addQueryStringParameter("uri", url);

        x.http().get(params, new Callback.CacheCallback<String>() {

            private List<NewsBean.ResultBean.DataBean> dataList;

            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                NewsBean newsBean = GsonUtils.GsonToBean(result, NewsBean.class);
                dataList = newsBean.getResult().getData();
                arrayList = new ArrayList<>();
                arrayList.addAll(dataList);
                FragmentAdapter adapter = new FragmentAdapter(context, arrayList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long
                            id) {
                        Intent intent = new Intent(context, WebUtils.class);
                        intent.putExtra("title", dataList.get(position - 1).getTitle());
                        intent.putExtra("author_name", dataList.get(position - 1).getAuthor_name());
                        intent.putExtra("url", dataList.get(position - 1).getUrl());
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}*/
