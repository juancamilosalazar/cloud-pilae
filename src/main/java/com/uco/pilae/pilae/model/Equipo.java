package com.uco.pilae.pilae.model;

import java.util.List;

public class Equipo {

    private  Integer codigo;
    private String nombre;
    private String locacion;
    private  String genero;
    private List<Jugador> jugadores;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
