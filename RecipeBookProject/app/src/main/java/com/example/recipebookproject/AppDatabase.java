package com.example.recipebookproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

@Database(entities = {Dish.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DishDao dishDao();
}
