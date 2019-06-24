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
@Table(name = "marcador_tbl")
public class MarcadorEntity {

    @Id
    @Column(name = "id_marcador",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "equipo_ganador")
    private String equipoGanador;
    @Column(name = "marcador_equipo_local")
    private int equipoLocalMrc;
    @Column(name = "marcador_equipo_visitante")
    private int equipoVisitanteMrc;

    @JoinColumn(name = "id_partido", nullable = false)
    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    private PartidoEntity fkPartido;
}
