package com.example.userlist.domain.entity;

public class User {

    private final int id;
    private String userName;
    private String userSurname;
    private int userAge;

    public User(int id, String userName, String userSurname, int userAge) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAge = userAge;
    }

    public int getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return this.userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public int getUserAge() {
        return this.userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
}
