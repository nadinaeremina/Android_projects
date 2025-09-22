package com.example.lists2;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<>();
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
        setInitialDate();
        recyclerView = findViewById(R.id.list);
        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(State state, int position) {
                // 'getApplicationContext' - запрашиваем контекст всего приложения
                Toast.makeText(getApplicationContext(),
                        "Был выбран пункт " + state.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        // при создании адаптера ему передается определенный слушатель
        StateAdapter adapter = new StateAdapter(this, states, stateClickListener);
        // вставляем адаптер в 'recyclerView'
        recyclerView.setAdapter(adapter);
    }

    public void setInitialDate() {
        states.add(new State("Brazil", "Brazilia", R.drawable.ic_launcher_background));
        states.add(new State("Russia", "Msk", R.drawable.ic_launcher_background));
        states.add(new State("USA", "NY", R.drawable.ic_launcher_background));
        states.add(new State("Turkey", "Kemer", R.drawable.ic_launcher_background));
        states.add(new State("Japan", "Osaka", R.drawable.ic_launcher_background));
        states.add(new State("China", "Pekin", R.drawable.ic_launcher_background));
        states.add(new State("Norway", "Oslo", R.drawable.ic_launcher_background));
        states.add(new State("Spain", "Madrid", R.drawable.ic_launcher_background));
        states.add(new State("Belarus", "Minsk", R.drawable.ic_launcher_background));
        states.add(new State("France", "Paris", R.drawable.ic_launcher_background));
        states.add(new State("Ukraine", "Kiev", R.drawable.ic_launcher_background));
        states.add(new State("Uzbekistan", "Tashkent", R.drawable.ic_launcher_background));
        states.add(new State("Germany", "Berlin", R.drawable.ic_launcher_background));
        states.add(new State("UK", "London", R.drawable.ic_launcher_background));
    }
}