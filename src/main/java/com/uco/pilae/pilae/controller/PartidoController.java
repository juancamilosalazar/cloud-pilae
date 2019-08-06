package com.uco.pilae.pilae.controller;


import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.model.Partido;
import com.uco.pilae.pilae.model.Torneo;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.FixtureQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pilae/fixture")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PartidoController {
    private final FixtureQueryService queryService;
    private final ModelMapper modelMapper;
    private final TorneoRepository torneoRepository;
    private final EquipoRepository equipoRepository;
    private final DataConversionUtil dataConversion;

    public PartidoController(FixtureQueryService queryService, ModelMapper modelMapper, TorneoRepository torneoRepository, EquipoRepository equipoRepository, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.torneoRepository = torneoRepository;
        this.equipoRepository = equipoRepository;
        this.dataConversion = dataConversion;
    }

    @GetMapping(params = {"idTorneo"})
    public List<Partido> saveFixtureWithReturn(@RequestParam(value = "idTorneo") final Long torneoId) {
        TorneoEntity torneo = torneoRepository.findById(torneoId).orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", torneoId));
        queryService.deleteByFkTorneo(torneo);
        List<EquipoEntity> equipos = equipoRepository.findByFkTorneo(torneo);
        return queryService.generateFixture(equipos, torneo)
                .parallelStream()
                .map(x->modelMapper.map(x,Partido.class))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"idNotReturn"})
    public List<Partido> saveFixtureWithNotReturn(@RequestParam(value = "idNotReturn") final Long torneoId) {
        TorneoEntity torneo = torneoRepository.findById(torneoId).orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", torneoId));
        queryService.deleteByFkTorneo(torneo);
        List<EquipoEntity> equipos = equipoRepository.findByFkTorneo(torneo);
       return queryService.generateFixtureNotReturn(equipos, torneo)
               .parallelStream().map(x->modelMapper.map(x,Partido.class))
               .collect(Collectors.toList());
    }

    @GetMapping(params = {"id"})
    public List<Partido> findAllByTorneo(@RequestParam(value = "id") final Long torneoId) {
        List<PartidoEntity> partidos = queryService.findAllByTorneo(torneoId)
                .stream()
                .sorted(Comparator.comparing(PartidoEntity::getCodigo))
                .collect(Collectors.toList());
        return partidos.parallelStream().map(partido -> modelMapper.map(partido, Partido.class)).collect(Collectors.toList());
    }

    @PostMapping(params = {"id", "idLocal", "idVisitante"})
    public ResponseEntity<String> create(@RequestParam(value = "id") final Long id, @RequestParam(value = "idLocal") final Long idLocal, @RequestParam(value = "idVisitante") final Long idVisitante, @RequestBody final Partido partido) {
        try {
            PartidoEntity partidoEntity = modelMapper.map(Objects.requireNonNull(partido, "Ocurrio un error al procesar el Body de la peticion"), PartidoEntity.class);
            partidoEntity.setFkEquipoLocal(equipoRepository.findById(idLocal).orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", idLocal)));
            partidoEntity.setFkEquipoVisitante(equipoRepository.findById(idVisitante).orElseThrow(() -> new ResourceNotFoundException("torneo_tbl", "torneo_tbl", idVisitante)));
            PartidoEntity newPartido = queryService.crear(partidoEntity, id);
            return buildResponse(newPartido);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }
    @PutMapping(params = {"id"})
    public ResponseEntity<String> update(@RequestParam(value = "id") final Long id,@RequestBody Partido partido){
        return buildResponse(queryService.update(id,partido));
    }

    private ResponseEntity<String> buildResponse(final PartidoEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Partido.class));
        return ResponseEntity.ok(jsonResponse);
    }
}
