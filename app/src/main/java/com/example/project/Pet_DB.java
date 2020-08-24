package com.example.project;

public class Pet_DB {
    String name;
    String age;
    String breed;
    String gender;
    String weigth;
    String type;

    public Pet_DB() {
    }

    public Pet_DB(String name, String age, String breed, String gender, String weigth, String type) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.weigth = weigth;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeigth() {
        return weigth;
    }

    public void setWeigth(String weigth) {
        this.weigth = weigth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

