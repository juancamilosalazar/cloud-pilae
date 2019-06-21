package com.uco.pilae.pilae.service;
import com.uco.pilae.pilae.entity.EquipoEntity;

public interface EquipoQueryService extends QueryServiceBase<EquipoEntity> {

    EquipoEntity crear(final EquipoEntity newEntity, final Long torneoId);
    void initPartido(EquipoEntity equipoEntity);
    EquipoEntity findById(final Long id);
}
