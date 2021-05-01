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
import com.car_pa_ra.overgarage.recyclerUtil.AdaptadorGrupos;
import com.car_pa_ra.overgarage.recyclerUtil.MyViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExploraFragment extends Fragment {

    private RecyclerView recycler;
    private AdaptadorGrupos adaptadorGrupos;
    private RecyclerView.LayoutManager llm;

    DatabaseReference dbRef;
    ValueEventListener vel = null;

    private MyViewModel viewModel;
    private ArrayList<Grupo> lGrupos;

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

        dbRef = FirebaseDatabase.getInstance()
                .getReference("datos/grupos");

        recycler = view.findViewById(R.id.rvExplora);
        recycler.setHasFixedSize(true);

        lGrupos = new ArrayList<>();
        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);


        return view;
    }

    private void cargarListaGrupos() {
        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);
        adaptadorGrupos = new AdaptadorGrupos(lGrupos);
        adaptadorGrupos.setListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gNum = recycler.getChildAdapterPosition(v);
                Grupo g = lGrupos.get(gNum);
                viewModel.setG(g);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new CategoriaFragment())
                        .addToBackStack(null)
                        .commit();
            }
        } );
        recycler.setAdapter(adaptadorGrupos);
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
                    Grupo g;
                    lGrupos.clear();
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