package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

public interface CochesAutoescuelaContract {

    interface Model {
        void loadCoches(long autoescuelaId, OnLoadCochesListener listener);

        interface OnLoadCochesListener{
            void onSuccess(List<Coche> coches);
            void onError(String message);
        }
    }
    interface View {
        void showCoches(List<Coche> coches);
        void showError(String message);
        void showMessage(String message);
    }

    interface Presenter {
        void loadCoches(long autoescuelaId);
    }



}
