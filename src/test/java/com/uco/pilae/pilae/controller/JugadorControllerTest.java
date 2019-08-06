package com.uco.pilae.pilae.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.JugadorEntity;
import com.uco.pilae.pilae.model.Equipo;
import com.uco.pilae.pilae.model.Jugador;
import com.uco.pilae.pilae.service.EquipoQueryService;
import com.uco.pilae.pilae.service.JugadorQueryService;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringJUnit4ClassRunner.class)
public class JugadorControllerTest {

    protected JugadorController base;

    @Mock
    JugadorQueryService queryService;

    @Before
    public void before() {
        base = new JugadorController(queryService, new ModelMapper(), new DataConversionUtil(new ObjectMapper()));
        Mockito.when(queryService.findAll()).thenReturn(TestDataProviderController.buildJugadorEntityList());
        Mockito.when(queryService.crear(any(JugadorEntity.class),anyLong())).thenReturn(TestDataProviderController.buildJugadorEntity());
        Mockito.when(queryService.findById(anyLong())).thenReturn(TestDataProviderController.buildJugadorEntity());
    }

    @Test
    public void methodFindAll() throws Exception {
        List<Jugador> listaPrueba = base.findAll();
        assertNotNull(listaPrueba);
    }

    @Test
    public void methodCreate () throws Exception {
        ResponseEntity<String> responseEntity = base.crear(2L,TestDataProviderController.buildJugador());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodCreateException () throws Exception {
        Mockito.when(queryService.crear(any(JugadorEntity.class),any(Long.class))).thenReturn(null);
        ResponseEntity<String> responseEntity = base.crear(2L, TestDataProviderController.buildJugador());
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
    @Test
    public void methodFindByIdTorneo() throws Exception {
        Mockito.when(queryService.findByEquipo(anyLong())).thenReturn(TestDataProviderController.buildJugadorEntityList());
        List<Jugador> responseEntity = base.findByEquipo(2L);
        assertNotNull(responseEntity);
    }


    @Test
    public void methodUpdate() throws Exception{
        Mockito.when(queryService.save(any(JugadorEntity.class))).thenReturn(TestDataProviderController.buildJugadorEntity());
        ResponseEntity<String> responseEntity = base.update(2L,TestDataProviderController.buildJugador());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void methodUpdate_ERROR() throws Exception{
        Mockito.when(queryService.findById(anyLong())).thenThrow(new RuntimeException("error"));
        ResponseEntity<String> responseEntity = base.update(2L,TestDataProviderController.buildJugador());
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
    @Test
    public void methodDelete() throws Exception{
        ResponseEntity<String> responseEntity = base.delete(2L);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

}