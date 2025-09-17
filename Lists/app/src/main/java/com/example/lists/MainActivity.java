package com.example.lists;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String[] countries = {"USA", "RUSSIA", "CHINA", "TURKEY", "Japan", "Canada",
            "USA", "RUSSIA", "CHINA", "TURKEY", "Japan", "Canada"};

    ListView countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        countriesList = findViewById(R.id.countriesList);

        // создаем адаптер для нашего списка
        // 1 - контекст (текущий), 2 - это готовый (встроенный layout), 3 - наш список
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);

        // устанавливаем наши страны для списка
        countriesList.setAdapter(adapter);
    }
}