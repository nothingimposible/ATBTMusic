package com.qst.atbtmusic.pojo;

import org.springframework.beans.factory.annotation.Value;

public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userImg;
    private int userPrivilege;
    private int userVip;
    private String userTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getUserPrivilege() {
        return userPrivilege;
    }

    public void setUserPrivilege(int userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public int getUserVip() {
        return userVip;
    }

    public void setUserVip(int userVip) {
        this.userVip = userVip;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userImg='" + userImg + '\'' +
                ", userPrivilege=" + userPrivilege +
                ", userVip=" + userVip +
                ", userTime='" + userTime + '\'' +
                '}';
    }
}
