package com.example.aa1autoescuelaandroid.contract;

import android.content.Context;
import android.net.Uri;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.time.LocalDate;
import java.util.List;

public interface RegisterCocheContract {
    interface Model{
        interface onRegisterListener{
            void onRegisterSuccess(Coche coche);
            void onRegisterError(String message);

        }
        interface OnAutoescuelasListener {
            void onSuccess(List<Autoescuela> autoescuelas);
            void onError(String message);
        }
        void registerCoche(Coche coche, RegisterCocheContract.Model.onRegisterListener listener);
        void loadAutoescuelas(OnAutoescuelasListener listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void showValidationError(String message);
        void showAutoescuelas(List<Autoescuela> autoescuelas);
        void showImagePreview(Uri uri);
        void finishView();
        Context getContext();


    }

    interface Presenter{
        void onSelectImageClicked();
        void onImageSelected(Uri uri);
        void loadAutoescuelas();
        void registerCoche(String matricula, String marca, String modelo, String tipoCambio,
                           int kilometraje, LocalDate fechaCompra, float precioCompra, boolean disponible, long autoescuelaId);

    }
}
