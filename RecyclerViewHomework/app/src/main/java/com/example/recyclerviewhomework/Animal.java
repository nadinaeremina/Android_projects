package com.example.recyclerviewhomework;

public class Animal {
    String species;
    String nickname;
    int flagResource;

    public Animal(String species, String nickname, int flagResource) {
        this.species = species;
        this.nickname = nickname;
        this.flagResource = flagResource;
    }

    public int getFlagResource() {
        return flagResource;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}
