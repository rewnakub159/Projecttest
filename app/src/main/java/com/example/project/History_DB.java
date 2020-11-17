package com.example.project;

public class History_DB {
    String id;
    String macname;
    String volume;
    String date;
    String time;
    String weight;

    public History_DB() {
    }

    public History_DB(String id, String macname, String volume, String date, String time, String weight) {
        this.id = id;
        this.macname = macname;
        this.volume = volume;
        this.date = date;
        this.time = time;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMacname() {
        return macname;
    }

    public void setMacname(String macname) {
        this.macname = macname;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
