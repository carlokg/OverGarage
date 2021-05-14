package com.car_pa_ra.overgarage.model;

public class Usuario {

    private String img;
    private String uId;
    private String nombre;
    private String email;
    private String ciu;
    private String taller;

    public  Usuario(){}

    public Usuario(String img, String uId, String nombre, String email, String ciu, String taller) {
        this.img = img;
        this.uId = uId;
        this.nombre = nombre;
        this.email = email;
        this.ciu = ciu;
        this.taller = taller;
    }

    public String getImg() {
        return img;
    }
    public String getuId() {
        return uId;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getCiu() {
        return ciu;
    }
    public String getTaller() {
        return taller;
    }
}
