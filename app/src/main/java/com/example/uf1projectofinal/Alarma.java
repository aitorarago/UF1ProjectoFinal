package com.example.uf1projectofinal;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Alarma {
    static class Respuesta {
        List<Timer> timers;
    }

    static class Timer {
        int hour;
        int minute;
        String comment;
    }
    public static Api api = new Retrofit.Builder()
            .baseUrl("http://mec.elpuig.xeill.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);
    public interface Api {
        @GET("/")
        Call<Respuesta> buscar(@Query("") String texto);
    }
}
