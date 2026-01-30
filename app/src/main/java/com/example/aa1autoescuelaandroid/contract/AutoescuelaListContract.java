package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

public interface AutoescuelaListContract {

    interface Model{
        interface OnLoadListener{
            void onLoadSuccess(List<Autoescuela> autoescuelas);
            void onLoadError(String message);
        }
        void loadAutoescuelas(OnLoadListener listener);
    }

    interface View{
        void show(List<Autoescuela> autoescuelas);
        void showError(String message);
        void showMessage(String message);

    }

    interface Presenter{
        void loadAutoescuelas();

    }
}
