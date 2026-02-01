package com.example.aa1autoescuelaandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.adapter.AutoescuelaAdapter;
import com.example.aa1autoescuelaandroid.contract.AutoescuelaListContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.presenter.AutoescuelaListPresenter;

import java.util.ArrayList;
import java.util.List;

public class AutoescuelaListView extends AppCompatActivity implements AutoescuelaListContract.View {

    List<Autoescuela> autoescuelaList;
    private AutoescuelaAdapter autoescuelaAdapter;
    private AutoescuelaListPresenter presenter;
    private OnAutoescuelaClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new AutoescuelaListPresenter(this);
        autoescuelaList = new ArrayList<>();

        RecyclerView autoescuelasList = findViewById(R.id.autoescuelasList);
        autoescuelasList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        autoescuelasList.setLayoutManager(linearLayoutManager);
        listener = autoescuela -> onAutoescuelaClick(autoescuela);
        autoescuelaAdapter = new AutoescuelaAdapter(autoescuelaList,listener);
        autoescuelasList.setAdapter(autoescuelaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadAutoescuelas();
    }


    @Override
    public void show(List<Autoescuela> autoescuelas) {
        autoescuelaList.clear();
        autoescuelaList.addAll(autoescuelas);
        autoescuelaAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    public void onAutoescuelaClick(Autoescuela autoescuela){
        Intent intent = new Intent(this, DetailAutoescuelaView.class);
        intent.putExtra(DetailAutoescuelaView.EXTRA_ID, autoescuela.getId());
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_register_autoescuela){
            Intent intent = new Intent(this, RegisterAutoescuelaView.class);
            startActivity(intent);
            return  true;
        }
        return false;
    }
}