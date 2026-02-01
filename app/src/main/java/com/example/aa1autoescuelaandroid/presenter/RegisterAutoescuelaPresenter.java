package com.example.aa1autoescuelaandroid.presenter;

import android.content.Intent;

import com.example.aa1autoescuelaandroid.contract.RegisterAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.model.RegisterAutoescuelaModel;
import com.example.aa1autoescuelaandroid.view.AutoescuelaListView;

import java.time.LocalDate;


public class RegisterAutoescuelaPresenter implements RegisterAutoescuelaContract.Presenter,RegisterAutoescuelaContract.Model.onRegisterListener {


    private RegisterAutoescuelaContract.Model model;
    private RegisterAutoescuelaContract.View view;

    public RegisterAutoescuelaPresenter (RegisterAutoescuelaContract.View view){
        model = new RegisterAutoescuelaModel();
        this.view = view;
    }
    @Override
    public void onRegisterSuccess(Autoescuela autoescuela) {
        view.showMessage("Registrada correctamente");
    }

    @Override
    public void onRegisterError(String message) {
        view.showError(message);
    }

    @Override
    public void registerAutoescuela(String nombre, String direccion, String ciudad, String telefono,
                                    String email, int capacidad,  LocalDate fechaApertura, float rating, boolean activa,
                                    double latitud, double longitud) {
        if (nombre.isBlank()) {
            view.showValidationError("El nombre es un campo obligatiorio");
        }
        Autoescuela autoescuela = Autoescuela.builder()
              .nombre(nombre)
              .direccion(direccion)
              .ciudad(ciudad)
              .telefono(telefono)
              .email(email).capacidad(capacidad)
              .fechaApertura(fechaApertura)
              .rating(rating)
              .activa(activa)
              .latitud(latitud)
              .longitud(longitud)
              .build();

        model.registerAutoescuela(autoescuela, this);

    }
}
