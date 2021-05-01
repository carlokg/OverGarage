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
import com.car_pa_ra.overgarage.model.Categoria;

import java.util.List;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.CategoriaViewHolder>
        implements View.OnClickListener{

    private List<Categoria> items;
    private View.OnClickListener listener;

    public class CategoriaViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView titulo;
        public TextView descripcion;

        public CategoriaViewHolder(View v) {
            super(v);
            img =  v.findViewById(R.id.imagenCard);
            titulo =  v.findViewById(R.id.tituloCard);
            descripcion =  v.findViewById(R.id.descripcionCard);
        }
    }


    public AdaptadorCategoria(List<Categoria> items) {
        this.items = items;
    }

    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categoria_foro_card, viewGroup, false);

        v.setOnClickListener(listener);
        return new CategoriaViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Glide.with(holder.img)
                .load(items.get(position).getImagen())
                .into(holder.img);
        holder.titulo.setText(items.get(position).getTitulo());
        holder.descripcion.setText(items.get(position).getDescripcion());
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
