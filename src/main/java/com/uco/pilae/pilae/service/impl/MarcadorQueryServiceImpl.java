package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.operaciones.JugarPartido;
import com.uco.pilae.pilae.repository.MarcadorRepository;
import com.uco.pilae.pilae.service.MarcadorQueryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MarcadorQueryServiceImpl implements MarcadorQueryService {
    final private JugarPartido jugarPartido;
    final private MarcadorRepository repository;

    public MarcadorQueryServiceImpl(final JugarPartido jugarPartido, final MarcadorRepository repository) {
        this.jugarPartido = jugarPartido;
        this.repository = repository;
    }


    @Override
    public MarcadorEntity jugarPartido(PartidoEntity partido, int equipoLocal, int equipoVisitante) {
        return jugarPartido.jugarPartido(partido, equipoLocal, equipoVisitante);
    }

    @Override
    public MarcadorEntity findByFkPartido(PartidoEntity partidoEntity) {
        return repository.findByFkPartido(partidoEntity);
    }

    @Override
    public List<MarcadorEntity> findByFkTorneo(Long id) {
        return repository.findByFkPartidoFkTorneoCodigo(id);
    }
}
