package com.bawei.taynews_demo.utils;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/11 12:57
 */

public class SQLiteXutils {

    private static DbManager.DaoConfig mDaoConfig;

    public static DbManager initDataBase() {
        //创建数据库
        mDaoConfig = new DbManager.DaoConfig()
                .setDbName("news.db")
                .setDbVersion(1);
        //创建数据库及表
        DbManager db = x.getDb(mDaoConfig);

        return db;
    }
}

