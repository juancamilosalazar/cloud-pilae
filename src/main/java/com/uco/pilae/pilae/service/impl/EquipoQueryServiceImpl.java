package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.PosicionRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.EquipoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EquipoQueryServiceImpl implements EquipoQueryService {

    private final EquipoRepository repository;
    private final TorneoRepository torneoRepository;
    private final PosicionRepository posicionRepository;

    @Autowired
    public EquipoQueryServiceImpl(final EquipoRepository repository, final TorneoRepository torneoRepository, final PosicionRepository posicionRepository) {
        this.repository = repository;
        this.torneoRepository = torneoRepository;
        this.posicionRepository = posicionRepository;
    }

    @Override
    public List<EquipoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<EquipoEntity> findAllByTorneo(Long idTorneo) {
        return repository.findAllByFkTorneoCodigo(idTorneo);
    }

    @Override
    public EquipoEntity save(EquipoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(EquipoEntity entity) {
        repository.deleteInBatch(Arrays.asList(entity));
    }

    @Override
    public EquipoEntity crear(EquipoEntity newEntity, Long torneoId) {
        return torneoRepository.findById(torneoId)
                .map(torneoEntity -> {
                    newEntity.setFkTorneo(torneoEntity);
                    initPartido(newEntity);
                    return newEntity;
                })
                .map(repository::saveAndFlush)
                .orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", torneoId));
    }


    @Override
    public void initPartido(EquipoEntity equipoEntity) {
        PosicionEntity posicionEntity = new PosicionEntity();
        posicionEntity.setPuntos(0);
        posicionEntity.setPartidosEmpatados(0);
        posicionEntity.setPartidosPerdidos(0);
        posicionEntity.setPartidosGanados(0);
        posicionEntity.setPartidosJugados(0);
        posicionEntity.setGolesDiferencia(0);
        posicionEntity.setGolesFavor(0);
        posicionEntity.setGolesContra(0);
        posicionEntity.setFkTorneo(equipoEntity.getFkTorneo());
        posicionEntity.setFkEquipo(equipoEntity);
        posicionRepository.save(posicionEntity);
    }

    @Override
    public EquipoEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("equipo_tbl", "equipo_tbl", id));
    }
}
