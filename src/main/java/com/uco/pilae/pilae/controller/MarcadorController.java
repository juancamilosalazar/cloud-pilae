package com.uco.pilae.pilae.controller;


import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.model.Marcador;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.service.MarcadorQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pilae/marcador")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class MarcadorController {

    private final MarcadorQueryService queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;
    private final PartidoRepository partidoRepository;

    @Autowired
    public MarcadorController(MarcadorQueryService queryService, final ModelMapper modelMapper, DataConversionUtil dataConversion, PartidoRepository partidoRepository) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
        this.partidoRepository = partidoRepository;
    }

    @PutMapping(params = {"id"})
    public ResponseEntity<String> jugarPartido(final @RequestBody Marcador marcador, @RequestParam(value = "id") final Long id) {
        MarcadorEntity marc = queryService.jugarPartido(partidoRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("partido_tbl", "id_partido", id))
                , marcador.getEquipoLocalMrc()
                , marcador.getEquipoVisitanteMrc());
        return buildResponse(marc);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<String> findMarcadorByPartido(@RequestParam(value = "id") final Long id) {
        MarcadorEntity marcador = queryService.findByFkPartido(partidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("partido_tbl", "id_partido", id)));
        return buildResponse(marcador);
    }

    @GetMapping(params = {"idTorneo"})
    public List<Marcador> findByTorneo(@RequestParam(value = "idTorneo") final Long id) {
        return queryService.findByFkTorneo(id)
                .parallelStream()
                .map(entity -> modelMapper.map(entity, Marcador.class))
                .collect(Collectors.toList());
    }

    private ResponseEntity<String> buildResponse(final MarcadorEntity entity) {
        final String jsonResponse = dataConversion
                .dtoToJson(modelMapper.map(entity, Marcador.class));
        return ResponseEntity.ok(jsonResponse);
    }

}
