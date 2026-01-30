package com.example.aa1autoescuelaandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.adapter.AutoescuelaAdapter;
import com.example.aa1autoescuelaandroid.db.AppDatabase;
import com.example.aa1autoescuelaandroid.db.DatabaseUtil;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Autoescuela> autoescuelaList;
    private AutoescuelaAdapter autoescuelaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoescuelaList = new ArrayList<>();

        RecyclerView autoescuelasList = findViewById(R.id.autoescuelasList);
        autoescuelasList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        autoescuelasList.setLayoutManager(linearLayoutManager);
        autoescuelaAdapter = new AutoescuelaAdapter(autoescuelaList);
        autoescuelasList.setAdapter(autoescuelaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        autoescuelaAdapter.notifyDataSetChanged();
    }





}