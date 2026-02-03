package com.example.aa1autoescuelaandroid.view;



import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.DetailCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.presenter.AutoescuelaListPresenter;
import com.example.aa1autoescuelaandroid.presenter.DetailAutoescuelaPresenter;
import com.example.aa1autoescuelaandroid.presenter.DetailCochePresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;

import java.time.LocalDate;

public class DetailCocheView extends AppCompatActivity implements DetailCocheContract.View {
    private TextView matricula, marca ,modelo, tipoCambio, kilometraje, fechaCompra, precioCompra, autoescuela;
    private CheckBox disponible;
    private Coche coche;
    private Autoescuela autescuela;
    private DetailCochePresenter presenter;
    private DetailAutoescuelaPresenter autoescuelaPresenter;
    private DetailAutoescuelaView autoescuelaView;
    public static final String EXTRA_ID = "COCHE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coche);

        matricula = findViewById(R.id.coche_detail_matricula);
        marca = findViewById(R.id.coche_detail_marca);
        modelo = findViewById(R.id.coche_detail_modelo);
        tipoCambio = findViewById(R.id.coche_detail_tipo_cambio);
        kilometraje = findViewById(R.id.coche_detail_kilometraje);
        fechaCompra = findViewById(R.id.coche_detail_fecha_compra);
        precioCompra = findViewById(R.id.coche_detail_precio_compra);
        disponible = findViewById(R.id.coche_detail_disponible);
        autoescuela = findViewById(R.id.coche_detail_autoescuela);

        long id = getIntent().getLongExtra(EXTRA_ID, -1);
        presenter = new DetailCochePresenter(this);
        presenter.loadCoche(id);
    }

    @Override
    public void showCoche(Coche c) {
        this.coche = c;
        matricula.setText(c.getMatricula());
        marca.setText(c.getMarca());
        modelo.setText(c.getModelo());
        tipoCambio.setText(c.getTipoCambio());
        kilometraje.setText(String.valueOf(c.getKilometraje()));
        LocalDate fecha = c.getFechaCompra();
        fechaCompra.setText(
                fecha != null
                        ? DateUtil.formatDate(fecha)
                        : "Fecha no disponible"
        );
        precioCompra.setText(String.valueOf(c.getPrecioCompra()));
        disponible.setChecked(c.isDisponible());
        if (coche.getAutoescuela() != null) {
            autoescuela.setText(
                    coche.getAutoescuela().getNombre()
            );
        } else {
            autoescuela.setText("Autoescuela no asignada");
        }

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
        getMenuInflater().inflate(R.menu.editcoche, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit_coche){
            Intent intent = new Intent(this, ModifyCocheView.class);
            startActivity(intent);
            return  true;
        }
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, CocheListView.class);
            startActivity(intent);
            return  true;
        }
        if (item.getItemId() == R.id.action_delete_coche) {
            confirmarBorrado();
            return true;
        }
        return false;
    }

    private void confirmarBorrado(){
        new AlertDialog.Builder(this).setTitle("Eliminar Coche")
                .setMessage("¿Seguro que quieres eliminar este coche?")
                .setPositiveButton("Eliminar", (dialog, which) -> presenter.deleteCoche(coche.getId()))
                .setNegativeButton("Cancelar",null)
                .show();
    }
    @Override
    public void onCocheDeleted(){
        finish();
    }
}