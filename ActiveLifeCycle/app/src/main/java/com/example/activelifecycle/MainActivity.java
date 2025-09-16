package com.example.activelifecycle;

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

        // 1 способ
        Log.d("Цикл","onCreate");
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 'Intent' - это обьект, в котором мы прописываем, какой 'activity' нам надо вызвать
                // первым пар-ом указываем класс, от которого уходим, а вторым куда приходим
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                // это будет означать, что этой 'activity' больше нет
                // finish(); // onDestroy
            }
        });

        ///////////////////// Cпособы создания нового 'activity': /////////////

        // 1 // java-layout-New-Layout Resource
        // но тогда в 'AndroidManifest' нужно будет прописать новую 'activity':

        // <activity
        // android:name=".SecondActivity"
        // android:exported="false" />

        // 2 // java-package-New-Activity-Login Views
        // здесь система сама пропишет новую 'activity' в 'AndroidManifest'
    }

    // когда мы вызываем другое 'activity' у нас вызываются методы по цепочке
    // 'onPause' -> 'onStop'

    // когда мы находимся в 'activity' и нажимаем кнопку 'назад'
    // 'onPause' -> 'onStop' -> 'onDestroy'

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Цикл","onStart");
    }

    // 'activity' полностью невидимое, здесь можно закрывать всякие сканеры,
    // закрывать БД, сохранять информацию
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Цикл","onStop");
    }

    // когда появляется другая 'activity' - здесь можно освобождать какие-то ресурсы
    // 'activity' по-прежнему остается видимой на экране
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

    // если пользователь решит к прежней 'activity'
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Цикл","onRestart");
    }

    // 'activity' полностью закрывается и завершает свою работу
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Цикл","onDestroy");
    }

}