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
                "Se encuentra contenido en el todo el acervo originario de la localidad, " +
                        "referente a su historia, cultura, costumbres, tradiciones e identidad del territorio. " +
                        "\nSe centra en dos temáticas: los primeros habitantes del pueblo y los mineros.",
                R.drawable.museo));
        lugares.add(new LugarTuristico("Mirador",
                -32.41787895791012,
                -64.98341839690686,
                "Abierto todo el día",
                00.00,
                "El mirador se hizo con la perspectiva de poder ver el eclipse de sol " +
                        "ocurrido en julio del 2019, para que todo el pueblo de Carpintería y " +
                        "todas las personas que quisieran concurrir al lugar, tuvieran una " +
                        "mejor visión del fenómeno que ocurriría. \nPero también desde allí podemos " +
                        "tener una vista panorámica tanto del pueblo como de las sierras y así " +
                        "apreciar los encantos de la naturaleza toda.",
                R.drawable.mirador
        ));
        lugares.add(new LugarTuristico("Camping Municipal",
                -32.41211750920532,
                -64.97834962741224,
                "8:00Hs - 20:30Hs",
                1500,
                "En la localidad de Carpintería a solo 5km de la Villa de Merlo, se encuentra un" +
                        " hermoso predio de 4 hectáreas cubierto por bosque nativo. " +
                        "\nEstá localizado a 500 mts de la plaza, sobre Avenida Los Mandarinos, junto al arroyo Delfín.",
                R.drawable.camping
        ));
        lugaresLiveData.setValue(lugares);
    }
}