package com.uco.pilae.pilae.operaciones;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.JugadorEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.model.Jugador;
import com.uco.pilae.pilae.model.Torneo;

public class Transform {

    public  EquipoEntity transformToEquipoEntity(Equipo equipo, TorneoEntity torneo) {
        EquipoEntity equipoEntity= new EquipoEntity();
        equipoEntity.setFkTorneo(torneo);
        equipoEntity.setNombre(equipo.getNombre());
        equipoEntity.setLocacion(equipo.getLocacion());
        equipoEntity.setGenero(equipo.getGenero());
        return equipoEntity;
    }

    public TorneoEntity transformToTorneoEntity(Torneo torneo) {
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setNombre(torneo.getNombre());
        torneoEntity.setDescripcion(torneo.getDescripcion());
        return torneoEntity;
    }

    public JugadorEntity transformToJugadorEntity(Jugador jugador, EquipoEntity equipoEntity) {
        JugadorEntity jugadorEntity= new JugadorEntity();
        jugadorEntity.setIdentificacion(jugador.getIdentificacion());
        jugadorEntity.setFechaNacimiento(jugador.getFechaNacimiento());
        jugadorEntity.setNombre(jugador.getNombre());
        jugadorEntity.setFkEquipo(equipoEntity);
        return jugadorEntity;
    }
}
