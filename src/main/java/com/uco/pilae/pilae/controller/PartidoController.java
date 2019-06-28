package com.uco.pilae.pilae.controller;


import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.model.Marcador;
import com.uco.pilae.pilae.model.Partido;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.FixtureQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pilae/fixture")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class PartidoController {
    private final FixtureQueryService queryService;
    private final ModelMapper modelMapper;
    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;

    public PartidoController(FixtureQueryService queryService, ModelMapper modelMapper, TorneoRepository torneoRepository, EquipoRepository equipoRepository) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.torneoRepository = torneoRepository;
        this.equipoRepository = equipoRepository;
    }

    @GetMapping(params = {"idTorneo"})
    public void save(@RequestParam(value = "idTorneo") Long torneoId) {
        TorneoEntity torneo= torneoRepository.findById(torneoId).orElseThrow(()->new ResourceNotFoundException("torneo_tbl","torneo_tbl",torneoId));
        queryService.deleteByFkTorneo(torneo);
        List<EquipoEntity> equipos = equipoRepository.findByFkTorneo(torneo);
        queryService.generateFixture(equipos,torneo);
    }

    @GetMapping(params = {"id"})
    public List<Partido> findAllByTorneo(@RequestParam(value = "id") Long torneoId) {
        List<PartidoEntity> partidos = queryService.findAllByTorneo(torneoId)
                .stream()
                .sorted(Comparator.comparing(PartidoEntity::getCodigo))
                .collect(Collectors.toList());
        return partidos.parallelStream().map(partido->modelMapper.map(partido,Partido.class)).collect(Collectors.toList());
    }
}
