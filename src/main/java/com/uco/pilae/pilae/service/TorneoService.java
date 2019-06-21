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
        return iterableConvertInList(torneo);
    }


    private List iterableConvertInList(Iterable listToConvert) {
        List list = new ArrayList();
        listToConvert.forEach(list::add);
        return list;
    }

    public void update(TorneoEntity torneoAsociado, Torneo torneo) {
        torneoAsociado.setNombre(torneo.getNombre());
        torneoAsociado.setDescripcion(torneo.getDescripcion());
        torneoRepository.save(torneoAsociado);
    }


    public void saveTorneo(Torneo torneo) {
        torneoRepository.save(transform.transformToTorneoEntity(torneo));
    }



}

