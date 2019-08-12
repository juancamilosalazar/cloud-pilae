package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.operaciones.FixtureNotReturn;
import com.uco.pilae.pilae.operaciones.FixtureWhithReturn;
import com.uco.pilae.pilae.operaciones.JugarPartido;
import com.uco.pilae.pilae.repository.PartidoRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringJUnit4ClassRunner.class)
public class FixtureQueryServiceImplTest {

    protected FixtureQueryServiceImpl base;
    @Mock
    FixtureWhithReturn fixtureWhithReturn;
    @Mock
    PartidoRepository repository;
    @Mock
    JugarPartido jugarPartido;
    @Mock
    FixtureNotReturn fixtureNotReturn;
    @Mock
    TorneoRepository torneoRepository;

    @Before
    public void before() {
        base = new FixtureQueryServiceImpl(fixtureWhithReturn, repository, jugarPartido, fixtureNotReturn, torneoRepository);
        Mockito.when(repository.saveAndFlush(any(PartidoEntity.class))).thenReturn(TestDataProviderController.buildPartidoEntity());
    }

    @Test
    public void generateFixture() {
        Mockito.when(fixtureWhithReturn.mostrarPartidos(any(), any(), any())).thenReturn(TestDataProviderController.buildPartidoEntityList());
        assertNotNull(base.generateFixture(TestDataProviderController.buildEquipoEntityList(), TestDataProviderController.buildTorneoEntity()));
    }

    @Test
    public void generateFixtureNotReturn() {
        Mockito.when(fixtureNotReturn.mostrarPartidos(any(), any(), any())).thenReturn(TestDataProviderController.buildPartidoEntityList());
        assertNotNull(base.generateFixtureNotReturn(TestDataProviderController.buildEquipoEntityList(), TestDataProviderController.buildTorneoEntity()));

    }

    @Test
    public void crear() {
        Mockito.when(torneoRepository.findById(anyLong())).thenReturn(TestDataProviderController.buildTorneoEntityOptional());
        assertNotNull(base.crear(TestDataProviderController.buildPartidoEntity(), ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void findAll() {
        Mockito.when(repository.findAll()).thenReturn(TestDataProviderController.buildPartidoEntityList());
        assertNotNull(base.findAll());
    }

    @Test
    public void findAllByTorneo() {
        Mockito.when(repository.findByFkTorneoCodigo(anyLong())).thenReturn(TestDataProviderController.buildPartidoEntityList());
        assertNotNull(base.findAllByTorneo(ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void save() {
        assertNotNull(base.save(TestDataProviderController.buildPartidoEntity()));
    }

    @Test
    public void delete() {
        base.delete(TestDataProviderController.buildPartidoEntity());
    }

    @Test
    public void deleteByFkTorneo() {
        base.deleteByFkTorneo(TestDataProviderController.buildTorneoEntity());
    }

    @Test
    public void update() {
        Mockito.when(repository.findById(anyLong())).thenReturn(TestDataProviderController.buildPartidoOptionalEntity());
        assertNotNull(base.update(ThreadLocalRandom.current().nextLong(0, 50), TestDataProviderController.buildPartido()));
    }
}