package com.example.aa1autoescuelaandroid.domain;



import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
    private Long autoescuelaId;
    private Autoescuela autoescuela;
    private String image;

    public Coche(long id, String matricula, String marca, String modelo, String tipoCambio, int kilometraje, LocalDate fechaCompra, float precioCompra, boolean disponible, Long autoescuelaId, Autoescuela autoescuela, String image) {
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
        this.autoescuela = autoescuela;
        this.image = image;
    }}

