package com.qst.atbtmusic.entity;

public class Artists {//歌手类

	private int singer_id;//歌手id
	private String singer_name;//歌手名字
	private String singer_img;
	private String singer_desc;
	private String singer_type;
	public int getSinger_id() {
		return singer_id;
	}
	public void setSinger_id(int singer_id) {
		this.singer_id = singer_id;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
	}
	public String getSinger_img() {
		return singer_img;
	}
	public void setSinger_img(String singer_img) {
		this.singer_img = singer_img;
	}
	public String getSinger_desc() {
		return singer_desc;
	}
	public void setSinger_desc(String singer_desc) {
		this.singer_desc = singer_desc;
	}
	public String getSinger_type() {
		return singer_type;
	}
	public void setSinger_type(String singer_type) {
		this.singer_type = singer_type;
	}
	@Override
	public String toString() {
		return "Artists [singer_id=" + singer_id + ", singer_name=" + singer_name + ", singer_img=" + singer_img
				+ ", singer_desc=" + singer_desc + ", singer_type=" + singer_type + "]";
	}
	
}
