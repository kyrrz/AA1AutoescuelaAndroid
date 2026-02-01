package com.example.aa1autoescuelaandroid.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.DetailAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.model.DetailAutoescuelaModel;
import com.example.aa1autoescuelaandroid.presenter.DetailAutoescuelaPresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;

import java.time.LocalDate;

public class DetailAutoescuelaView extends AppCompatActivity
        implements DetailAutoescuelaContract.View {

    private TextView nombre;
    private TextView direccion;
    private TextView ciudad;
    private TextView telefono;
    private TextView email;
    private TextView capacidad;
    private TextView fechaApertura;
    private TextView rating;
    private CheckBox activa;
    private TextView latitud;
    private TextView longitud;
    public static final String EXTRA_ID = "AUTOESCUELA_ID";

    private DetailAutoescuelaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_autoescuela);
        nombre = findViewById(R.id.autoescuela_detail_nombre);
        direccion = findViewById(R.id.autoescuela_detail_direccion);
        ciudad = findViewById(R.id.autoescuela_detail_ciudad);
        telefono = findViewById(R.id.autoescuela_detail_telefono);
        email = findViewById(R.id.autoescuela_detail_email);
        capacidad = findViewById(R.id.autoescuela_detail_capacidad);
        fechaApertura = findViewById(R.id.autoescuela_detail_fecha_apertura);
        rating = findViewById(R.id.autoescuela_detail_rating);
        activa = findViewById(R.id.autoescuela_detail_activa);




        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        if (id == -1) {
            finish();
            return;
        }

        DetailAutoescuelaContract.Model model = new DetailAutoescuelaModel();
        presenter = new DetailAutoescuelaPresenter(this, model);
        presenter.loadAutoescuela(id);
    }

    @Override
    public void showAutoescuela(Autoescuela a) {

        nombre.setText(a.getNombre());
        direccion.setText(a.getDireccion());
        ciudad.setText(a.getCiudad());
        telefono.setText(a.getTelefono());
        email.setText(a.getEmail());
        capacidad.setText(String.valueOf(a.getCapacidad()));
        rating.setText(String.valueOf(a.getRating()));
        LocalDate fecha = a.getFechaApertura();
        fechaApertura.setText(
                fecha != null
                        ? DateUtil.formatDate(fecha)
                        : "Fecha no disponible"
        );
        activa.setChecked(a.isActiva());


        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:" + a.getLatitud() + "," + a.getLongitud())
        );
        startActivity(intent);
    }
    

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editautoescuela, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit_autoescuela){
            Intent intent = new Intent(this, ModifyAutoescuelaView.class);
            startActivity(intent);
            return  true;
        }
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, AutoescuelaListView.class);
            startActivity(intent);
            return  true;
        }
        return false;
    }
}