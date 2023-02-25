package com.example.uf1projectofinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uf1projectofinal.databinding.FragmentAlarmasTotBinding;
import com.google.android.material.snackbar.Snackbar;

public class AlarmasTot extends Fragment {
    NavController navController;
    View view;
    FragmentAlarmasTotBinding binding;

    private Button buttontots;
    private Button buttonmati;
    private Button buttontarda;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentAlarmasTotBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view=view;
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        buttontots=view.findViewById(R.id.ciclesformtiustots);
        AlarmaReciver alarmReceiver = new AlarmaReciver();

        buttontots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmReceiver.start(0);
            }
        });
        buttonmati=view.findViewById(R.id.ciclesformtiusmati);
        buttonmati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmReceiver.start(1);
            }
        });
        buttontarda=view.findViewById(R.id.ciclesformtiustarda);
        buttontarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmReceiver.start(2);
            }
        });


    }
    public void sendError(){
        Snackbar.make(view.findViewById(R.id.mainview),"ERROR AL INTENTAR DESCARGAR LAS ALARMAS EN EL DISPOSITIVO", Snackbar.LENGTH_SHORT).show();
    }
}

