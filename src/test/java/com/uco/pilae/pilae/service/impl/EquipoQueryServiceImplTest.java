package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.PosicionRepository;
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
public class EquipoQueryServiceImplTest {

    protected EquipoQueryServiceImpl base;

    @Mock
    EquipoRepository repository;
    @Mock
    TorneoRepository torneoRepository;
    @Mock
    PosicionRepository posicionRepository;

    @Before
    public void base() {
        base = new EquipoQueryServiceImpl(repository, torneoRepository, posicionRepository);
        Mockito.when(repository.saveAndFlush(any(EquipoEntity.class))).thenReturn(TestDataProviderController.buildEquipoEntity());
    }

    @Test
    public void findAll() {
        Mockito.when(repository.findAll()).thenReturn(TestDataProviderController.buildEquipoEntityList());
        assertNotNull(base.findAll());
    }

    @Test
    public void findAllByTorneo() {
        Mockito.when(repository.findAllByFkTorneoCodigo(anyLong())).thenReturn(TestDataProviderController.buildEquipoEntityList());
        assertNotNull(base.findAllByTorneo(ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void save() {
        assertNotNull(base.save(TestDataProviderController.buildEquipoEntity()));
    }

    @Test
    public void delete() {
        base.delete(TestDataProviderController.buildEquipoEntity());
    }

    @Test
    public void crear() throws Exception {
        Mockito.when(torneoRepository.findById(anyLong())).thenReturn(TestDataProviderController.buildTorneoEntityOptional());
        Mockito.when(posicionRepository.save(any(PosicionEntity.class))).thenReturn(TestDataProviderController.buildPosicionEntity());
        assertNotNull(base.crear(TestDataProviderController.buildEquipoEntity(), ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void findById() {
        Mockito.when(repository.findById(anyLong())).thenReturn(TestDataProviderController.buildEquipoEntityOptional());
        assertNotNull(base.findById(ThreadLocalRandom.current().nextLong(0, 50)));
    }
}