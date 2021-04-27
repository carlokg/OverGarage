package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.car_pa_ra.overgarage.model.Post;
import com.car_pa_ra.overgarage.recyclerUtil.MyViewModel;

import java.util.ArrayList;

public class ForosFragment extends Fragment {

    private RecyclerView recycler;
    private AdaptadorForo adapter;
    private RecyclerView.LayoutManager llm;
    DatabaseReference dbRef;
    ValueEventListener vel;

    private MyViewModel viewModel;

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

        /*dbRef = FirebaseDatabase.getInstance()
                .getReference("");*/

        viewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);

        recycler = view.findViewById(R.id.rvForo);
        recycler.setHasFixedSize(true);

        lPost = new ArrayList<Post>();

        return view;

    }

    private void cargarListaPost() {
        llm = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(llm);

        adapter = new AdaptadorForo(lPost);
        adapter.setListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pNum = recycler.getChildAdapterPosition( v);
                Post p = lPost.get( pNum );
                viewModel.setP( p );
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new InfoGrupoFragment())
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
        /*if (vel == null) {
            vel = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    lGrupos.clear();
                    Grupos g;
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        g = dss.getValue(Grupos.class);
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
        }*/
    }



    @Override
    public void onPause() {
        super.onPause();
        removeListener();

    }

    private void removeListener() {
        /*if (vel != null) {
            dbRef.removeEventListener(vel);
            vel = null;
        }*/
    }

}