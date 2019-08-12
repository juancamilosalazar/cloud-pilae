package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.repository.DeporteRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
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
public class TorneoQueryServiceImplTest {

    TorneoQueryServiceImpl base;

    @Mock
    TorneoRepository repository;
    @Mock
    DeporteRepository deporteRepository;

    @Before
    public void before() {
        base = new TorneoQueryServiceImpl(repository, deporteRepository);
        Mockito.when(repository.saveAndFlush(any(TorneoEntity.class))).thenReturn(TestDataProviderController.buildTorneoEntity());
    }

    @Test
    public void findAll() {
        Mockito.when(repository.findAll()).thenReturn(TestDataProviderController.buildTorneoEntityList());
        assertNotNull(base.findAll());
    }

    @Test
    public void findAllByTorneo() {
        assertNull(base.findAllByTorneo(ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void save() {
        assertNotNull(base.save(TestDataProviderController.buildTorneoEntity()));
    }

    @Test
    public void delete() {
        base.delete(TestDataProviderController.buildTorneoEntity());
    }

    @Test
    public void findById() {
        Mockito.when(repository.findById(anyLong())).thenReturn(TestDataProviderController.buildTorneoEntityOptional());
        assertNotNull(base.findById(ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void findByDeporte() {
        Mockito.when(repository.findAllByFkDeporteCodigo(anyLong())).thenReturn(TestDataProviderController.buildTorneoEntityList());
        assertNotNull(base.findByDeporte(ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void create() {
        Mockito.when(deporteRepository.findById(anyLong())).thenReturn(TestDataProviderController.buildDeporteOptionalEntity());
        assertNotNull(base.create(TestDataProviderController.buildTorneoEntity(), ThreadLocalRandom.current().nextLong(0, 50)));
    }
}