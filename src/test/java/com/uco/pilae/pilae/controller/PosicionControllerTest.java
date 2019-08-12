package com.uco.pilae.pilae.controller;

import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.model.Posicion;
import com.uco.pilae.pilae.service.PosicionQueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringJUnit4ClassRunner.class)
public class PosicionControllerTest {

    protected PosicionController base;
    @Mock
    PosicionQueryService queryService;

    @Before
    public void before(){
        base = new PosicionController(queryService,new ModelMapper());
    }
    @Test
    public void methodFindByTorneo() throws Exception{
        Mockito.when(queryService.findByTorneo(anyLong())).thenReturn(TestDataProviderController.buildPosicionEntityList());
        List<Posicion> responseEntity =base.findByTorneo(ThreadLocalRandom.current().nextLong(1,50));
        assertNotNull(responseEntity);
    }


}