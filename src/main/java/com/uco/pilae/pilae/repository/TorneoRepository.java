package com.uco.pilae.pilae.repository;

import com.uco.pilae.pilae.entity.TorneoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TorneoRepository extends CrudRepository<TorneoEntity,Long> {


}
