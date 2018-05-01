package com.example.windowns81.music;

/**
 * 音乐文件信息类，用来放置保存从数据库加载的音乐信息
 */
public class Mp3Info {
	private int id;
	private int album_id;
	private String title;
	private String artist;
	private int duration;
	private long size;
	private String url;
	private String album;
	private int lyric_id;
	private boolean isFavorite = false;

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean favorite) {
		isFavorite = favorite;
	}

	public int getLyric_id() {
		return lyric_id;
	}

	public void setLyric_id(int lyric_id) {
		this.lyric_id = lyric_id;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
