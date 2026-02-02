package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

public interface DetailAutoescuelaContract {
    interface Model {
        interface OnLoadListener{
            void onLoadSuccess(Autoescuela autoescuela);
            void onLoadError(String message);
        }
        interface OnDeleteListener{
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }
        void loadAutoescuela(long id, DetailAutoescuelaContract.Model.OnLoadListener listener);
        void deleteAutoescuela(long id, DetailAutoescuelaContract.Model.OnDeleteListener listener);
    }

    interface View {
        void showAutoescuela(Autoescuela autoescuela);
        void showError(String message);
        void showMessage(String message);
        void onAutoescuelaDeleted();
    }
    interface Presenter {
        void loadAutoescuela(long id);
        void deleteAutoescuela(long id);
    }


}
