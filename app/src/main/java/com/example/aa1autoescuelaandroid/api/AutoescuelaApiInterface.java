package com.example.aa1autoescuelaandroid.api;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AutoescuelaApiInterface {

    @GET("autoescuelas")
    Call<List<Autoescuela>> getAutoescuelas();

    @GET("autoescuelas/{id}")
    Call<Autoescuela> getAutoescuela(@Path("id") long id);

    @POST("autoescuelas")
    Call<Autoescuela> registerAutoescuela(@Body Autoescuela autoescuela);

    @PUT("autoescuelas/{id}")
    Call<Autoescuela> modifyAutoescuela(@Path("id") long id, @Body Autoescuela autoescuela);

    @DELETE("autoescuelas/{id}")
    Call<Autoescuela> deleteAutoescuela(@Path("id") long id);


    @GET("coches")
    Call<List<Coche>> getCoches();

    @GET("coches/{id}")
    Call<Coche> getCoche(@Path("id") long id);

    @POST("coches")
    Call<Coche> registerCoche(@Body Coche coche);

    @PUT("coches/{id}")
    Call<Coche> modifyCoche(@Path("id") long id, @Body Coche coche);

    @DELETE("coches/{id}")
    Call<Coche> deleteCoche(@Path("id") long id);
}