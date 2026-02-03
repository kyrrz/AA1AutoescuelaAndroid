package com.example.aa1autoescuelaandroid.presenter;

import com.example.aa1autoescuelaandroid.api.AutoescuelaApi;
import com.example.aa1autoescuelaandroid.contract.DetailAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.model.DetailAutoescuelaModel;
import com.example.aa1autoescuelaandroid.view.DetailAutoescuelaView;

import java.util.List;

public class DetailAutoescuelaPresenter implements DetailAutoescuelaContract.Presenter, DetailAutoescuelaContract.Model.OnLoadListener, DetailAutoescuelaContract.Model.OnDeleteListener {

    private DetailAutoescuelaContract.View view;
    private DetailAutoescuelaContract.Model model;

    public  DetailAutoescuelaPresenter(DetailAutoescuelaContract.View view){
        this.view = view;
        this.model = new DetailAutoescuelaModel();
    }

    @Override
    public void loadAutoescuela(long id) {
        model.loadAutoescuela(id, this);
    }

    @Override
    public void deleteAutoescuela(long id) {
        model.deleteAutoescuela(id, this);
    }

    @Override
    public void onLoadSuccess(Autoescuela autoescuela) {
        view.showAutoescuela(autoescuela);
        view.showMessage("Cargado con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        view.onAutoescuelaDeleted();
    }

    @Override
    public void onDeleteError(String message) {

        view.showError(message);

    }
}
