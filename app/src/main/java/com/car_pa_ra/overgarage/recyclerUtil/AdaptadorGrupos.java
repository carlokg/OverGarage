package com.car_pa_ra.overgarage.recyclerUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.R;
import com.car_pa_ra.overgarage.model.Grupo;

import java.util.List;

public class AdaptadorGrupos extends RecyclerView.Adapter<AdaptadorGrupos.GrupoViewHolder>
        implements View.OnClickListener{

    private List<Grupo> items;
    private View.OnClickListener listener;

    public class GrupoViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagen;
        public TextView titulo;
        public TextView descripcion;
        public TextView numCategorias;


        public GrupoViewHolder(View v) {
            super(v);
            imagen =  v.findViewById(R.id.imagen);
            titulo =  v.findViewById(R.id.titulo);
            descripcion =  v.findViewById(R.id.descripcion);
            numCategorias = v.findViewById(R.id.numCategorias);
        }
    }

    public AdaptadorGrupos(List<Grupo> items) {
        this.items = items;
    }

    @Override
    public GrupoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grupo_card, viewGroup, false);

        v.setOnClickListener( listener );
        return new GrupoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorGrupos.GrupoViewHolder holder, int position) {
        Glide.with(holder.imagen)
                .load(items.get(position).getImagen())
                .into(holder.imagen);
        holder.titulo.setText(items.get(position).getTitulo());
        holder.descripcion.setText(items.get(position).getDescripcion());
        holder.numCategorias.setText(items.get(position).getNumCategorias());
    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if(listener !=null){
            listener.onClick(v);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
