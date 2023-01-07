package model;

import db.UserState;

import java.sql.Date;

public class UserModel {
    int userId;
    String name;
    String surname;
    String phone;
    UserState state;
    Date crtdt;

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public Date getCrtdt() {
        return crtdt;
    }

    public void setCrtdt(Date crtdt) {
        this.crtdt = crtdt;
    }

    public UserModel(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
