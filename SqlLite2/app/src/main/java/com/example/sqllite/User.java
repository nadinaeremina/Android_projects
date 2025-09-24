package com.example.sqllite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// это модель данных
@Entity(tableName = "mytable")
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String lastName;
    String email;
    String phone;
}