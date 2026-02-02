package com.example.aa1autoescuelaandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.RegisterAutoescuelaContract;
import com.example.aa1autoescuelaandroid.presenter.RegisterAutoescuelaPresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;

public class RegisterAutoescuelaView extends AppCompatActivity implements RegisterAutoescuelaContract.View {

    private RegisterAutoescuelaContract.Presenter presenter;
    private MapView mapView;
    private GoogleMap googleMap;
    private Marker marker;
    private Double latitud = null;
    private Double longitud = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_autoescuela);
        presenter = new RegisterAutoescuelaPresenter(this);
        mapView = findViewById(R.id.register_autoescuela_map_view);

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

    }

    public void registerAutoescuela(View view) {
        String nombre = ((EditText) findViewById(R.id.autoescuela_nombre)).getText().toString();
        String direccion = ((EditText) findViewById(R.id.autoescuela_direccion)).getText().toString();
        String ciudad = ((EditText) findViewById(R.id.autoescuela_ciudad)).getText().toString();
        String telefono = ((EditText) findViewById(R.id.autoescuela_telefono)).getText().toString();
        String email = ((EditText) findViewById(R.id.autoescuela_email)).getText().toString();
        int capacidad = Integer.parseInt(((EditText) findViewById(R.id.autoescuela_capacidad)).getText().toString());
        LocalDate fechaApertura = DateUtil.parseDate(((EditText) findViewById(R.id.autoescuela_fecha_apertura)).getText().toString());
        float rating = Float.parseFloat(((EditText) findViewById(R.id.autoescuela_rating)).getText().toString());
        CheckBox checkBox = findViewById(R.id.autoescuela_activa);
        boolean activa = checkBox.isChecked();


        presenter.registerAutoescuela(nombre, direccion, ciudad, telefono, email, capacidad, fechaApertura, rating, activa, latitud, longitud);
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
    @Override protected void onResume() { super.onResume(); mapView.onResume(); }
    @Override protected void onPause() { mapView.onPause(); super.onPause(); }
    @Override protected void onDestroy() { mapView.onDestroy(); super.onDestroy(); }
    @Override public void onLowMemory() { super.onLowMemory(); mapView.onLowMemory(); }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.returnbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, AutoescuelaListView.class);
            startActivity(intent);
            return  true;
        }
        return false;
    }
}
