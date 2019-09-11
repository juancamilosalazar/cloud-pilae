package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.model.Posicion;

import java.util.List;

public interface PosicionQueryService {

    List<PosicionEntity> findByTorneo(final Long torneo);
    PosicionEntity save(final PosicionEntity posicion);
}
