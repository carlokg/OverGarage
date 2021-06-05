package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.car_pa_ra.overgarage.model.Post;
import com.car_pa_ra.overgarage.model.Response;
import com.car_pa_ra.overgarage.model.Usuario;
import com.car_pa_ra.overgarage.recyclerUtil.MyViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class RespondFragment extends Fragment {

    public TextInputLayout etResponder;
    public Button btnResponder;

    DatabaseReference myRef;

    private MyViewModel vModel;
    Usuario u;
    Post p;
    String postId;

    public RespondFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_respond, container, false);

        etResponder = v.findViewById(R.id.etResponder);
        btnResponder = v.findViewById(R.id.btnResponder);

        myRef = FirebaseDatabase.getInstance()
                .getReference("datos/post");

        vModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);
        p = vModel.getP();
        u = vModel.getU();
        postId = p.getIdPost();



        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesarRespuesta();
                volverAPost();
            }
        });

        return v;
    }

    public void  procesarRespuesta(){
        String respuesta = etResponder.getEditText().getText().toString().trim();
        String img = u.getImg();
        String userNom = u.getNombre();

        if(respuesta.isEmpty()){
            Toast.makeText(getContext(), R.string.warn_no_response, Toast.LENGTH_SHORT).show();
        } else{
            Response r = new Response(respuesta,img,userNom);
            myRef.child(postId).child("responses").push().setValue(r);
        }
    }

    public void volverAPost() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new PostFragment())
                .addToBackStack(null)
                .commit();
    }
}