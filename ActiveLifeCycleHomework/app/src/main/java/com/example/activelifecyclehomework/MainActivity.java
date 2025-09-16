package com.example.activelifecyclehomework;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Log.d("Цикл", "onCreate");
        button = findViewById(R.id.button);
        // При нажатии запускается TwoActivity через явный интент
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Цикл","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Цикл","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Цикл","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Цикл","onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Цикл","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Цикл","onDestroy");
    }
}