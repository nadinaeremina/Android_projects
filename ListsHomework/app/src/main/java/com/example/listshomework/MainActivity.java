package com.example.listshomework;

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

    String[] elements = {"Элемент 1", "Элемент 2", "Элемент 3", "Элемент 4", "Элемент 5",
            "Элемент 6", "Элемент 7", "Элемент 8", "Элемент 9", "Элемент 10", "Элемент 11",
            "Элемент 12", "Элемент 13", "Элемент 14", "Элемент 15", "Элемент 16", "Элемент 17",
            "Элемент 18", "Элемент 19", "Элемент 20"};
    TextView textView;
    ListView elementsList;

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

        elementsList = findViewById(R.id.elementsList);
        textView = findViewById(R.id.textView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, elements);

        elementsList.setAdapter(adapter);
        elementsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = elements[position];
                textView.setText("Вы нажали: " + selectedItem);
            }
        });
    }
}