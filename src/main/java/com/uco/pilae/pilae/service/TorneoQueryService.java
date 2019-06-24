package com.uco.pilae.pilae.service;

import com.uco.pilae.pilae.entity.TorneoEntity;

public interface TorneoQueryService extends QueryServiceBase<TorneoEntity> {
    TorneoEntity findById(final Long id);
}
