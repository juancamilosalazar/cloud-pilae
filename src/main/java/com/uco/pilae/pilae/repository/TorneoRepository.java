package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Transactional
@Repository
@CrossOrigin(origins = "*")
public interface TorneoRepository extends JpaRepository<TorneoEntity, Long> {
    List<TorneoEntity> findAllByFkDeporteCodigo(final Long idDeporte);
}
