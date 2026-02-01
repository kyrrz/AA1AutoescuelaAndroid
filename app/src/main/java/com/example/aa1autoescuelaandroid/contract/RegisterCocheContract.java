package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.time.LocalDate;

public interface RegisterCocheContract {
    interface Model{
        interface onRegisterListener{
            void onRegisterSuccess(Coche coche);
            void onRegisterError(String message);

        }
        void registerCoche(Coche coche, RegisterCocheContract.Model.onRegisterListener listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void showValidationError(String message);

    }

    interface Presenter{
        void registerCoche(String matricula, String marca, String modelo, String tipoCambio,
                           int kilometraje, LocalDate fechaCompra, float precioCompra, boolean disponible, Autoescuela autoescuela);

    }
}
