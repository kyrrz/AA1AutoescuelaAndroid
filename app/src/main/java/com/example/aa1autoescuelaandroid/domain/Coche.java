package com.example.aa1autoescuelaandroid.domain;

import androidx.room.Entity;

import java.time.LocalDate;

@Entity
public class Coche {

    private long id;
    private String matricula;
    private String marca;
    private String modelo;
    private String tipoCambio;
    private int kilometraje;
    private LocalDate fechaCompra;
    private float precioCompra;
    private boolean disponible;
    private Autoescuela autoescuela;

    public Coche(long id, String matricula, String marca, String modelo, String tipoCambio, int kilometraje, LocalDate fechaCompra, float precioCompra, boolean disponible, Autoescuela autoescuela) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoCambio = tipoCambio;
        this.kilometraje = kilometraje;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.disponible = disponible;
        this.autoescuela = autoescuela;
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

    public Autoescuela getAutoescuela() {
        return autoescuela;
    }

    public void setAutoescuela(Autoescuela autoescuela) {
        this.autoescuela = autoescuela;
    }
}
