package com.bawei.taynews_demo.utils;

import com.google.gson.Gson;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/10 20:25
 */

public class GsonUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }
    private GsonUtils() {
    }
    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }


}
