package com.example.recipebookproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// в этом интерфейсе мы будем описывать методы для работы с БД
@Dao
public interface DishDao {
    // для вставки нашего пользователя
    @Insert
    long insert(Dish dish);

    // получение списка сотрудников
    @Query("SELECT * FROM myDishes")
    List<Dish> getAll();

    // очищаем данные из таблицы
    @Query("DELETE FROM myDishes")
    int clearAll();

    // удаляем полностью таблицу
    // DROP TABLE mytable

    // сбрасываем счетчик
    // можно использовать, если есть хотя бы одна таблица с автоинкрементом
    // @PrimaryKey(autoGenerate = true)
    @Query("DELETE FROM sqlite_sequence WHERE name = 'myDishes'")
    void resetAutoIncrement();
}
