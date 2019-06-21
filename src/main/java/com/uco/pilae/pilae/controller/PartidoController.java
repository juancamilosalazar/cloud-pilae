package com.uco.pilae.pilae.controller;


import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
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
    private final PartidoRepository partidoRepository;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;
    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;

    public PartidoController(FixtureQueryService queryService, PartidoRepository partidoRepository, ModelMapper modelMapper, DataConversionUtil dataConversion, TorneoRepository torneoRepository, EquipoRepository equipoRepository) {
        this.queryService = queryService;
        this.partidoRepository = partidoRepository;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
        this.torneoRepository = torneoRepository;
        this.equipoRepository = equipoRepository;
    }

    @PostMapping(params = {"id"})
    public void save(@RequestParam(value = "id") Long id) {
        TorneoEntity torneo= torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("torneo_tbl","torneo_tbl",id));
        partidoRepository.deleteByfkTorneo(torneo);
        List<EquipoEntity> equipos = equipoRepository.findByFkTorneo(torneo);
        queryService.generateFixture(equipos,torneo);
    }

    @GetMapping(params = {"id"})
    public List<Partido> findAll(@RequestParam(value = "id") Long id) {
        List<PartidoEntity> partidos = partidoRepository.findByFkTorneoCodigo(id)
                .stream()
                .sorted(Comparator.comparing(PartidoEntity::getCodigo))
                .collect(Collectors.toList());
        return partidos.parallelStream().map(partido->modelMapper.map(partido,Partido.class)).collect(Collectors.toList());
    }

    private ResponseEntity<String> buildResponse(final PartidoEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Partido.class));
        return ResponseEntity.ok(jsonResponse);
    }
}
