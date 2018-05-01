package com.example.windowns81.music;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RadioGroup radiogroup;
    private int menuid;
    private TabHost tabHost;
    private ListView SonglistView;
    private List<Mp3Info> mp3Infos = null;
    private MusicListAdapter ListAdapter;
    private  RadioButton radiobutton;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
        setContentView(R.layout.activity_home);
        initTabHost();
        tabChange();
        initView();
        initSqlite();

        TextView music_name = (TextView) findViewById(R.id.music_name);
        TextView main_artist = (TextView) findViewById(R.id.main_artist);
        TextView music_duration = (TextView) findViewById(R.id.music_duration);

        SonglistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int currentTab = tabHost.getCurrentTab();
                setCurrentTabWithAnim(currentTab, 2, "tab2");
                radiobutton = (RadioButton)findViewById(R.id.radioButton2);
                radiobutton.setChecked(true);

                Mp3Info mp3Info = (Mp3Info) ListAdapter.getItem(position);

                ((TextView) findViewById(R.id.music_name)).setText(mp3Info.getTitle());
                System.out.println(mp3Info.getArtist());
//                ((TextView) findViewById(R.id.main_artist)).setText(mp3Info.getArtist());
                ((TextView) findViewById(R.id.music_duration)).setText(timeFormat(mp3Info.getDuration()));
                ((Button) findViewById(R.id.play)).setBackgroundResource(R.drawable.btn_pause1);

                if(mediaPlayer==null)
                {
                    mediaPlayer=new MediaPlayer();
                }
                Uri uri=Uri.parse(mp3Info.getUrl());

//                mediaPlayer.create(HomeActivity.this,uri);
                try {
                    mediaPlayer.setDataSource(mp3Info.getUrl());
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();


            }
        });


    }


//    private boolean seekTo(int rate){
//        if (mPlayState == MusicPlayState.MPS_NOFILE ||
//                mPlayState == MusicPlayState.MPS_INVALID)
//        {
//            return false;
//        }
//        int r = reviceSeekValue(rate);
//        int time = mMediaPlayer.getDuration();
//        int curTime = (int) ((float)r / 100 * time);
//        mMediaPlayer.seekTo(curTime);
//        return true;
//    }
    private String timeFormat(int time){
        String miunite = String.valueOf(time/60000);
        String second = String.format("%02d",time%60000).substring(0,2);
        return miunite + ":" + second;
    }

    private void tabChange(){
        radiogroup = (RadioGroup) findViewById(R.id.navgroup);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                menuid = checkedId;
                int currentTab = tabHost.getCurrentTab();
                switch (checkedId) {
                    case R.id.radioButton1:
                        setCurrentTabWithAnim(currentTab, 1, "tab1");
                        break;
                    case R.id.radioButton2:
                        //动画效果
                        setCurrentTabWithAnim(currentTab, 2, "tab2");
                        break;
                    case R.id.radioButton3:
                        setCurrentTabWithAnim(currentTab, 3, "tab3");

                }
                // 刷新actionbar的menu
                getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
            }
        });
    }

    private void initView() {
        SonglistView = (ListView) findViewById(R.id.list_music);
        boolean ifInDB= MusicDBUtils.checkIfExistSong(this);
        if(ifInDB)
        {
            mp3Infos = MusicDBUtils.getMp3Infos(this);
        }
        else
        {
            mp3Infos = MusicUtil.getMp3Infos(HomeActivity.this);
            MusicDBUtils.setMp3Infos(this,mp3Infos);
        }

        ListAdapter=new MusicListAdapter(this,mp3Infos);
        SonglistView.setAdapter(ListAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Mp3Info mp3Info=mp3Infos.get(position);
//                //TODO
//            }
//        });

    }

    private void initSqlite(){
        SQLiteOpenHelper dbHelper = new MySqLite(HomeActivity.this);
        // 调用getReadableDatabase()或getWritableDatabase()才算真正创建或打开数据库
        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
    }

    public void initTabHost() {
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Tab1", getDrawable(R.drawable.tab_icon1_pressed)).setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Tab2", getDrawable(R.drawable.tab_icon2_pressed)).setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("Tab3", getDrawable(R.drawable.tab_icon3_pressed)).setContent(R.id.tab3));
        tabHost.setCurrentTabByTag("tab2");
        radiobutton = (RadioButton)findViewById(R.id.radioButton2);
        radiobutton.setChecked(true);
    }

    // 这个方法是关键，用来判断动画滑动的方向
    private void setCurrentTabWithAnim(int now, int next, String tag) {
        if (now > next) {
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
            tabHost.setCurrentTabByTag(tag);
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
        } else {
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
            tabHost.setCurrentTabByTag(tag);
            tabHost.getCurrentView().startAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        }
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
