package com.example.windowns81.music;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends FragmentActivity {

    private RadioGroup navGroup;
    private String tabs[] = {"歌单", "音乐", "曲库"};
    private Toast toast;
    private ListView listView = null;
    private int[] imageId = new int[]{R.drawable.music, R.drawable.download,
            R.drawable.collect};
    private String home_list[] = {"本地音乐", "下载管理", "我的收藏"};
    private TabHost tabHost;
    private RadioGroup radiogroup;
    private int menuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);


//        radiogroup = (RadioGroup) findViewById(R.id.navgroup);
//        tabHost = (TabHost) findViewById(android.R.id.tabhost);
//        tabHost.setup();
//        tabHost.addTab(tabHost.newTabSpec("main").setIndicator("main")
//                .setContent(R.id.fragment_main));
//        tabHost.addTab(tabHost.newTabSpec("music").setIndicator("music")
//                .setContent(R.id.fragment_music));
//        tabHost.addTab(tabHost.newTabSpec("other").setIndicator("other")
//                .setContent(R.id.fragment_other));
//        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                menuid = checkedId;
//                int currentTab = tabHost.getCurrentTab();
//                switch (checkedId) {
//                    case R.id.radioButton1:
//                        tabHost.setCurrentTabByTag("main");
//                        break;
//                    case R.id.radioButton2:
//                        //动画效果
//                        setCurrentTabWithAnim(currentTab, 1, "music");
//                        break;
//                    case R.id.radioButton3:
//                        tabHost.setCurrentTabByTag("other");
//                }
//                // 刷新actionbar的menu
//                getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
//            }
//        });
    }
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("listitem_image",R.drawable.music);
        map.put("listitem_tv","本地音乐");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("listitem_image",R.drawable.download);
        map.put("listitem_tv","下载管理");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("listitem_image",R.drawable.collect);
        map.put("listitem_tv","我的收藏");
        list.add(map);

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        switch (menuid) {
            case R.id.radioButton1:
                getMenuInflater().inflate(R.menu.main,menu);
                break;
            case R.id.radioButton2:
                menu.clear();
                break;
            case R.id.radioButton3:
                menu.clear();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.radioButton3) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
}

