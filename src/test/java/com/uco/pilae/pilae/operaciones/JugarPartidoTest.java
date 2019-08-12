package com.uco.pilae.pilae.operaciones;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.*;
import com.uco.pilae.pilae.repository.MarcadorRepository;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.repository.PosicionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Matchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class JugarPartidoTest {
    @InjectMocks
    protected JugarPartido base;

    @Mock
    PartidoRepository partidoRepository;
    @Mock
    MarcadorRepository marcadorRepository;
    @Mock
    PosicionRepository posicionRepository;

    @Before
    public void before() {
        Mockito.when(posicionRepository.findByFkEquipo(any(EquipoEntity.class))).thenReturn(TestDataProviderController.buildPosicionEntity());
    }

    @Test
    public void jugarPartidoVisitanteWin() {
        PartidoEntity partidoEntity=TestDataProviderController.buildPartidoEntity();
        MarcadorEntity marcadorEntity = base.jugarPartido(partidoEntity, ThreadLocalRandom.current().nextInt(0, 4), ThreadLocalRandom.current().nextInt(5, 10));
        assertNotNull(marcadorEntity);
        assertTrue(marcadorEntity.getEquipoLocalMrc()<marcadorEntity.getEquipoVisitanteMrc());
        assertEquals(marcadorEntity.getEquipoGanador(), partidoEntity.getFkEquipoVisitante().getNombre());
    }
    @Test
    public void jugarPartidoLocalWin() {
        PartidoEntity partidoEntity=TestDataProviderController.buildPartidoEntity();
        MarcadorEntity marcadorEntity = base.jugarPartido(partidoEntity, ThreadLocalRandom.current().nextInt(5, 10), ThreadLocalRandom.current().nextInt(0, 4));
        assertNotNull(marcadorEntity);
        assertTrue(marcadorEntity.getEquipoLocalMrc()>marcadorEntity.getEquipoVisitanteMrc());
        assertEquals(marcadorEntity.getEquipoGanador(), partidoEntity.getFkEquipoLocal().getNombre());
    }
    @Test
    public void jugarPartidoTie() {
        PartidoEntity partidoEntity=TestDataProviderController.buildPartidoEntity();
        int resultadoEmpate = ThreadLocalRandom.current().nextInt(0, 10);
        MarcadorEntity marcadorEntity = base.jugarPartido(partidoEntity,resultadoEmpate,resultadoEmpate);
        assertNotNull(marcadorEntity);
        assertEquals(marcadorEntity.getEquipoLocalMrc(), marcadorEntity.getEquipoVisitanteMrc());
        assertEquals(marcadorEntity.getEquipoGanador(), "empate");
    }

}