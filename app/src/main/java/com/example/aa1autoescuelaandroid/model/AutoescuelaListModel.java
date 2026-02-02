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

        getAutoescuelasCall.enqueue(new Callback<>() {
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

    @Override
    public void deleteAutoescuela(long id, OnDeleteListener listener) {
        AutoescuelaApiInterface autoescuelaApi = AutoescuelaApi.buildInstance();
        Call<Autoescuela> deleteAutoescuelasCall = autoescuelaApi.deleteAutoescuela(id);
        deleteAutoescuelasCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Autoescuela> call, Response<Autoescuela> response) {
                if (response.code() == 204){
                    listener.onDeleteSuccess("Autoescuela borrada");
                } else {
                    listener.onDeleteError("No se pudo borrar ");
                }
            }

            @Override
            public void onFailure(Call<Autoescuela> call, Throwable t) {
                listener.onDeleteError("No se ha podido conectar con el servidor");
            }
        });
    }
}

