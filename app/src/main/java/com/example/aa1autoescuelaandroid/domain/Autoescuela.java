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
    private LocalDate fechaApertura;
    private float rating;
    private boolean activa;
//    private List<CocheOutDto> coches;
//    private List<ProfesorOutDto> profesores;
    private double latitud;
    private double longitud;


    public Autoescuela(long id, String nombre, String direccion, String ciudad, String telefono, String email, LocalDate fechaApertura, float rating, boolean activa, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
        this.fechaApertura = fechaApertura;
        this.rating = rating;
        this.activa = activa;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}