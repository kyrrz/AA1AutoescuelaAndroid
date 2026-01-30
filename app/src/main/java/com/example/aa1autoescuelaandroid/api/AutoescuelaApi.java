package com.example.aa1autoescuelaandroid.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutoescuelaApi {
    public static AutoescuelaApiInterface buildInstance() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(AutoescuelaApiInterface.class);
    }
}