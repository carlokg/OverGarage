package com.car_pa_ra.overgarage.model;

import java.util.ArrayList;

public class Response {

    private String text;
    private String userNom;
    private String img;

    public Response() {
    }

    public Response(String text, String img, String userNom) {
        this.text = text;
        this.userNom = userNom;
        this.img = img;
    }

    public String getText() {
        return text;
    }
    public String getImg() {
        return img;
    }
    public String getUserNom() {
        return userNom;
    }
}
