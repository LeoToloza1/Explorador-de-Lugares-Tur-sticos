package com.leotoloza.exploradordelugaresturisticos.modelo;

import java.io.Serializable;

public class LugarTuristico implements Serializable {
    private String nombre;
    private double latitud;
    private double longitud;
    private String horario;
    private double precio;
    private int imagen;
    private String descripcion;

    public LugarTuristico(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario = "";
        this.precio = 0.0;
        this.imagen = 0;
        this.descripcion = "";
    }
    public LugarTuristico(String nombre, double latitud, double longitud, String horario, double precio, String descripcion, int imagen) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario = horario;
        this.precio = precio;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }
        public String getNombre() {
            return nombre;
        }

        public double getLatitud() {
            return latitud;
        }

        public double getLongitud() {
            return longitud;
        }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
