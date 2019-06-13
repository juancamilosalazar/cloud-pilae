package com.uco.pilae.pilae.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "partido_tbl")
public class PartidoEntity {
    @Id
    @Column(name = "id_partido",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "fecha_partido")
    private LocalDate fechaDelpartido;
    @Column(name = "ida_vuelta")
    private String idaVuelta;
    @Column(name = "ronda")
    private String ronda;
    @Column(name = "estado_partido")
    private String estadoPartido;


    @JoinColumn(name = "id_equipo_local", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private EquipoEntity fkEquipoLocal;

    @JoinColumn(name = "id_equipo_visitante", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private EquipoEntity fkEquipoVisitante;

    @JoinColumn(name = "id_torneo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private TorneoEntity fkTorneo;


    public LocalDate getFechaDelpartido() {
        return fechaDelpartido;
    }

    public void setFechaDelpartido(LocalDate fechaDelpartido) {
        this.fechaDelpartido = fechaDelpartido;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public EquipoEntity getFkEquipoLocal() {
        return fkEquipoLocal;
    }

    public void setFkEquipoLocal(EquipoEntity fkEquipoLocal) {
        this.fkEquipoLocal = fkEquipoLocal;
    }

    public EquipoEntity getFkEquipoVisitante() {
        return fkEquipoVisitante;
    }

    public void setFkEquipoVisitante(EquipoEntity fkEquipoVisitante) {
        this.fkEquipoVisitante = fkEquipoVisitante;
    }

    public TorneoEntity getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(TorneoEntity fkTorneo) {
        this.fkTorneo = fkTorneo;
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

    public Long getCodigo() {
        return codigo;
    }
}
