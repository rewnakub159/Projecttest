package com.example.project;

public class Machine_DB {
    String name;
    String status;
    String volume;
    String volume_now;
    String food_level;
    String history;
    String notification;
    String timeno;
    String createdate;

    public Machine_DB() {
    }

    public Machine_DB(String name, String status, String volume, String volume_now, String food_level, String history, String notification, String timeno, String createdate) {
        this.name = name;
        this.status = status;
        this.volume = volume;
        this.volume_now = volume_now;
        this.food_level = food_level;
        this.history = history;
        this.notification = notification;
        this.timeno = timeno;
        this.createdate = createdate;
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

    public String getFood_level() {
        return food_level;
    }

    public void setFood_level(String food_level) {
        this.food_level = food_level;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getTimeno() {
        return timeno;
    }

    public void setTimeno(String timeno) {
        this.timeno = timeno;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}
