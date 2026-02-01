package com.example.aa1autoescuelaandroid.contract;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.List;

public interface DetailAutoescuelaContract {
    interface Model {
        interface OnLoadListener{
            void onLoadSuccess(Autoescuela autoescuela);
            void onLoadError(String message);
        }
        void loadAutoescuela(long id, DetailAutoescuelaContract.Model.OnLoadListener listener);
    }

    interface View {
        void showAutoescuela(Autoescuela autoescuela);
        void showError(String message);
        void showMessage(String message);

    }
    interface Presenter {
        void loadAutoescuela(long id);
    }


}
