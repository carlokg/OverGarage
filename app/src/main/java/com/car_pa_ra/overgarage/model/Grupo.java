package com.car_pa_ra.overgarage.model;

public class Grupo {
    private String subforos;
    private String imagen;
    private String titulo;
    private String descripcion;

    public Grupo() {
    }

    public Grupo(String subforos, String imagen, String titulo, String descripcion) {
        this.subforos = subforos;
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getSubforos() {
        return subforos;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
