package com.qst.atbtmusic.entity;

import java.util.List;

//歌曲所在的专辑
public class Album {

	private String picUrl;//专辑图片

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Override
	public String toString() {
		return "Album [图片地址=" + picUrl + "]";
	}

	
	
	
	
	
}
