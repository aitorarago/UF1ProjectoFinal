package com.example.uf1projectofinal;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AlarmReceiver extends Fragment {
    AlarmViewModel alarmViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);

    public void start(int i){
        switch (i){
            case 0:
                setBuscador("ccff-diurn-i-tarda.json");

            case 1:
                setBuscador("ccff-diurn.json");

            case 2:
                setBuscador("ccff-tarda.json");

        }

    }
    public void setBuscador(String string){
        alarmViewModel.buscar(string);

    }
    static class ContenidoViewHolder extends RecyclerView.ViewHolder {
        ViewholderContenidoBinding binding;

        public ContenidoViewHolder(@NonNull ViewholderContenidoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ContenidosAdapter extends RecyclerView.Adapter<ContenidoViewHolder>{
        List<Alarma.Respuesta> contenidoList;

        @NonNull
        @Override
        public ContenidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ContenidoViewHolder(ViewholderContenidoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ContenidoViewHolder holder, int position) {
            Alarma.Respuesta contenido = contenidoList.get(position);

            holder.binding.title.setText(contenido.trackName);
            holder.binding.artist.setText(contenido.artistName);
            Glide.with(requireActivity()).load(contenido.artworkUrl100).into(holder.binding.artwork);
        }

        @Override
        public int getItemCount() {
            return contenidoList == null ? 0 : contenidoList.size();
        }

        void establecerListaContenido(List<Alarma.Respuesta> contenidoList){
            this.contenidoList = contenidoList;
            notifyDataSetChanged();
        }
    }
}