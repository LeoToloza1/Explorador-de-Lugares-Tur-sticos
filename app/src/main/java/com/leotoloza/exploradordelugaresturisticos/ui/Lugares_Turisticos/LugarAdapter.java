package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leotoloza.exploradordelugaresturisticos.R;
import com.leotoloza.exploradordelugaresturisticos.modelo.LugarTuristico;

import java.util.List;

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder>{

    private List<LugarTuristico> listaLugares;
    private Context context;

    public LugarAdapter(List<LugarTuristico> listaLugares, Context context) {
        this.listaLugares = listaLugares;
        this.context = context;
    }

    @NonNull
    @Override
    public LugarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LugarAdapter.ViewHolder holder, int position) {
        LugarTuristico lugarTuristico= listaLugares.get(position);
        holder.nombreLugar.setText(lugarTuristico.getNombre());
        holder.imagen.setImageResource(lugarTuristico.getImagen());
        holder.btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,DetalleActivity.class);
                intent.putExtra("lugares", lugarTuristico);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombreLugar;
        ImageView imagen;
        Button btnDetalle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreLugar=itemView.findViewById(R.id.nombreLugar);
            imagen=itemView.findViewById(R.id.imagen);
            btnDetalle=itemView.findViewById(R.id.btnDetalles);

        }
    }
}

