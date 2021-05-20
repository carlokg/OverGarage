package com.car_pa_ra.overgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    AlertDialog dialog;
    FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ExploraFragment())
                .addToBackStack(null)
                .commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.explora:
                    selectedFragment = new ExploraFragment();
                    break;
                case R.id.ajustes:
                    showAlertDialogButtonClicked(MainActivity.this);
                    break;
                case R.id.perfil:
                    //selectedFragment = new PerfilFragment();
                    break;
            }


            if(selectedFragment != null){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .addToBackStack(null)
                        .commit();
            }

            return true;

        }
    };

    private void showAlertDialogButtonClicked(MainActivity mainActivity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(getLayoutInflater().inflate(R.layout.expandable_ajustes,
                null));


        dialog = builder.create();
        dialog.show();
    }

    public void selectFrgment(String texto) {

        Fragment selectedFragment = null;

        switch (texto){
            case "about":
                selectedFragment = new AboutFragment();
                break;
            case "ayuda":
                selectedFragment = new AyudaFragment();
                break;
            case "logout":
                fba.signOut();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .addToBackStack(null)
                .commit();
    }

    public void logOut(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
    public void aboutUs(View view) {
        Fragment selectedFragment = new AboutFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .addToBackStack(null)
                .commit();

        dialog.dismiss();
    }

    public void ayuda(View view) {
        Fragment selectedFragment = new AyudaFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .addToBackStack(null)
                .commit();

        dialog.dismiss();
    }

}