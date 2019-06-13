package com.uco.pilae.pilae.service;
import com.uco.pilae.pilae.entity.*;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.model.Jugador;
import com.uco.pilae.pilae.model.Torneo;
import com.uco.pilae.pilae.operaciones.FixtureWhithReturn;
import com.uco.pilae.pilae.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class TorneoService {
    @Autowired
    private TorneoRepository torneoRepository;
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private PartidoRepository partidoRepository;
    @Autowired
    private PosicionRepository posicionRepository;
    @Autowired
    private FixtureWhithReturn fixtureWhithReturn;

    public void save(Torneo torneo)  {
            TorneoEntity torneoEntity = new TorneoEntity();
            EquipoEntity equipoEntity= new EquipoEntity();
            List<EquipoEntity> equipoEntityList= new ArrayList<>();
            torneo.getEquipoList().stream().map(t-> transformEquipo(t,torneoEntity)).forEach(equipoEntityList::add);
            equipoRepository.saveAll(equipoEntityList);
            torneoEntity.setDescripcion(torneo.getDescripcion());
            torneoEntity.setNombre(torneo.getNombre());
            torneoRepository.save(torneoEntity);
    }



    public List<Torneo> listTorneos(){
        Iterable torneo = torneoRepository.findAll();
        List list= new ArrayList();
        torneo.forEach(list::add);
        return  list;
    }

    public List<Torneo> listEquipos(){
        Iterable torneo = equipoRepository.findAll();
        List list= new ArrayList();
        torneo.forEach(list::add);
        return  list;
    }
    public List listPartidos(){
        Iterable partidos = partidoRepository.findAll();
        List list= new ArrayList();
        partidos.forEach(list::add);
        return  list;
    }
    public List listJugadores() {
        Iterable jugadores = jugadorRepository.findAll();
        List list = new ArrayList();
        jugadores.forEach(list::add);
        return list;
    }
    public List<PartidoEntity> fixture(List<EquipoEntity> equiposFixture, TorneoEntity id){

        HashMap<Integer,EquipoEntity> generateFixture= new HashMap<>();
        int idEquipo=0;
        for (EquipoEntity equipo:equiposFixture) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            generateFixture.put(idEquipo,equipo);
        }
        //calcula el fixture con el hashmap generado
        fixtureWhithReturn.mostrarPartidos(fixtureWhithReturn.calcularLiga(generateFixture),generateFixture,id);

        return partidoRepository.findByFkTorneo(id);
    }


    public EquipoEntity transformEquipo(Equipo equipo, TorneoEntity torneoEntity){
        PosicionEntity posicionEntity= new PosicionEntity();
        EquipoEntity equipoEntity = new EquipoEntity();
        equipoEntity.setGenero(equipo.getGenero());
        equipoEntity.setLocacion(equipo.getLocacion());
        equipoEntity.setNombre(equipo.getNombre());
        equipoEntity.setFkTorneo(torneoEntity);
        List<JugadorEntity> jugadorEntities = new ArrayList<>();
        equipo.getJugadores().stream().map(t->transformJugador(t,equipoEntity)).forEach(jugadorEntities::add);
        jugadorRepository.saveAll(jugadorEntities);
        posicionEntity.setFkEquipo(equipoEntity);
        initPartido(posicionEntity, equipoEntity.getFkTorneo());
        posicionRepository.save(posicionEntity);
        return equipoEntity;
    }

    public JugadorEntity transformJugador(Jugador jugador, EquipoEntity equipoEntity){
        JugadorEntity jugadorEntity = new JugadorEntity();
        jugadorEntity.setIdentificacion(jugador.getIdentificacion());
        jugadorEntity.setFechaNacimiento(jugador.getFechaNacimiento());
        jugadorEntity.setNombre(jugador.getNombre());
        jugadorEntity.setFkEquipo(equipoEntity);
        return jugadorEntity;
    }


    public void saveTorneo(Torneo torneo) {
        TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setNombre(torneo.getNombre());
        torneoEntity.setDescripcion(torneo.getDescripcion());
        torneoRepository.save(torneoEntity);
    }

    public void saveEquipo(Equipo equipo, TorneoEntity torneo) {
        PosicionEntity posicionEntity= new PosicionEntity();
        EquipoEntity equipoEntity= new EquipoEntity();
        equipoEntity.setFkTorneo(torneo);
        equipoEntity.setNombre(equipo.getNombre());
        equipoEntity.setLocacion(equipo.getLocacion());
        equipoEntity.setGenero(equipo.getGenero());
        equipoRepository.save(equipoEntity);

        posicionEntity.setFkEquipo(equipoEntity);
        initPartido(posicionEntity,equipoEntity.getFkTorneo());
        posicionRepository.save(posicionEntity);

    }

    private void initPartido(PosicionEntity posicionEntity, TorneoEntity fkTorneo) {
        posicionEntity.setPuntos(0);
        posicionEntity.setPartidosEmpatados(0);
        posicionEntity.setPartidosPerdidos(0);
        posicionEntity.setPartidosGanados(0);
        posicionEntity.setPartidosJugados(0);
        posicionEntity.setGolesDiferencia(0);
        posicionEntity.setGolesFavor(0);
        posicionEntity.setGolesContra(0);
        posicionEntity.setFkTorneo(fkTorneo);
    }


    public void saveJugador(Jugador jugador, EquipoEntity equipoEntity) {
        JugadorEntity jugadorEntity= new JugadorEntity();
        jugadorEntity.setIdentificacion(jugador.getIdentificacion());
        jugadorEntity.setFechaNacimiento(jugador.getFechaNacimiento());
        jugadorEntity.setNombre(jugador.getNombre());
        jugadorEntity.setFkEquipo(equipoEntity);
        jugadorRepository.save(jugadorEntity);
    }
}

