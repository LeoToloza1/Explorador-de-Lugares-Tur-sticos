package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;


public class DetalleViewModel2 extends AndroidViewModel {
    public DetalleViewModel2(@NonNull Application application) {
        super(application);
    }
    private MutableLiveData<LugarTuristico> lugarLiveData;

    public MutableLiveData<LugarTuristico> getMutableLugar(){
        if(lugarLiveData == null){
            lugarLiveData=new MutableLiveData<>();
        }
        return lugarLiveData;
    }

    public void recuperarLugar(Bundle bundle){
       LugarTuristico lugar = (LugarTuristico) bundle.get("lugar");
       if(lugar!=null){
        lugarLiveData.setValue(lugar);
       }
    }
}