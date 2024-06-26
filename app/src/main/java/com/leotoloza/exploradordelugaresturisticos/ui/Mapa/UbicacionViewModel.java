package com.leotoloza.exploradordelugaresturisticos.ui.Mapa;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;

import java.util.ArrayList;
import java.util.List;

public class UbicacionViewModel extends AndroidViewModel {
    private MutableLiveData<List<LugarTuristico>> lugaresLiveData;
    private MutableLiveData<Integer> tipoMapaMutable;
    private MutableLiveData<Location> mLocation;
    private FusedLocationProviderClient fused;
    private LocationCallback callback;

    public UbicacionViewModel(@NonNull Application application) {
        super(application);
        fused = LocationServices.getFusedLocationProviderClient(getApplication());
    }

    public LiveData<List<LugarTuristico>> getLugaresTuristicos() {
        if (lugaresLiveData == null) {
            lugaresLiveData = new MutableLiveData<>();
        }
        return lugaresLiveData;
    }

//    public MutableLiveData<Integer>getTipoMapaMutable(){
//        if(tipoMapaMutable==null){
//            tipoMapaMutable=new MutableLiveData<>();
//        }
//        return tipoMapaMutable;
//    }
//    public void setTipoMapa(int tipoMapa) {
//        Log.d("salida", "setTipoMapa: viewModel"+tipoMapa );
//        tipoMapaMutable.setValue(tipoMapa);
//    }
    public void cargarLugares() {
        List<LugarTuristico> lugares = new ArrayList<>();
        lugares.add(new LugarTuristico("Museo de Carpinteria", -32.415945190627006, -64.99283913141227));
        lugares.add(new LugarTuristico("Mirador", -32.41787895791012, -64.98341839690686));
        lugares.add(new LugarTuristico("Camping Municipal", -32.41211750920532, -64.97834962741224));
        lugaresLiveData.setValue(lugares);
    }
    public LiveData<Location> getMLocation() {
        if (mLocation == null) {
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }
    public void obtenerUltimaUbicacion() {

        if (ActivityCompat.checkSelfPermission(getApplication(),
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getApplication(),
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fused.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            mLocation.postValue(location);
                        }
                    });
                }
            }
        });


    }

    /**
     * no se van a usar, pero por las dudas estan
     */
    public void lecturaPermanente() {
        LocationRequest request = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000).build();
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult == null) {
                    return;
                }
                Location location = locationResult.getLastLocation();
                mLocation.postValue(location);
            }
        };
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplication(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fused.requestLocationUpdates(request, callback, null);
    }

    public void pararLecturaPermanente(){
        fused.removeLocationUpdates(callback);

    }
}