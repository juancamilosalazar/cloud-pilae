package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.repository.PosicionRepository;
import com.uco.pilae.pilae.service.PosicionQueryService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PosicionQueryServiceImpl implements PosicionQueryService {

    private final PosicionRepository repository;

    public PosicionQueryServiceImpl(PosicionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PosicionEntity> findByTorneo(Long torneoId) {
        List<PosicionEntity> posiciones = repository.findByFkTorneoCodigo(torneoId);
        return posiciones.stream()
                .sorted(Comparator.comparing(PosicionEntity::getPuntos).thenComparing(PosicionEntity::getGolesDiferencia).reversed())
                .collect(Collectors.toList());
    }
}
