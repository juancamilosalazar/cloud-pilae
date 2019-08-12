package com.uco.pilae.pilae.TestData;

import com.uco.pilae.pilae.entity.*;
import com.uco.pilae.pilae.exceptions.ResourceNotFoundException;
import com.uco.pilae.pilae.model.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestDataProviderController {

    public static List<Deporte> buildDeporteList() {
        return IntStream.range(0, ThreadLocalRandom.current().nextInt(20, 80))
                .mapToObj(x -> buildDeporte())
                .collect(Collectors.toList());
    }


    public static Deporte buildDeporte() {
        final Deporte deporte = new Deporte();
        deporte.setNombre("adfasfd");
        deporte.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        return deporte;
    }

    public static List<DeporteEntity> buildDeporteEntityList() {
        return IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 5))
                .mapToObj(i -> buildDeporteEntity())
                .collect(Collectors.toList());
    }

    public static DeporteEntity buildDeporteEntity() {
        final DeporteEntity deporte = new DeporteEntity();
        deporte.setNombre("adfasfd");
        deporte.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        return deporte;
    }

    public static List<EquipoEntity> buildEquipoEntityList() {

        return IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 5))
                .mapToObj(i -> buildEquipoEntity())
                .collect(Collectors.toList());
    }

    public static EquipoEntity buildEquipoEntity() {
        final EquipoEntity equipoEntity = new EquipoEntity();
        equipoEntity.setFkTorneo(buildTorneoEntity());
        equipoEntity.setGenero("masculino");
        equipoEntity.setNombre("dsfqasf");
        equipoEntity.setLocacion("sdfasfdf");
        equipoEntity.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        return equipoEntity;
    }

    public static TorneoEntity buildTorneoEntity() {
        final TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setFkDeporte(buildDeporteEntity());
        torneoEntity.setDescripcion("dswfdasg");
        torneoEntity.setNombre("dgdasgsts");
        torneoEntity.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        return torneoEntity;
    }

    public static Equipo buildEquipo() {
        Equipo equipo = new Equipo();
        equipo.setGenero("dsfasdf");
        equipo.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        equipo.setLocacion("asfadsf");
        equipo.setNombre("sefgfda");
        equipo.setFkTorneo(buildTorneo());
        return equipo;
    }

    public static Torneo buildTorneo() {
        Torneo torneo = new Torneo();
        torneo.setDescripcion("dwfadgfa");
        torneo.setNombre("dfadgsfd");
        torneo.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        torneo.setFkDeporte(buildDeporte());
        return torneo;
    }

    public static List<TorneoEntity> buildTorneoEntityList() {
        return IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 5))
                .mapToObj(i -> buildTorneoEntity())
                .collect(Collectors.toList());
    }

    public static List<JugadorEntity> buildJugadorEntityList() {
        return IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 5))
                .mapToObj(i -> buildJugadorEntity())
                .collect(Collectors.toList());
    }

    public static JugadorEntity buildJugadorEntity() {
        JugadorEntity jugadorEntity = new JugadorEntity();
        jugadorEntity.setFkEquipo(buildEquipoEntity());
        jugadorEntity.setIdentificacion("2452354");
        jugadorEntity.setId(ThreadLocalRandom.current().nextLong(0, 50));
        jugadorEntity.setNombre("dasfasf");
        jugadorEntity.setFechaNacimiento(buildDateRandom(915166800000L));
        return jugadorEntity;
    }

    public static Jugador buildJugador() {
        final Jugador jugador = new Jugador();
        jugador.setFechaNacimiento(915166800000L);
        jugador.setFkEquipo(buildEquipo());
        jugador.setId(ThreadLocalRandom.current().nextLong(0, 50));
        jugador.setIdentificacion("34525");
        jugador.setNombre("fdsgdfsg");
        return jugador;
    }

    public static List<MarcadorEntity> buildMarcadorEntityList() {
        return IntStream.range(0, (ThreadLocalRandom.current().nextInt(1, 5)))
                .mapToObj(i -> buildMarcadorEntity())
                .collect(Collectors.toList());
    }

    public static MarcadorEntity buildMarcadorEntity() {
        final MarcadorEntity marcadorEntity = new MarcadorEntity();
        marcadorEntity.setEquipoVisitanteMrc(ThreadLocalRandom.current().nextInt(0, 5));
        marcadorEntity.setEquipoLocalMrc(ThreadLocalRandom.current().nextInt(0, 5));
        marcadorEntity.setEquipoGanador("rfgwahfa");
        marcadorEntity.setCodigo(ThreadLocalRandom.current().nextLong(1, 50));
        marcadorEntity.setFkPartido(buildPartidoEntity());
        return marcadorEntity;
    }

    public static PartidoEntity buildPartidoEntity() {
        final PartidoEntity partidoEntity = new PartidoEntity();
        partidoEntity.setIdaVuelta("vuelta");
        partidoEntity.setRonda("dgfasg");
        partidoEntity.setFechaDelpartido(buildDateRandom(915166800000L));
        partidoEntity.setEstadoPartido("fdagsgfa");
        partidoEntity.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        partidoEntity.setFkEquipoVisitante(buildEquipoEntity());
        partidoEntity.setFkEquipoLocal(buildEquipoEntity());
        partidoEntity.setFkTorneo(buildTorneoEntity());
        return partidoEntity;
    }


    public static Optional<PartidoEntity> buildPartidoOptionalEntity() {
        final PartidoEntity partidoEntity = new PartidoEntity();
        partidoEntity.setIdaVuelta("vuelta");
        partidoEntity.setRonda("dgfasg");
        partidoEntity.setFechaDelpartido(buildDateRandom(915166800000L));
        partidoEntity.setEstadoPartido("fdagsgfa");
        partidoEntity.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        partidoEntity.setFkEquipoVisitante(buildEquipoEntity());
        partidoEntity.setFkEquipoLocal(buildEquipoEntity());
        partidoEntity.setFkTorneo(buildTorneoEntity());
        return Optional.of(partidoEntity);
    }

    public static Marcador buildMarcador() {
        final Marcador marcador = new Marcador();
        marcador.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        marcador.setEquipoGanador("dsfasddas");
        marcador.setEquipoLocalMrc(ThreadLocalRandom.current().nextInt(0, 5));
        marcador.setEquipoVisitanteMrc(ThreadLocalRandom.current().nextInt(0, 5));
        marcador.setFkPartido(buildPartido());
        return marcador;
    }

    public static Partido buildPartido() {
        Partido partido = new Partido();
        partido.setIdaVuelta("vuelta");
        partido.setRonda("dgfasg");
        partido.setFechaDelPartido(ThreadLocalRandom.current().nextLong(915166800000L, System.currentTimeMillis()));
        partido.setEstadoPartido("fdagsgfa");
        partido.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        partido.setFkEquipoVisitante(buildEquipo());
        partido.setFkEquipoLocal(buildEquipo());
        partido.setFkTorneo(buildTorneo());
        return partido;
    }

    public static List<PartidoEntity> buildPartidoEntityList() {
        return IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 5))
                .mapToObj(i -> buildPartidoEntity())
                .collect(Collectors.toList());
    }

    public static Optional<EquipoEntity> buildEquipoEntityOptional() {
        final EquipoEntity equipoEntity = new EquipoEntity();
        equipoEntity.setFkTorneo(buildTorneoEntity());
        equipoEntity.setGenero("masculino");
        equipoEntity.setNombre("dsfqasf");
        equipoEntity.setLocacion("sdfasfdf");
        equipoEntity.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        return Optional.of(equipoEntity);
    }

    public static Optional<TorneoEntity> buildTorneoEntityOptional() {
        final TorneoEntity torneoEntity = new TorneoEntity();
        torneoEntity.setFkDeporte(buildDeporteEntity());
        torneoEntity.setDescripcion("dswfdasg");
        torneoEntity.setNombre("dgdasgsts");
        torneoEntity.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        return Optional.of(torneoEntity);
    }

    public static List<PosicionEntity> buildPosicionEntityList() {
        return IntStream.range(0, ThreadLocalRandom.current().nextInt(1, 5))
                .mapToObj(x -> buildPosicionEntity())
                .collect(Collectors.toList());
    }

    public static PosicionEntity buildPosicionEntity() {
        final PosicionEntity posicionEntity = new PosicionEntity();
        posicionEntity.setCodigo(ThreadLocalRandom.current().nextLong(1, 50));
        posicionEntity.setFkEquipo(buildEquipoEntity());
        posicionEntity.setFkTorneo(buildTorneoEntity());
        posicionEntity.setGolesContra(ThreadLocalRandom.current().nextInt(0, 10));
        posicionEntity.setGolesFavor(ThreadLocalRandom.current().nextInt(0, 10));
        posicionEntity.setGolesDiferencia(ThreadLocalRandom.current().nextInt(-10, 10));
        posicionEntity.setPartidosJugados(ThreadLocalRandom.current().nextInt(0, 10));
        posicionEntity.setPartidosGanados(ThreadLocalRandom.current().nextInt(0, 10));
        posicionEntity.setPartidosPerdidos(ThreadLocalRandom.current().nextInt(0, 10));
        posicionEntity.setPartidosEmpatados(ThreadLocalRandom.current().nextInt(0, 10));
        posicionEntity.setPuntos(ThreadLocalRandom.current().nextInt(0, 10));
        return posicionEntity;
    }

    private static Date buildDateRandom(final Long origin) {
        return new Date(ThreadLocalRandom.current().nextLong(origin, System.currentTimeMillis()));
    }

    public static HashMap<Integer, EquipoEntity> buildHashMapFixtureEquiposPar() {
        HashMap<Integer, EquipoEntity> hashMap = new HashMap<>();
        List<EquipoEntity> equipoEntityList = new ArrayList<>();
        EquipoEntity equipoEntity1 = buildEquipoEntity();
        EquipoEntity equipoEntity2 = buildEquipoEntity();
        equipoEntityList.add(equipoEntity1);
        equipoEntityList.add(equipoEntity2);
        int idEquipo = 0;
        for (EquipoEntity equipo : equipoEntityList) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            hashMap.put(idEquipo, equipo);
        }
        return hashMap;
    }

    public static HashMap<Integer, EquipoEntity> buildHashMapFixtureEquiposImpar() {
        HashMap<Integer, EquipoEntity> hashMap = new HashMap<>();
        List<EquipoEntity> equipoEntityList = new ArrayList<>();
        EquipoEntity equipoEntity1 = buildEquipoEntity();
        EquipoEntity equipoEntity2 = buildEquipoEntity();
        EquipoEntity equipoEntity3 = buildEquipoEntity();
        equipoEntityList.add(equipoEntity1);
        equipoEntityList.add(equipoEntity2);
        equipoEntityList.add(equipoEntity3);
        int idEquipo = 0;
        for (EquipoEntity equipo : equipoEntityList) {
            //para cada equipo le agrega un id que ayudara a generar el fixture
            idEquipo++;
            //inserta el equipo con el id generado para el fixture y el id
            hashMap.put(idEquipo, equipo);
        }
        return hashMap;
    }

    public static Optional<JugadorEntity> buildJugadorEntityOptional() {
        JugadorEntity jugadorEntity = new JugadorEntity();
        jugadorEntity.setFkEquipo(buildEquipoEntity());
        jugadorEntity.setIdentificacion("2452354");
        jugadorEntity.setId(ThreadLocalRandom.current().nextLong(0, 50));
        jugadorEntity.setNombre("dasfasf");
        jugadorEntity.setFechaNacimiento(buildDateRandom(915166800000L));
        return Optional.of(jugadorEntity);
    }

    public static Optional<DeporteEntity> buildDeporteOptionalEntity() {
        final DeporteEntity deporte = new DeporteEntity();
        deporte.setNombre("adfasfd");
        deporte.setCodigo(ThreadLocalRandom.current().nextLong(0, 50));
        return Optional.of(deporte);
    }

    public static ResourceNotFoundException buildNotFoundException() {
        ResourceNotFoundException exception;
        exception = new ResourceNotFoundException("Marcador","Codigo",2L);
        return exception;
    }
}
