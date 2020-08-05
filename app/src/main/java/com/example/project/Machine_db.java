package com.example.project;

public class Machine_db {
    String name;
    String amount_of_food;

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

    public Machine_db(String name, String amount_of_food) {
        this.name = name;
        this.amount_of_food = amount_of_food;
    }
}
