package com.example.project;

public class SetTime_Db {
    String name;
  String amountfood;
  String settime;

    public SetTime_Db() {
    }

    public SetTime_Db(String name, String amountfood, String settime) {
        this.name = name;
        this.amountfood = amountfood;
        this.settime = settime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountfood() {
        return amountfood;
    }

    public void setAmountfood(String amountfood) {
        this.amountfood = amountfood;
    }

    public String getSettime() {
        return settime;
    }

    public void setSettime(String settime) {
        this.settime = settime;
    }
}
