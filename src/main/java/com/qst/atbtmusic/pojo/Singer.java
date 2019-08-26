package com.qst.atbtmusic.pojo;

public class Singer {
    private int singerId;
    private String singerName;
    private String singerType;
    private String singerImg;
    private String singerDesc;

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerType() {
        return singerType;
    }

    public void setSingerType(String singerType) {
        this.singerType = singerType;
    }

    public String getSingerImg() {
        return singerImg;
    }

    public void setSingerImg(String singerImg) {
        this.singerImg = singerImg;
    }

    public String getSingerDesc() {
        return singerDesc;
    }

    public void setSingerDesc(String singerDesc) {
        this.singerDesc = singerDesc;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                ", singerType='" + singerType + '\'' +
                ", singerImg='" + singerImg + '\'' +
                ", singerDesc='" + singerDesc + '\'' +
                '}';
    }
}
