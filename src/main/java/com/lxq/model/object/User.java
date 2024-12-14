package com.lxq.model.object;

//用户
public class User {
    //用户id
    private String userId;
    //用户账号
    private String userAccount;
    //用户昵称
    private String userName;
    //用户密码
    private String userPw;
    //用户级别
    private String userLv;
    //用户性别
    private String userSex;
    //用户手机
    private String userIphone;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserLv() {
        return userLv;
    }

    public void setUserLv(String userLv) {
        this.userLv = userLv;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserIphone() {
        return userIphone;
    }

    public void setUserIphone(String userIphone) {
        this.userIphone = userIphone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userLv='" + userLv + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userIphone='" + userIphone + '\'' +
                '}';
    }
}
