package com.qst.atbtmusic.pojo;

public class SongAndSinger {
    private int songId;
    private int singerId;
    private String songName;
    private String songImg;
    private String songAddress;
    private String songTime;
    private String songDesc;
    private String songType;
    private int songPlayernum;
    private String singerName;

    public void copy(Song song,Singer singer){
        songId=song.getSongId();
        singerId=song.getSingerId();
        songName=song.getSongName();
        songImg=song.getSongImg();
        songAddress=song.getSongAddress();
        songTime=song.getSongTime();
        songDesc=song.getSongDesc();
        songType=song.getSongType();
        songPlayernum=song.getSongPlayernum();
        singerName=singer.getSingerName();
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

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    @Override
    public String toString() {
        return "SongAndSinger{" +
                "songId=" + songId +
                ", singerId=" + singerId +
                ", songName='" + songName + '\'' +
                ", songImg='" + songImg + '\'' +
                ", songAddress='" + songAddress + '\'' +
                ", songTime='" + songTime + '\'' +
                ", songDesc='" + songDesc + '\'' +
                ", songType='" + songType + '\'' +
                ", songPlayernum=" + songPlayernum +
                ", singerName='" + singerName + '\'' +
                '}';
    }
}
