package com.example.uf1projectofinal;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmViewModel extends AndroidViewModel {
    MutableLiveData<Alarma.Respuesta> respuestaMutableLiveData = new MutableLiveData<>();

    public AlarmViewModel(@NonNull Application application) {
        super(application);
    }

    public void buscar(String texto){
        Alarma.api.buscar(texto).enqueue(new Callback<Alarma.Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Alarma.Respuesta> call, @NonNull Response<Alarma.Respuesta> response) {
                respuestaMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Alarma.Respuesta> call, @NonNull Throwable t) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.sendError();
            }
        });
    }

}