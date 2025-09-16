package com.example.activelifecyclehomework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TwoActivity extends AppCompatActivity {

    Button button_browser;
    Button button_go_back;

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

        button_browser = findViewById(R.id.button_browser);
        button_go_back = findViewById(R.id.button_go_back);

        Log.d("Цикл", "onCreate");
        // запускает неявный интент для открытия сайта
        button_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent implictIntent = new Intent(Intent.ACTION_VIEW);
                implictIntent.setData(Uri.parse("https://developer.android.com"));
                startActivity(implictIntent);
            }
        });

        Log.d("Цикл", "onFinish");
        // закрывает активити
        button_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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