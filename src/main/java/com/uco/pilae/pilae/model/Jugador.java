package com.uco.pilae.pilae.model;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class Jugador implements java.io.Serializable {

    private Long id;
    private String nombre;
    @EqualsAndHashCode.Include
    private String identificacion;
    //TODO:CAMBIAR LONG
    private String fechaNacimiento;
    private Equipo fkEquipo;

}
