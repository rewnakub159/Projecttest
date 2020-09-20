package com.example.project;

public class Machine_DB {
    String name;
    String amount_of_food;
    String food_now;
    String status;

    public Machine_DB() {
    }

    public Machine_DB(String name, String amount_of_food, String food_now, String status) {
        this.name = name;
        this.amount_of_food = amount_of_food;
        this.food_now = food_now;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount_of_food() {
        return amount_of_food;
    }

    public void setAmount_of_food(String amount_of_food) {
        this.amount_of_food = amount_of_food;
    }

    public String getFood_now() {
        return food_now;
    }

    public void setFood_now(String food_now) {
        this.food_now = food_now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
