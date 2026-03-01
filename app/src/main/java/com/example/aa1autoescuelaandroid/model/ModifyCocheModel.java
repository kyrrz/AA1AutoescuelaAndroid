package com.example.aa1autoescuelaandroid.model;

import static com.example.aa1autoescuelaandroid.util.CocheMapper.toEntity;

import android.content.Context;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.api.AutoescuelaApiInterface;
import com.example.aa1autoescuelaandroid.contract.ModifyCocheContract;
import com.example.aa1autoescuelaandroid.db.AppDatabase;
import com.example.aa1autoescuelaandroid.db.DatabaseUtil;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyCocheModel implements ModifyCocheContract.Model {
    private AppDatabase db;

    public ModifyCocheModel(Context context){
        db = DatabaseUtil.getDb(context);
    }

    @Override
    public void modifyCoche(long cocheId, Coche coche, onModifyListener listener) {
        AutoescuelaApiInterface api = AutoescuelaApi.buildInstance();
        Call<Coche> putCocheCall = api.modifyCoche(cocheId, coche);

        putCocheCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Coche> call, Response<Coche> response) {
                if(response.code() == 200) {
                    listener.onModifySuccess(response.body());
                    new Thread(() -> {
                        db.cocheDao().update(toEntity(response.body()));
                    }).start();
                } else if (response.code() == 400) {
                    listener.onModifyError("Errores de validación");
                } else if (response.code() == 404) {
                    listener.onModifyError("Coche no encontrado");
                }
            }

            @Override
            public void onFailure(Call<Coche> call, Throwable t) {
                listener.onModifyError("No se ha podido conectar con el servidor");
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

    @Override
    public void loadCoche(long cocheId, OnLoadCocheListener listener) {
        AutoescuelaApiInterface api = AutoescuelaApi.buildInstance();
        Call<Coche> getCocheCall = api.getCoche(cocheId);

        getCocheCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Coche> call, Response<Coche> response) {
                if(response.code() == 200) {
                    listener.onSuccess(response.body());
                } else if(response.code() == 404) {
                    listener.onError("Coche no encontrado");
                } else if(response.code() == 500) {
                    listener.onError("Error interno del servidor");
                }
            }

            @Override
            public void onFailure(Call<Coche> call, Throwable t) {
                listener.onError("No se ha podido conectar con el servidor");
            }
        });
    }
}