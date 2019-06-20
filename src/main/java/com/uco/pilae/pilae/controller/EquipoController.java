package com.uco.pilae.pilae.controller;


import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/pilae/equipo")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class EquipoController {
    @Autowired
    private TorneoService torneoService;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private TorneoRepository torneoRepository;

    @PostMapping("insertar/equipo/{id}")
    public void insertarEquipo(@RequestBody Equipo equipo, @PathVariable(value = "id") Long id){
        torneoService.saveEquipo(equipo,torneoAsociado(id));
    }
    @GetMapping("listar")
    public ResponseEntity equipos(){
        return new ResponseEntity(torneoService.listEquipos(), HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public void updateEuipo(@RequestBody Equipo equipo, @PathVariable(value = "id") Long id){
        torneoService.updateEquipo(equipoAsociado(id),equipo);
    }
    @DeleteMapping(value = "delete/{id}")
    public void deleteEquipo(@PathVariable("id") Long id) {
        equipoRepository.deleteById(id);
    }


    private TorneoEntity torneoAsociado(Long id) {
        return  torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
    }

    private EquipoEntity equipoAsociado(Long id) {
        return equipoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
    }
}
