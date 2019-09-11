package com.uco.pilae.pilae.controller;

import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.model.Deporte;
import com.uco.pilae.pilae.model.Posicion;
import com.uco.pilae.pilae.service.PosicionQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pilae/posicion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class PosicionController {
    private final PosicionQueryService queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    public PosicionController(final PosicionQueryService queryService, final ModelMapper modelMapper,final DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion= dataConversion;
    }

    @GetMapping(params = {"id"})
    public List<Posicion> findByTorneo(@RequestParam(value = "id") Long id) {
        return queryService.findByTorneo(id)
                .parallelStream()
                .map(posicion -> modelMapper.map(posicion, Posicion.class))
                .collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<String> Create(@RequestBody final Posicion posicion) {
        try {
            PosicionEntity posicionEntity = modelMapper.map(Objects.requireNonNull(posicion, "Ocurrio un error al procesar el Body de la peticion"), PosicionEntity.class);
            PosicionEntity newPosicion= queryService.save(posicionEntity);
            return buildResponse(newPosicion);

        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }
    private ResponseEntity<String> buildResponse(final PosicionEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Posicion.class));
        return ResponseEntity.ok(jsonResponse);
    }

}
