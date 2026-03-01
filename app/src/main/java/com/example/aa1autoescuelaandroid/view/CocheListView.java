package com.example.aa1autoescuelaandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.adapter.CocheAdapter;
import com.example.aa1autoescuelaandroid.contract.CocheListContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.presenter.CocheListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CocheListView extends AppCompatActivity implements CocheListContract.View {
    List<Coche> cocheList;
    private ImageView cocheImage;
    private CocheAdapter cocheAdapter;
    private CocheListPresenter presenter;
    private OnCocheClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coche_list);
        presenter = new CocheListPresenter(this);
        cocheList = new ArrayList<>();

        FloatingActionButton fab = findViewById(R.id.fab_action_register_coche);
        fab.setOnClickListener(v -> goToRegisterCoche());

        RecyclerView cochesListView = findViewById(R.id.coche_list);
        cochesListView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cochesListView.setLayoutManager(linearLayoutManager);
        listener = new OnCocheClickListener() {
            @Override
            public void onCocheClick(Coche coche) {
                onCocheItemClick(coche);
            }

            @Override
            public void onDeleteClick(Coche coche) {
                CocheListView.this.onDeleteClick(coche);
            }
        };
        cocheAdapter = new CocheAdapter(cocheList ,listener);
        cochesListView.setAdapter(cocheAdapter);
    }



    @Override protected void onResume() { super.onResume(); ; presenter.loadCoches(); }

    @Override
    public void show(List<Coche> coches) {
        cocheList.clear();
        cocheList.addAll(coches);
        cocheAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }


    public void onCocheItemClick(Coche coche) {
        Intent intent = new Intent(this, DetailCocheView.class);
        intent.putExtra(DetailCocheView.EXTRA_ID, coche.getId());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Coche coche){
        confirmarBorrado(coche);
    }


    private void confirmarBorrado(Coche coche) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar coche")
                .setMessage("¿Seguro que deseas eliminar " + coche.getMarca() + " " + coche.getModelo() +  "?")
                .setPositiveButton("Eliminar", (d, w) ->
                        presenter.deleteCoche(coche.getId())
                )
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addcoche, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register_coche){
            goToRegisterCoche();
            return  true;
        }
        if (item.getItemId() == R.id.back_button){
            goToAutoescuelaList();
            return  true;
        }
        return false;
    }

    private void goToRegisterCoche() {
        Intent intent = new Intent(this, RegisterCocheView.class);
        startActivity(intent);
    }

    private void goToAutoescuelaList() {
        Intent intent = new Intent(this, AutoescuelaListView.class);
        startActivity(intent);
    }



}