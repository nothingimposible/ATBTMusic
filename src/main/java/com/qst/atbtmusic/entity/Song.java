package com.qst.atbtmusic.entity;

public class Song {

	private int song_id;
	private int singer_id;
	private String song_name;
	private String song_img;
	private String song_address;
	private String song_time;
	private String song_desc;
	private String song_type;
	public int getSong_id() {
		return song_id;
	}
	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
	public int getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(int singer_id) {
		this.singer_id = singer_id;
	}
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public String getSong_img() {
		return song_img;
	}
	public void setSong_img(String song_img) {
		this.song_img = song_img;
	}
	public String getSong_address() {
		return song_address;
	}
	public void setSong_address(String song_address) {
		this.song_address = song_address;
	}
	public String getSong_time() {
		return song_time;
	}
	public void setSong_time(String song_time) {
		this.song_time = song_time;
	}
	public String getSong_desc() {
		return song_desc;
	}
	public void setSong_desc(String song_desc) {
		this.song_desc = song_desc;
	}
	public String getSong_type() {
		return song_type;
	}
	public void setSong_type(String song_type) {
		this.song_type = song_type;
	}
	@Override
	public String toString() {
		return "Song [song_id=" + song_id + ", singer_id=" + singer_id + ", 歌曲名称=" + song_name + ", 歌曲图片="
				+ song_img + ", 歌曲音频地址=" + song_address + ", 歌曲上传时间=" + song_time + ", 描述=" + song_desc
				+ ", 类型=" + song_type + "]";
	}
	
	
}
