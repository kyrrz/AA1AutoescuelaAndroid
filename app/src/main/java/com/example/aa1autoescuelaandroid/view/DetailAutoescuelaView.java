package com.example.aa1autoescuelaandroid.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.DetailAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.presenter.DetailAutoescuelaPresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.time.LocalDate;

public class DetailAutoescuelaView extends AppCompatActivity
        implements DetailAutoescuelaContract.View, OnMapReadyCallback {

    private TextView nombre, direccion, ciudad, telefono, email, capacidad, fechaApertura, rating;
    private CheckBox activa;

    public static final String EXTRA_ID = "AUTOESCUELA_ID";

    private MapView mapView;
    private Autoescuela autoescuela;
    private GoogleMap googleMap;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private DetailAutoescuelaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_autoescuela);
        mapView = findViewById(R.id.autoescuela_detail_map_view);
        nombre = findViewById(R.id.autoescuela_detail_nombre);
        direccion = findViewById(R.id.autoescuela_detail_direccion);
        ciudad = findViewById(R.id.autoescuela_detail_ciudad);
        telefono = findViewById(R.id.autoescuela_detail_telefono);
        email = findViewById(R.id.autoescuela_detail_email);
        capacidad = findViewById(R.id.autoescuela_detail_capacidad);
        fechaApertura = findViewById(R.id.autoescuela_detail_fecha_apertura);
        rating = findViewById(R.id.autoescuela_detail_rating);
        activa = findViewById(R.id.autoescuela_detail_activa);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
        findViewById(R.id.action_view_coches_list).setOnClickListener(v -> {
            Intent intent = new Intent(this, CochesAutoescuelaView.class);
            intent.putExtra(
                    CochesAutoescuelaView.EXTRA_AUTOESCUELA_ID,
                    getIntent().getLongExtra(EXTRA_ID, -1)
            );
            startActivity(intent);
        });


        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        if (id == -1) {
            finish();
            return;
        }

        presenter = new DetailAutoescuelaPresenter(this);
        presenter.loadAutoescuela(id);

    }

    @Override
    public void showAutoescuela(Autoescuela a) {
        this.autoescuela = a;
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


//        Intent intent = new Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("geo:" + a.getLatitud() + "," + a.getLongitud())
//        );
//        startActivity(intent);

        if (googleMap != null) {
            updateMap();
        }
    }
    private void updateMap() {
        if (autoescuela == null) return;

        LatLng position = new LatLng(
                autoescuela.getLatitud(),
                autoescuela.getLongitud()
        );

        googleMap.clear();

        googleMap.addMarker(
                new MarkerOptions()
                        .position(position)
                        .title(autoescuela.getNombre())
        );

        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(position, 16f)
        );
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
            intent.putExtra("AUTOESCUELA_ID", autoescuela.getId());
            startActivity(intent);
            return  true;
        }
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, AutoescuelaListView.class);
            startActivity(intent);
            return  true;
        }
        if (item.getItemId() == R.id.action_delete_autoescuela) {
            confirmarBorrado();
            return true;
        }
        return false;
    }

    private void confirmarBorrado(){
        new AlertDialog.Builder(this).setTitle("Eliminar Autoescuela")
                .setMessage("¿Seguro que quieres eliminar esta autoescuela?")
                .setPositiveButton("Eliminar", (dialog, which) -> presenter.deleteAutoescuela(autoescuela.getId()))
                .setNegativeButton("Cancelar",null)
                .show();
    }
    @Override
    public void onAutoescuelaDeleted(){
        finish();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        if (autoescuela != null) {
            updateMap();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
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
    protected void onStop() {
        mapView.onStop();
        super.onStop();
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }
        mapView.onSaveInstanceState(mapViewBundle);
    }
}
