package com.leotoloza.exploradordelugaresturisticos.ui.Lugares_Turisticos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class LugarAdapter extends RecyclerView.Adapter<LugarAdapter.ViewHolder> {

    private List<LugarTuristico> listaLugares;
    private Context context;
    private ClickListener clickListener;

    public LugarAdapter(List<LugarTuristico> listaLugares, Context context) {
        this.listaLugares = listaLugares;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    public void setClickListener(ClickListener listener) {
        clickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LugarTuristico lugarTuristico = listaLugares.get(position);
        holder.nombreLugar.setText(lugarTuristico.getNombre());
        holder.horario.setText(lugarTuristico.getHorario());
        holder.precio.setText("$:"+lugarTuristico.getPrecio());
        holder.imagen.setImageResource(lugarTuristico.getImagen());
   holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("salida", "onClickADAPTER: llega");
                    if (clickListener != null) {
                        clickListener.clickDetalle(lugarTuristico);
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombreLugar;
        ImageView imagen;
        TextView horario;
        TextView precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreLugar = itemView.findViewById(R.id.nombreLugar);
            imagen = itemView.findViewById(R.id.imagen);
            horario = itemView.findViewById(R.id.txtHorario);
            precio = itemView.findViewById(R.id.txtPrecio);

      }
    }

    public interface ClickListener {
        void clickDetalle(LugarTuristico lugar);
    }
}

