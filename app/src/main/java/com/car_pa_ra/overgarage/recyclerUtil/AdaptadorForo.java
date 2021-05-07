package com.car_pa_ra.overgarage.recyclerUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.R;
import com.car_pa_ra.overgarage.model.Post;

import java.util.List;

public class AdaptadorForo extends RecyclerView.Adapter<AdaptadorForo.GrupoViewHolder>
        implements View.OnClickListener{

    private List<Post> items;
    private View.OnClickListener listener;

    public static class GrupoViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagenForo;
        public TextView tituloForo;
        public TextView descripcionForo;

        public GrupoViewHolder(View v) {
            super(v);

            imagenForo =  v.findViewById(R.id.imagenCard);
            tituloForo =  v.findViewById(R.id.tituloCard);
            descripcionForo =  v.findViewById(R.id.descripcionCard);

        }
    }

    public AdaptadorForo (List<Post> items) {
        this.items = items;

    }

    @Override
    public void onClick(View v) {
        if(listener !=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public AdaptadorForo.GrupoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.categoria_foro_card, viewGroup, false);

        v.setOnClickListener( listener );
        return new GrupoViewHolder(v);

    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorForo.GrupoViewHolder holder, int position) {
        Glide.with(holder.imagenForo).load(items.get(position).getImagen()).into(holder.imagenForo);
        holder.tituloForo.setText(items.get(position).getTitulo());
        holder.descripcionForo.setText(items.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
