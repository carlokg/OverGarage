package com.car_pa_ra.overgarage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilFragment extends Fragment {

    private OnControlerFragmentListener listener;

    ImageView imgUsuario;
    TextView tvUsername;
    TextView tvCorreoUsuario;
    TextView tvLocalizacion;
    TextView tvTaller;
    LinearLayout llLocation;
    DatabaseReference dbRef;
    ValueEventListener vel;
    Usuario user = new Usuario();
    FirebaseUser userFb;
    FirebaseAuth fba;

    public PerfilFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        imgUsuario = view.findViewById(R.id.imgUsuario);
        //Glide.with(this).load(R.drawable.perfil).into(imgUsuario);

        tvUsername = view.findViewById(R.id.tvUsername);
        tvCorreoUsuario = view.findViewById(R.id.tvCorreoUsuario);
        tvLocalizacion = view.findViewById(R.id.tvLocalizacion);
        llLocation = view.findViewById(R.id.llLocation);
        tvTaller = view.findViewById(R.id.tvTaller);
        fba = FirebaseAuth.getInstance();
        userFb = fba.getCurrentUser();

        dbRef = FirebaseDatabase.getInstance().getReference("datos/user");

        return view;
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

                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if (userFb.getUid().equals(dss.getValue(Usuario.class).getuId())){
                            user = dss.getValue(Usuario.class);
                        }
                    }

                    cargarUser();

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

    private void cargarUser() {
        Glide.with(imgUsuario.getContext())
                .load(user.getImg())
                .circleCrop()
                .into(imgUsuario);

        tvUsername.setText(user.getNombre());
        tvCorreoUsuario.setText(user.getEmail());
        tvLocalizacion.setText(user.getCiu());
        tvTaller.setText(user.getTaller());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnControlerFragmentListener) {
            listener = (OnControlerFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
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