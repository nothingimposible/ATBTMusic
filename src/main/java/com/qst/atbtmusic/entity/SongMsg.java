package com.qst.atbtmusic.entity;

import java.util.List;
import java.util.Map;

public class SongMsg {

	private String id;//歌曲地址
	private String name;//歌曲名称
	private String mvid;//视频id
	private Album album;//专辑信息
	//private List<Artists> artists;//歌手信息
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMvid() {
		return mvid;
	}
	public void setMvid(String mvid) {
		this.mvid = mvid;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	@Override
	public String toString() {
		return "歌曲地址=" + id + ", 歌曲名称=" + name + ", mv地址=" + mvid + ", 图片地址=" + album.getPicUrl();
	}
	
	
	
}
