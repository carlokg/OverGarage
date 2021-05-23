package com.car_pa_ra.overgarage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AboutFragment extends Fragment {

    ImageView imgp;
    ImageView imgc;
    ImageView imgr;
    public AboutFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        imgr = view.findViewById(R.id.img3);
        imgc = view.findViewById(R.id.img1);
        imgp = view.findViewById(R.id.img2);

        Glide.with(this).load(R.drawable.foto_c).circleCrop().into(imgc);
        Glide.with(this).load(R.drawable.foto_p).circleCrop().into(imgp);
        Glide.with(this).load(R.drawable.foto_r).circleCrop().into(imgr);
        return view;
    }
}