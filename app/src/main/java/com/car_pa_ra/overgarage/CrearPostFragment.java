package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.car_pa_ra.overgarage.model.Categoria;
import com.car_pa_ra.overgarage.model.Post;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class CrearPostFragment extends Fragment {

    private final String img = "https://firebasestorage.googleapis.com/v0/b/overgarage-d5158.appspot.com/o/post%2Fchat.png?alt=media&token=63651f72-e192-4083-b608-48a75b46c085";

    public TextInputLayout etTitutlo;
    public TextInputLayout etDesc;
    public  TextInputLayout etModelo;
    public Button btnResponder;

    private MyViewModel vModel;

    private FirebaseAuth fba;
    private FirebaseUser fbu;

    String userId;
    String userImg;
    Categoria c;
    String cat;

    DatabaseReference myRef;
    ValueEventListener velU;
    Usuario u;

    public CrearPostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crear_post, container, false);

        etTitutlo = v.findViewById(R.id.etTitulo);
        etDesc = v.findViewById(R.id.etDesc);
        etModelo = v.findViewById(R.id.etModelo);
        btnResponder = v.findViewById(R.id.btnResp);

        myRef = FirebaseDatabase.getInstance().getReference("datos");
        vModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(MyViewModel.class);
        fba = FirebaseAuth.getInstance();
        fbu = fba.getCurrentUser();

        userId = fbu.getUid();
        c = vModel.getC();
        cat = c.getTitulo();
        addListenerU();


        btnResponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPost();
                volverAForo();
            }
        });

        return v;
    }


    private void volverAForo() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new ForosFragment())
                .addToBackStack(null)
                .commit();
    }

    private void crearPost() {
        final String titStr = etTitutlo.getEditText().getText().toString().trim();
        final String descStr = etDesc.getEditText().getText().toString().trim();
        final String modStr = etModelo.getEditText().getText().toString().trim();

        if(titStr.isEmpty()||descStr.isEmpty()||modStr.isEmpty()){


        } else{
            Post p = new Post(titStr,modStr,img ,descStr,cat,userImg,
                    modStr+"_"+userId.substring(0,5));

            Log.d("Post:", p.toString());
            myRef.child("post").child(modStr+"_"+userId.substring(0,5))
                    .setValue(p);
        }
    }

    private void addListenerU() {
        if (velU == null) {
            velU = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dss: dataSnapshot.getChildren()) {
                        if(dss.getValue(Usuario.class).getuId().equals(userId)) {
                            userImg = dss.getValue(Usuario.class).getImg();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(),
                            R.string.error_lectura, Toast.LENGTH_LONG).show();
                }
            };
            myRef.child("user").addValueEventListener(velU);
        }
    }
}