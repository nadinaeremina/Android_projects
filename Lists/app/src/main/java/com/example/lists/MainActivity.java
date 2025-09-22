package com.example.lists;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String[] countries = {"USA", "RUSSIA", "CHINA", "TURKEY", "Japan", "Canada",
            "USA", "RUSSIA", "CHINA", "TURKEY", "Japan", "Canada"};
    TextView textView;

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
        textView = findViewById(R.id.textView);

        // создаем адаптер для нашего списка
        // 1 - контекст (текущий), 2 - это готовый (встроенный layout), 3 - наш список
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countries);

        // устанавливаем наши страны для списка
        countriesList.setAdapter(adapter);

        countriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 1-наш listView, 2-нажатый виджет, 3-индекс нажатого виджета, 4-id
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SparseBooleanArray selected = countriesList.getCheckedItemPositions();

                // 1 // по позиции получаем выбранный элемент
                //String selectedItem = countries[position];
                
                String selectedItem = "";
                for (int i = 0; i < countries.length; i++) {
                    if (selected.get(i)) {
                        selectedItem += countries[i]+".";
                    }
                }
                
                // текст элемента вставляем в 'textView'
                textView.setText("Выбрано: " + selectedItem);
            }
        });
    }
}