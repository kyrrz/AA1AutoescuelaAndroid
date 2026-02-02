package com.example.aa1autoescuelaandroid.model;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.CochesAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CochesAutoescuelaModel implements CochesAutoescuelaContract.Model {
    @Override
    public void loadCoches(long autoescuelaId, OnLoadCochesListener listener) {
        AutoescuelaApiInterface autoescuelaApi = AutoescuelaApi.buildInstance();

        Call<List<Coche>> getCochesByAutoescuelasCall = autoescuelaApi.getCochesByAutoescuela(autoescuelaId);

        getCochesByAutoescuelasCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Coche>> call, Response<List<Coche>> response) {
                if (response.code() == 200){
                    List<Coche> coches = response.body();
                    listener.onSuccess(coches);
                }else if(response.code() == 500) {
                    listener.onError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<List<Coche>> call, Throwable t) {
                    listener.onError("No se ha podido conectar con el servidor");
            }
        });
    }
}
