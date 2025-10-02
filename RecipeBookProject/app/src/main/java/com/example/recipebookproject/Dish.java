package com.example.recipebookproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// это модель данных
@Entity(tableName = "myDishes")
public class Dish {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String reference;
    int flagResource;

    public Dish(String name, String reference, int flagResource) {
        this.name = name;
        this.reference = reference;
        this.flagResource = flagResource;
    }

    public String getName() {
        return name;
    }

    public int getFlagResource() {
        return flagResource;
    }

    public String getReference() {
        return reference;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}