package com.example.activelifecycle;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    // это первый метод, с которого начинается выполнение 'activity'
    // он обязателен, в нем происходит начальная настройка 'activity'
    // в частности создаются обьекты визуального интерфейса
    // 'Bundle' - содержит прежнее состояние 'activity', если оно было сохранено
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}