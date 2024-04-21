package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.leotoloza.exploradordelugaresturisticos.R;
import com.leotoloza.exploradordelugaresturisticos.databinding.ActivityDetalleBinding;
import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;

public class DetalleActivity extends AppCompatActivity {
    private ActivityDetalleBinding binding;
    private DetalleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btn = binding.btnAtras;

        viewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DetalleViewModel.class);
        viewModel.getMutableLugar().observe(this, new Observer<LugarTuristico>() {
            @Override
            public void onChanged(LugarTuristico lugarTuristico) {
                TextView nombre = binding.nombreLugar;
                TextView horario = binding.horario;
                TextView precio = binding.precio;
                ImageView imagen = binding.imagen;

                nombre.setText(lugarTuristico.getNombre());
                horario.setText("Nuestro horario es: "+lugarTuristico.getHorario());
                precio.setText("Precio: "+lugarTuristico.getPrecio());
                imagen.setImageResource(lugarTuristico.getImagen());
            }
        });

        viewModel.recuperarLugar(getIntent());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
