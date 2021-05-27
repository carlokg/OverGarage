package com.car_pa_ra.overgarage.recyclerUtil;

import androidx.lifecycle.ViewModel;

import com.car_pa_ra.overgarage.model.Categoria;
import com.car_pa_ra.overgarage.model.Grupo;
import com.car_pa_ra.overgarage.model.Post;
import com.car_pa_ra.overgarage.model.Usuario;

public class MyViewModel extends ViewModel {

    public Usuario u;
    public Post p;
    public Grupo g;
    public Categoria c;

    public Categoria getC() {
        return c;
    }
    public void setC(Categoria c) {
        this.c = c;
    }

    public Grupo getG() {
        return g;
    }
    public void setG(Grupo g) {
        this.g = g;
    }

    public Post getP(){
        return p;
    }
    public void setP(Post p){
        this.p = p;
    }

    public Usuario getU() {
        return u;
    }
    public void setU(Usuario u) {
        this.u = u;
    }
}
