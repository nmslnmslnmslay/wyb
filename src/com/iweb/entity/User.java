package com.iweb.entity;

/**
 * lombok
 * @author azzhu
 * @create 2019-08-11 10:23:59
 */
public class User {
    private Integer id;
    private String userName;    //用户名
    private String password;    //密码
    private String nickName;    //昵称
    private String picPath;     //头像地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", picPath='" + picPath + '\'' +
                '}';
    }
}
