package com.uco.pilae.pilae.service;
import com.uco.pilae.pilae.entity.*;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.model.Jugador;
import com.uco.pilae.pilae.model.Torneo;
import com.uco.pilae.pilae.operaciones.FixtureWhithReturn;
import com.uco.pilae.pilae.operaciones.Transform;
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
    @Autowired
    private Transform transform;

    public List listTorneos(){
        Iterable torneo = torneoRepository.findAll();
        return listConvert(torneo);
    }

    public List listEquipos(){
        Iterable equipo = equipoRepository.findAll();
        return listConvert(equipo);
    }
    public List listPartidos(){
        Iterable partidos = partidoRepository.findAll();
        return  listConvert(partidos);
    }
    public List listJugadores() {
        Iterable jugadores = jugadorRepository.findAll();
        return listConvert(jugadores);
    }

    private List listConvert(Iterable listToConvert) {
        List list = new ArrayList();
        listToConvert.forEach(list::add);
        return list;
    }

    public void update(TorneoEntity torneoAsociado, Torneo torneo) {
        torneoAsociado.setNombre(torneo.getNombre());
        torneoAsociado.setDescripcion(torneo.getDescripcion());
        torneoRepository.save(torneoAsociado);
    }

    public void updateEquipo(EquipoEntity equipoAsociado, Equipo equipo) {
        equipoAsociado.setNombre(equipo.getNombre());
        equipoAsociado.setGenero(equipo.getGenero());
        equipoAsociado.setLocacion(equipo.getLocacion());
        equipoRepository.save(equipoAsociado);
    }

    public void updateJugador(JugadorEntity jugadorAsociado, Jugador jugador) {
        jugadorAsociado.setNombre(jugador.getNombre());
        jugadorAsociado.setFechaNacimiento(jugador.getFechaNacimiento());
        jugadorAsociado.setIdentificacion(jugador.getIdentificacion());
        jugadorRepository.save(jugadorAsociado);
    }

    public void saveTorneo(Torneo torneo) {
        torneoRepository.save(transform.transformToTorneoEntity(torneo));
    }

    public void saveJugador(Jugador jugador, EquipoEntity equipoEntity) {
        jugadorRepository.save(transform.transformToJugadorEntity(jugador,equipoEntity));
    }

    public void saveEquipo(Equipo equipo, TorneoEntity torneo) {
        EquipoEntity equipoEntity= transform.transformToEquipoEntity(equipo,torneo);
        equipoRepository.save(equipoEntity);
        initPartido(equipoEntity);
    }

    private void initPartido(EquipoEntity equipoEntity) {
        PosicionEntity posicionEntity = new PosicionEntity();
        posicionEntity.setPuntos(0);
        posicionEntity.setPartidosEmpatados(0);
        posicionEntity.setPartidosPerdidos(0);
        posicionEntity.setPartidosGanados(0);
        posicionEntity.setPartidosJugados(0);
        posicionEntity.setGolesDiferencia(0);
        posicionEntity.setGolesFavor(0);
        posicionEntity.setGolesContra(0);
        posicionEntity.setFkTorneo(equipoEntity.getFkTorneo());
        posicionEntity.setFkEquipo(equipoEntity);
        posicionRepository.save(posicionEntity);
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


}

