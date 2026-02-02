package com.example.aa1autoescuelaandroid.model;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.RegisterCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCocheModel implements RegisterCocheContract.Model {
    @Override
    public void registerCoche(Coche coche, onRegisterListener listener) {
        AutoescuelaApiInterface api = AutoescuelaApi.buildInstance();
        Call<Coche> postCocheCall = api.registerCoche(coche);
        postCocheCall.enqueue(new Callback<Coche>() {
            @Override
            public void onResponse(Call<Coche> call, Response<Coche> response) {
                if(response.code() == 201) {
                    listener.onRegisterSuccess(response.body());
                } else if (response.code() == 400) {
                    listener.onRegisterError("Errores de validacion");

                }
            }

            @Override
            public void onFailure(Call<Coche> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar con el servidor");
            }
        });
    }

    @Override
    public void loadAutoescuelas(OnAutoescuelasListener listener) {
        AutoescuelaApiInterface autoescuelaApi = AutoescuelaApi.buildInstance();
        Call<List<Autoescuela>> getAutoescuelasCall = autoescuelaApi.getAutoescuelas();

        getAutoescuelasCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Autoescuela>> call, Response<List<Autoescuela>> response) {
                if(response.code() == 200) {
                    List<Autoescuela> autoescuelas = response.body();
                    listener.onSuccess(autoescuelas);
                } else if(response.code() == 500) {
                    listener.onError("Error interno del servidor");
                }

            }

            @Override
            public void onFailure(Call<List<Autoescuela>> call, Throwable t) {
                listener.onError("No se ha podido conectar con el servidor");
            }
        });
    }
}
