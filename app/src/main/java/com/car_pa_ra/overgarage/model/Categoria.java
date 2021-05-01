package com.car_pa_ra.overgarage.model;

public class Categoria {

    private String nombre;
    private String descripcion;
    private String grupo;
    private String imagen;

    public Categoria(){
    }

    public Categoria(String nombre, String descripcion, String grupo, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupo = grupo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getImagen() {
        return imagen;
    }
}
