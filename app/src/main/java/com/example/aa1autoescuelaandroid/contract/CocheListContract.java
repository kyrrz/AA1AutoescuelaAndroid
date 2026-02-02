package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;

import java.util.List;

public interface CocheListContract {

    interface Model{
        interface OnLoadListener{
            void onLoadSuccess(List<Coche> coches);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadCoches(OnLoadListener listener);
        void deleteCoche(long id, OnDeleteListener listener);

    }

    interface View{
        void show(List<Coche> coches);
        void showError(String message);
        void showMessage(String message);

        void onCocheItemClick(Coche coche);

        void onDeleteClick(Coche coche);
    }

    interface Presenter{
        void loadCoches();
        void deleteCoche(long id);

    }
}
