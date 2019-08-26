package com.qst.atbtmusic.pojo;

public class Satisfaction {
   private int userId;
   private int songId;
   private int satifaction;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getSatifaction() {
        return satifaction;
    }

    public void setSatifaction(int satifaction) {
        this.satifaction = satifaction;
    }

    @Override
    public String toString() {
        return "Satisfaction{" +
                "userId=" + userId +
                ", songId=" + songId +
                ", satifaction=" + satifaction +
                '}';
    }
}
