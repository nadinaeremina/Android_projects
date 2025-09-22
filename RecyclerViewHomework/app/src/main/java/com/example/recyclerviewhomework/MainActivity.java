package com.example.recyclerviewhomework;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Animal> animals = new ArrayList<>();
    RecyclerView recyclerView;

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

        setInitialAnimal();
        recyclerView = findViewById(R.id.list);

        AnimalAdapter.OnStateClickListener stateClickListener = new AnimalAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(Animal animal, int position) {
                Toast.makeText(getApplicationContext(),
                        "Был выбран пункт " + animal.getSpecies(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        AnimalAdapter adapter = new AnimalAdapter(this, animals, stateClickListener);
        recyclerView.setAdapter(adapter);
    }

    public void setInitialAnimal() {
        animals.add(new Animal("Кот", "Барсик", R.drawable.ic_launcher_background));
        animals.add(new Animal("Собака", "Рекс", R.drawable.ic_launcher_background));
        animals.add(new Animal("Лев", "Бомбей", R.drawable.ic_launcher_background));
        animals.add(new Animal("Тигр", "Гранд", R.drawable.ic_launcher_background));
        animals.add(new Animal("Слон", "Мика", R.drawable.ic_launcher_background));
    }
}