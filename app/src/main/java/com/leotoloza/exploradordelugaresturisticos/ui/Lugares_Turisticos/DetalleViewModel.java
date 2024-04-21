package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;

public class DetalleViewModel extends AndroidViewModel {
    private MutableLiveData<LugarTuristico> lugarLiveData;
    public DetalleViewModel(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData<LugarTuristico> getMutableLugar(){
        if(lugarLiveData == null){
            lugarLiveData=new MutableLiveData<>();
        }
        return lugarLiveData;
    }

    public void recuperarLugar(Intent intent){
        LugarTuristico lugar=(LugarTuristico) intent.getSerializableExtra("lugares");
        if (lugar!=null){
            lugarLiveData.setValue(lugar);
        }
        }

}
