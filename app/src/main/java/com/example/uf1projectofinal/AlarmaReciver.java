package com.example.uf1projectofinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uf1projectofinal.databinding.FragmentAlarmBinding;
import com.example.uf1projectofinal.databinding.ViewholderContenidoBinding;

import java.util.List;

public class AlarmaReciver extends Fragment {
    private FragmentAlarmBinding binding;
    AlarmViewModel alarmViewModel;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (this.binding = FragmentAlarmBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        alarmViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);
        ContenidosAdapter contenidosAdapter = new ContenidosAdapter();
        binding.recyclerviewAlarmas.setAdapter(contenidosAdapter);
    }
    public void start(int i){
        switch (i){
            case 0:
                setBuscador("ccff-diurn-i-tarda.json");
                break;
            case 1:
                setBuscador("ccff-diurn.json");
                break;
            case 2:
                setBuscador("ccff-tarda.json");
                break;
        }

    }
    public void setBuscador(String string){
        alarmViewModel.buscar(string);
    }
    static class ContenidoViewHolder extends RecyclerView.ViewHolder {
        ViewholderContenidoBinding binding;
        private TextView hora;

        public ContenidoViewHolder(@NonNull ViewholderContenidoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            hora=binding.comment;
        }
        public void bind(Alarma.Respuesta respuesta){
            hora.setText((CharSequence) respuesta.timers.get(2));
        }
    }
    class ContenidosAdapter extends RecyclerView.Adapter<AlarmaReciver.ContenidoViewHolder> {
        List<Alarma.Respuesta> contenidoList;

        @NonNull
        @Override
        public AlarmaReciver.ContenidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ContenidoViewHolder(ViewholderContenidoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AlarmaReciver.ContenidoViewHolder holder, int position) {
            Alarma.Respuesta contenido = contenidoList.get(position);
            holder.binding.comment.setText((CharSequence) contenido);
        }

        @Override
        public int getItemCount() {
            return contenidoList == null ? 0 : contenidoList.size();
        }

        public void setContenidoList(List<Alarma.Respuesta> contenidoList) {
            this.contenidoList = contenidoList;
            notifyDataSetChanged();
        }
    }
}