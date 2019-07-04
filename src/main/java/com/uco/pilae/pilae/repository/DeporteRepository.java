package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DeporteRepository extends JpaRepository<DeporteEntity,Long> {

}
