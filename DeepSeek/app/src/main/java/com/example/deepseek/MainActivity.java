package com.example.deepseek;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText messageInput;
    Button sendButton;
    TextView responseOutput;

    DeepSeekAPI deepSeekAPI;

    String systemPrompt="Ты будешь отвечать в стиле Иван Васильевича из советского фильма";
    double temp=0.7;
    int max_tokens=1000;

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

        messageInput = findViewById(R.id.message_input);
        sendButton = findViewById(R.id.send_button);
        responseOutput = findViewById(R.id.response_output);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        // нам нужно, чтобы присылали тело
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                // по умолчанию - 10 секунд, поэтому выставим - 60
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deepseek.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        deepSeekAPI = retrofit.create(DeepSeekAPI.class);

        sendButton.setOnClickListener(v->{
            String userInput = messageInput.getText().toString().trim();
            if (!userInput.isEmpty()) {
                sendMessageToDeepSeek(userInput);
            }
        });
    }

    private void sendMessageToDeepSeek(String userInput) {

        responseOutput.setText("Загрузка");
        List<ChatRequest.Message> messages = new ArrayList<>();
        messages.add(new ChatRequest.Message("system",systemPrompt));
        messages.add(new ChatRequest.Message("user",userInput));
        ChatRequest request = new ChatRequest("deepseek-chat",messages,temp,max_tokens);

        Call<ChatResponse> call = deepSeekAPI.sendMessage(request);
        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String reply = response.body().choices.get(0).message.content;
                    responseOutput.setText(reply);
                    messageInput.setText("");
                }else {
                    responseOutput.setText("Ошибка "+response.code());
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Log.d("GPT","Ошибка подключения");
                responseOutput.setText("Ошибка подключения "+t.getMessage());
            }
        });
    }
}