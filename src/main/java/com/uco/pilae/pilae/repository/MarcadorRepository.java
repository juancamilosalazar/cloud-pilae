package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.model.Marcador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface MarcadorRepository extends CrudRepository<MarcadorEntity,Long> {
    MarcadorEntity findByFkPartido(PartidoEntity partidoEntity);
}
