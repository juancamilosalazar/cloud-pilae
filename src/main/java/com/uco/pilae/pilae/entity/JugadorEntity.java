package com.uco.pilae.pilae.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jugador_tbl")
public class JugadorEntity {

    @Id
    @Column(name = "id_jugador",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;


    @JoinColumn(name = "id_equipo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private EquipoEntity fkEquipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EquipoEntity getFkEquipo() {
        return fkEquipo;
    }

    public void setFkEquipo(EquipoEntity fkEquipo) {
        this.fkEquipo = fkEquipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
