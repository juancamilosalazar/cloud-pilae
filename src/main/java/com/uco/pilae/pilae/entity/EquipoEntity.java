package com.uco.pilae.pilae.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "equipo_tbl")
public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo",nullable = false)
    private long codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name="locacion")
    private String locacion;
    @Column(name="genero")
    private String genero;

    @JoinColumn(name = "id_torneo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private TorneoEntity fkTorneo;


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

    public void setGenero(String descripcion) {
        this.genero = descripcion;
    }

    public TorneoEntity getFkTorneo() {
        return fkTorneo;
    }

    public void setFkTorneo(TorneoEntity fkTorneo) {
        this.fkTorneo = fkTorneo;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
}
