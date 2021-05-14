package com.car_pa_ra.overgarage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.car_pa_ra.overgarage.model.Usuario;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegistroActivity extends AppCompatActivity {

    private static final int RC_PHOTO_ADJ = 555;
    FirebaseAuth fba;
    FirebaseUser fbu;
    ImageView imgLogin;
    TextInputLayout etNumUser;
    TextInputLayout etEmail;
    TextInputLayout etPass;
    TextInputLayout etPass2;
    TextInputLayout etCiudad;
    TextInputLayout etTaller;

    Uri selectedUri;
    StorageReference mFotoStorageRef;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNumUser = findViewById(R.id.etNomUsuario);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPw);
        etPass2 = findViewById(R.id.etPw2);
        etCiudad = findViewById(R.id.etCiudad);
        etTaller = findViewById(R.id.etTaller);
        imgLogin = findViewById(R.id.imgLogin);

        fba = FirebaseAuth.getInstance();
        fbu = fba.getCurrentUser();

        Uri selectedUri;
        myRef = FirebaseDatabase.getInstance().getReference("datos").child("user");
        mFotoStorageRef = FirebaseStorage.getInstance().getReference().child("FotoUser");
    }


    public void btnRegistrarse(View view) {
        final String sNomUser = etNumUser.getEditText().getText().toString().trim();
        final String sEmail = etEmail.getEditText().getText().toString().trim();
        final String sPass = etPass.getEditText().getText().toString().trim();
        final String sPass2 = etPass2.getEditText().getText().toString().trim();
        final String sCiu = etCiudad.getEditText().getText().toString().trim();
        final String sTaller = etTaller.getEditText().getText().toString().trim();

        if(sNomUser.isEmpty() || sEmail.isEmpty() || sPass.isEmpty() || sPass.isEmpty() ||
                sPass2.isEmpty() || sCiu.isEmpty() || sTaller.isEmpty() || selectedUri == null) {

            Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();
        }else if(sPass.equals(sPass2)){
            fba.createUserWithEmailAndPassword(sEmail, sPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                fbu = fba.getCurrentUser();
                                final StorageReference fotoRef = mFotoStorageRef
                                        .child(selectedUri.getEncodedPath());
                                UploadTask ut = fotoRef.putFile(selectedUri);

                                Task<Uri> urlTask = ut.continueWithTask(
                                        new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                            @Override
                                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task)
                                                    throws Exception {
                                                if (!task.isSuccessful()) {
                                                    throw task.getException();
                                                }
                                                return fotoRef.getDownloadUrl();
                                            }
                                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if (task.isSuccessful()) {
                                            Uri downloadUri = task.getResult();
                                            Usuario u = new Usuario(downloadUri.toString(),
                                                    fbu.getUid(), sNomUser, sEmail,sCiu, sTaller );


                                            myRef.child(fbu.getUid()+"_"+sNomUser)
                                                    .setValue(u);
                                            Log.d("UID: ", fbu.getUid()+"_"+sNomUser);
                                        }
                                    }
                                });
                                // ACCEDER A LA APLICACIÓN
                                Toast.makeText(RegistroActivity.this,
                                        R.string.msj_registrado,
                                        Toast.LENGTH_SHORT).show();
                                accederApp();
                            } else {
                                Toast.makeText(RegistroActivity.this,
                                        getString(R.string.msj_no_registrado)
                                                + "\n" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } } });

        } else {

            Toast.makeText(RegistroActivity.this,
                    R.string.msj_pw_iguales,
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void accederApp() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void adjuntarFotoUser(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.CATEGORY_APP_GALLERY, true);
        startActivityForResult(Intent.createChooser(intent,
                "abriendo galeria"), RC_PHOTO_ADJ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_PHOTO_ADJ && resultCode == RESULT_OK) {
            selectedUri = data.getData();

            Glide.with(imgLogin.getContext())
                    .load(selectedUri)
                    .circleCrop()
                    .into(imgLogin);
        }
    }
}




























