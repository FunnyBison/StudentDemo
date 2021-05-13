package com.chenzhi.shop.application;

import android.app.Application;

import com.okhttplib.CacheLevel;
import com.okhttplib.CacheType;
import com.okhttplib.OkHttpUtil;

/**
 * Created by chenzhi on 2016/7/6 0006.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpUtil.init(this)
                .setConnectTimeout(30)//超时时间设置
                .setMaxCacheSize(10 * 1024 * 1024)//设置缓存空间大小
                .setCacheLevel(CacheLevel.FIRST_LEVEL)//缓存等级
                .setCacheType(CacheType.NETWORK_THEN_CACHE)//缓存类型
                .setShowHttpLog(true)//显示请求日志
                .setShowLifecycleLog(true)
                .setRetryOnConnectionFailure(true)
                .build();
    }
}
