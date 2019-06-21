package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;

import java.util.List;

public interface FixtureQueryService {
    void generateFixture(List<EquipoEntity> equipos, TorneoEntity id);
}
