package com.uco.pilae.pilae.model;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class Partido implements java.io.Serializable {
    private  Long codigo;
    private Equipo fkEquipoLocal;
    private Equipo fkEquipoVisitante;
    private String ronda;
    private String fechaDelPartido;
    private String idaVuelta;
    private String estadoPartido;
    private Torneo fkTorneo;

}
