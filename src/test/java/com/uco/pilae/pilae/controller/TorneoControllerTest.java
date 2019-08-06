package com.uco.pilae.pilae.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.model.Torneo;
import com.uco.pilae.pilae.service.EquipoQueryService;
import com.uco.pilae.pilae.service.TorneoQueryService;
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
public class TorneoControllerTest {

    protected TorneoController base;
    @Mock
    TorneoQueryService queryService;

    @Before
    public void before() {
        base = new TorneoController(queryService, new ModelMapper(), new DataConversionUtil(new ObjectMapper()));
        Mockito.when(queryService.findAll()).thenReturn(TestDataProviderController.buildTorneoEntityList());
        Mockito.when(queryService.create(any(TorneoEntity.class),anyLong())).thenReturn(TestDataProviderController.buildTorneoEntity());
        Mockito.when(queryService.findById(anyLong())).thenReturn(TestDataProviderController.buildTorneoEntity());

    }

    @Test
    public void methodFindAll() throws Exception {
        List<Torneo> torneos = base.findAll();
        assertNotNull(torneos);
    }

    @Test
    public void methodCreate () throws Exception {
        ResponseEntity<String> responseEntity = base.insertarTorneo(TestDataProviderController.buildTorneo(),2L);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodCreateException () throws Exception {
        Mockito.when(queryService.create(any(TorneoEntity.class),any(Long.class))).thenReturn(null);
        ResponseEntity<String> responseEntity = base.insertarTorneo(TestDataProviderController.buildTorneo(),2L);
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
    @Test
    public void methodFindByIdDeporte() throws Exception {
        Mockito.when(queryService.findByDeporte(anyLong())).thenReturn(TestDataProviderController.buildTorneoEntityList());
        List<Torneo> responseEntity = base.finfByDeporte(2L);
        assertNotNull(responseEntity);
    }
    @Test
    public void methodUpdate() throws Exception{
        Mockito.when(queryService.save(any(TorneoEntity.class))).thenReturn(TestDataProviderController.buildTorneoEntity());
        ResponseEntity<String> responseEntity = base.update(ThreadLocalRandom.current().nextLong(0,50),TestDataProviderController.buildTorneo());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodUpdate_ERROR() throws Exception{
        Mockito.when(queryService.findById(anyLong())).thenThrow(new RuntimeException("error"));
        Mockito.when(queryService.save(any(TorneoEntity.class))).thenReturn(null);
        ResponseEntity<String> responseEntity = base.update(ThreadLocalRandom.current().nextLong(0,50),TestDataProviderController.buildTorneo());
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
    @Test
    public void methodDelete() throws Exception{
        ResponseEntity<String> responseEntity = base.deleteTorneo(ThreadLocalRandom.current().nextLong(0,50));
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

}