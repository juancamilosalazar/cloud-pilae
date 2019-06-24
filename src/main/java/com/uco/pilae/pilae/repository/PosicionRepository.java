package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PosicionRepository extends JpaRepository<PosicionEntity,Long> {
    PosicionEntity findByFkEquipo(EquipoEntity fkEquipo );
    List<PosicionEntity> findByFkTorneoCodigo(Long fkTorneo);
}
