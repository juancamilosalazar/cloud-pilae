package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.model.Marcador;
import com.uco.pilae.pilae.operaciones.FixtureWhithReturn;
import com.uco.pilae.pilae.operaciones.JugarPartido;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.service.FixtureQueryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FixtureQueryServiceImpl implements FixtureQueryService {

    private final FixtureWhithReturn fixtureWhithReturn;
    private final PartidoRepository repository;
    private final JugarPartido jugarPartido;

    public FixtureQueryServiceImpl(final FixtureWhithReturn fixtureWhithReturn,final PartidoRepository repository,final JugarPartido jugarPartido) {
        this.fixtureWhithReturn = fixtureWhithReturn;
        this.repository = repository;
        this.jugarPartido = jugarPartido;
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

    @Override
    public List<PartidoEntity> findAllByTorneo(Long id) {
        return repository.findByFkTorneoCodigo(id);
    }

    @Override
    public void deleteByFkTorneo(TorneoEntity fkTorneo) {
        repository.deleteByfkTorneo(fkTorneo);
    }






}
