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
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String ronda;
    private LocalDate fechaDelPartido;
    private Torneo torneo;
    private String idaVuelta;
    private String estadoPartido;

}
