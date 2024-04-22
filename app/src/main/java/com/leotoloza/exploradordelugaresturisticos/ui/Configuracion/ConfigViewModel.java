package com.leotoloza.exploradordelugaresturisticos.ui.Configuracion;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.GoogleMap;

public class ConfigViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> tipoMapaMutable;
    private MutableLiveData<String> idiomaMutable;

    public ConfigViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getTipoMapaMutable() {
        if (tipoMapaMutable == null) {
            tipoMapaMutable = new MutableLiveData<>();
        }
        return tipoMapaMutable;
    }

    public void setTipoMapa(int tipo) {
        Log.d("salida", "setTipoMapa: " + tipo);
        tipoMapaMutable.setValue(tipo);
    }

    public LiveData<String> getLanguage() {
        if(idiomaMutable==null){
            idiomaMutable = new MutableLiveData<>();
        }
        return idiomaMutable;
    }
    public void cambiarIdioma(boolean isChecked) {
        String lang = isChecked ? "en" : "es";
        setIdioma(lang);
    }
    public void setIdioma(String idioma) {
        idiomaMutable.setValue(idioma);
    }
}