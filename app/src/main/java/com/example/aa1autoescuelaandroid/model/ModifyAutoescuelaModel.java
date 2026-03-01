package com.example.aa1autoescuelaandroid.model;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.ModifyAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyAutoescuelaModel implements ModifyAutoescuelaContract.Model {

    @Override
    public void modifyAutoescuela(long autoescuelaId, Autoescuela autoescuela, onModifyListener listener) {
        AutoescuelaApiInterface api = AutoescuelaApi.buildInstance();
        Call<Autoescuela> putAutoescuelaCall = api.modifyAutoescuela(autoescuelaId, autoescuela);

        putAutoescuelaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Autoescuela> call, Response<Autoescuela> response) {
                if(response.code() == 200) {
                    listener.onModifySuccess(response.body());
                } else if (response.code() == 400) {
                    listener.onModifyError("Errores de validación");
                } else if (response.code() == 404) {
                    listener.onModifyError("Autoescuela no encontrada");
                }
            }

            @Override
            public void onFailure(Call<Autoescuela> call, Throwable t) {
                listener.onModifyError("No se ha podido conectar con el servidor");
            }
        });
    }

    @Override
    public void loadAutoescuela(long autoescuelaId, OnLoadAutoescuelaListener listener) {
        AutoescuelaApiInterface api = AutoescuelaApi.buildInstance();
        Call<Autoescuela> getAutoescuelaCall = api.getAutoescuela(autoescuelaId);

        getAutoescuelaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Autoescuela> call, Response<Autoescuela> response) {
                if(response.code() == 200) {
                    listener.onSuccess(response.body());
                } else if(response.code() == 404) {
                    listener.onError("Autoescuela no encontrada");
                } else if(response.code() == 500) {
                    listener.onError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<Autoescuela> call, Throwable t) {
                listener.onError("No se ha podido conectar con el servidor");
            }
        });
    }
}