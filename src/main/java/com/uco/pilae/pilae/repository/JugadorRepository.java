package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.JugadorEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity,Long> {
    List<JugadorEntity> findByFkEquipo(EquipoEntity fkEquipo );
}
