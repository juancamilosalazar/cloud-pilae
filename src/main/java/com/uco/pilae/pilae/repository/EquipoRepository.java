package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Repository
@Transactional
@CrossOrigin(origins = "*")
public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {
    List<EquipoEntity> findByFkTorneo(TorneoEntity fkTorneo);

    List<EquipoEntity> findAllByFkTorneoCodigo(final Long codigo);
}

