package com.uco.pilae.pilae.operaciones;
import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

@Component
public class FixtureNotReturn {

    @Autowired
    private PartidoRepository partidoRepository;

    private static FixtureWhithReturn.Partido[][] calcularLigaNumEquiposPar(HashMap equipos) {
        int numRondas = equipos.size() - 1;
        int numPartidosPorRonda = equipos.size() / 2;

        FixtureWhithReturn.Partido[][] rondas = new FixtureWhithReturn.Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i++) {
            for (int j = 0; j < numPartidosPorRonda; j++) {
                rondas[i][j] = new FixtureWhithReturn.Partido();
                rondas[i][j].local = k;
                k++;

                if (k == numRondas)
                    k = 0;
            }
        }

        for (int i = 0; i < numRondas; i++) {
            if (i % 2 == 0) {
                rondas[i][0].visitante = equipos.size() - 1;
            } else {
                rondas[i][0].visitante = rondas[i][0].local;
                rondas[i][0].local = equipos.size() - 1;
            }
        }

        int equipoMasAlto = equipos.size() - 1;
        int equipoImparMasAlto = equipoMasAlto - 1;

        for (int i = 0, k = equipoImparMasAlto; i < numRondas; i++) {
            for (int j = 1; j < numPartidosPorRonda; j++) {
                rondas[i][j].visitante = k;

                k--;

                if (k == -1)
                    k = equipoImparMasAlto;
            }
        }

        return rondas;
    }

    private static FixtureWhithReturn.Partido[][] calcularLigaNumEquiposImpar(HashMap equipos) {
        int numRondas = equipos.size();
        int numPartidosPorRonda = equipos.size() / 2;

        FixtureWhithReturn.Partido[][] rondas = new FixtureWhithReturn.Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i++) {
            for (int j = -1; j < numPartidosPorRonda; j++) {
                if (j >= 0) {
                    rondas[i][j] = new FixtureWhithReturn.Partido();
                    rondas[i][j].local = k;
                }

                k++;

                if (k == numRondas)
                    k = 0;
            }
        }

        int equipoMasAlto = equipos.size() - 1;

        for (int i = 0, k = equipoMasAlto; i < numRondas; i++) {
            for (int j = 0; j < numPartidosPorRonda; j++) {
                rondas[i][j].visitante = k;

                k--;

                if (k == -1)
                    k = equipoMasAlto;
            }
        }

        return rondas;
    }

    public FixtureWhithReturn.Partido[][] calcularLiga(HashMap<Integer, EquipoEntity> equipos) {

        if (equipos.size() % 2 == 0)
            return calcularLigaNumEquiposPar(equipos);
        else
            return calcularLigaNumEquiposImpar(equipos);
    }

    public void mostrarPartidos(FixtureWhithReturn.Partido[][] rondas, HashMap<Integer, EquipoEntity> equipos, TorneoEntity id) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());

        for (int i = 0; i < rondas.length; i++) {
            for (int j = 0; j < rondas[i].length; j++) {
                PartidoEntity partidoEntity = new PartidoEntity();
                partidoEntity.setRonda("Ronda " + (i + 1));
                partidoEntity.setFechaDelpartido(calendar.getTime());
                partidoEntity.setFkTorneo(id);
                partidoEntity.setFkEquipoLocal(equipos.get(1 + rondas[i][j].local));
                partidoEntity.setFkEquipoVisitante(equipos.get(1 + rondas[i][j].visitante));
                partidoEntity.setEstadoPartido("sin jugar");
                partidoEntity.setIdaVuelta("solo ida");
                partidoRepository.save(partidoEntity);
            }
        }
    }

    static public class Partido {
        public int local = -1, visitante = -1;
    }
}
