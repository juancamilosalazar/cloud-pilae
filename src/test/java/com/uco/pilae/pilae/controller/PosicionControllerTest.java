package com.uco.pilae.pilae.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.PosicionEntity;
import com.uco.pilae.pilae.model.Posicion;
import com.uco.pilae.pilae.service.PosicionQueryService;
import com.uco.pilae.pilae.util.DataConversionUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringJUnit4ClassRunner.class)
public class PosicionControllerTest {

    protected PosicionController base;
    @Mock
    PosicionQueryService queryService;

    @Before
    public void before(){
        base = new PosicionController(queryService,new ModelMapper(),new DataConversionUtil(new ObjectMapper()));
    }
    @Test
    public void methodFindByTorneo() throws Exception{
        Mockito.when(queryService.findByTorneo(anyLong())).thenReturn(TestDataProviderController.buildPosicionEntityList());
        List<Posicion> responseEntity =base.findByTorneo(ThreadLocalRandom.current().nextLong(1,50));
        assertNotNull(responseEntity);
    }
    @Test
    public void create() throws Exception{
        Mockito.when(queryService.save(any(PosicionEntity.class))).thenReturn(TestDataProviderController.buildPosicionEntity());
        ResponseEntity<String> responseEntity = base.Create(TestDataProviderController.builposicion());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void createException() throws Exception{
        Mockito.when(queryService.save(any(PosicionEntity.class))).thenReturn(null);
        ResponseEntity<String> responseEntity = base.Create(TestDataProviderController.builposicion());
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }

}