package com.uco.pilae.pilae.controller;

import com.uco.pilae.pilae.model.Posicion;
import com.uco.pilae.pilae.service.PosicionQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pilae/posicion")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

public class PosicionController {
    private final PosicionQueryService queryService;
    private final ModelMapper modelMapper;
    private final DataConversionUtil dataConversion;

    public PosicionController(PosicionQueryService queryService, ModelMapper modelMapper, DataConversionUtil dataConversion) {
        this.queryService = queryService;
        this.modelMapper = modelMapper;
        this.dataConversion = dataConversion;
    }

    @GetMapping(params = {"id"})
    public List<Posicion> findByTorneo(@RequestParam(value = "id") Long id){
        return queryService.findByTorneo(id)
                .parallelStream()
                .map(posicion-> modelMapper.map(posicion,Posicion.class))
                .collect(Collectors.toList());
    }

}
