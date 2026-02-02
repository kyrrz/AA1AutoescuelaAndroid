package com.example.aa1autoescuelaandroid.presenter;

import com.example.aa1autoescuelaandroid.contract.RegisterAutoescuelaContract;
import com.example.aa1autoescuelaandroid.contract.RegisterCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.model.RegisterCocheModel;

import java.time.LocalDate;
import java.util.List;

public class RegisterCochePresenter implements RegisterCocheContract.Presenter, RegisterCocheContract.Model.onRegisterListener {

    private final RegisterCocheContract.View view;
    private final RegisterCocheContract.Model model;

    public RegisterCochePresenter(RegisterCocheContract.View view) {
        this.view = view;
        this.model = new RegisterCocheModel();
    }

    @Override
    public void loadAutoescuelas() {
        model.loadAutoescuelas(new RegisterCocheContract.Model.OnAutoescuelasListener() {
            @Override
            public void onSuccess(List<Autoescuela> autoescuelas) {
                view.showAutoescuelas(autoescuelas);
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void registerCoche(String matricula, String marca, String modelo, String tipoCambio, int kilometraje, LocalDate fechaCompra,
                              float precioCompra, boolean disponible, long autoescuelaId) {
        if (marca.isBlank()) {
            view.showValidationError("La marca es un campo obligatiorio");
        }
        Coche coche = Coche.builder().matricula(matricula)
                .marca(marca)
                .modelo(modelo)
                .tipoCambio(tipoCambio)
                .kilometraje(kilometraje)
                .fechaCompra(fechaCompra)
                .precioCompra(precioCompra)
                .disponible(disponible)
                .autoescuelaId(autoescuelaId)
                .build();

        model.registerCoche(coche, this);

    }

    @Override
    public void onRegisterSuccess(Coche coche) {
        view.showMessage("Registrado correctamente");
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }
}




