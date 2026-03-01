package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.time.LocalDate;

public interface ModifyAutoescuelaContract {

    interface Model{
        interface onModifyListener{
            void onModifySuccess(Autoescuela autoescuela);
            void onModifyError(String message);
        }

        interface OnLoadAutoescuelaListener {
            void onSuccess(Autoescuela autoescuela);
            void onError(String message);
        }

        void modifyAutoescuela(long autoescuelaId, Autoescuela autoescuela, onModifyListener listener);
        void loadAutoescuela(long autoescuelaId, OnLoadAutoescuelaListener listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void showValidationError(String message);
        void populateAutoescuelaData(Autoescuela autoescuela);
        void finishView();
    }

    interface Presenter{
        void loadAutoescuela(long autoescuelaId);
        void modifyAutoescuela(long autoescuelaId, String nombre, String direccion, String ciudad,
                               String telefono, String email, int capacidad, LocalDate fechaApertura,
                               float rating, boolean activa, Double latitud, Double longitud);
    }
}