package com.example.aa1autoescuelaandroid.presenter;

import android.content.Context;
import android.net.Uri;

import com.example.aa1autoescuelaandroid.contract.RegisterAutoescuelaContract;
import com.example.aa1autoescuelaandroid.contract.RegisterCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.model.RegisterCocheModel;
import com.example.aa1autoescuelaandroid.view.RegisterCocheView;

import java.time.LocalDate;
import java.util.List;

public class RegisterCochePresenter implements RegisterCocheContract.Presenter, RegisterCocheContract.Model.onRegisterListener {

    private final RegisterCocheContract.View view;
    private final RegisterCocheContract.Model model;
    private Uri imageUri;

    public RegisterCochePresenter(RegisterCocheContract.View view, Context context) {
        this.view = view;
        this.model = new RegisterCocheModel(context);
    }

    @Override
    public void onSelectImageClicked() {
        ((RegisterCocheView) view).openGallery();
    }

    @Override
    public void onImageSelected(Uri uri) {
        imageUri = uri;
        view.showImagePreview(uri);
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
        if (imageUri == null) {
            view.showError("Selecciona una imagen");
            return;
        }
        String image = imageUri.toString();
        Coche coche = Coche.builder().matricula(matricula)
                .marca(marca)
                .modelo(modelo)
                .tipoCambio(tipoCambio)
                .kilometraje(kilometraje)
                .fechaCompra(fechaCompra)
                .precioCompra(precioCompra)
                .disponible(disponible)
                .autoescuelaId(autoescuelaId)
                .image(image)
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




