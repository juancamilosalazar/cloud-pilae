package com.uco.pilae.pilae.controller;


import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.model.Deporte;
import com.uco.pilae.pilae.service.DeporteQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pilae/deporte", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class DeporteController {
    private final DeporteQueryService queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    public DeporteController(DeporteQueryService queryService, ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }

    @GetMapping
    public List<Deporte> findAll() {
        return queryService.findAll()
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Deporte.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody final Deporte deporte) {
        try {
            DeporteEntity deporteEntity = modelMapper.map(Objects.requireNonNull(deporte, "Ocurrio un error al procesar el Body de la peticion"), DeporteEntity.class);
            DeporteEntity newDeporte = queryService.save(deporteEntity);
            return buildResponse(newDeporte);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }


    private ResponseEntity<String> buildResponse(final DeporteEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Deporte.class));
        return ResponseEntity.ok(jsonResponse);
    }

}
