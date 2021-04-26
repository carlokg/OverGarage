package com.car_pa_ra.overgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout etEmail;
    TextInputLayout etPass;
    private FirebaseAuth fba;
    private FirebaseUser fbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        fba = FirebaseAuth.getInstance();
        fbu = fba.getCurrentUser();

        if( fbu != null){
            etEmail.getEditText().setText(fbu.getEmail());
        }
    }


    public void btnLogin(View view) {
        String email = etEmail.getEditText().getText().toString().trim();
        String pass = etPass.getEditText().getText().toString().trim();
        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, getString(R.string.no_data),
                    Toast.LENGTH_LONG).show();
        } else{
            fba.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                fbu = fba.getCurrentUser();
                                accederApp();
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        getString(R.string.msj_no_accede)
                                                + "\n" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Log.i("Sing in", "error: "+ task.getException().getMessage());
                            }
                        }
                    });
        }
    }

    public void accederApp() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void btnRegistrarse(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }


}