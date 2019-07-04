package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;

import java.util.List;

public interface TorneoQueryService extends QueryServiceBase<TorneoEntity> {
    TorneoEntity findById(final Long id);
    List<TorneoEntity> findByDeporte(final Long idDeporte);
    TorneoEntity create(final TorneoEntity newTorneo,final Long idDeporte);
}
