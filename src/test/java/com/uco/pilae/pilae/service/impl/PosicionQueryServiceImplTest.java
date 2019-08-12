package com.uco.pilae.pilae.service.impl;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.repository.PosicionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringJUnit4ClassRunner.class)
public class PosicionQueryServiceImplTest {

    PosicionQueryServiceImpl base;
    @Mock
    PosicionRepository repository;

    @Before
    public void before() {
        base = new PosicionQueryServiceImpl(repository);
    }

    @Test
    public void findByTorneo() {
        Mockito.when(repository.findByFkTorneoCodigo(anyLong())).thenReturn(TestDataProviderController.buildPosicionEntityList());
        assertNotNull(base.findByTorneo(ThreadLocalRandom.current().nextLong(0, 50)));
    }
}