package com.bawei.taynews_demo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类用途：
 * 作者：ShiZhuangZhuang
 * 时间：2017/4/15 12:49
 */

public class MySQLite extends SQLiteOpenHelper {
    public MySQLite(Context context) {
        super(context, "channel.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_favor = "create table favor(id Integer primary key autoincrement," +
                "title text,pic text,url text,author_name text)";
        db.execSQL(create_favor);
        db.execSQL("create table channel(_id integer primary key autoincrement ,name text, url text, style Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
