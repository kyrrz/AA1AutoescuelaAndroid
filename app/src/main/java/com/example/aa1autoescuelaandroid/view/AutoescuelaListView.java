package com.example.aa1autoescuelaandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.adapter.AutoescuelaAdapter;
import com.example.aa1autoescuelaandroid.contract.AutoescuelaListContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.presenter.AutoescuelaListPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AutoescuelaListView extends AppCompatActivity implements AutoescuelaListContract.View, OnMapReadyCallback
{

    List<Autoescuela> autoescuelaList;
    private AutoescuelaAdapter autoescuelaAdapter;
    private AutoescuelaListPresenter presenter;
    private OnAutoescuelaClickListener listener;
    private MapView mapView;
    private GoogleMap googleMap;

    private static final LatLng SPAIN_CENTER = new LatLng(40.4168, -3.7038);
    private static final float SPAIN_ZOOM = 5.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new AutoescuelaListPresenter(this);
        autoescuelaList = new ArrayList<>();

        FloatingActionButton fab = findViewById(R.id.fab_action_register_autoescuela);
        fab.setOnClickListener(v -> goToRegisterAutoescuela());

        mapView = findViewById(R.id.autoescuela_list_map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        RecyclerView autoescuelasList = findViewById(R.id.autoescuelasList);
        autoescuelasList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        autoescuelasList.setLayoutManager(linearLayoutManager);
        listener = new OnAutoescuelaClickListener() {
            @Override
            public void onAutoescuelaClick(Autoescuela autoescuela) {
                onAutoescuelaItemClick(autoescuela);
            }

            @Override
            public void onDeleteClick(Autoescuela autoescuela) {
                confirmarBorrado(autoescuela);
            }
        };
        autoescuelaAdapter = new AutoescuelaAdapter(autoescuelaList,listener);
        autoescuelasList.setAdapter(autoescuelaAdapter);
    }

    @Override protected void onResume() {
        super.onResume();
        mapView.onResume();
        presenter.loadAutoescuelas();
    }

    @Override
    public void show(List<Autoescuela> autoescuelas) {
        autoescuelaList.clear();
        autoescuelaList.addAll(autoescuelas);
        autoescuelaAdapter.notifyDataSetChanged();

        if (googleMap != null) {
            showMarkers(autoescuelas);
        }
    }

    private void showMarkers(List<Autoescuela> autoescuelas) {
        googleMap.clear();


        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

        for (Autoescuela a : autoescuelas) {
            if (a.getLatitud() == 0 || a.getLongitud() == 0) continue;

            LatLng position = new LatLng(a.getLatitud(), a.getLongitud());

            Marker marker = googleMap.addMarker(
                    new MarkerOptions()
                            .position(position)
                            .title(a.getNombre())
            );

            marker.setTag(a);
            boundsBuilder.include(position);
        }


    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteClick(Autoescuela  autoescuela){
        confirmarBorrado(autoescuela);
    }
    public void onAutoescuelaItemClick(Autoescuela autoescuela){
        Intent intent = new Intent(this, DetailAutoescuelaView.class);
        intent.putExtra(DetailAutoescuelaView.EXTRA_ID, autoescuela.getId());
        startActivity(intent);
    }

    private void confirmarBorrado(Autoescuela autoescuela) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar autoescuela")
                .setMessage("¿Seguro que deseas eliminar " + autoescuela.getNombre() + "?")
                .setPositiveButton("Eliminar", (d, w) ->
                        presenter.deleteAutoescuela(autoescuela.getId())
                )
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addautoescuela, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register_autoescuela){
            goToRegisterAutoescuela();
            return  true;
        }
        if (item.getItemId() == R.id.action_coche_list){
            goToCocheList();
            return true;
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.googleMap = map;

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SPAIN_CENTER, SPAIN_ZOOM));

        googleMap.setOnMarkerClickListener(marker -> {
            Autoescuela autoescuela = (Autoescuela) marker.getTag();
            if (autoescuela != null) {
                onAutoescuelaItemClick(autoescuela);
            }
            return true;
        });
    }
    @Override protected void onStart() { super.onStart(); mapView.onStart(); }

    @Override protected void onPause() { mapView.onPause(); super.onPause(); }
    @Override protected void onStop() { mapView.onStop(); super.onStop(); }
    @Override protected void onDestroy() { mapView.onDestroy(); super.onDestroy(); }
    @Override public void onLowMemory() { super.onLowMemory(); mapView.onLowMemory(); }

    private void goToRegisterAutoescuela() {
        Intent intent = new Intent(this, RegisterAutoescuelaView.class);
        startActivity(intent);
    }

    private void goToCocheList() {
        Intent intent = new Intent(this, CocheListView.class);
        startActivity(intent);
    }

}