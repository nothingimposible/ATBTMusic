package com.qst.atbtmusic.pojo;

public class SongList {
    private int songListId;
    private String songListName;
    private int userId;
    private String songListImg;
    private String songListType;
    private String songListDesc;
    private int songListPlayTime;

    public int getSongListPlayTime() {
        return songListPlayTime;
    }

    public void setSongListPlayTime(int songListPlayTime) {
        this.songListPlayTime = songListPlayTime;
    }

    public String getSongListDesc() {
        return songListDesc;
    }

    public void setSongListDesc(String songListDesc) {
        this.songListDesc = songListDesc;
    }

    public int getSongListId() {
        return songListId;
    }

    public void setSongListId(int songListId) {
        this.songListId = songListId;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSongListImg() {
        return songListImg;
    }

    public void setSongListImg(String songListImg) {
        this.songListImg = songListImg;
    }

    public String getSongListType() {
        return songListType;
    }

    public void setSongListType(String songListType) {
        this.songListType = songListType;
    }

    @Override
    public String toString() {
        return "SongList{" +
                "songListId=" + songListId +
                ", songListName='" + songListName + '\'' +
                ", userId=" + userId +
                ", songListImg='" + songListImg + '\'' +
                ", songListType='" + songListType + '\'' +
                '}';
    }
}
