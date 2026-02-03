package com.example.aa1autoescuelaandroid.db;

import androidx.room.ColumnInfo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.aa1autoescuelaandroid.domain.Autoescuela;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class CocheEntity {
    @PrimaryKey
    private long id;
    @ColumnInfo
    private String matricula;
    @ColumnInfo
    private String marca;
    @ColumnInfo
    private String modelo;
    @ColumnInfo
    private String tipoCambio;
    @ColumnInfo
    private int kilometraje;
    @ColumnInfo
    private LocalDate fechaCompra;
    @ColumnInfo
    private float precioCompra;
    @ColumnInfo
    private boolean disponible;
    @ColumnInfo
    private Long autoescuelaId;
    @ColumnInfo
    private String image;

    public CocheEntity(long id, String matricula, String marca, String modelo, String tipoCambio, int kilometraje,
                       LocalDate fechaCompra, float precioCompra, boolean disponible, Long autoescuelaId, String image) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoCambio = tipoCambio;
        this.kilometraje = kilometraje;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.disponible = disponible;
        this.autoescuelaId = autoescuelaId;
        this.image = image;
    }
    @Ignore
    public CocheEntity() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Long getAutoescuelaId() {
        return autoescuelaId;
    }

    public void setAutoescuelaId(Long autoescuelaId) {
        this.autoescuelaId = autoescuelaId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


