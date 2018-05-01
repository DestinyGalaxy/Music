package com.example.windowns81.music;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by windowns8.1 on 2018/3/27.
 */
public class DataBaseConnection extends SQLiteOpenHelper {



    public DataBaseConnection(Context ctx) {
        super(ctx, "music.db", null, 1);
    }

    public DataBaseConnection(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYLIST = "create table if not exists playlist(id integer primary key autoincrement,name text)";
        String CREATE_PLAYLIST_SONG = "create table if not exists playlistsong(id integer primary key autoincrement,playlist_id integer, song_id integer);";
        String CREATE_SONG="create table if not exists song(id integer primary key autoincrement,title varchar(50),duration integer,artist varchar(50),url varchar(200),lyric_id integer)";

        db.execSQL(CREATE_PLAYLIST);
        db.execSQL(CREATE_PLAYLIST_SONG);
        db.execSQL(CREATE_SONG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
