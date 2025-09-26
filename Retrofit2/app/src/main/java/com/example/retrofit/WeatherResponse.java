package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    @SerializedName("main")
    Main main;

    @SerializedName("weather")
    List<Weather> weather;

    @SerializedName("wind")
    Wind wind;

    public class Main {
        // когда сериалайзер поймает в json параметр 'temp'
        // он его запишет сюда - в переменную 'temp'
        @SerializedName("temp")
        float temp;
        @SerializedName("feels_like")
        float feels_like;
    }

    public class Weather {
        // когда сериалайзер поймает в json параметр 'description'
        // он его запишет сюда - в переменную 'description'
        @SerializedName("description")
        String description;
    }

    public class Wind {
        // когда сериалайзер поймает в json параметр 'temp'
        // он его запишет сюда - в переменную 'temp'
        @SerializedName("speed")
        float speed;
    }
}
