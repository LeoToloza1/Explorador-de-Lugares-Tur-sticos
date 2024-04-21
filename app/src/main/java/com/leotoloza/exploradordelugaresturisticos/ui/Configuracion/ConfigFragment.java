package com.leotoloza.exploradordelugaresturisticos.ui.Configuracion;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.GoogleMap;
import com.leotoloza.exploradordelugaresturisticos.databinding.FragmentConfigBinding;;import java.util.Locale;

public class ConfigFragment extends Fragment {
    private ConfigViewModel viewModel;
    private FragmentConfigBinding binding;
    private RadioButton radioDefecto;
    private RadioButton radioSatelital;
    public static int mapaElegido =1;

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("switchState", binding.switchIdioma.isChecked());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel= new ViewModelProvider(this).get(ConfigViewModel.class);
        binding = FragmentConfigBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        radioDefecto = binding.radioMapaDefecto;
        radioSatelital = binding.radioMapaSatelital;
        radioDefecto.setOnClickListener(v -> viewModel.setTipoMapa(GoogleMap.MAP_TYPE_TERRAIN));
        radioSatelital.setOnClickListener(v -> viewModel.setTipoMapa(GoogleMap.MAP_TYPE_SATELLITE));
        viewModel.getTipoMapaMutable().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer tipoMapa) {
                seleccionRadio(tipoMapa);
            }
        });
        boolean switchState = false;
        if (savedInstanceState != null) {
            switchState = savedInstanceState.getBoolean("switchState");
        }
        binding.switchIdioma.setChecked(switchState);
        Switch switchIdioma = binding.switchIdioma;
        boolean finalSwitchState = switchState;
        switchIdioma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == finalSwitchState) {
                    // El cambio fue causado por la recreación de la actividad, no hacer nada
                    return;
                }
                if (isChecked) {
                    Log.d("salida", "onCheckedChanged: INGLES");
                    viewModel.setIdioma("en");
                } else {
                    Log.d("salida", "onCheckedChanged: ESPAÑOL");
                    viewModel.setIdioma("es");
                }
                getActivity().recreate();
            }
        });

    viewModel.getLanguage().observe(getViewLifecycleOwner(), new Observer<String>() {
    @Override
    public void onChanged(String lang) {
        setLocale(lang);
    }

});

        return root;
    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);
    }

    private void seleccionRadio(int tipoMapa) {
        switch (tipoMapa) {
            case GoogleMap.MAP_TYPE_TERRAIN:
                radioDefecto.setChecked(true);
                radioSatelital.setChecked(false);
                mapaElegido = tipoMapa;
                Log.d("salida", "seleccionRadio: TERRAIN"+tipoMapa);
                break;
            case GoogleMap.MAP_TYPE_SATELLITE:
                radioDefecto.setChecked(false);
                radioSatelital.setChecked(true);
                mapaElegido = tipoMapa;
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}