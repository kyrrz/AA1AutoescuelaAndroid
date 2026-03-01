package com.example.aa1autoescuelaandroid.model;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.DetailCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCocheModel implements DetailCocheContract.Model {
    @Override
    public void loadCoche(long id, OnLoadListener listener) {
        AutoescuelaApiInterface autoescuelaApi = AutoescuelaApi.buildInstance();
        Call<Coche> getCocheCall = autoescuelaApi.getCoche(id);
        getCocheCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Coche> call, Response<Coche> response) {
                if (response.code() == 200){
                    Coche coche = response.body();
                    listener.onLoadSuccess(coche);
                } else if (response.code() == 404) {
                    listener.onLoadError("Coche not found");
                } else if(response.code() == 500) {
                    listener.onLoadError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<Coche> call, Throwable t) {
                listener.onLoadError("No se ha podido conectar con el servidor");
            }
        });
    }

    @Override
    public void deleteCoche(long id, OnDeleteListener listener) {
        AutoescuelaApiInterface autoescuelaApi = AutoescuelaApi.buildInstance();
        Call<Coche> deleteCocheCall = autoescuelaApi.deleteCoche(id);
        deleteCocheCall.enqueue(new Callback<Coche>() {
            @Override
            public void onResponse(Call<Coche> call, Response<Coche> response) {
                if(response.code() == 204){
                    listener.onDeleteSuccess("Coche borrado");
                } else {
                    listener.onDeleteError("Error borrando coche");
                }
            }

            @Override
            public void onFailure(Call<Coche> call, Throwable t) {
                listener.onDeleteError("No se ha podido conectar con el servidor");
            }
        });
    }
}
