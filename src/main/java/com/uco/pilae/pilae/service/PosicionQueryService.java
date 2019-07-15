package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.PosicionEntity;

import java.util.List;

public interface PosicionQueryService {

    List<PosicionEntity> findByTorneo(Long torneo);
}
