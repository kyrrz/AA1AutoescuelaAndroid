package com.example.aa1autoescuelaandroid.presenter;

import com.example.aa1autoescuelaandroid.contract.CochesAutoescuelaContract;
import com.example.aa1autoescuelaandroid.contract.DetailCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.model.DetailCocheModel;

public class DetailCochePresenter implements DetailCocheContract.Presenter, DetailCocheContract.Model.OnDeleteListener, DetailCocheContract.Model.OnLoadListener {
    private DetailCocheContract.Model model;
    private DetailCocheContract.View view;

    public DetailCochePresenter(DetailCocheContract.View view){
        this.view = view;
        this.model = new DetailCocheModel();
    }
    @Override
    public void loadCoche(long id) {
        model.loadCoche(id, this);
    }

    @Override
    public void deleteCoche(long id) {
        model.deleteCoche(id, this);
    }

    @Override
    public void onLoadSuccess(Coche coche) {
        view.showCoche(coche);
        view.showMessage("Cargado con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        view.onCocheDeleted();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }
}
