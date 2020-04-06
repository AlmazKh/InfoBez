package ru.itis;

public class User {
    String name;
    int access_lv;

    public User(String name, int access_lv) {
        this.name = name;
        this.access_lv = access_lv;
    }

    public void setAccess_lv(int access_lv) {
        this.access_lv = access_lv;
    }
}
