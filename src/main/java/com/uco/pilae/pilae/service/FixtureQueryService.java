package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.model.Marcador;

import java.util.List;

public interface FixtureQueryService {
    void generateFixture(final List<EquipoEntity> equipos,final TorneoEntity id);
    List<PartidoEntity> findAllByTorneo(final Long id);
    void deleteByFkTorneo(final TorneoEntity fkTorneo);

}
