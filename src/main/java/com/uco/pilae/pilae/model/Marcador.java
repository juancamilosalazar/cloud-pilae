package com.uco.pilae.pilae.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Marcador {
    private Long codigo;
    private String equipoGanador;
    private int equipoLocalMrc;
    private int equipoVisitanteMrc;
    private Partido fkPartido;
}
