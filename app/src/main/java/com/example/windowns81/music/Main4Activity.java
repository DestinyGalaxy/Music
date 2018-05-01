package com.example.windowns81.music;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main4Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main4);
        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.musiclist,
                new String[]{"listitem_image", "listitem_tv"},
                new int[]{R.id.listitem_image, R.id.listitem_tv});
        setListAdapter(adapter);
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("listitem_image",R.drawable.music);
        map.put("listitem_tv","本地音乐");
        list.add(map);

        /*map = new HashMap<String, Object>();
        map.put("listitem_image",R.drawable.download);
        map.put("listitem_tv","下载管理");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("listitem_image",R.drawable.collect);
        map.put("listitem_tv","我的收藏");
        list.add(map);*/

        return list;
    }
}
