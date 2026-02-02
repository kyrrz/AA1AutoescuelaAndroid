package com.example.aa1autoescuelaandroid.presenter;

import com.example.aa1autoescuelaandroid.contract.CochesAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.model.CochesAutoescuelaModel;

import java.util.List;

public class CochesAutoescuelaPresenter implements CochesAutoescuelaContract.Presenter, CochesAutoescuelaContract.Model.OnLoadCochesListener {
    private CochesAutoescuelaContract.Model model;
    private CochesAutoescuelaContract.View view;
    public CochesAutoescuelaPresenter(CochesAutoescuelaContract.View view){
        this.model = new CochesAutoescuelaModel();
        this.view = view;
    }
    @Override
    public void loadCoches(long autoescuelaId) {
        model.loadCoches(autoescuelaId, this);
    }

    @Override
    public void onSuccess(List<Coche> coches) {
        view.showCoches(coches);
        view.showMessage("Cargado con éxito");
    }

    @Override
    public void onError(String message) {view.showError(message);

    }
}
