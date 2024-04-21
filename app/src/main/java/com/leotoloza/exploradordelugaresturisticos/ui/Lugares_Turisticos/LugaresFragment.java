package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leotoloza.exploradordelugaresturisticos.databinding.FragmentLugarBinding;
import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;

import java.util.List;

public class LugaresFragment extends Fragment {

    private FragmentLugarBinding binding;
    private LugaresViewModel viewModel;
    private Intent intent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LugaresViewModel.class);
        binding = FragmentLugarBinding.inflate(inflater, container, false);
     RecyclerView listaLugares = binding.lista;
        viewModel.getMutableLugares().observe(getViewLifecycleOwner(), new Observer<List<LugarTuristico>>() {
         @Override
         public void onChanged(List<LugarTuristico> lugarTuristicos) {
            LugarAdapter lugarAdapter = new LugarAdapter(lugarTuristicos,getContext());
             GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
             listaLugares.setLayoutManager(glm);
             listaLugares.setAdapter(lugarAdapter);
         }
     });
        viewModel.cargarLugares();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}