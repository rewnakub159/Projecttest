package com.example.project;

public class SetTime_Db {
  String volume;
  String settime;
  String status;
    String name;

    public SetTime_Db() {
    }

    public SetTime_Db(String volume, String settime, String status, String name) {
        this.volume = volume;
        this.settime = settime;
        this.status = status;
        this.name = name;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSettime() {
        return settime;
    }

    public void setSettime(String settime) {
        this.settime = settime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
