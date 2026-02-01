package com.example.aa1autoescuelaandroid.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Autoescuela {
    private long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String email;
    private int capacidad;
    private LocalDate fechaApertura;
    private float rating;
    private boolean activa;
//    private List<CocheOutDto> coches;
//    private List<ProfesorOutDto> profesores;
    private double latitud;
    private double longitud;


    public Autoescuela(long id, String nombre, String direccion, String ciudad, String telefono, String email, int capacidad, LocalDate fechaApertura, float rating, boolean activa, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
        this.capacidad = capacidad;
        this.fechaApertura = fechaApertura;
        this.rating = rating;
        this.activa = activa;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Autoescuela() {

    }

}