package pe.edu.idat.appidatpatitas.view.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import pe.edu.idat.appidatpatitas.R;
import pe.edu.idat.appidatpatitas.databinding.FragmentMascotaBinding;
import pe.edu.idat.appidatpatitas.retrofit.response.MascotaResponse;
import pe.edu.idat.appidatpatitas.view.adapters.MascotaAdapter;
import pe.edu.idat.appidatpatitas.viewmodel.MascotaViewModel;


public class MascotaFragment extends Fragment
        implements SearchView.OnQueryTextListener {

    private FragmentMascotaBinding binding;
    private MascotaViewModel mascotaViewModel;
    private MascotaAdapter mascotaAdapter = new MascotaAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMascotaBinding.inflate(inflater,
                container, false);
        mascotaViewModel = new ViewModelProvider(requireActivity())
                .get(MascotaViewModel.class);
        binding.rvmascota.setLayoutManager(
                new LinearLayoutManager(requireActivity())
        );
        binding.rvmascota.setAdapter(mascotaAdapter);
        mascotaViewModel.listarMascotas();
        mascotaViewModel.listMutableLiveData.observe(
                getViewLifecycleOwner(),
                new Observer<List<MascotaResponse>>() {
                    @Override
                    public void onChanged(List<MascotaResponse> mascotaResponses) {
                        mascotaAdapter.setMascotas(mascotaResponses);
                    }
                }
        );
        binding.sbmascota.setOnQueryTextListener(this);
        return binding.getRoot();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mascotaAdapter.filtrarMascotas(s);
        return false;
    }
}