package com.example.uf1projectofinal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.uf1projectofinal.databinding.FragmentNuevaAlarmaBinding;

import java.util.Calendar;

public class NuevaAlarma extends Fragment {
    private FragmentNuevaAlarmaBinding binding;
    private ImageButton close;
    private Button save;
    private TimePicker picker;
    Calendar calendar;
    PendingIntent pending_intent;
    Intent intent;
    AlarmManager alarm_manager;
    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentNuevaAlarmaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = Navigation.findNavController(view);
        close=view.findViewById(R.id.closse);
        save=view.findViewById(R.id.guardar);
        picker=view.findViewById(R.id.timePicker);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                pending_intent = PendingIntent.getBroadcast(NuevaAlarma.super.getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);
                Log.d("msg119", ""+calendar.getTimeInMillis()+" "+pending_intent);

                navController.navigate(R.id.alarmaReciver);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.alarmaReciver);
            }
        });
    }
}