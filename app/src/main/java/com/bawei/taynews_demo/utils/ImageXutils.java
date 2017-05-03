package com.bawei.taynews_demo.utils;

import android.widget.ImageView;


import com.bawei.taynews_demo.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/10 21:16
*/

public class ImageXutils {
    public static void getimage(ImageView image, String url) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))//图片大小
                .setRadius(DensityUtil.dip2px(5))//ImageView圆角半径
                .setCrop(true)// 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa)//加载中默认显示图片
                .setUseMemCache(true)//设置使用缓存
                .setFailureDrawableId(R.mipmap.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa)//加载失败后默认显示图片
                .build();
        x.image().bind(image, url, imageOptions);
    }
}