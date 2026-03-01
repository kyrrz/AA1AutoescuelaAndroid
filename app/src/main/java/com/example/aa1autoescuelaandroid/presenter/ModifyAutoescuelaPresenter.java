package com.example.aa1autoescuelaandroid.presenter;

import com.example.aa1autoescuelaandroid.contract.ModifyAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.model.ModifyAutoescuelaModel;

import java.time.LocalDate;

public class ModifyAutoescuelaPresenter implements ModifyAutoescuelaContract.Presenter,
        ModifyAutoescuelaContract.Model.onModifyListener {

    private ModifyAutoescuelaContract.Model model;
    private ModifyAutoescuelaContract.View view;

    public ModifyAutoescuelaPresenter(ModifyAutoescuelaContract.View view) {
        model = new ModifyAutoescuelaModel();
        this.view = view;
    }

    @Override
    public void loadAutoescuela(long autoescuelaId) {
        model.loadAutoescuela(autoescuelaId, new ModifyAutoescuelaContract.Model.OnLoadAutoescuelaListener() {
            @Override
            public void onSuccess(Autoescuela autoescuela) {
                view.populateAutoescuelaData(autoescuela);
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void onModifySuccess(Autoescuela autoescuela) {
        view.showMessage("Autoescuela modificada correctamente");
        view.finishView();
    }

    @Override
    public void onModifyError(String message) {
        view.showError(message);
    }

    @Override
    public void modifyAutoescuela(long autoescuelaId, String nombre, String direccion, String ciudad,
                                  String telefono, String email, int capacidad, LocalDate fechaApertura,
                                  float rating, boolean activa, Double latitud, Double longitud) {
        if (nombre == null || nombre.isBlank()) {
            view.showValidationError("El nombre es un campo obligatorio");
            return;
        }

        if (latitud == null || longitud == null) {
            view.showValidationError("Debe seleccionar una ubicación en el mapa");
            return;
        }

        Autoescuela autoescuela = Autoescuela.builder()
                .id(autoescuelaId)
                .nombre(nombre)
                .direccion(direccion)
                .ciudad(ciudad)
                .telefono(telefono)
                .email(email)
                .capacidad(capacidad)
                .fechaApertura(fechaApertura)
                .rating(rating)
                .activa(activa)
                .latitud(latitud)
                .longitud(longitud)
                .build();

        model.modifyAutoescuela(autoescuelaId, autoescuela, this);
    }
}