package com.example.deepseek;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeepSeekAPI {
    @Headers({"Content-Type: application/json","Authorization: Bearer sk-eb3954f44d2744d6ab2b3fa06f801e62"})
    // тк мы отправляем сообщение - post-запрос обязательно с телом
    @POST("v1/chat/completions")
    Call<ChatResponse> sendMessage(@Body ChatRequest request);
}
