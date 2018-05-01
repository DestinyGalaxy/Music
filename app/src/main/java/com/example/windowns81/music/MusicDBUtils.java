package com.example.windowns81.music;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windowns8.1 on 2018/3/27.
 */
public class MusicDBUtils {
    private static SQLiteOpenHelper dbConnection;
    private static SQLiteDatabase getWriteDB(Context context)
    {
        if(dbConnection==null)
        {
            dbConnection = new DataBaseConnection(context,"music.db",null,1);
        }
        SQLiteDatabase writeDB= dbConnection.getWritableDatabase();
        return writeDB;
    }

    private static SQLiteDatabase getReadDB(Context context)
    {
        if(dbConnection==null)
        {
            dbConnection =  new DataBaseConnection(context,"music.db",null,1);
        }
        SQLiteDatabase readDB=dbConnection.getReadableDatabase();
        return readDB;
    }

    public static List<Mp3Info> getMp3Infos(Context context)
    {
        Cursor cursor = getReadDB(context).rawQuery("select * from song",null);
        List<Mp3Info> musicInfo = new ArrayList<>();
        for(int i = 0;i < cursor.getCount();i++){
            cursor.moveToNext();
            Mp3Info mp3Info = new Mp3Info();
            int id = cursor.getInt( cursor.getColumnIndex("id")    );
            String title =  cursor.getString( cursor.getColumnIndex("title") );
            String artist =  cursor.getString(cursor.getColumnIndex("artist") );
            int duration = cursor.getInt(cursor.getColumnIndex("duration"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            int lyric_id = cursor.getInt(cursor.getColumnIndex("lyric_id"));

            mp3Info.setId(id);
            mp3Info.setTitle(title);
            mp3Info.setArtist(artist);
            mp3Info.setDuration(duration);
            mp3Info.setUrl(url);
            mp3Info.setLyric_id(lyric_id);
            musicInfo.add(mp3Info);
        }
        return musicInfo;
    }

    public static void setMp3Infos(Context context,List<Mp3Info> mp3Infos)
    {
        for (Mp3Info mp3Info : mp3Infos) {
            ContentValues cv=new ContentValues();
            cv.put("id",mp3Info.getId());
            cv.put("title",mp3Info.getTitle());
            cv.put("duration",mp3Info.getDuration());
            cv.put("artist",mp3Info.getArtist());
            cv.put("url",mp3Info.getUrl());
            cv.put("lyric_id",-1);
            getWriteDB(context).insert("song",null,cv);
        }

}

    public static boolean checkIfExistSong(Context context)
    {
        Cursor cursor= getReadDB(context).rawQuery("select count(*) from song",null);
        cursor.moveToNext();
        int num=cursor.getInt(0);
        return num>0?true:false;
    }

//    public static void insertData(String fullPath) {
//        // 先判断数据库中是否有这条数据
//        String sql = "SELECT song_name FROM music WHERE full_path = ?";
//        Globals.dbc
//        Cursor c = Globals.dbc.getReadableDatabase().rawQuery(sql,
//                new String[] { fullPath });
//        if (!c.moveToFirst()) {
//            // 之前没有添加过,需要添加新数据
//            sql = "INSERT INTO music VALUES (?,?)";
//            Globals.dbc.getWritableDatabase().execSQL(
//                    sql,
//                    new Object[] { fullPath,
//                            Globals.allSongNameMap.get(fullPath) });
//        }
//        c.close();
//    }
//
//    public static List<Map<String, Object>> listData() {
//        String sql = "SELECT full_path,song_name FROM music";
//        Cursor c = Globals.dbc.getReadableDatabase().rawQuery(sql, null);
//
//        List<Map<String, Object>> allValues = new ArrayList<Map<String, Object>>();
//        c.moveToFirst();
//        while (!c.isAfterLast()) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("fullPath", c.getString(0));
//            map.put("songName", c.getString(1));
//            allValues.add(map);
//
//            c.moveToNext();
//        }
//
//        c.close();
//
//        return allValues;
//    }
}
