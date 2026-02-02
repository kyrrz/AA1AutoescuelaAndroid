package com.example.aa1autoescuelaandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.RegisterCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.presenter.RegisterCochePresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterCocheView extends AppCompatActivity implements RegisterCocheContract.View {

    private Spinner spinnerAutoescuelas;
    private List<Autoescuela> autoescuelas;
    private RegisterCochePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_coche);
        presenter = new RegisterCochePresenter(this);
        spinnerAutoescuelas = findViewById(R.id.coche_spinner_autoescuelas);


        presenter.loadAutoescuelas();
    }

    public void registerCoche(View view) {
        String matricula = ((EditText) findViewById(R.id.coche_matricula)).getText().toString();
        String marca = ((EditText) findViewById(R.id.coche_marca)).getText().toString();
        String modelo = ((EditText) findViewById(R.id.coche_modelo)).getText().toString();
        String tipoCambio = ((EditText) findViewById(R.id.coche_tipo_cambio)).getText().toString();
        int kilometraje = Integer.parseInt(((EditText) findViewById(R.id.coche_kilometraje)).getText().toString());
        LocalDate fechaCompra = DateUtil.parseDate(((EditText) findViewById(R.id.coche_fecha_compra)).getText().toString());
        float precioCompra = Float.parseFloat(((EditText) findViewById(R.id.coche_precio_compra)).getText().toString());
        CheckBox checkBox = findViewById(R.id.coche_disponible);
        boolean disponible = checkBox.isChecked();

        Autoescuela autoescuela =
                autoescuelas.get(spinnerAutoescuelas.getSelectedItemPosition());
        long autoescuelaId = autoescuela.getId();
        presenter.registerCoche(matricula, marca, modelo, tipoCambio,  kilometraje,  fechaCompra,
         precioCompra,  disponible,  autoescuelaId);
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

    @Override
    public void showAutoescuelas(List<Autoescuela> autoescuelas) {
        this.autoescuelas = autoescuelas;
        List<String> nombres = new ArrayList<>();
        for (Autoescuela a : autoescuelas){
            nombres.add(a.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombres
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAutoescuelas.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.returnbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, CocheListView.class);
            startActivity(intent);
            return  true;
        }

        return false;
    }
}