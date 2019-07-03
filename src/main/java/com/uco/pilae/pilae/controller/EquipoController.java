package com.uco.pilae.pilae.controller;


import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.service.EquipoQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pilae/equipo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class EquipoController {

    private final EquipoQueryService queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    @Autowired
    public EquipoController(final EquipoQueryService queryService, final ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }

    @GetMapping
    public List<Equipo> findAll() {
        return queryService.findAll()
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Equipo.class))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"id"})
    public Equipo findById(@RequestParam("id") final Long id) {
        final EquipoEntity entity = queryService.findById(id);
        return modelMapper.map(entity, Equipo.class);
    }
    @GetMapping(params = {"idTorneo"})
    public List<Equipo> findByIdTorneo(@RequestParam("idTorneo") final Long id) {
        return queryService.findAllByTorneo(id)
                .parallelStream()
                .map(equipo-> modelMapper.map(equipo,Equipo.class))
                .collect(Collectors.toList());
    }

    @PostMapping(params = {"torneoId"})
    public ResponseEntity<String> create(@RequestParam(value = "torneoId") final Long torneoId, @RequestBody final Equipo equipo) {
        try {
            final EquipoEntity newEntity = modelMapper.map(Objects.requireNonNull(equipo, "Ocurrio un error al procesar el Body de la peticion"), EquipoEntity.class);
            final EquipoEntity created = queryService.crear(newEntity, torneoId);
            return buildResponse(created);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }


    @PutMapping(params = {"id"})
    public ResponseEntity<String> update(@RequestParam(value = "id") final Long id, @RequestBody final Equipo equipo) {
        try {
            final EquipoEntity old = queryService.findById(id);
            old.setNombre(equipo.getNombre());
            old.setLocacion(equipo.getLocacion());
            old.setGenero(equipo.getGenero());
            final EquipoEntity updated = queryService.save(old);
            return buildResponse(updated);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity delete(@RequestParam("id") final Long id) {
        final EquipoEntity old = queryService.findById(id);
        queryService.delete(old);
        return buildResponse(old);
    }

    private ResponseEntity<String> buildResponse(final EquipoEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Equipo.class));
        return ResponseEntity.ok(jsonResponse);
    }
}
