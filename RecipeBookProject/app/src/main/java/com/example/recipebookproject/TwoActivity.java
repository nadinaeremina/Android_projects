package com.example.recipebookproject;

import android.content.DialogInterface;
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

public class TwoActivity extends AppCompatActivity {

    Button gobackButton;
    Button gotoButton;
    String[] categories = {"Salads", "Snacks", "Soups", "Main courses", "Desserts"};
    ListView categoriesList;
    TextView textView;
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
        gobackButton = findViewById(R.id.gobackButton);
        gotoButton = findViewById(R.id.gotoButton);
        categoriesList = findViewById(R.id.categoriesList);
        textView = findViewById(R.id.textView);
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
                textView.setText("Выбрано: "+selectedItem);
            }
        });
        gobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //класс билдера для создания диалога
                AlertDialog.Builder builder = new AlertDialog.Builder(TwoActivity.this);
                //заголовок диалога
                builder.setTitle("Подтверждение");
                //иконка
                builder.setIcon(R.drawable.ic_launcher_background);
                //метод запрета на сворачивание диалога (опционально)
                builder.setCancelable(false);
                //сообщение пользователю
                builder.setMessage("Вы уверены что хотите выйти?");

                // 1
                //кнопка согласия пользователя
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                // 2
                //кнопка отрицания пользователя
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // закрытие диалога
                        dialogInterface.dismiss();
                    }
                });

                //создание самого диалога и вывод на экран
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        gotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}