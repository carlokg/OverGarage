package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.model.Post;
import com.car_pa_ra.overgarage.recyclerUtil.AdaptadorForo;
import com.car_pa_ra.overgarage.recyclerUtil.MyViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PostFragment extends Fragment {

    private MyViewModel vModel;
    Post p;

    TextView txtNomPost;
    TextView txtNomModel;
    ImageView imgUser;
    TextView txtDesc;

    public PostFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_post, container, false);

        txtNomPost = v.findViewById(R.id.txtNomPost);
        txtNomModel = v.findViewById(R.id.txtNomModel);
        imgUser = v.findViewById(R.id.imgUser);
        txtDesc = v.findViewById(R.id.txtDesc);

        vModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);
        p = vModel.getP();

        cargarPost();

        return v;
    }


    public void cargarPost(){
        txtNomPost.setText(p.getTitulo());
        txtNomModel.setText(p.getDescripcion());
        Glide.with(imgUser.getContext())
                .load(p.getUser().getImg())
                .into(imgUser);
        txtDesc.setText("Holaaalasaldadadalalafamtmafalfamfalamaflafmaflafmalfmalfmaf\nafojfwgagqqq");
    }
}