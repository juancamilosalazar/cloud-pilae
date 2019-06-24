package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;

public interface MarcadorQueryService {
    MarcadorEntity jugarPartido(final PartidoEntity partido,final int equipoLocal,final int equipoVisitante);
    MarcadorEntity findByFkPartido(final PartidoEntity partidoEntity);
}
