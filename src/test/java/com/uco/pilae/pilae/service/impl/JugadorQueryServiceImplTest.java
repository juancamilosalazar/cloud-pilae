package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.JugadorEntity;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.JugadorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringJUnit4ClassRunner.class)
public class JugadorQueryServiceImplTest {

    protected JugadorQueryServiceImpl base;
    @Mock
    JugadorRepository repository;
    @Mock
    EquipoRepository equipoRepository;

    @Before
    public void before() {
        base = new JugadorQueryServiceImpl(repository, equipoRepository);
        Mockito.when(repository.saveAndFlush(any(JugadorEntity.class))).thenReturn(TestDataProviderController.buildJugadorEntity());

    }

    @Test
    public void findAll() {
        Mockito.when(repository.findAll()).thenReturn(TestDataProviderController.buildJugadorEntityList());
        assertNotNull(base.findAll());
    }

    @Test
    public void findAllByTorneo() {
        assertNull(base.findAllByTorneo(ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void save() {
        assertNotNull(base.save(TestDataProviderController.buildJugadorEntity()));
    }

    @Test
    public void delete() {
        base.delete(TestDataProviderController.buildJugadorEntity());
    }

    @Test
    public void crear() {
        Mockito.when(equipoRepository.findById(anyLong())).thenReturn(TestDataProviderController.buildEquipoEntityOptional());
        assertNotNull(base.crear(TestDataProviderController.buildJugadorEntity(), ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void findById() {
        Mockito.when(repository.findById(anyLong())).thenReturn(TestDataProviderController.buildJugadorEntityOptional());
        JugadorEntity jugadorEntity = base.findById(ThreadLocalRandom.current().nextLong(0, 50));
        assertNotNull(jugadorEntity);

    }

    @Test
    public void findByEquipo() {
        Mockito.when(repository.findAllByFkEquipoCodigo(anyLong())).thenReturn(TestDataProviderController.buildJugadorEntityList());
        assertNotNull(base.findByEquipo(ThreadLocalRandom.current().nextLong(0, 50)));
    }
}