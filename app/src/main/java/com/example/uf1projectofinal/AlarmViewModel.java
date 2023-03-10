package com.example.uf1projectofinal;
import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmViewModel extends AndroidViewModel {
    MutableLiveData<Alarma.Respuesta> alarmas = new MutableLiveData<>();
    AlarmaReciver alarmaReciver = new AlarmaReciver();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    View view;
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public AlarmViewModel(@NonNull Application application) {
        super(application);
    }

    public void buscar(String pos,View view){
        this.view=view;
        Alarma.api.buscar(pos).enqueue(new Callback<Alarma.Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Alarma.Respuesta> call, @NonNull Response<Alarma.Respuesta> response) {
                alarmas.postValue(response.body());
                alarmaReciver.setListTimers(alarmas);
            }

            @Override
            public void onFailure(@NonNull Call<Alarma.Respuesta> call, @NonNull Throwable t) {
                alarmaReciver.sendError(view);
            }
        });
    }
}