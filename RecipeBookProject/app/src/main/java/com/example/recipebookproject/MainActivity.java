package com.example.recipebookproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    Button welcomeButton;
    String LOG="SQL";
    AppDatabase db;
    DishDao dishDao;
    String[] categories = {"Salads", "Snacks", "Soups", "Main courses", "Desserts"};

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
        welcomeButton = findViewById(R.id.welcomeButton);

        // 1
        Dish dish1 = new Dish();
        dish1.category = categories[0];
        dish1.name = "Caesar";
        dish1.flagResource = 1;

        long id1 = dishDao.insert(dish1);

        // 2
        Dish dish2 = new Dish();
        dish2.category = categories[1];
        dish2.name = "rolls";
        dish2.flagResource = 2;

        long id2 = dishDao.insert(dish2);

        // 3
        Dish dish3 = new Dish();
        dish3.category = categories[2];
        dish3.name = "Borsch";
        dish3.flagResource = 3;

        long id3 = dishDao.insert(dish3);

        // 4
        Dish dish4 = new Dish();
        dish4.category = categories[3];
        dish4.name = "Turkey";
        dish4.flagResource = 4;

        long id4 = dishDao.insert(dish4);

        // 5
        Dish dish5 = new Dish();
        dish5.category = categories[4];
        dish5.name = "Cheesecake";
        dish5.flagResource = 5;

        long id5 = dishDao.insert(dish5);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class,"myDb")
                .allowMainThreadQueries().build();


        dishDao = db.dishDao();
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Подтверждение");
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setCancelable(false);
                builder.setMessage("Вы уверены, что хотите перейти с списку рецептов?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}