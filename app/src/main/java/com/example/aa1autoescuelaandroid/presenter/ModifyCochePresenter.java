package com.example.aa1autoescuelaandroid.presenter;

import android.content.Context;
import android.net.Uri;

import com.example.aa1autoescuelaandroid.contract.ModifyCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.model.ModifyCocheModel;
import com.example.aa1autoescuelaandroid.view.ModifyCocheView;

import java.time.LocalDate;
import java.util.List;

public class ModifyCochePresenter implements ModifyCocheContract.Presenter,
        ModifyCocheContract.Model.onModifyListener {

    private final ModifyCocheContract.View view;
    private final ModifyCocheContract.Model model;
    private Uri imageUri;
    private String currentImageUri;

    public ModifyCochePresenter(ModifyCocheContract.View view, Context context) {
        this.view = view;
        this.model = new ModifyCocheModel(context);
    }

    @Override
    public void onSelectImageClicked() {
        ((ModifyCocheView) view).openGallery();
    }

    @Override
    public void onImageSelected(Uri uri) {
        imageUri = uri;
        view.showImagePreview(uri);
    }

    @Override
    public void loadAutoescuelas() {
        model.loadAutoescuelas(new ModifyCocheContract.Model.OnAutoescuelasListener() {
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
    public void loadCoche(long cocheId) {
        model.loadCoche(cocheId, new ModifyCocheContract.Model.OnLoadCocheListener() {
            @Override
            public void onSuccess(Coche coche) {
                currentImageUri = coche.getImage();
                view.populateCocheData(coche);
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void modifyCoche(long cocheId, String matricula, String marca, String modelo, String tipoCambio,
                            int kilometraje, LocalDate fechaCompra, float precioCompra,
                            boolean disponible, long autoescuelaId) {
        if (marca.isBlank()) {
            view.showValidationError("La marca es un campo obligatorio");
            return;
        }

        // Si no se ha seleccionado una nueva imagen, usar la imagen actual
        String image = imageUri != null ? imageUri.toString() : currentImageUri;

        if (image == null || image.isBlank()) {
            view.showError("Selecciona una imagen");
            return;
        }

        Coche coche = Coche.builder()
                .id(cocheId)
                .matricula(matricula)
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

        model.modifyCoche(cocheId, coche, this);
    }

    @Override
    public void onModifySuccess(Coche coche) {
        view.showMessage("Coche modificado correctamente");
        view.finishView();
    }

    @Override
    public void onModifyError(String message) {
        view.showError(message);
    }
}