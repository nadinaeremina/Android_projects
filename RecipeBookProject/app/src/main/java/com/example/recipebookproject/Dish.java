package com.example.recipebookproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// это модель данных
@Entity(tableName = "myDishes")
public class Dish {
    @PrimaryKey(autoGenerate = true)
    int id;
    String category;
    String name;
    int flagResource;
}
