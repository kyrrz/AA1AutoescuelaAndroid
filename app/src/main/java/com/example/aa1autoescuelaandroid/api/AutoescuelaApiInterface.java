package com.example.aa1autoescuelaandroid.api;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AutoescuelaApiInterface {

    @GET("autoescuelas")
    Call<List<Autoescuela>> getAutoescuelas();

    @POST("autoescuelas")
    Call<Autoescuela> registerAutoescuela(@Body Autoescuela autoescuela);

    @GET("autoescuelas/{id}")
    Call<Autoescuela> getAutoescuela(@Path("id") long id);
}