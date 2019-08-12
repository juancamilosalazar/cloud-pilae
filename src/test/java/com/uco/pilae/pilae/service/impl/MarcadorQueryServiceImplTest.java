package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.MarcadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.operaciones.JugarPartido;
import com.uco.pilae.pilae.repository.MarcadorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MarcadorQueryServiceImplTest {

    protected MarcadorQueryServiceImpl base;

    @Mock
    JugarPartido jugarPartido;
    @Mock
    MarcadorRepository repository;

    @Before
    public void before() {
        base = new MarcadorQueryServiceImpl(jugarPartido, repository);

    }

    @Test
    public void jugarPartido() {
        Mockito.when(jugarPartido.jugarPartido(any(PartidoEntity.class), anyInt(), anyInt())).thenReturn(TestDataProviderController.buildMarcadorEntity());
        MarcadorEntity marcadorEntity = base.jugarPartido(TestDataProviderController.buildPartidoEntity(), ThreadLocalRandom.current().nextInt(0, 5), ThreadLocalRandom.current().nextInt(0, 5));
        assertNotNull(marcadorEntity);

    }

    @Test
    public void findByFkPartido() {
        Mockito.when(repository.findByFkPartido(any(PartidoEntity.class))).thenReturn(TestDataProviderController.buildMarcadorEntity());
        assertNotNull(base.findByFkPartido(TestDataProviderController.buildPartidoEntity()));
    }

    @Test
    public void findByFkTorneo() {
        Mockito.when(repository.findByFkPartidoFkTorneoCodigo(anyLong())).thenReturn(TestDataProviderController.buildMarcadorEntityList());
        assertNotNull(base.findByFkTorneo(ThreadLocalRandom.current().nextLong(0, 50)));
    }
}