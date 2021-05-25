package com.car_pa_ra.overgarage.model;

import java.util.Map;

public class Post {

    private String titulo;
    private String descripcion;
    private String imagen;
    private String descPro;
    private String categoria;
    private String userUid;
    private Map<String,Response> responses;
    private String idPost;

    public Post() {
    }

    public Post(String titulo, String descripcion, String imagen, String descPro, String categoria, String userUid, String idPost) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.descPro = descPro;
        this.categoria = categoria;
        this.userUid = userUid;
        this.idPost = idPost;
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

    public Map<String,Response> getResponses() {
        return responses;
    }

    public String getDescPro() {
        return descPro;
    }

    public String getIdPost() {
        return idPost;
    }

    @Override
    public String toString() {
        return "Post{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descPro='" + descPro + '\'' +
                ", categoria='" + categoria + '\'' +
                ", userUid='" + userUid + '\'' +
                ", idPost='" + idPost + '\'' +
                '}';
    }
}
