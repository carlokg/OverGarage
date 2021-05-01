package com.car_pa_ra.overgarage.model;

public class Categoria {

    private String titulo;
    private String descripcion;
    private String grupo;
    private String img;

    public Categoria(){
    }

    public Categoria(String titulo, String descripcion, String grupo, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.grupo = grupo;
        this.img = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getImagen() {
        return img;
    }
}
