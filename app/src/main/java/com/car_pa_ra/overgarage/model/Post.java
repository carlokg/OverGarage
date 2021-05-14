package com.car_pa_ra.overgarage.model;

public class Post {

    private String titulo;
    private String descripcion;
    private String imagen;
    private  String categoria;
    private String userUid;

    public Post() {
    }

    public Post(String titulo, String descripcion, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getUserUid() {
        return userUid;
    }
}
