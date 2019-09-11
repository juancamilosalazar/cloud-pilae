package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.repository.DeporteRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.TorneoQueryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TorneoQueryServiceImpl implements TorneoQueryService {

    private final TorneoRepository repository;
    private final DeporteRepository deporteRepository;

    public TorneoQueryServiceImpl(TorneoRepository repository, DeporteRepository deporteRepository) {
        this.repository = repository;
        this.deporteRepository = deporteRepository;
    }

    @Override
    public List<TorneoEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<TorneoEntity> findAllByTorneo(Long idTorneo) {
        return null;
    }

    @Override
    public TorneoEntity save(TorneoEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(TorneoEntity entity) {
        repository.deleteInBatch(Arrays.asList(entity));
    }

    @Override
    public TorneoEntity findById(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", id));
    }

    @Override
    public List<TorneoEntity> findByDeporte(Long idDeporte) {
        return repository.findAllByFkDeporteCodigo(idDeporte);
    }

    @Override
    public TorneoEntity create(TorneoEntity newTorneo, Long idDeporte) {
        return deporteRepository.findById(idDeporte).map(deporteEntity -> {
            newTorneo.setFkDeporte(deporteEntity);
            return newTorneo;
        }).map(repository::saveAndFlush).orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", idDeporte));
    }
}
