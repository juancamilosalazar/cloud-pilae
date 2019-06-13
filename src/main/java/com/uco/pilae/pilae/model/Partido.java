package com.uco.pilae.pilae.model;

import java.time.LocalDate;

public class Partido {
    private  Integer codigo;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String ronda;
    private LocalDate fechaDelPartido;
    private Torneo torneo;
    private String idaVuelta;
    private String estadoPartido;

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public LocalDate getFechaDelPartido() {
        return fechaDelPartido;
    }

    public void setFechaDelPartido(LocalDate fechaDelPartido) {
        this.fechaDelPartido = fechaDelPartido;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public String getIdaVuelta() {
        return idaVuelta;
    }

    public void setIdaVuelta(String idaVuelta) {
        this.idaVuelta = idaVuelta;
    }

    public String getEstadoPartido() {
        return estadoPartido;
    }

    public void setEstadoPartido(String estadoPartido) {
        this.estadoPartido = estadoPartido;
    }

    public Integer getCodigo() {
        return codigo;
    }
}
