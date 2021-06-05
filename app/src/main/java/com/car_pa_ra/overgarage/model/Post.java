 package com.car_pa_ra.overgarage.model;

import java.util.Map;

public class Post {

    private String titulo;
    private String descripcion;
    private String imagen;
    private String descPro;
    private String categoria;
    private String userImg;
    private Map<String,Response> responses;
    private String idPost;

    public Post() {
    }

    public Post(String titulo, String descripcion, String imagen, String descPro, String categoria, String userImg, String idPost) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.descPro = descPro;
        this.categoria = categoria;
        this.userImg = userImg;
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

    public String getUserImg() {
        return userImg;
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

    public void setResponses(Map<String, Response> responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "Post{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descPro='" + descPro + '\'' +
                ", categoria='" + categoria + '\'' +
                ", userUid='" + userImg + '\'' +
                ", idPost='" + idPost + '\'' +
                '}';
    }
}
