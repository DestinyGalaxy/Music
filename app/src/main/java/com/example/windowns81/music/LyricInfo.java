package com.example.windowns81.music;

import java.util.List;

/**
 * Created by windowns8.1 on 2018/3/14.
 */
public class LyricInfo {
    // 偏移量
    private long offset;
    // 名字
    private String title;
    // 作者
    private String artist;
    // 专辑
    private String album;
    // 制作
    private String by;
    // 歌词
    private List<LineInfo> lines;

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public List<LineInfo> getLines() {
        return lines;
    }

    public void setLines(List<LineInfo> lines) {
        this.lines = lines;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}
