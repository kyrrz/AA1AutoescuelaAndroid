package com.example.aa1autoescuelaandroid.presenter;

import com.example.aa1autoescuelaandroid.contract.AutoescuelaListContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.model.AutoescuelaListModel;

import java.util.List;

public class AutoescuelaListPresenter implements AutoescuelaListContract.Presenter, AutoescuelaListContract.Model.OnLoadListener, AutoescuelaListContract.Model.OnDeleteListener {

    private AutoescuelaListContract.Model model;
    private AutoescuelaListContract.View view;
    public AutoescuelaListPresenter(AutoescuelaListContract.View view){
        this.model = new AutoescuelaListModel();
        this.view = view;
    }
    @Override
    public void loadAutoescuelas() {
        model.loadAutoescuelas(this);
    }

    @Override
    public void deleteAutoescuela(long id) {
        model.deleteAutoescuela(id,this);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        loadAutoescuelas();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }

    @Override
    public void onLoadSuccess(List<Autoescuela> autoescuelas) {
        view.show(autoescuelas);
        view.showMessage("Cargado con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }


}
