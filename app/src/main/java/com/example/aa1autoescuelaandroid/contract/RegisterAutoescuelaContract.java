package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.time.LocalDate;

public interface RegisterAutoescuelaContract {

    interface Model{
        interface onRegisterListener{
            void onRegisterSuccess(Autoescuela autoescuela);
            void onRegisterError(String message);

        }
        void registerAutoescuela(Autoescuela autoescuela, onRegisterListener listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void showValidationError(String message);

    }

    interface Presenter{
        void registerAutoescuela(String nombre, String direccion, String ciudad, String telefono ,
                                 String email,int capacidad, LocalDate fechaApertura, float rating, boolean activa, double latitud, double longitud);

    }
}
