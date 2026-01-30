package com.example.aa1autoescuelaandroid.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.RegisterAutoescuelaContract;
import com.example.aa1autoescuelaandroid.presenter.RegisterAutoescuelaPresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;

public class RegisterAutoescuelaView extends AppCompatActivity implements RegisterAutoescuelaContract.View {

    private RegisterAutoescuelaContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_autoescuela);
        presenter = new RegisterAutoescuelaPresenter(this);

    }

    public void registerAutoescuela(View view) {
        String nombre = ((EditText) findViewById(R.id._name)).getText().toString();
        String direccion = ((EditText) findViewById(R.id.game_description)).getText().toString();
        String ciudad = ((EditText) findViewById(R.id.game_type)).getText().toString();
        String telefono =  ((EditText) findViewById(R.id.autoescuela_telefono)).getText().toString();
        String email =  ((EditText) findViewById(R.id.autoescuela_email)).getText().toString();
        LocalDate fechaApertura = DateUtil.parseDate(((EditText) findViewById(R.id.game_release_date)).getText().toString());
        float rating = Float.parseFloat(((EditText) findViewById(R.id.autoescuela_rating)).getText().toString());
        boolean activa = Boolean.parseBoolean(((EditText) findViewById(R.id.autoescuela_rating)).getText().toString());
        double latitud = Double.parseDouble(((EditText) findViewById(R.id.autoescuela_rating)).getText().toString());
        double longitud = Double.parseDouble(((EditText) findViewById(R.id.autoescuela_rating)).getText().toString());

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(this.getCurrentFocus(), message, Snackbar.LENGTH_LONG);
    }

    @Override
    public void showValidationError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}