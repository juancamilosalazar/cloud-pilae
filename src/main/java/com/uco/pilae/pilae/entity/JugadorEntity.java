package com.uco.pilae.pilae.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jugador_tbl")
public class JugadorEntity {

    @Id
    @Column(name = "id_jugador", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;


    @JoinColumn(name = "id_equipo", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private EquipoEntity fkEquipo;

}
