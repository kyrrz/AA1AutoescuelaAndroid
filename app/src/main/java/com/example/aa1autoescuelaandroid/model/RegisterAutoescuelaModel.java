package com.example.aa1autoescuelaandroid.model;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.RegisterAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAutoescuelaModel  implements RegisterAutoescuelaContract.Model {
    @Override
    public void registerAutoescuela(Autoescuela autoescuela, onRegisterListener listener) {
        AutoescuelaApiInterface api = AutoescuelaApi.buildInstance();
        Call<Autoescuela> postAutoescuelaCall = api.registerAutoescuela(autoescuela);
        postAutoescuelaCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Autoescuela> call, Response<Autoescuela> response) {
                if(response.code() == 201) {
                    listener.onRegisterSuccess(response.body());
                } else if (response.code() == 400) {
                    listener.onRegisterError("Errores de validacion");

                }
            }

            @Override
            public void onFailure(Call<Autoescuela> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar con el servidor");
            }
        });
    }
}
