package com.uco.pilae.pilae.controller;

import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.model.Torneo;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.TorneoQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE
@RestController
@RequestMapping(path = "/pilae/torneo")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TorneoController {

    private final TorneoQueryService queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    public TorneoController(TorneoQueryService queryService, ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }

    @PostMapping(params = {"idDeporte"})
    public ResponseEntity<String> insertarTorneo(@RequestBody final Torneo torneo, @RequestParam(value = "idDeporte") final Long idDeporte) {
        try {
            TorneoEntity newTorneo = modelMapper.map(Objects.requireNonNull(torneo, "Ocurrio un error al procesar el Body de la peticion"), TorneoEntity.class);
            TorneoEntity created = queryService.create(newTorneo, idDeporte);
            return buildResponse(created);

        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteTorneo(@RequestParam("id") final Long id) {
        TorneoEntity old = queryService.findById(id);
        queryService.delete(old);
        return buildResponse(old);
    }


    @PutMapping(params = {"id"})
    public ResponseEntity<String> update(@RequestParam(value = "id") final Long id, @RequestBody final Torneo torneo) {
        try {
            final TorneoEntity old = queryService.findById(id);
            old.setNombre(torneo.getNombre());
            old.setDescripcion(torneo.getDescripcion());
            final TorneoEntity updated = queryService.save(old);
            return buildResponse(updated);
        } catch (final Exception ex) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }

    @GetMapping
    public List<Torneo> findAll() {
        return queryService.findAll()
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Torneo.class))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"idDeporte"})
    public List<Torneo> finfByDeporte(@RequestParam(value = "idDeporte") final Long idDeporte) {
        return queryService.findByDeporte(idDeporte)
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Torneo.class))
                .collect(Collectors.toList());
    }

    private ResponseEntity<String> buildResponse(final TorneoEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Torneo.class));
        return ResponseEntity.ok(jsonResponse);
    }
}
