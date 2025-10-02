package com.example.recipebookproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TwoActivity extends AppCompatActivity {

    String[] categories = {"Salads", "Snacks", "Soups", "Main courses", "Desserts"};
    TextView textView;
    Button show;
    ListView categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        categoriesList = findViewById(R.id.categoriesList);
        textView = findViewById(R.id.textView);
        show = findViewById(R.id.show);

        // создаем адаптер для нашего списка
        // 1 - контекст (текущий), 2 - это готовый (встроенный layout), 3 - наш список
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categories);

        // устанавливаем наши страны для списка
        categoriesList.setAdapter(adapter);

        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // 1-наш listView, 2-нажатый виджет, 3-индекс нажатого виджета, 4-id
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //SparseBooleanArray selected = categoriesList.getCheckedItemPositions();

                // 1 // по позиции получаем выбранный элемент
                String selectedItem = categories[position];

                // текст элемента вставляем в 'textView'
                textView.setText(selectedItem);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoActivity.this, ThirdActivity.class);
                CharSequence selectedCategory = textView.getText();
                intent.putExtra("SelectedCategory", selectedCategory);
                startActivity(intent);
            }
        });
    }
}