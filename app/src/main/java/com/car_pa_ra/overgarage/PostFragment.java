package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.model.Post;
import com.car_pa_ra.overgarage.model.Response;
import com.car_pa_ra.overgarage.model.Usuario;
import com.car_pa_ra.overgarage.recyclerUtil.AdaptadorForo;
import com.car_pa_ra.overgarage.recyclerUtil.AdaptadorPost;
import com.car_pa_ra.overgarage.recyclerUtil.MyViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostFragment extends Fragment {

    private MyViewModel vModel;
    Post p = null;
    String idPost;
    String idUser;
    String img = new String();

    TextView txtNomPost;
    TextView txtNomModel;
    ImageView imgUser;
    TextView txtDesc;
    Button btnResponsePost;

    List<Response> lResponse;

    private RecyclerView recycler;
    private AdaptadorPost adaptadorPost;
    private RecyclerView.LayoutManager llm;

    DatabaseReference dbRef;
    ValueEventListener vel;
    ValueEventListener velU;
    ValueEventListener velR;

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
        btnResponsePost = v.findViewById(R.id.btnResponsePost);

        dbRef = FirebaseDatabase.getInstance()
                .getReference("datos");

        vModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);
        idPost = vModel.getP().getIdPost();
        idUser = vModel.getP().getUserUid();

        lResponse = new ArrayList<>();

        recycler = v.findViewById(R.id.rvPost);
        recycler.setHasFixedSize(true);

        addListener();
        addListenerU();
        addListenerR();

        btnResponsePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, new RespondFragment()).commit();
            }
        });


        return v;
    }


    public void cargarPost(){

        txtNomPost.setText(p.getTitulo());
        txtNomModel.setText(p.getDescripcion());
        Glide.with(imgUser)
                .load(img)
                .circleCrop()
                .into(imgUser);
        txtDesc.setText(p.getDescPro());
        cargarResponse();
    }
    private void cargarResponse() {
        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);


        if(lResponse != null){
            adaptadorPost = new AdaptadorPost(lResponse);
            recycler.setAdapter(adaptadorPost);
        }
    }

    private void addListenerU() {
        if (velU == null) {
            velU = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if(dss.getValue(Usuario.class).getuId().equals(idUser)) {
                            img = dss.getValue(Usuario.class).getImg();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(),
                            R.string.error_lectura, Toast.LENGTH_LONG).show();
                }
            };
            dbRef.child("user").addValueEventListener(velU);
        }
    }
    private void addListenerR() {
        if (velR == null) {
            velR = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        Response r = dss.getValue(Response.class);
                        lResponse.add(r);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(),
                            R.string.error_lectura, Toast.LENGTH_LONG).show();
                }
            };
            dbRef.child("post/"+idPost+"/responses").addValueEventListener(velR);
        }
    }
    private void addListener() {
        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if(idPost.equals(dss.getValue(Post.class).getIdPost())) {
                            p = dss.getValue(Post.class);

                            cargarPost();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(),
                            R.string.error_lectura, Toast.LENGTH_LONG).show();
                }
            };
            dbRef.child("post").addValueEventListener(vel);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        removeListener();
    }

    private void removeListener() {
        if (vel != null) {
            dbRef.removeEventListener(vel);
            vel = null;
        }
        if (velU != null) {
            dbRef.removeEventListener(velU);
            velU = null;
        }
        if (velR != null) {
            dbRef.removeEventListener(velR);
            velR = null;
        }

    }
}