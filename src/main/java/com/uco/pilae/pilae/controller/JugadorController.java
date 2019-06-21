package com.uco.pilae.pilae.controller;
import com.uco.pilae.pilae.entity.JugadorEntity;
import com.uco.pilae.pilae.model.Jugador;
import com.uco.pilae.pilae.service.JugadorQueryService;
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
@RequestMapping(path = "/pilae/jugador", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class JugadorController {

    private final JugadorQueryService queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    @Autowired
    public JugadorController(final JugadorQueryService queryService, final ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }


    @GetMapping
    public List<Jugador> findAll(){
        return queryService.findAll()
                .parallelStream()
                .map(jugador-> modelMapper.map(jugador, Jugador.class))
                .collect(Collectors.toList());
    }
    @GetMapping(params = {"id"})
    public List<Jugador> findByEquipo(@RequestParam(value = "id")final Long idEquipo){
        return queryService.findByEquipo(idEquipo)
                .parallelStream()
                .map(jugador-> modelMapper.map(jugador, Jugador.class))
                .collect(Collectors.toList());
    }

    @PostMapping(params = {"equipoId"})
    public ResponseEntity<String> crear(@RequestParam(value = "equipoId") final Long equipoId,@RequestBody final Jugador jugador){
        try {
            final JugadorEntity newJugador= modelMapper.map(Objects.requireNonNull(jugador, "Ocurrio un error al procesar el Body de la peticion"),JugadorEntity.class);
            final JugadorEntity created = queryService.crear(newJugador,equipoId);
            return buildResponse(created);

        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }
    }
    @PutMapping(params = {"id"})
    public ResponseEntity<String> update(@RequestParam(value = "id") final Long id,@RequestBody final Jugador newJugador){
        try {
            JugadorEntity old= queryService.findById(id);
            old.setNombre(newJugador.getNombre());
            old.setFechaNacimiento(newJugador.getFechaNacimiento());
            old.setIdentificacion(newJugador.getIdentificacion());
            final JugadorEntity jugadorEntity = queryService.save(old);
            return buildResponse(jugadorEntity);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(ex.getMessage());
        }

    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity delete(@RequestParam (value = "id")final Long id){
        final JugadorEntity old = queryService.findById(id);
        queryService.delete(old);
        return buildResponse(old);
    }


    private ResponseEntity<String> buildResponse(final JugadorEntity entity) {
        final String jsonResponse = dataConversion.dtoToJson(modelMapper.map(entity, Jugador.class));
        return ResponseEntity.ok(jsonResponse);
    }

}
