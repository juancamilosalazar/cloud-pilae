package com.uco.pilae.pilae.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "torneo_tbl")
public class TorneoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_torneo", nullable = false)
    private Long codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;

    @JoinColumn(name = "id_deporte", nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    private DeporteEntity fkDeporte;

}
