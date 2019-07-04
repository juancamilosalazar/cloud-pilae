package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.repository.DeporteRepository;
import com.uco.pilae.pilae.service.DeporteQueryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DeporteQueryServiceImpl implements DeporteQueryService {

    private final DeporteRepository repository;

    public DeporteQueryServiceImpl(DeporteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DeporteEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<DeporteEntity> findAllByTorneo(Long idTorneo) {
        return null;
    }

    @Override
    public DeporteEntity save(DeporteEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(DeporteEntity entity) {
        repository.deleteInBatch(Arrays.asList(entity));
    }
}
