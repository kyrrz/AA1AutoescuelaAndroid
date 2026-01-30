package com.example.aa1autoescuelaandroid.model;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.AutoescuelaListContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutoescuelaListModel implements AutoescuelaListContract.Model {
    @Override
    public void loadAutoescuelas(OnLoadListener listener) {
        AutoescuelaApiInterface autoescuelaApi = AutoescuelaApi.buildInstance();
        Call<List<Autoescuela>> getAutoescuelasCall = autoescuelaApi.getAutoescuelas();

        getAutoescuelasCall.enqueue(new Callback<List<Autoescuela>>() {
            @Override
            public void onResponse(Call<List<Autoescuela>> call, Response<List<Autoescuela>> response) {
                if(response.code() == 200) {
                    List<Autoescuela> autoescuelas = response.body();
                    listener.onLoadSuccess(autoescuelas);
                } else if(response.code() == 500) {
                    listener.onLoadError("Error interno del servidor");
                }

            }

            @Override
            public void onFailure(Call<List<Autoescuela>> call, Throwable t) {
                listener.onLoadError("No se ha podido conectar con el servidor");
            }
        });
    }
}

