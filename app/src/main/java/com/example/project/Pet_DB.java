package com.example.project;

public class Pet_DB {
    String petnumber;
    String petname;
    String breed;
    String gender;
    String birthday;
    String type;

    public Pet_DB() {
    }

    public Pet_DB(String petnumber, String petname, String breed, String gender, String birthday, String type) {
        this.petnumber = petnumber;
        this.petname = petname;
        this.breed = breed;
        this.gender = gender;
        this.birthday = birthday;
        this.type = type;
    }

    public String getPetnumber() {
        return petnumber;
    }

    public void setPetnumber(String petnumber) {
        this.petnumber = petnumber;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

