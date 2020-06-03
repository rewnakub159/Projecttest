package com.example.project;

class dbpet11 {
    String petname;
    String species;
    String breed;
    String birtday;

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirtday() {
        return birtday;
    }

    public void setBirtday(String birtday) {
        this.birtday = birtday;
    }

    public dbpet11() {
    }

    public dbpet11(String petname, String species, String breed, String birtday) {
        this.petname = petname;
        this.species = species;
        this.breed = breed;
        this.birtday = birtday;
    }
}
