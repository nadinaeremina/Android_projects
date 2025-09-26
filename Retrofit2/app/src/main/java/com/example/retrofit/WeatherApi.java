package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// в интерфейсе описывается команда запроса для сервера
public interface WeatherApi {
    // на основании запроса:
    // https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String city,
                                     @Query("appid")String apiKey,
                                     @Query("units")String units);
}
