package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leotoloza.exploradordelugaresturisticos.databinding.FragmentDetalleBinding;
import com.leotoloza.exploradordelugaresturisticos.R;
import com.leotoloza.exploradordelugaresturisticos.databinding.FragmentLugarBinding;
import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;

public class DetalleFragment extends Fragment {

    private DetalleViewModel2 viewModel;
    private FragmentDetalleBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DetalleViewModel2.class);
        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        viewModel.getMutableLugar().observe(getViewLifecycleOwner(), new Observer<LugarTuristico>() {
            @Override
            public void onChanged(LugarTuristico lugarTuristico) {
                TextView nombre = binding.nombreLugar;
                TextView horario = binding.horario;
                TextView precio = binding.precio;
                TextView descripcion = binding.descripcion;
                ImageView imagen = binding.imagen;
                nombre.setText(lugarTuristico.getNombre());
                horario.setText("Nuestro horario es: "+lugarTuristico.getHorario());
                precio.setText("Precio: "+lugarTuristico.getPrecio());
                descripcion.setText("Descripcion: "+lugarTuristico.getDescripcion());
                imagen.setImageResource(lugarTuristico.getImagen());
            }

        });
        viewModel.recuperarLugar(getArguments());
        return binding.getRoot();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}