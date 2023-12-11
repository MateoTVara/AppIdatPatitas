package pe.edu.idat.appidatpatitas.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pe.edu.idat.appidatpatitas.databinding.ItemMascotaBinding;
import pe.edu.idat.appidatpatitas.retrofit.response.MascotaResponse;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.ViewHolder> {

    List<MascotaResponse> mascotaResponseList = new ArrayList<>();
    List<MascotaResponse> mascotaResponseListOriginal = new ArrayList<>();

    @NonNull
    @Override
    public MascotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemMascotaBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull MascotaAdapter.ViewHolder holder, int position) {
        final MascotaResponse mascotaResponse = mascotaResponseList
                .get(position);
        holder.binding.tvnombre.setText(mascotaResponse.getNommascota());
        holder.binding.tvcontacto.setText(mascotaResponse.getContacto());
        holder.binding.tvfecha.setText(mascotaResponse.getFechaperdida());
        holder.binding.tvlugar.setText(mascotaResponse.getLugar());
        Glide.with(holder.binding.getRoot())
                .load(mascotaResponse.getUrlimagen())
                .into(holder.binding.ivmascota);
    }
    @Override
    public int getItemCount() {
        return mascotaResponseList.size();
    }

    public void setMascotas(List<MascotaResponse> mascotas){
        mascotaResponseList.addAll(mascotas);
        notifyDataSetChanged();
        mascotaResponseListOriginal.addAll(mascotas);
    }
    public void filtrarMascotas(String filtro){
        if(filtro.isEmpty()){
            mascotaResponseList.clear();
            mascotaResponseList.addAll(mascotaResponseListOriginal);
        }else{
            List<MascotaResponse> busquedaMascota =
                    mascotaResponseList.stream()
                            .filter(m -> m.getNommascota()
                                    .toLowerCase().contains(
                                            filtro.toLowerCase()))
                            .collect(Collectors.toList());
            mascotaResponseList.clear();
            mascotaResponseList.addAll(busquedaMascota);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMascotaBinding binding;
        public ViewHolder(ItemMascotaBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
