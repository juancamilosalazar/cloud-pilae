package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PartidoRepository extends CrudRepository<PartidoEntity,Long> {
    List<PartidoEntity> findByFkTorneo(TorneoEntity fkTorneo );
    Long deleteByfkTorneo(TorneoEntity fkTorneo);
}
