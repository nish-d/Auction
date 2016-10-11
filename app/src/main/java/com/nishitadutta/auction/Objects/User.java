package com.nishitadutta.auction.Objects;

/**
 * Created by Nishita on 09-10-2016.
 */
public class User {
    String uId;
    String phone;
    String userName;

    public User(String uId, String phone, String userName) {
        this.uId = uId;
        this.phone = phone;
        this.userName = userName;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
