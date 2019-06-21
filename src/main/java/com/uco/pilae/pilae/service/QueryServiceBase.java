package com.uco.pilae.pilae.service;

import java.util.List;

public interface QueryServiceBase<T> {

    List<T> findAll();

    List<T> findAllByTorneo(final Long idTorneo);

    T save(final T entity);

    void delete(final T entity);
}
