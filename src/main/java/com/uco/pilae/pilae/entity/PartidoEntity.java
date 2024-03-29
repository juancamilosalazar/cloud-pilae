package com.uco.pilae.pilae.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "partido_tbl")
public class PartidoEntity {
    @Id
    @Column(name = "id_partido", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(name = "fecha_partido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDelpartido;
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

}
