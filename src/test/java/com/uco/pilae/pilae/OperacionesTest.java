package com.uco.pilae.pilae;

import com.uco.pilae.pilae.entity.*;
import com.uco.pilae.pilae.operaciones.JugarPartido;
import org.junit.Assert;
import org.junit.Test;

public class OperacionesTest {
    @Test
    public void validateTeamWin() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        MarcadorEntity marcadorEntity = new MarcadorEntity();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        equipoLocal.setNombre("nacional");
        equipoVisitante.setNombre("medellin");
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorMarcador(partidoEntity,2,3,marcadorEntity);
        Assert.assertEquals(marcadorEntity.getEquipoGanador(),"medellin");
    }
    @Test
    public void validateTie() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        MarcadorEntity marcadorEntity = new MarcadorEntity();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        equipoLocal.setNombre("nacional");
        equipoVisitante.setNombre("medellin");
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorMarcador(partidoEntity,2,2,marcadorEntity);
        Assert.assertEquals(marcadorEntity.getEquipoGanador(),"empate");
    }

    @Test
    public void validatePointTeamLocal() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        PosicionEntity poscionLocal= new PosicionEntity();
        PosicionEntity posicionVisitante= new PosicionEntity();
        poscionLocal.setFkEquipo(equipoLocal);
        posicionVisitante.setFkEquipo(equipoVisitante);
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorPosicion(partidoEntity,2,3,poscionLocal,posicionVisitante);
        Assert.assertEquals(poscionLocal.getPuntos(),0);
    }
    @Test
    public void validatePointTeamVisitante() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        PosicionEntity poscionLocal= new PosicionEntity();
        PosicionEntity posicionVisitante= new PosicionEntity();
        poscionLocal.setFkEquipo(equipoLocal);
        posicionVisitante.setFkEquipo(equipoVisitante);
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorPosicion(partidoEntity,2,3,poscionLocal,posicionVisitante);
        Assert.assertEquals(posicionVisitante.getPuntos(),3);
    }
    @Test
    public void validateMatchsLoseTeamLocal() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        PosicionEntity poscionLocal= new PosicionEntity();
        PosicionEntity posicionVisitante= new PosicionEntity();
        poscionLocal.setFkEquipo(equipoLocal);
        posicionVisitante.setFkEquipo(equipoVisitante);
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorPosicion(partidoEntity,2,3,poscionLocal,posicionVisitante);
        Assert.assertEquals(poscionLocal.getPartidosPerdidos(),1);
    }
    @Test
    public void validateMatchsLoseVisitante() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        PosicionEntity poscionLocal= new PosicionEntity();
        PosicionEntity posicionVisitante= new PosicionEntity();
        poscionLocal.setFkEquipo(equipoLocal);
        posicionVisitante.setFkEquipo(equipoVisitante);
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorPosicion(partidoEntity,2,1,poscionLocal,posicionVisitante);
        Assert.assertEquals(posicionVisitante.getPartidosPerdidos(),1);
    }
    @Test
    public void validateMatchsWinLocal() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        PosicionEntity poscionLocal= new PosicionEntity();
        PosicionEntity posicionVisitante= new PosicionEntity();
        poscionLocal.setFkEquipo(equipoLocal);
        posicionVisitante.setFkEquipo(equipoVisitante);
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorPosicion(partidoEntity,2,1,poscionLocal,posicionVisitante);
        Assert.assertEquals(poscionLocal.getPartidosGanados(),1);
    }
    @Test
    public void validateMatchsWinVisitante() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        PosicionEntity poscionLocal= new PosicionEntity();
        PosicionEntity posicionVisitante= new PosicionEntity();
        poscionLocal.setFkEquipo(equipoLocal);
        posicionVisitante.setFkEquipo(equipoVisitante);
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorPosicion(partidoEntity,2,3,poscionLocal,posicionVisitante);
        Assert.assertEquals(posicionVisitante.getPartidosGanados(),1);
    }
    @Test
    public void validateMatchsTie() {
        PartidoEntity partidoEntity = new PartidoEntity();
        JugarPartido jugarPartido = new JugarPartido();
        EquipoEntity equipoLocal = new EquipoEntity();
        EquipoEntity equipoVisitante= new EquipoEntity();
        PosicionEntity poscionLocal= new PosicionEntity();
        PosicionEntity posicionVisitante= new PosicionEntity();
        poscionLocal.setFkEquipo(equipoLocal);
        posicionVisitante.setFkEquipo(equipoVisitante);
        partidoEntity.setFkEquipoLocal(equipoLocal);
        partidoEntity.setFkEquipoVisitante(equipoVisitante);
        jugarPartido.validateEquipoGanadorPosicion(partidoEntity,2,2,poscionLocal,posicionVisitante);
        Assert.assertEquals(posicionVisitante.getPartidosEmpatados(),1);
        Assert.assertEquals(posicionVisitante.getPartidosEmpatados(),1);
    }
}
