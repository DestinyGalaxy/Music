package com.example.windowns81.music;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by windowns8.1 on 2018/3/27.
 */
public class MySqLite extends SQLiteOpenHelper {
    public static final String DATABASENAME = "mymusic.db";
    private static final int VERSION = 1;//数据库版本号
    private static MySqLite mySqlite = null;

    public static final synchronized MySqLite getInstance(final Context context) {
        if (mySqlite == null) {
            mySqlite = new MySqLite(context);
        }
        return mySqlite;
    }


    public static final String CREATE_PLAYLISTINFO = "create table if not exists playlist_info(playlist_id integer primary key autoincrement,playlist_name text,playlist_count text,playlist_img text)";
    public static final String CREATE_PLAYLISTS = "create table if not exists playlists(id integer primary key autoincrement,playlist_id integer, song_id integer);";
    public static final String CREATE_SONGINFO = "create table Mp3Info(id String primary key,name varchar(200));";

    public MySqLite(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYLISTINFO);
        db.execSQL(CREATE_PLAYLISTS);
        db.execSQL(CREATE_SONGINFO);
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
        switch(oldVersion){
            case 1:
            case 2:
            default:
        }
    }

}
