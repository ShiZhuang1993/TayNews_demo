package com.bawei.taynews_demo.initialize;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/10 18:33
 */

public class InitXutils extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        Utils.init(getApplicationContext());
        SMSSDK.initSDK(this, "1d01f6cdff416", "a404b4bd37428a5e160377eb8386a592");
        UMShareAPI.get(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        config.isOpenShareEditActivity(true);
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_SSO);
        config.setShareToLinkedInFriendScope(UMShareConfig.LINKED_IN_FRIEND_SCOPE_ANYONE);
    }
    {
        PlatformConfig.setQQZone("1106029755", "AaFrTt1byVgUpWIq");
    }
}
