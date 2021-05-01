package com.car_pa_ra.overgarage.recyclerUtil;

import com.car_pa_ra.overgarage.model.Grupo;
import com.car_pa_ra.overgarage.model.Post;

public class MyViewModel {

    public Post p;
    public Grupo g;

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

}
