package com.uco.pilae.pilae.controller;
import com.uco.pilae.pilae.entity.*;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.model.Jugador;
import com.uco.pilae.pilae.model.Torneo;
import com.uco.pilae.pilae.operaciones.JugarPartido;
import com.uco.pilae.pilae.repository.*;
import com.uco.pilae.pilae.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE
@RestController
@RequestMapping(path = "/pilae/torneo")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class TorneoController {

    @Autowired
    private TorneoService torneoService;
    @Autowired
    private TorneoRepository torneoRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private PartidoRepository partidoRepository;
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private MarcadorRepository marcadorRepository;
    @Autowired
    private PosicionRepository posicionRepository;
    @Autowired
    private JugarPartido jugarPartido;



    @PostMapping("insertar/torneo")
    public void insertarTorneo(@RequestBody Torneo torneo){
        torneoService.saveTorneo(torneo);
    }

    @DeleteMapping(value = "delete/torneo/{id}")
    public void deleteTorneo(@PathVariable("id") Long id) {
        torneoRepository.deleteById(id);
    }


    @GetMapping("save/fixture/{id}")
    public ResponseEntity saveFixture(@PathVariable(value = "id") Long id) {
        TorneoEntity torneo= torneoAsociado(id);
        partidoRepository.deleteByfkTorneo(torneo);
        List equipos = equipoRepository.findByFkTorneo(torneo);
        List fixture = torneoService.fixture(equipos,torneo);
        return new ResponseEntity(fixture, HttpStatus.OK);
    }
    @PutMapping("update/torneo/{id}")
    public void upadteTorneo(@RequestBody Torneo torneo, @PathVariable(value = "id") Long id){
        torneoService.update(torneoAsociado(id),torneo);
    }

    @PostMapping("jugar/{id}")
    public void jugarPartido(@RequestBody MarcadorEntity marcador, @PathVariable(value = "id") Long id){
        jugarPartido.jugarPartido(partidoAsociado(id),marcador.getEquipoLocalMrc(),marcador.getEquipoVisitanteMrc());
    }

    @GetMapping("fixture/{id}")
    public ResponseEntity fixture(@PathVariable(value = "id") Long id) {
        List equipos = partidoRepository.findByFkTorneo(torneoAsociado(id)).stream().sorted(Comparator.comparing(PartidoEntity::getCodigo)).collect(Collectors.toList());
        return new ResponseEntity(equipos, HttpStatus.OK);
    }
    @GetMapping("marcador/{id}")
    public ResponseEntity marcador(@PathVariable(value = "id") Long id) {
        MarcadorEntity marcador = marcadorRepository.findByFkPartido(partidoAsociado(id));
        return new ResponseEntity(marcador, HttpStatus.OK);
    }

    @GetMapping("jugadores/{id}")
    public ResponseEntity jugadorPorEquipo(@PathVariable(value = "id") Long id) {
        List jugadores = jugadorRepository.findByFkEquipo(equipoAsociado(id));
        return new ResponseEntity(jugadores, HttpStatus.OK);
    }


    @GetMapping("listar/posiciones/{id}")
    public ResponseEntity posiciones(@PathVariable(value = "id") Long id){
        return new ResponseEntity(jugarPartido.ordenarPosiciones(torneoAsociado(id)), HttpStatus.OK);
    }
    @GetMapping("listar/partidos")
    public ResponseEntity partidos(){
        return new ResponseEntity(torneoService.listPartidos(), HttpStatus.OK);
    }

    @GetMapping(value="listar/torneos")
    public ResponseEntity listaTransactions(){
        return new ResponseEntity(torneoService.listTorneos(), HttpStatus.OK);
    }

    private TorneoEntity torneoAsociado(Long id) {
        return  torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
    }
    private EquipoEntity equipoAsociado(Long id) {
        return equipoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
    }
    private PartidoEntity partidoAsociado(Long id) {
        return partidoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
    }
    private JugadorEntity jugadorAsociado(Long id) {
        return jugadorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("torneo_tbl","torneo_tbl",id));
    }

}
