package com.car_pa_ra.overgarage.model;

public class Grupo {
    private String imagen;
    private String titulo;
    private String descripcion;

    public Grupo() {
    }

    public Grupo(String imagen, String titulo, String descripcion) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
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
