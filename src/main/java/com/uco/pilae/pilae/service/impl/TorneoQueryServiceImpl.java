package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.TorneoQueryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TorneoQueryServiceImpl implements TorneoQueryService {

    final private TorneoRepository repository;

    public TorneoQueryServiceImpl(TorneoRepository repository) {
        this.repository = repository;
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
}
