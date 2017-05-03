package com.bawei.taynews_demo.utils;

import com.bawei.taynews_demo.bean.ChildInfo;
import com.bawei.taynews_demo.bean.NewsTitleBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * 类的用途：
 * Created by shizhuangzhuang
 * 2017/4/12  19:10
 */

public class TitleHttpUtils {
    private volatile static TitleHttpUtils instance;

    /**
     * 单利模式
     *
     * @return
     */
    public static TitleHttpUtils getInstance() {
        if (instance == null) {
            synchronized (TitleHttpUtils.class) {
                if (instance == null) {
                    instance = new TitleHttpUtils();
                }
            }
        }
        return instance;
    }
    /**
     * xUtils post 请求方式
     *
     * @param url    post方式的网络路径
     * @param key    params 的 key 值
     * @param values params 的 values 值
     */
    public void httpXUtilsPOST(String url, String key, String values, final MyHttpCallback
            callback) {

        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter(key, values);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                callback.onFinished();
            }
        });
    }

    /**
     * 接口回调
     */
    public interface MyHttpCallback {
        void onSuccess(String result);

        void onError(String errorMsg);

        void onFinished();
    }

}
