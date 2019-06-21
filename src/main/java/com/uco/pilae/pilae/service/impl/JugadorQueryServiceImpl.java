package com.uco.pilae.pilae.service.impl;


import com.uco.pilae.pilae.entity.JugadorEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.JugadorRepository;
import com.uco.pilae.pilae.service.JugadorQueryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JugadorQueryServiceImpl implements JugadorQueryService {

    private final JugadorRepository repository;
    private final EquipoRepository equipoRepository;

    public JugadorQueryServiceImpl(final JugadorRepository repository, final EquipoRepository equipoRepository) {
        this.repository=repository;
        this.equipoRepository = equipoRepository;
    }

    @Override
    public List<JugadorEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<JugadorEntity> findAllByTorneo(Long idTorneo) {
        return null;
    }

    @Override
    public JugadorEntity save(JugadorEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(JugadorEntity entity) {
        repository.deleteInBatch(Arrays.asList(entity));
    }

    @Override
    public JugadorEntity crear(JugadorEntity newJugador, Long equipoId) {
        return equipoRepository.findById(equipoId)
                .map(equipoEntity-> {
                    newJugador.setFkEquipo(equipoEntity);
                    return newJugador;
                })
                .map(repository::saveAndFlush)
                .orElseThrow(() -> new ResourceNotFoundException("jugador_tbl", "equipo_tbl", equipoId));
    }

    @Override
    public JugadorEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("jugador_tbl", "jugador_tbl", id));
    }

    @Override
    public List<JugadorEntity> findByEquipo(Long idEquipo) {
        return repository.findAllByFkEquipoCodigo(idEquipo);
    }
}
