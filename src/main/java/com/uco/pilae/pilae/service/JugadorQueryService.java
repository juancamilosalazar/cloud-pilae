package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.JugadorEntity;

import java.util.List;


public interface JugadorQueryService extends QueryServiceBase<JugadorEntity> {

    JugadorEntity crear(final JugadorEntity newJugador, final Long equipoId);

    JugadorEntity findById(final Long id);

    List<JugadorEntity> findByEquipo(final Long idEquipo);

}
