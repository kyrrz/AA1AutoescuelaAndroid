package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Coche;

public interface DetailCocheContract {

        interface Model {
            interface OnLoadListener{
                void onLoadSuccess(Coche coche);
                void onLoadError(String message);
            }
            interface OnDeleteListener{
                void onDeleteSuccess(String message);
                void onDeleteError(String message);
            }
            void loadCoche(long id, DetailCocheContract.Model.OnLoadListener listener);
            void deleteCoche(long id, DetailCocheContract.Model.OnDeleteListener listener);
        }

        interface View {
            void showCoche(Coche coche);
            void showError(String message);
            void showMessage(String message);
            void onCocheDeleted();
        }
        interface Presenter {
            void loadCoche(long id);
            void deleteCoche(long id);
        }


    }


