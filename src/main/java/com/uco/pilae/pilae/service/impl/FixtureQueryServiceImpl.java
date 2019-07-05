package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.operaciones.FixtureNotReturn;
import com.uco.pilae.pilae.operaciones.FixtureWhithReturn;
import com.uco.pilae.pilae.operaciones.JugarPartido;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.FixtureQueryService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FixtureQueryServiceImpl implements FixtureQueryService {

    private final FixtureWhithReturn fixtureWhithReturn;
    private final PartidoRepository repository;
    private final JugarPartido jugarPartido;
    private final FixtureNotReturn fixtureNotReturn;
    private final TorneoRepository torneoRepository;
    public FixtureQueryServiceImpl(final FixtureWhithReturn fixtureWhithReturn, final PartidoRepository repository, final JugarPartido jugarPartido, FixtureNotReturn fixtureNotReturn, TorneoRepository torneoRepository) {
        this.fixtureWhithReturn = fixtureWhithReturn;
        this.repository = repository;
        this.jugarPartido = jugarPartido;
        this.fixtureNotReturn = fixtureNotReturn;
        this.torneoRepository = torneoRepository;
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
    public void generateFixtureNotReturn(List<EquipoEntity> equipos, TorneoEntity id) {
        HashMap<Integer, EquipoEntity> generateFixture = new HashMap<>();
        int idEquipo = 0;
        for (EquipoEntity equipo : equipos) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            generateFixture.put(idEquipo, equipo);
        }
        //calcula el fixture con el hashmap generado
        fixtureNotReturn.mostrarPartidos(fixtureWhithReturn.calcularLiga(generateFixture), generateFixture, id);
    }

    @Override
    public PartidoEntity crear(PartidoEntity newEntity, Long torneoId) {
        return torneoRepository.findById(torneoId)
                .map(torneoEntity -> {
                    newEntity.setFkTorneo(torneoEntity);
                    final Calendar calendar = Calendar.getInstance(Locale.getDefault());
                    newEntity.setFechaDelpartido(calendar.getTime());
                    newEntity.setEstadoPartido("sin jugar");
                    return newEntity;
                })
                .map(repository::saveAndFlush)
                .orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", torneoId));
    }

    @Override
    public List<PartidoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<PartidoEntity> findAllByTorneo(Long id) {
        return repository.findByFkTorneoCodigo(id);
    }

    @Override
    public PartidoEntity save(PartidoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(PartidoEntity entity) {
        repository.deleteInBatch(Arrays.asList(entity));
    }

    @Override
    public void deleteByFkTorneo(TorneoEntity fkTorneo) {
        repository.deleteByfkTorneo(fkTorneo);
    }






}
