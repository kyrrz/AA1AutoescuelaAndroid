package com.example.aa1autoescuelaandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.ModifyAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.presenter.ModifyAutoescuelaPresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.time.LocalDate;

public class ModifyAutoescuelaView extends AppCompatActivity implements ModifyAutoescuelaContract.View {

    private ModifyAutoescuelaContract.Presenter presenter;
    private MapView mapView;
    private GoogleMap googleMap;
    private Marker marker;
    private Double latitud = null;
    private Double longitud = null;
    private long autoescuelaId;

    private EditText etNombre, etDireccion, etCiudad, etTelefono, etEmail, etCapacidad, etFechaApertura, etRating;
    private SwitchMaterial checkActiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_autoescuela);

        autoescuelaId = getIntent().getLongExtra("AUTOESCUELA_ID", -1);
        if (autoescuelaId == -1) {
            Toast.makeText(this, "Error: ID de autoescuela no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        presenter = new ModifyAutoescuelaPresenter(this);


        etNombre = findViewById(R.id.autoescuela_nombre);
        etDireccion = findViewById(R.id.autoescuela_direccion);
        etCiudad = findViewById(R.id.autoescuela_ciudad);
        etTelefono = findViewById(R.id.autoescuela_telefono);
        etEmail = findViewById(R.id.autoescuela_email);
        etCapacidad = findViewById(R.id.autoescuela_capacidad);
        etFechaApertura = findViewById(R.id.autoescuela_fecha_apertura);
        etRating = findViewById(R.id.autoescuela_rating);
        checkActiva = findViewById(R.id.autoescuela_activa);

        mapView = findViewById(R.id.modify_autoescuela_map_view);

        Bundle mapViewBundle = savedInstanceState != null
                ? savedInstanceState.getBundle("MapViewBundleKey")
                : null;

        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(map -> {
            googleMap = map;

            LatLng defaultLocation = new LatLng(40.4168, -3.7038);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12f));
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.setOnMapClickListener(latLng -> {
                if (marker != null) marker.remove();

                marker = googleMap.addMarker(
                        new MarkerOptions().position(latLng).title("Ubicación seleccionada")
                );

                latitud = latLng.latitude;
                longitud = latLng.longitude;
            });
        });

        presenter.loadAutoescuela(autoescuelaId);
    }

    public void modifyAutoescuela(View view) {
        try {
            String nombre = etNombre.getText().toString();
            String direccion = etDireccion.getText().toString();
            String ciudad = etCiudad.getText().toString();
            String telefono = etTelefono.getText().toString();
            String email = etEmail.getText().toString();
            int capacidad = Integer.parseInt(etCapacidad.getText().toString());
            LocalDate fechaApertura = DateUtil.parseDate(etFechaApertura.getText().toString());
            float rating = Float.parseFloat(etRating.getText().toString());
            boolean activa = checkActiva.isChecked();

            presenter.modifyAutoescuela(autoescuelaId, nombre, direccion, ciudad, telefono,
                    email, capacidad, fechaApertura, rating, activa, latitud, longitud);
        } catch (NumberFormatException e) {
            showValidationError("Por favor, completa todos los campos correctamente");
        }
    }

    @Override
    public void populateAutoescuelaData(Autoescuela autoescuela) {
        etNombre.setText(autoescuela.getNombre());
        etDireccion.setText(autoescuela.getDireccion());
        etCiudad.setText(autoescuela.getCiudad());
        etTelefono.setText(autoescuela.getTelefono());
        etEmail.setText(autoescuela.getEmail());
        etCapacidad.setText(String.valueOf(autoescuela.getCapacidad()));
        etFechaApertura.setText(DateUtil.formatDate(autoescuela.getFechaApertura()));
        etRating.setText(String.valueOf(autoescuela.getRating()));
        checkActiva.setChecked(autoescuela.isActiva());


            latitud = autoescuela.getLatitud();
            longitud = autoescuela.getLongitud();

            if (googleMap != null) {
                LatLng location = new LatLng(latitud, longitud);

                if (marker != null) marker.remove();

                marker = googleMap.addMarker(
                        new MarkerOptions().position(location).title(autoescuela.getNombre())
                );

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15f));
            }

    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showValidationError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.returnbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, AutoescuelaListView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}