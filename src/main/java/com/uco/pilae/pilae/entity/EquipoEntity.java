package com.uco.pilae.pilae.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipo_tbl")
public class EquipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo", nullable = false)
    private long codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "locacion")
    private String locacion;
    @Column(name = "genero")
    private String genero;

    @JoinColumn(name = "id_torneo", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    private TorneoEntity fkTorneo;
}
