package com.example.aa1autoescuelaandroid.contract;

import android.content.Context;
import android.net.Uri;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.time.LocalDate;
import java.util.List;

public interface ModifyCocheContract {
    interface Model{
        interface onModifyListener{
            void onModifySuccess(Coche coche);
            void onModifyError(String message);
        }

        interface OnAutoescuelasListener {
            void onSuccess(List<Autoescuela> autoescuelas);
            void onError(String message);
        }

        interface OnLoadCocheListener {
            void onSuccess(Coche coche);
            void onError(String message);
        }

        void modifyCoche(long cocheId, Coche coche, onModifyListener listener);
        void loadAutoescuelas(OnAutoescuelasListener listener);
        void loadCoche(long cocheId, OnLoadCocheListener listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void showValidationError(String message);
        void showAutoescuelas(List<Autoescuela> autoescuelas);
        void showImagePreview(Uri uri);
        void populateCocheData(Coche coche);
        void finishView();
        Context getContext();
    }

    interface Presenter{
        void onSelectImageClicked();
        void onImageSelected(Uri uri);
        void loadAutoescuelas();
        void loadCoche(long cocheId);
        void modifyCoche(long cocheId, String matricula, String marca, String modelo, String tipoCambio,
                         int kilometraje, LocalDate fechaCompra, float precioCompra, boolean disponible, long autoescuelaId);
    }
}