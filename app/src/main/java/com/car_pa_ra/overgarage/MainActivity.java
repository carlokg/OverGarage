package com.car_pa_ra.overgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

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
                case R.id.social:
                    //selectedFragment = new SocialFragment();
                    break;
                case R.id.perfil:
                    //selectedFragment = new PerfilFragment();
                    break;
                case R.id.ayuda:
                    //selectedFragment = new AyudaFragment();
                    break;
                case R.id.config:
                    //showAlertDialogButtonClicked(MainActivity.this);
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

    public void selectFrgment(String texto) {

        //ESTE METODO ESTA POR VER SI LO DESARROLLAMOS, PORQUE LO HICIMOS PARA AJUSTES

    }
}