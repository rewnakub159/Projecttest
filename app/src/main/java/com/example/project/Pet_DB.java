package com.example.project;

public class Pet_DB {
    String petnumber;
    String petname;
    String breed;
    String gender;
    String weight;
    String type;
    String age;
    String id;
    String tagstatus;

    public Pet_DB() {
    }

    public Pet_DB(String petnumber, String petname, String breed, String gender, String weight, String type, String age, String id, String tagstatus) {
        this.petnumber = petnumber;
        this.petname = petname;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
        this.type = type;
        this.age = age;
        this.id = id;
        this.tagstatus = tagstatus;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagstatus() {
        return tagstatus;
    }

    public void setTagstatus(String tagstatus) {
        this.tagstatus = tagstatus;
    }
}

