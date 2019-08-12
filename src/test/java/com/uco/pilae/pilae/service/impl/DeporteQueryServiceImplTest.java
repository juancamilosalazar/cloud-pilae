package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.repository.DeporteRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
public class DeporteQueryServiceImplTest {

    protected DeporteQueryServiceImpl base;

    @Mock
    DeporteRepository repository;

    @Before
    public void base() {
        base = new DeporteQueryServiceImpl(repository);
    }

    @Test
    public void findAll() {
        Mockito.when(repository.findAll()).thenReturn(TestDataProviderController.buildDeporteEntityList());
        assertNotNull(base.findAll());
    }

    @Test
    public void findAllByTorneo() {
        assertNull(base.findAllByTorneo(ThreadLocalRandom.current().nextLong(0, 50)));
    }

    @Test
    public void save() {
        Mockito.when(repository.saveAndFlush(any(DeporteEntity.class))).thenReturn(TestDataProviderController.buildDeporteEntity());
        assertNotNull(base.save(TestDataProviderController.buildDeporteEntity()));
    }

    @Test
    public void delete() {
        base.delete(TestDataProviderController.buildDeporteEntity());
    }
}