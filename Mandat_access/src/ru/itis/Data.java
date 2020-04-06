package ru.itis;

public class Data {
    String name;
    int access_mark;
    String data;

    public Data(String name, int access_mark, String data) {
        this.name = name;
        this.access_mark = access_mark;
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setAccess_mark(int access_mark) {
        this.access_mark = access_mark;
    }
}
