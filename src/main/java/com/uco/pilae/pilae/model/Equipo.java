package com.uco.pilae.pilae.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class Equipo implements java.io.Serializable {

    private Long codigo;
    @EqualsAndHashCode.Include
    private String nombre;
    @EqualsAndHashCode.Include
    private String locacion;
    private String genero;
    private List<Jugador> jugadores;

}
