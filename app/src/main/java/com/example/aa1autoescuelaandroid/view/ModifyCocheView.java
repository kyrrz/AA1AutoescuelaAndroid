package com.example.aa1autoescuelaandroid.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aa1autoescuelaandroid.R;
import com.example.aa1autoescuelaandroid.contract.ModifyCocheContract;
import com.example.aa1autoescuelaandroid.domain.Autoescuela;
import com.example.aa1autoescuelaandroid.domain.Coche;
import com.example.aa1autoescuelaandroid.presenter.ModifyCochePresenter;
import com.example.aa1autoescuelaandroid.util.DateUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModifyCocheView extends AppCompatActivity implements ModifyCocheContract.View {

    private Spinner spinnerAutoescuelas;
    private Button btnSelectImage;
    private List<Autoescuela> autoescuelas;
    private ModifyCochePresenter presenter;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private ImageView imagePreview;
    private long cocheId;
    private Coche cocheActual;

    private EditText etMatricula, etMarca, etModelo, etTipoCambio, etKilometraje, etFechaCompra, etPrecioCompra;
    private SwitchMaterial switchDisponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_coche);

        cocheId = getIntent().getLongExtra("COCHE_ID", -1);
        if (cocheId == -1) {
            Toast.makeText(this, "Error: ID de coche no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        presenter = new ModifyCochePresenter(this, this);

        imagePreview = findViewById(R.id.coche_image);
        spinnerAutoescuelas = findViewById(R.id.coche_spinner_autoescuelas);
        btnSelectImage = findViewById(R.id.btn_select_image);

        etMatricula = findViewById(R.id.coche_matricula);
        etMarca = findViewById(R.id.coche_marca);
        etModelo = findViewById(R.id.coche_modelo);
        etTipoCambio = findViewById(R.id.coche_tipo_cambio);
        etKilometraje = findViewById(R.id.coche_kilometraje);
        etFechaCompra = findViewById(R.id.coche_fecha_compra);
        etPrecioCompra = findViewById(R.id.coche_precio_compra);
        switchDisponible = findViewById(R.id.coche_disponible);

        btnSelectImage.setOnClickListener(v -> presenter.onSelectImageClicked());

        presenter.loadAutoescuelas();
        presenter.loadCoche(cocheId);
    }

    public void modifyCoche(View view) {
        try {
            String matricula = etMatricula.getText().toString();
            String marca = etMarca.getText().toString();
            String modelo = etModelo.getText().toString();
            String tipoCambio = etTipoCambio.getText().toString();
            int kilometraje = Integer.parseInt(etKilometraje.getText().toString());
            LocalDate fechaCompra = DateUtil.parseDate(etFechaCompra.getText().toString());
            float precioCompra = Float.parseFloat(etPrecioCompra.getText().toString());
            boolean disponible = switchDisponible.isChecked();

            long autoescuelaId = 0;
            if (autoescuelas != null && spinnerAutoescuelas.getSelectedItemPosition() >= 0) {
                Autoescuela autoescuela = autoescuelas.get(spinnerAutoescuelas.getSelectedItemPosition());
                autoescuelaId = autoescuela.getId();
            }

            presenter.modifyCoche(cocheId, matricula, marca, modelo, tipoCambio, kilometraje,
                    fechaCompra, precioCompra, disponible, autoescuelaId);
        } catch (NumberFormatException e) {
            showValidationError("Por favor, completa todos los campos correctamente");
        }
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
    public void showAutoescuelas(List<Autoescuela> autoescuelas) {
        this.autoescuelas = autoescuelas;
        List<String> nombres = new ArrayList<>();
        for (Autoescuela a : autoescuelas){
            nombres.add(a.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombres
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAutoescuelas.setAdapter(adapter);


        if (cocheActual != null && cocheActual.getAutoescuelaId() != null) {
            seleccionarAutoescuela(cocheActual.getAutoescuelaId());
        }
    }

    @Override
    public void showImagePreview(Uri uri) {
        imageUri = uri;
        imagePreview.setImageURI(uri);
    }

    @Override
    public void populateCocheData(Coche coche) {
        this.cocheActual = coche;

        etMatricula.setText(coche.getMatricula());
        etMarca.setText(coche.getMarca());
        etModelo.setText(coche.getModelo());
        etTipoCambio.setText(coche.getTipoCambio());
        etKilometraje.setText(String.valueOf(coche.getKilometraje()));
        etFechaCompra.setText(DateUtil.formatDate(coche.getFechaCompra()));
        etPrecioCompra.setText(String.valueOf(coche.getPrecioCompra()));
        switchDisponible.setChecked(coche.isDisponible());


        if (coche.getImage() != null && !coche.getImage().isEmpty()) {
            try {
                Uri uri = Uri.parse(coche.getImage());
                imagePreview.setImageURI(uri);
            } catch (Exception e) {
                Log.e("ModifyCoche", "Error al cargar imagen: " + e.getMessage());
            }
        }


        if (autoescuelas != null && coche.getAutoescuelaId() != null) {
            seleccionarAutoescuela(coche.getAutoescuelaId());
        }
    }


    private void seleccionarAutoescuela(Long autoescuelaId) {
        if (autoescuelas != null && autoescuelaId != null) {
            for (int i = 0; i < autoescuelas.size(); i++) {
                if (autoescuelas.get(i).getId() == autoescuelaId) {
                    spinnerAutoescuelas.setSelection(i);
                    break;
                }
            }
        }
    }

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            if (selectedImageUri != null) {
                try {
                    getContentResolver().takePersistableUriPermission(
                            selectedImageUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION
                    );
                    Log.d("ModifyCoche", "Permisos persistentes tomados para: " + selectedImageUri);
                } catch (SecurityException e) {
                    Log.e("ModifyCoche", "Error al tomar permisos persistentes: " + e.getMessage());
                    Toast.makeText(this, "Advertencia: Los permisos de la imagen podrían no persistir", Toast.LENGTH_SHORT).show();
                }
            }

            presenter.onImageSelected(selectedImageUri);
        }
    }

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.returnbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back_button){
            Intent intent = new Intent(this, CocheListView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}