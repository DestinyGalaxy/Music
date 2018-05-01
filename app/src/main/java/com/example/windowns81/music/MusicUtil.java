package com.example.windowns81.music;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windowns8.1 on 2018/3/20.
 */
public class MusicUtil
{


    /**
     *搜索本地音乐
     *获取数据库里的音乐文件信息，并以Mp3Info类来保存数据
     */
    public static List<Mp3Info> getMp3Infos(Context context){
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        );

        List<Mp3Info> musicInfo = new ArrayList<Mp3Info>();
        for(int i = 0;i < cursor.getCount();i++){
            cursor.moveToNext();
            Mp3Info mp3Info = new Mp3Info();
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            String songName =  cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String singer =  cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album =  cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            long albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if(isMusic != 0 && singer != "<unknown>"){
                mp3Info.setId(id);
                mp3Info.setTitle(songName);
                mp3Info.setArtist(singer);
                mp3Info.setAlbum(album);
                mp3Info.setDuration(duration);
                mp3Info.setSize(size);
                mp3Info.setUrl(url);
            }
            musicInfo.add(mp3Info);
        }
        cursor.close();
        return musicInfo;
    }


    public static String formatTime(Long time){                     //将歌曲的时间转换为分秒的制度
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";

        if(min.length() < 2)
            min = "0" + min;
        switch (sec.length()){
            case 4:
                sec = "0" + sec;
                break;
            case 3:
                sec = "00" + sec;
                break;
            case 2:
                sec = "000" + sec;
                break;
            case 1:
                sec = "0000" + sec;
                break;
        }
        return min + ":" + sec.trim().substring(0,2);
    }


}
