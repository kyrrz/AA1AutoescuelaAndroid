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
import com.example.aa1autoescuelaandroid.adapter.CocheAdapter;
import com.example.aa1autoescuelaandroid.contract.CochesAutoescuelaContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.model.CochesAutoescuelaModel;
import com.example.aa1autoescuelaandroid.presenter.AutoescuelaListPresenter;
import com.example.aa1autoescuelaandroid.presenter.CochesAutoescuelaPresenter;

import java.util.ArrayList;
import java.util.List;

public class CochesAutoescuelaView extends AppCompatActivity
        implements CochesAutoescuelaContract.View {

    public static final String EXTRA_AUTOESCUELA_ID = "AUTOESCUELA_ID";

    private CochesAutoescuelaPresenter presenter;

    private OnCocheClickListener listener;

    private CocheAdapter cocheAdapter;

    List<Coche> cochesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coche_list);

        long autoescuelaId = getIntent().getLongExtra(EXTRA_AUTOESCUELA_ID, -1);
        if (autoescuelaId == -1) {
            finish();
            return;
        }
        presenter = new CochesAutoescuelaPresenter(this);
        presenter.loadCoches(autoescuelaId);
        cochesList = new ArrayList<>();

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

            }
        };
        cocheAdapter = new CocheAdapter(cochesList ,listener);
        cochesListView.setAdapter(cocheAdapter);
    }
    @Override protected void onResume() {
        super.onResume();
        cocheAdapter.notifyDataSetChanged();
    }


    @Override
    public void showCoches(List<Coche> coches) {
        cochesList.clear();
        cochesList.addAll(coches);
        cocheAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {

    }
    public void onCocheItemClick(Coche coche){
        Intent intent = new Intent(this, DetailCocheView.class);
        intent.putExtra(DetailAutoescuelaView.EXTRA_ID, coche.getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.returnbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button){
            finish();
            return  true;
        }
        return false;
    }


}
