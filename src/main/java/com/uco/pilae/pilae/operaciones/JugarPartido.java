package com.uco.pilae.pilae.operaciones;

import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.repository.MarcadorRepository;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.repository.PosicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JugarPartido {
    @Autowired
    PartidoRepository partidoRepository;
    @Autowired
    MarcadorRepository marcadorRepository;
    @Autowired
    PosicionRepository posicionRepository;

    public MarcadorEntity jugarPartido(PartidoEntity partido, int equipoLocal, int equipoVisitante) {
        partido.setEstadoPartido("jugado");
        partidoRepository.save(partido);
        saveTablaPosicion(partido, equipoLocal, equipoVisitante);
        return saveMarcador(partido, equipoLocal, equipoVisitante);
    }

    private void saveTablaPosicion(PartidoEntity partido, int equipoLocal, int equipoVisitante) {
        PosicionEntity posicionEntityLocal = posicionRepository.findByFkEquipo(partido.getFkEquipoLocal());
        PosicionEntity posicionEntityVisitante = posicionRepository.findByFkEquipo(partido.getFkEquipoVisitante());
        posicionEntityLocal.setFkEquipo(partido.getFkEquipoLocal());
        posicionEntityLocal.setGolesContra(posicionEntityLocal.getGolesContra() + equipoVisitante);
        posicionEntityLocal.setGolesFavor(posicionEntityLocal.getGolesFavor() + equipoLocal);
        posicionEntityLocal.setGolesDiferencia(posicionEntityLocal.getGolesFavor() - posicionEntityLocal.getGolesContra());
        posicionEntityLocal.setPartidosJugados(posicionEntityLocal.getPartidosJugados() + 1);
        posicionEntityVisitante.setFkEquipo(partido.getFkEquipoVisitante());
        posicionEntityVisitante.setGolesContra(posicionEntityVisitante.getGolesContra() + equipoLocal);
        posicionEntityVisitante.setGolesFavor(posicionEntityVisitante.getGolesFavor() + equipoVisitante);
        posicionEntityVisitante.setGolesDiferencia(posicionEntityVisitante.getGolesFavor() - posicionEntityVisitante.getGolesContra());
        posicionEntityVisitante.setPartidosJugados(posicionEntityVisitante.getPartidosJugados() + 1);
        validateEquipoGanadorPosicion(partido, equipoLocal, equipoVisitante, posicionEntityLocal, posicionEntityVisitante);
        posicionRepository.save(posicionEntityVisitante);
        posicionRepository.save(posicionEntityLocal);
    }

    private void validateEquipoGanadorPosicion(PartidoEntity partido, int equipoLocal, int equipoVisitante, PosicionEntity posicionEntityLocal, PosicionEntity posicionEntityVisitante) {
        if (equipoLocal > equipoVisitante) {
            posicionEntityLocal.setPartidosGanados(posicionEntityLocal.getPartidosGanados() + 1);
            posicionEntityVisitante.setPartidosPerdidos(posicionEntityVisitante.getPartidosPerdidos() + 1);
            posicionEntityLocal.setPuntos(posicionEntityLocal.getPuntos() + 3);
        } else if (equipoLocal == equipoVisitante) {
            posicionEntityLocal.setPartidosEmpatados(posicionEntityLocal.getPartidosEmpatados() + 1);
            posicionEntityVisitante.setPartidosEmpatados(posicionEntityVisitante.getPartidosEmpatados() + 1);
            posicionEntityLocal.setPuntos(posicionEntityLocal.getPuntos() + 1);
            posicionEntityVisitante.setPuntos(posicionEntityVisitante.getPuntos() + 1);
        } else {
            posicionEntityVisitante.setPartidosGanados(posicionEntityVisitante.getPartidosGanados() + 1);
            posicionEntityLocal.setPartidosPerdidos(posicionEntityLocal.getPartidosPerdidos() + 1);
            posicionEntityVisitante.setPuntos(posicionEntityVisitante.getPuntos() + 3);
        }
    }


    private MarcadorEntity saveMarcador(PartidoEntity partido, int equipoLocal, int equipoVisitante) {
        MarcadorEntity marcadorEntity = new MarcadorEntity();
        marcadorEntity.setFkPartido(partido);
        marcadorEntity.setEquipoLocalMrc(equipoLocal);
        marcadorEntity.setEquipoVisitanteMrc(equipoVisitante);
        validateEquipoGanadorMarcador(partido, equipoLocal, equipoVisitante, marcadorEntity);
        marcadorRepository.save(marcadorEntity);
        return marcadorEntity;
    }

    private void validateEquipoGanadorMarcador(PartidoEntity partido, int equipoLocal, int equipoVisitante, MarcadorEntity marcadorEntity) {
        if (equipoLocal > equipoVisitante)
            marcadorEntity.setEquipoGanador(partido.getFkEquipoLocal().getNombre());
        else if (equipoLocal == equipoVisitante)
            marcadorEntity.setEquipoGanador("empate");
        else
            marcadorEntity.setEquipoGanador(partido.getFkEquipoVisitante().getNombre());
    }


}
