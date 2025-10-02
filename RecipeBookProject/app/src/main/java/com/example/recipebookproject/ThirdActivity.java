package com.example.recipebookproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    ArrayList<Dish> dishes = new ArrayList<>();
    RecyclerView recyclerView;
    String[] categories = {"Salads", "Snacks", "Soups", "Main courses", "Desserts"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        // Получаем строку
        String message = (intent.getStringExtra("SelectedCategory")).toString();
        setInitialDish(message);

        DishAdapter.OnStateClickListener stateClickListener = new DishAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Dish dish, int position) {
                Toast.makeText(getApplicationContext(),
                        "Был выбран пункт " + dish.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        DishAdapter adapter = new DishAdapter(this, dishes, stateClickListener);
        recyclerView.setAdapter(adapter);
    }

    public void setInitialDish(String message) {
        if (message.equals(categories[0])) {
            dishes.add(new Dish("Caesar", "https://www.russianfood.com/recipes/bytype/?fid=721", R.drawable.ic_launcher_foreground));
        } else if (message.equals(categories[1])) {
            dishes.add(new Dish("rolls", "https://www.russianfood.com/recipes/bytype/?fid=721", R.drawable.ic_launcher_foreground));
        } else if (message.equals(categories[2])) {
            dishes.add(new Dish("Borsch", "https://www.russianfood.com/recipes/bytype/?fid=721", R.drawable.ic_launcher_foreground));
        } else if (message.equals(categories[3])) {
            dishes.add(new Dish("Turkey", "https://www.russianfood.com/recipes/bytype/?fid=721", R.drawable.ic_launcher_foreground));
        } else if (message.equals(categories[4])) {
            dishes.add(new Dish("Cheesecake", "https://www.russianfood.com/recipes/bytype/?fid=721", R.drawable.ic_launcher_foreground));
        }
    }
}