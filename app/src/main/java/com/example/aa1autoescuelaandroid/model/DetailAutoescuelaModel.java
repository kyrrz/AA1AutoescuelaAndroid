package com.example.aa1autoescuelaandroid.model;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.AutoescuelaListContract;
import com.example.aa1autoescuelaandroid.contract.DetailAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAutoescuelaModel implements DetailAutoescuelaContract.Model {


    @Override
    public void loadAutoescuela(long id, DetailAutoescuelaContract.Model.OnLoadListener listener) {
        AutoescuelaApiInterface autoescuelaApi = AutoescuelaApi.buildInstance();
        Call<Autoescuela> getAutoescuelaCall = autoescuelaApi.getAutoescuela(id);
        getAutoescuelaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Autoescuela> call, Response<Autoescuela> response) {
                if (response.code() == 200){
                    Autoescuela autoescuela = response.body();
                    listener.onLoadSuccess(autoescuela);
                } else if (response.code() == 404) {
                    listener.onLoadError("Autoescuela not found");
                } else if(response.code() == 500) {
                    listener.onLoadError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<Autoescuela> call, Throwable t) {
                listener.onLoadError("No se ha podido conectar con el servidor");
            }
        });

    }
}
