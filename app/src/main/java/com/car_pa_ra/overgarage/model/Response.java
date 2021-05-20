package com.car_pa_ra.overgarage.model;

import java.util.ArrayList;

public class Response {

    private String text;
    private String img;

    public Response() {
    }

    public Response(String text, String img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }
    public String getImg() {
        return img;
    }
}
