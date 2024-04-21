package com.leotoloza.exploradordelugaresturisticos.ui.Configuracion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.maps.GoogleMap;
import com.leotoloza.exploradordelugaresturisticos.databinding.ActivitySettingsBinding;
import com.leotoloza.exploradordelugaresturisticos.ui.Mapa.MapsFragment;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    private SettingsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        RadioButton radioDefecto = binding.radioMapaDefecto;
        RadioButton radioSatelital = binding.radioMapaSatelital;
        radioDefecto.setOnClickListener(v -> viewModel.setTipoMapa(GoogleMap.MAP_TYPE_NORMAL));
        radioSatelital.setOnClickListener(v -> viewModel.setTipoMapa(GoogleMap.MAP_TYPE_SATELLITE));
        viewModel.getTipoMapaMutable().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer tipoMapa) {
              viewModel.seleccionRadio(radioDefecto,radioSatelital,tipoMapa);
            }
        });

    }

}
