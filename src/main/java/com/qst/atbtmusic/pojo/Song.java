package com.qst.atbtmusic.pojo;

public class Song {
    private int songId;
    private int singerId;
    private String songName;
    private String songImg;
    private String songAddress;
    private String songTime;
    private String songDesc;
    private String songType;
    private int songPlayernum;
    private String lyric;

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongImg() {
        return songImg;
    }

    public void setSongImg(String songImg) {
        this.songImg = songImg;
    }

    public String getSongAddress() {
        return songAddress;
    }

    public void setSongAddress(String songAddress) {
        this.songAddress = songAddress;
    }

    public String getSongTime() {
        return songTime;
    }

    public void setSongTime(String songTime) {
        this.songTime = songTime;
    }

    public String getSongDesc() {
        return songDesc;
    }

    public void setSongDesc(String songDesc) {
        this.songDesc = songDesc;
    }

    public String getSongType() {
        return songType;
    }

    public void setSongType(String songType) {
        this.songType = songType;
    }

    public int getSongPlayernum() {
        return songPlayernum;
    }

    public void setSongPlayernum(int songPlayernum) {
        this.songPlayernum = songPlayernum;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", singerId=" + singerId +
                ", songName='" + songName + '\'' +
                ", songImg='" + songImg + '\'' +
                ", songAddress='" + songAddress + '\'' +
                ", songTime='" + songTime + '\'' +
                ", songDesc='" + songDesc + '\'' +
                ", songType='" + songType + '\'' +
                ", songPlayernum=" + songPlayernum +
                ", lyric='" + lyric + '\'' +
                '}';
    }
}
