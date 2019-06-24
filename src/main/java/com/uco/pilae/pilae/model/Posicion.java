package com.uco.pilae.pilae.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posicion {
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int partidosEmpatados;
    private int golesFavor;
    private int golesContra;
    private int golesDiferencia;
    private int puntos;
    private Equipo fkEquipo;
    private Torneo fkTorneo;
}
