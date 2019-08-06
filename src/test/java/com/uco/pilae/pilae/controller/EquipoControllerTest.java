package com.uco.pilae.pilae.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.DeporteEntity;
import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.model.Deporte;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.service.EquipoQueryService;
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
import static org.mockito.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EquipoControllerTest {

    protected EquipoController base;
    @Mock
    EquipoQueryService queryService;

    @Before
    public void before() {
        base = new EquipoController(queryService, new ModelMapper(), new DataConversionUtil(new ObjectMapper()));
        Mockito.when(queryService.findAll()).thenReturn(TestDataProviderController.buildEquipoEntityList());
        Mockito.when(queryService.crear(any(EquipoEntity.class),anyLong())).thenReturn(TestDataProviderController.buildEquipoEntity());
        Mockito.when(queryService.findById(anyLong())).thenReturn(TestDataProviderController.buildEquipoEntity());
    }

    @Test
    public void methodFindAll() throws Exception {
        List<Equipo> listaPrueba = base.findAll();
        assertNotNull(listaPrueba);
    }

    @Test
    public void methodCreate () throws Exception {
        ResponseEntity<String> responseEntity = base.create(2L,TestDataProviderController.buildEquipo());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodCreateException () throws Exception {
        Mockito.when(queryService.crear(any(EquipoEntity.class),any(Long.class))).thenReturn(null);
        ResponseEntity<String> responseEntity = base.create(2L,TestDataProviderController.buildEquipo());
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
    @Test
    public void methodFindByIdTorneo() throws Exception {
        Mockito.when(queryService.findAllByTorneo(anyLong())).thenReturn(TestDataProviderController.buildEquipoEntityList());
        List<Equipo> responseEntity = base.findByIdTorneo(2L);
        assertNotNull(responseEntity);
    }
    @Test
    public void methodFindById() throws Exception {
        Equipo responseEntity = base.findById(2L);
        assertNotNull(responseEntity);
    }

    @Test
    public void methodUpdate() throws Exception{
        Mockito.when(queryService.save(any(EquipoEntity.class))).thenReturn(TestDataProviderController.buildEquipoEntity());
        ResponseEntity<String> responseEntity = base.update(2L,TestDataProviderController.buildEquipo());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodUpdate_ERROR() throws Exception{
        Mockito.when(queryService.findById(anyLong())).thenThrow(new RuntimeException("error"));
        ResponseEntity<String> responseEntity = base.update(2L,TestDataProviderController.buildEquipo());
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
    @Test
    public void methodDelete() throws Exception{
        ResponseEntity<String> responseEntity = base.delete(ThreadLocalRandom.current().nextLong(0,50));
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

}