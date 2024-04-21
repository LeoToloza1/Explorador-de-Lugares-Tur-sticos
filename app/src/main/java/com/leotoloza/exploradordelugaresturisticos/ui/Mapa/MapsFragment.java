package com.leotoloza.exploradordelugaresturisticos.ui.Mapa;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leotoloza.exploradordelugaresturisticos.R;
import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;
import com.leotoloza.exploradordelugaresturisticos.ui.Configuracion.ConfigFragment;

import java.util.List;
public class MapsFragment extends Fragment {
    private UbicacionViewModel viewModel;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            viewModel.getLugaresTuristicos().observe(getViewLifecycleOwner(), new Observer<List<LugarTuristico>>() {
                @Override
                public void onChanged(List<LugarTuristico> lugarTuristicos) {
                    for (LugarTuristico lugar : lugarTuristicos) {
                        LatLng ubicacion = new LatLng(lugar.getLatitud(), lugar.getLongitud());
                        googleMap.addMarker(new MarkerOptions().position(ubicacion).title(lugar.getNombre()));
                    }
                }
            });
            viewModel.getMLocation().observe(getViewLifecycleOwner(), new Observer<Location>() {
                @Override
                public void onChanged(Location location) {
                    LatLng ubicacion = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marcador)).position(ubicacion).title("Mi Ubicación"));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.0f));

                }
            });
            viewModel.obtenerUltimaUbicacion();
            viewModel.cargarLugares();
            googleMap.setMapType(ConfigFragment.mapaElegido);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(UbicacionViewModel.class);
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}