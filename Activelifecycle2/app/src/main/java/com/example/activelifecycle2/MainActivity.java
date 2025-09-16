package com.example.activelifecycle2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText;
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
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /////////////////////////////////////// 1 //////////////////////////////////////////
                // будем открывать браузер - 'Intent.ACTION_VIEW' - открыть новую активити
                // здесь мы говорим - ищи мне 'activity', в которой мы можем что-то посмотреть

                // Intent implictIntent = new Intent(Intent.ACTION_VIEW);

                // далее должны передать какое-то значение
                // класс 'Uri' работает с ссылками
                // здесь говорим, что конкретно нам нужно посмотреть

                // implictIntent.setData(Uri.parse("https://www.google.com"));
                // startActivity(implictIntent);

                // это будет означать, что этой 'activity' больше нет
                // finish(); // onDestroy

                /////////////////////////////////////// 2 //////////////////////////////////////////
                // отправить сообщение на почту
                // Intent implictIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("malito:example@email.com"));
                // implictIntent.putExtra(Intent.EXTRA_SUBJECT,"Тема письма");
                // implictIntent.putExtra(Intent.EXTRA_TEXT,"Текст письма");
                // PackageManager pm = getPackageManager();
                // if (pm.queryIntentActivities(implictIntent,0).size()>0) {
                    // startActivity(Intent.createChooser(implictIntent,"Выберите почтовый клиент"));
                // }else {
                    // Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                // }

                ////////////////////////////////////// 3 ///////////////////////////////////////////
                // отправить текст на новую активити
                // можно передавать числа, текст, массивы
                String string = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
                // первым пар-ом всегда идет ключ, а вторым - строка
                intent.putExtra("text", string);
                startActivity(intent);
            }
        });
    }

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