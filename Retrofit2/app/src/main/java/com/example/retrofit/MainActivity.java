package com.example.retrofit;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText editTextCity;
    Button button;
    TextView textView;
    WeatherApi weatherApi;
    ImageView image;
    String API_KEY = "a7b76e3c606d48effb4e257c3ff96b01";

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

        editTextCity = findViewById(R.id.editTextCity);
        button = findViewById(R.id.buttonGenerate);
        textView = findViewById(R.id.textviewResult);
        image = findViewById(R.id.image);

        // мы создали 'Retrofit', который содержит базовый url
        // и способность преобразовывать json-данные с помощью
        // указанного конвертера 'gson'
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // в скобках указываем ему класс интерфейса
        weatherApi = retrofit.create(WeatherApi.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editTextCity.getText().toString();
                if (!city.isEmpty()) {
                    getWeather(city);
                }
            }
        });
    }

    // метод запроса погоды
    private void getWeather(String city) {
        Call<WeatherResponse> call = weatherApi.getWeather(city, API_KEY, "metric");

        // 'enqueue' - синхронный запрос
        call.enqueue(new Callback<WeatherResponse>() {

            // 'onResponse' - достучались
            // вызовется всегда, даже если запрос был неуспешным
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    String description = weather.weather.get(0).description;
                    String result = "Температура воздуха: "+weather.main.temp +"°C" +
                            ", ощущается как: "+weather.main.feels_like +
                            "°C. \n" + "Скорость ветра: " + weather.wind.speed + "м/с.\n" +
                            "Краткое описание: "+description;
                    textView.setText(result);
                    if (description.contains("clear")) {
                        image.setImageResource(R.drawable.ic_launcher_background);
                    } else if (description.contains("cloud")) {
                        image.setImageResource((R.drawable.ic_launcher_foreground));
                    }
                }else {
                    textView.setText("Ошибка "+response.message());
                }
            }

            // 'onFailure' - не достучались
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                textView.setText("Сбой "+t.getMessage());
            }
        });
    }
}