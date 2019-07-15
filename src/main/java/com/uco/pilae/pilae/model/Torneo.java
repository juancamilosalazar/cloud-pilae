package com.uco.pilae.pilae.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Torneo {

    private Long codigo;
    private String nombre;
    private String descripcion;
    private Deporte fkDeporte;
}
