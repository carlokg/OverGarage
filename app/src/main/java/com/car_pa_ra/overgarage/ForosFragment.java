package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.model.Categoria;
import com.car_pa_ra.overgarage.model.Post;
import com.car_pa_ra.overgarage.recyclerUtil.AdaptadorForo;
import com.car_pa_ra.overgarage.recyclerUtil.MyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForosFragment extends Fragment {

    private RecyclerView recycler;
    private AdaptadorForo adaptadorForo;
    private RecyclerView.LayoutManager llm;
    private TextView txtNombreForo;
    private TextView txtDescForo;
    private ImageView imgForos;
    DatabaseReference dbRef;
    ValueEventListener vel;
    Categoria c;

    private TextView tvNomForo;
    private TextView tvDescForo;
    private ImageView imgForo;

    private MyViewModel viewModel;
    FloatingActionButton fabCrear;

    private ArrayList<Post> lPost;

    public ForosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_foros, container, false);

        dbRef = FirebaseDatabase.getInstance()
                .getReference("datos/post");

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);
        recycler = view.findViewById(R.id.rvForo);
        txtDescForo = view.findViewById(R.id.txtDescForo);
        txtNombreForo = view.findViewById(R.id.txtNombreForo);
        imgForos = view.findViewById(R.id.imgForos);
        c = viewModel.getC();

        Glide.with(imgForos)
                .load(c.getImagen())
                .into(imgForos);

        txtNombreForo.setText(c.getTitulo());
        txtDescForo.setText(c.getDescripcion());

        recycler.setHasFixedSize(true);

        tvNomForo = view.findViewById(R.id.txtNombreForo);
        tvDescForo = view.findViewById(R.id.txtDescForo);
        imgForo = view.findViewById(R.id.imgForos);

        tvNomForo.setText(c.getTitulo());
        tvDescForo.setText(c.getDescripcion());
        Glide.with(imgForo).load(c.getImagen()).into(imgForo);



        fabCrear = view.findViewById(R.id.fabCrearPost);

        lPost = new ArrayList<>();

        fabCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, new CrearPostFragment()).commit();
            }
        });

        return view;

    }

    private void cargarListaPost() {
        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);

        adaptadorForo = new AdaptadorForo(lPost);
        adaptadorForo.setListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pNum = recycler.getChildAdapterPosition( v);
                Post p = lPost.get( pNum );
                viewModel.setP( p );
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new PostFragment())
                        .addToBackStack(null)
                        .commit();
            }
        } );
        recycler.setAdapter(adaptadorForo);
    }

    @Override
    public void onResume() {
        super.onResume();
        addListener();
    }

    private void addListener() {

        if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Post p;
                    lPost.clear();
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if(c.getTitulo().equals(dss.getValue(Post.class).getCategoria())) {
                            p = dss.getValue(Post.class);
                            lPost.add(p);
                        }
                    }
                    cargarListaPost();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(),
                            R.string.error_lectura, Toast.LENGTH_LONG).show();
                }
            };
            dbRef.addValueEventListener(vel);
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
    }

}