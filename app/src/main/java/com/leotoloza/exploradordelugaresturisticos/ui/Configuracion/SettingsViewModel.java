package com.leotoloza.exploradordelugaresturisticos.ui.Configuracion;

import android.app.Application;
import android.util.Log;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.GoogleMap;
import com.leotoloza.exploradordelugaresturisticos.ui.Mapa.MapsFragment;

public class SettingsViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> tipoMapaMutable;
    public SettingsViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<Integer> getTipoMapaMutable(){
        if(tipoMapaMutable == null) {
            tipoMapaMutable = new MutableLiveData<>();
        }
        return tipoMapaMutable;
    }

    public void setTipoMapa(int tipo) {
        tipoMapaMutable.setValue(tipo);
    }
    public void seleccionRadio(RadioButton radioDefecto, RadioButton radioSatelital, int tipoMapa) {
        switch (tipoMapa) {
            case GoogleMap.MAP_TYPE_NORMAL:
                radioDefecto.setChecked(true);
                radioSatelital.setChecked(false);
                break;
            case GoogleMap.MAP_TYPE_SATELLITE:
                radioDefecto.setChecked(false);
                radioSatelital.setChecked(true);
                break;
        }
    }

}

