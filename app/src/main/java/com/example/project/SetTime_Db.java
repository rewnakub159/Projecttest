package com.example.project;

public class SetTime_Db {

    String settime;
    String time;
    String volume;
    String status;

    public SetTime_Db() {
    }

    public SetTime_Db(String settime, String time, String volume, String status) {
        this.settime = settime;
        this.time = time;
        this.volume = volume;
        this.status = status;
    }

    public String getSettime() {
        return settime;
    }

    public void setSettime(String settime) {
        this.settime = settime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
