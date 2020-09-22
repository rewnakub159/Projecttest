package com.example.project;

public class Machine_DB {
    String name;
    String status;
    String volume;
    String volume_now;

    public Machine_DB() {
    }

    public Machine_DB(String name, String status, String volume, String volume_now) {
        this.name = name;
        this.status = status;
        this.volume = volume;
        this.volume_now = volume_now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume_now() {
        return volume_now;
    }

    public void setVolume_now(String volume_now) {
        this.volume_now = volume_now;
    }
}
