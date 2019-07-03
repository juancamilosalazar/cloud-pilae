package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface MarcadorRepository extends JpaRepository<MarcadorEntity,Long> {
    MarcadorEntity findByFkPartido(final PartidoEntity partidoEntity);
    List<MarcadorEntity> findByFkPartidoFkTorneoCodigo(final Long id);
}
