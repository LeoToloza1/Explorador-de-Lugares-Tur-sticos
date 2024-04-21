package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.leotoloza.exploradordelugaresturisticos.R;
import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;

import java.util.ArrayList;
import java.util.List;

public class LugaresViewModel extends AndroidViewModel {
    private MutableLiveData<List<LugarTuristico>> lugaresLiveData;
    public LugaresViewModel(@NonNull Application application) {super(application);}
    public MutableLiveData<List<LugarTuristico>> getMutableLugares(){
        if(lugaresLiveData == null){
            lugaresLiveData=new MutableLiveData<>();
        }
        return lugaresLiveData;
    }
    public void cargarLugares() {
        List<LugarTuristico> lugares = new ArrayList<>();
        lugares.add(new LugarTuristico("Museo de Carpinteria",
                -32.415945190627006,
                -64.99283913141227,
                "10:00Hs - 18:00Hs",
                1500.52,
                R.drawable.museo));
        lugares.add(new LugarTuristico("Mirador",
                -32.41787895791012,
                -64.98341839690686,
                "Abierto todo el día",
                00.00,
                R.drawable.mirador
        ));
        lugares.add(new LugarTuristico("Camping Municipal",
                -32.41211750920532,
                -64.97834962741224,
                "8:00Hs - 20:30Hs",
                1500,
                R.drawable.camping
        ));
        lugaresLiveData.setValue(lugares);
    }
}