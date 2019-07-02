package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;

import java.util.List;

public interface MarcadorQueryService {
    MarcadorEntity jugarPartido(final PartidoEntity partido,final int equipoLocal,final int equipoVisitante);
    MarcadorEntity findByFkPartido(final PartidoEntity partidoEntity);
    List<MarcadorEntity> findByFkTorneo(final Long id);
}
