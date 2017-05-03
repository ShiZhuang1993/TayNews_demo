package com.bawei.taynews_demo.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/13 20:15
 */

public class TopTitle {
    /**
     * setStatusBar  沉浸式状态栏
     * 在Activity onCreate方法 setContentView 方法后使用，版本21后有效
     *
     * @param activity 上下文
     * @param color    String类型的颜色代码
     */
    public static void setStatusBar(Activity activity, String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            //获得Activity中的 decorView
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            //设置属性
            decorView.setSystemUiVisibility(option);
            //将状态栏变为透明
            activity.getWindow().setStatusBarColor(Color.parseColor(color));
        }

    }
}
