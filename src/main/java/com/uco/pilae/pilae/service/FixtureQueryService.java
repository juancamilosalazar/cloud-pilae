package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.model.Partido;

import java.util.List;

public interface FixtureQueryService extends QueryServiceBase<PartidoEntity> {
    void generateFixture(final List<EquipoEntity> equipos, final TorneoEntity id);
    void generateFixtureNotReturn(final List<EquipoEntity> equipos, final TorneoEntity id);
    PartidoEntity crear(final PartidoEntity newEntity, final Long torneoId);
    List<PartidoEntity> findAllByTorneo(final Long id);
    void deleteByFkTorneo(final TorneoEntity fkTorneo);
    PartidoEntity update(final Long id, final Partido partido);

}
