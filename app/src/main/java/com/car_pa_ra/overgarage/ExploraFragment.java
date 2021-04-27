package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.car_pa_ra.overgarage.model.Grupo;
import com.car_pa_ra.overgarage.model.MyViewModel;
import com.car_pa_ra.overgarage.recyclerUtil.Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExploraFragment extends Fragment {

    private RecyclerView recycler;
    private Adapter adapter;
    private RecyclerView.LayoutManager llm;
    private MyViewModel viewModel;
    private ArrayList<Grupo> lGrupos;

    DatabaseReference dbRef;
    ValueEventListener vel;

    public ExploraFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explora, container, false);
        //TODO: ¡¡¡¡ PONER REFERENCIA BASE DE DATOS !!!!!
        dbRef = FirebaseDatabase.getInstance()
                .getReference("datos/grupo");
        dbRef.child("datos/grupo").child("sss");
        dbRef.setValue("s");

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);

        recycler = view.findViewById(R.id.rvExplora);
        recycler.setHasFixedSize(true);

        lGrupos = new ArrayList<Grupo>();

        return view;
    }

    private void cargarListaGrupos() {
        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);

        adapter = new Adapter(lGrupos);
        adapter.setListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gNum = recycler.getChildAdapterPosition( v);
                Grupo g = lGrupos.get( gNum );
                viewModel.setG( g );
                getFragmentManager()
                        .beginTransaction()
                        //TODO: Esta linea para cargar el siguiente Fragment (CATEGORIAS)
                        //.replace(R.id.fragment_container, new CategoriasFragment())
                        .addToBackStack(null)
                        .commit();
            }
        } );
        recycler.setAdapter(adapter);
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
                    lGrupos.clear();
                    Grupo g;
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        g = dss.getValue(Grupo.class);
                        lGrupos.add(g);
                    }
                    cargarListaGrupos();

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