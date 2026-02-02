package com.example.aa1autoescuelaandroid.presenter;

import com.example.aa1autoescuelaandroid.contract.AutoescuelaListContract;
import com.example.aa1autoescuelaandroid.contract.CocheListContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.model.AutoescuelaListModel;
import com.example.aa1autoescuelaandroid.model.CocheListModel;

import java.util.List;

public class CocheListPresenter implements CocheListContract.Presenter, CocheListContract.Model.OnLoadListener, CocheListContract.Model.OnDeleteListener {

    private CocheListContract.Model model;
    private CocheListContract.View view;
    public CocheListPresenter(CocheListContract.View view){
        this.model = new CocheListModel();
        this.view = view;
    }
    @Override
    public void loadCoches() {
        model.loadCoches(this);
    }

    @Override
    public void deleteCoche(long id) {
        model.deleteCoche(id,this);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        loadCoches();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }

    @Override
    public void onLoadSuccess(List<Coche> coches) {
        view.show(coches);
        view.showMessage("Cargado con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }


}
