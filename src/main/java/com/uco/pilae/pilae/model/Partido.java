package com.uco.pilae.pilae.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class Partido implements java.io.Serializable {
    private Long codigo;
    private Equipo fkEquipoLocal;
    private Equipo fkEquipoVisitante;
    private String ronda;
    private Long fechaDelPartido;
    private String idaVuelta;
    private String estadoPartido;
    private Torneo fkTorneo;

}
