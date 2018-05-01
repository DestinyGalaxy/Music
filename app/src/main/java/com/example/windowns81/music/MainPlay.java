package com.example.windowns81.music;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainPlay extends FragmentActivity {

    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainplay);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

    }


}
