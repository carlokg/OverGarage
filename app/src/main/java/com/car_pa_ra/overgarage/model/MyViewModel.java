package com.car_pa_ra.overgarage.model;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    public Grupo g;

    public Grupo getG() {
        return g;
    }

    public void setG(Grupo g) {
        this.g = g;
    }
}