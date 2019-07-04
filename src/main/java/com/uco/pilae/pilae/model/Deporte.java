package com.uco.pilae.pilae.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class Deporte {
    private Long codigo;
    @EqualsAndHashCode.Include
    private String nombre;
}
