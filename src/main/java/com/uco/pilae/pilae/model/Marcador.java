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

    private String equipoGanador;
    private int equipoLocalMrc;
    private int equipoVisitanteMrc;
}
