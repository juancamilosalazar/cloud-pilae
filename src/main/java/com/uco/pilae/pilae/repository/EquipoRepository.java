package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface EquipoRepository extends CrudRepository<EquipoEntity,Long> {
    List<EquipoEntity>  findByFkTorneo(TorneoEntity fkTorneo );

}

