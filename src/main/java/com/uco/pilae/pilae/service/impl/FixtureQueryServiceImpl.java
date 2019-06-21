package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.operaciones.FixtureWhithReturn;
import com.uco.pilae.pilae.service.FixtureQueryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FixtureQueryServiceImpl implements FixtureQueryService {

    private final FixtureWhithReturn fixtureWhithReturn;

    public FixtureQueryServiceImpl(FixtureWhithReturn fixtureWhithReturn) {
        this.fixtureWhithReturn = fixtureWhithReturn;
    }


    @Override
    public void generateFixture(List<EquipoEntity> equipos, TorneoEntity id) {
        HashMap<Integer, EquipoEntity> generateFixture = new HashMap<>();
        int idEquipo = 0;
        for (EquipoEntity equipo : equipos) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            generateFixture.put(idEquipo, equipo);
        }
        //calcula el fixture con el hashmap generado
        fixtureWhithReturn.mostrarPartidos(fixtureWhithReturn.calcularLiga(generateFixture), generateFixture, id);

    }

}
