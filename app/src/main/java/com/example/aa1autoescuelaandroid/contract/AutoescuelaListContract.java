package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

public interface AutoescuelaListContract {

    interface Model{
        interface OnLoadListener{
            void onLoadSuccess(List<Autoescuela> autoescuelas);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadAutoescuelas(OnLoadListener listener);
        void deleteAutoescuela(long id, OnDeleteListener listener);

    }

    interface View{
        void show(List<Autoescuela> autoescuelas);
        void showError(String message);
        void showMessage(String message);

        void onAutoescuelaItemClick(Autoescuela autoescuela);

        void onDeleteClick(Autoescuela autoescuela);
    }

    interface Presenter{
        void loadAutoescuelas();
        void deleteAutoescuela(long id);

    }
}
