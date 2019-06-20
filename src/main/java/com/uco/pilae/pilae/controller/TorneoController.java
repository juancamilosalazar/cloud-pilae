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
@Controller
@RequestMapping(path = "/pilae")
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

    @PostMapping("insertar")
    public ResponseEntity insertar(@RequestBody Torneo torneo){
        torneoService.save(torneo);
        List torneos = torneoService.listEquipos();
        return new ResponseEntity(torneos, HttpStatus.OK);
    }
    @PostMapping("insertar/equipo/{id}")
    public ResponseEntity insertarEquipo(@RequestBody Equipo equipo, @PathVariable(value = "id") Long id){
        TorneoEntity torneo= torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
        torneoService.saveEquipo(equipo,torneo);
        List torneos = torneoService.listEquipos();
        return new ResponseEntity(torneos, HttpStatus.OK);
    }
    @PostMapping("insertar/jugador/{id}")
    public ResponseEntity insertarJugador(@RequestBody Jugador jugador, @PathVariable(value = "id") Long id){
        EquipoEntity equipoEntity = equipoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));;
        torneoService.saveJugador(jugador,equipoEntity);
        List torneos = torneoService.listEquipos();
        return new ResponseEntity(torneos, HttpStatus.OK);
    }
    @PostMapping("insertar/torneo")
    public ResponseEntity insertarTorneo(@RequestBody Torneo torneo){
        torneoService.saveTorneo(torneo);
        List torneos = torneoService.listEquipos();
        return new ResponseEntity(torneos, HttpStatus.OK);
    }
    @RequestMapping(value = "delete/torneo/{id}", method = RequestMethod.DELETE)
    public void deleteTorneo(@PathVariable("id") Long id) { torneoRepository.deleteById(id);}
    @RequestMapping(value = "delete/equipo/{id}", method = RequestMethod.DELETE)
    public void deleteEquipo(@PathVariable("id") Long id) {
        equipoRepository.deleteById(id);
    }
    @RequestMapping(value = "delete/jugador/{id}", method = RequestMethod.DELETE)
    public void deleteJugador(@PathVariable("id") Long id) {
        jugadorRepository.deleteById(id);
    }

    @GetMapping("save/fixture/{id}")
    public ResponseEntity saveFixture(@PathVariable(value = "id") Long id) {
        TorneoEntity torneo= torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
        partidoRepository.deleteByfkTorneo(torneo);
        List equipos = equipoRepository.findByFkTorneo(torneo);
        List fixture = torneoService.fixture(equipos,torneo);
        return new ResponseEntity(fixture, HttpStatus.OK);
    }
    @PutMapping("update/torneo/{id}")
    public void upadteTorneo(@RequestBody Torneo torneo, @PathVariable(value = "id") Long id){
        TorneoEntity torneoEntity= torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("torneo_tbl","torneo_tbl",id));
        torneoEntity.setNombre(torneo.getNombre());
        torneoEntity.setDescripcion(torneo.getDescripcion());
        torneoRepository.save(torneoEntity);
    }
    @PutMapping("update/equipo/{id}")
    public void updateEuipo(@RequestBody Equipo equipo, @PathVariable(value = "id") Long id){
        EquipoEntity equipoEntity= equipoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("torneo_tbl","torneo_tbl",id));
        equipoEntity.setNombre(equipo.getNombre());
        equipoEntity.setGenero(equipo.getGenero());
        equipoEntity.setLocacion(equipo.getLocacion());
        equipoRepository.save(equipoEntity);
    }
    @PutMapping("update/jugador/{id}")
    public void updateJugador(@RequestBody Jugador jugador, @PathVariable(value = "id") Long id){
        JugadorEntity jugadorEntity= jugadorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("torneo_tbl","torneo_tbl",id));
        jugadorEntity.setNombre(jugador.getNombre());
        jugadorEntity.setFechaNacimiento(jugador.getFechaNacimiento());
        jugadorEntity.setIdentificacion(jugador.getIdentificacion());
        jugadorRepository.save(jugadorEntity);
    }
    @PostMapping("jugar/{id}")
    public ResponseEntity jugarPartido(@RequestBody MarcadorEntity marcador, @PathVariable(value = "id") Long id){
        PartidoEntity partido= partidoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
        jugarPartido.jugarPartido(partido,marcador.getEquipoLocalMrc(),marcador.getEquipoVisitanteMrc());
        return new ResponseEntity(marcadorRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping("fixture/{id}")
    public ResponseEntity fixture(@PathVariable(value = "id") Long id) {
        TorneoEntity torneo= torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
        List equipos = partidoRepository.findByFkTorneo(torneo).stream().sorted(Comparator.comparing(PartidoEntity::getCodigo)).collect(Collectors.toList());
        return new ResponseEntity(equipos, HttpStatus.OK);
    }
    @GetMapping("marcador/{id}")
    public ResponseEntity marcador(@PathVariable(value = "id") Long id) {
        PartidoEntity torneo= partidoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
        MarcadorEntity marcador = marcadorRepository.findByFkPartido(torneo);
        return new ResponseEntity(marcador, HttpStatus.OK);
    }
    @GetMapping("jugadores/{id}")
    public ResponseEntity jugadorPorEquipo(@PathVariable(value = "id") Long id) {
        EquipoEntity equipo= equipoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));
        List equipos = jugadorRepository.findByFkEquipo(equipo);
        return new ResponseEntity(equipos, HttpStatus.OK);
    }
    @GetMapping("listar/equipos")
    public ResponseEntity equipos(){
        List torneos = torneoService.listEquipos();
        return new ResponseEntity(torneos, HttpStatus.OK);
    }
    @GetMapping("listar/posiciones/{id}")
    public ResponseEntity posiciones(@PathVariable(value = "id") Long id){
        TorneoEntity torneoEntity = torneoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("partido_tbl","id_partido",id));;
        List ordenada = jugarPartido.ordenarPosiciones(torneoEntity);
        return new ResponseEntity(ordenada, HttpStatus.OK);
    }
    @GetMapping("listar/partidos")
    public ResponseEntity partidos(){
        List torneos = torneoService.listPartidos();
        return new ResponseEntity(torneos, HttpStatus.OK);
    }
    @GetMapping("listar/jugadores")
    public ResponseEntity jugadores(){
        List jugadores = torneoService.listJugadores();
        return new ResponseEntity(jugadores,HttpStatus.OK);
    }
    @RequestMapping(value="listar/torneos", method = RequestMethod.GET)
    public ResponseEntity listaTransactions(){
        List torneos = torneoService.listTorneos();
        return new ResponseEntity(torneos, HttpStatus.OK);
    }

}
