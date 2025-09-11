package com.example.testingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

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
        textView = findViewById(R.id.text); // обращаемся к: android:id="@+id/text"
        button = findViewById(R.id.button); // обращаемся к: android:id="@+id/button"

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // здесь пишем все, что будет происходить при нажатии на эту кнопку
                // например: поменяем текст
                textView.setText("Приложение");

                // добавим всплывающее сообщение
                Toast.makeText(MainActivity.this, "Это всплывающее сообщение",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}