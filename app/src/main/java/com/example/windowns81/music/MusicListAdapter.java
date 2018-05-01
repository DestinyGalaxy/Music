package com.example.windowns81.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by windowns8.1 on 2018/3/15.
 *
 */
class ViewContainer{
    public ImageView albumImage;                             //这里是为了下面的使用方便,将很多个控件放在了一个类里面
    public TextView music_title;
    public TextView music_artist;
    public TextView music_duration;
    public ImageButton list_down_button;
    public LinearLayout linearLayout;
}

/**
 * 适配器的配置
 */
public class MusicListAdapter extends BaseAdapter {

    private Context context;        //上下文对象引用
    private List<Mp3Info> mp3Infos;   //存放Mp3Info引用的集合
    private Mp3Info mp3Info;        //Mp3Info对象引用
    private int pos = -1;           //列表位置
    private ViewContainer vc;


    public MusicListAdapter(Context context,List<Mp3Info> mp3Infos){
        this.context = context;
        this.mp3Infos = mp3Infos;
    }


    @Override
    public int getCount() {
        return mp3Infos.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        vc = null;
        if(convertView == null){
            vc = new ViewContainer();
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.list_item, null);
            vc.music_title = (TextView)convertView.findViewById(R.id.music_title);
            vc.music_artist = (TextView)convertView.findViewById(R.id.music_Artist);
            convertView.setTag(vc);
        }
        else{
            vc = (ViewContainer)convertView.getTag();
        }
        mp3Info = mp3Infos.get(position);

        vc.music_title.setText(mp3Info.getTitle());         //显示标题
        vc.music_artist.setText(mp3Info.getArtist());       //显示艺术家
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mp3Infos.get(position);
    }

//    /**
//     * 定义一个方法用来格式化获取到的时间
//     */
//    public static String formatTime(int time) {
//        if (time / 1000 % 60 < 10) {
//            return time / 1000 / 60 + ":0" + time / 1000 % 60;
//
//        } else {
//            return time / 1000 / 60 + ":" + time / 1000 % 60;
//        }
//    }

/**
 * 时间格式转换
 */
 public static String formatTime(Long time){//将歌曲的时间转换为分秒的制度
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
