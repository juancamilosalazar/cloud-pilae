package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PartidoRepository extends JpaRepository<PartidoEntity, Long> {
    List<PartidoEntity> findByFkTorneoCodigo(Long torneoId);

    Long deleteByfkTorneo(TorneoEntity fkTorneo);
}
