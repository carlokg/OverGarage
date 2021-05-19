package com.car_pa_ra.overgarage.recyclerUtil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.R;
import com.car_pa_ra.overgarage.model.Post;
import com.car_pa_ra.overgarage.model.Response;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorPost extends RecyclerView.Adapter<AdaptadorPost.GrupoViewHolder>
        implements View.OnClickListener{

    private List<Response> items;
    private View.OnClickListener listener;

    public static class GrupoViewHolder extends RecyclerView.ViewHolder{

        public ImageView imagenPost;
        public TextView descripcionPost;

        public GrupoViewHolder(View v) {
            super(v);

            imagenPost =  v.findViewById(R.id.rPostImg);
            descripcionPost =  v.findViewById(R.id.textCard);

        }
    }

    public AdaptadorPost (List<Response> items) {
        this.items = items;

    }

    @Override
    public void onClick(View v) {
        if(listener !=null){
            listener.onClick(v);
        }
    }

    @Override
    public AdaptadorPost.GrupoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.respuesta_post_card, viewGroup, false);

        v.setOnClickListener( listener );
        return new AdaptadorPost.GrupoViewHolder(v);

    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPost.GrupoViewHolder holder, int position) {
        Glide.with(holder.imagenPost)
                .load(items.get(position).getImg())
                .circleCrop()
                .into(holder.imagenPost);
        holder.descripcionPost.setText(items.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
