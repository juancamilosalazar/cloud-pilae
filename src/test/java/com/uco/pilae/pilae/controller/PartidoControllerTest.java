package com.uco.pilae.pilae.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.pilae.pilae.TestData.TestDataProviderController;
import com.uco.pilae.pilae.entity.EquipoEntity;
import com.uco.pilae.pilae.entity.JugadorEntity;
import com.uco.pilae.pilae.entity.PartidoEntity;
import com.uco.pilae.pilae.entity.TorneoEntity;
import com.uco.pilae.pilae.model.Jugador;
import com.uco.pilae.pilae.model.Partido;
import com.uco.pilae.pilae.repository.EquipoRepository;
import com.uco.pilae.pilae.repository.TorneoRepository;
import com.uco.pilae.pilae.service.FixtureQueryService;
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
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PartidoControllerTest {

    protected PartidoController base;

    @Mock
    FixtureQueryService queryService;
    @Mock
    TorneoRepository torneoRepository;
    @Mock
    EquipoRepository equipoRepository;


    @Before
    public void before() {
        base = new PartidoController(queryService,new ModelMapper(),torneoRepository,equipoRepository,new DataConversionUtil(new ObjectMapper()));
        Mockito.when(queryService.crear(any(PartidoEntity.class),anyLong())).thenReturn(TestDataProviderController.buildPartidoEntity());
        Mockito.when(equipoRepository.findById(anyLong())).thenReturn(TestDataProviderController.buildEquipoEntityOptional());
        Mockito.when(torneoRepository.findById(anyLong())).thenReturn(TestDataProviderController.buildTorneoEntityOptional());
        Mockito.when(equipoRepository.findByFkTorneo(any(TorneoEntity.class))).thenReturn(TestDataProviderController.buildEquipoEntityList());
        Mockito.doNothing().when(queryService).deleteByFkTorneo(any(TorneoEntity.class));
    }


    @Test
    public void methodFindByIdTorneo() throws Exception {
        Mockito.when(queryService.findAllByTorneo(anyLong())).thenReturn(TestDataProviderController.buildPartidoEntityList());
        List<Partido> responseEntity = base.findAllByTorneo(2L);
        assertNotNull(responseEntity);
    }
    @Test
    public void methodUpdate() throws Exception{
        Mockito.when(queryService.update(anyLong(),any(Partido.class))).thenReturn(TestDataProviderController.buildPartidoEntity());
        ResponseEntity<String> responseEntity = base.update(2L,TestDataProviderController.buildPartido());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodUpdatedvf() throws Exception{
        Mockito.when(queryService.update(anyLong(),any(Partido.class))).thenReturn(TestDataProviderController.buildPartidoEntity());
        ResponseEntity<String> responseEntity = base.update(2L,TestDataProviderController.buildPartido());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());

    }
    @Test
    public void methodCreate () throws Exception {
        ResponseEntity<String> responseEntity = base.create(ThreadLocalRandom.current().nextLong(0,50),ThreadLocalRandom.current().nextLong(0,50),ThreadLocalRandom.current().nextLong(0,50),TestDataProviderController.buildPartido());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void methodCreateException () throws Exception {
        Mockito.when(queryService.crear(any(PartidoEntity.class),anyLong())).thenReturn(null);
        ResponseEntity<String> responseEntity = base.create(ThreadLocalRandom.current().nextLong(1,50),ThreadLocalRandom.current().nextLong(0,50),ThreadLocalRandom.current().nextLong(0,50),TestDataProviderController.buildPartido());
        assertEquals(HttpStatus.EXPECTATION_FAILED,responseEntity.getStatusCode());
    }
    @Test
    public void mwthodCreateFixtureWhitReturn () throws Exception {
        Mockito.when(queryService.generateFixture(anyListOf(EquipoEntity.class),any(TorneoEntity.class))).thenReturn(TestDataProviderController.buildPartidoEntityList());
        List<Partido> responseEntity= base.saveFixtureWithReturn(ThreadLocalRandom.current().nextLong(1,50));
        assertNotNull(responseEntity);
    }
    @Test
    public void mwthodCreateFixtureWhitNotReturn () throws Exception {
        Mockito.when(queryService.generateFixtureNotReturn(anyListOf(EquipoEntity.class),any(TorneoEntity.class))).thenReturn(TestDataProviderController.buildPartidoEntityList());
        List<Partido> responseEntity= base.saveFixtureWithNotReturn(ThreadLocalRandom.current().nextLong(1,50));
        assertNotNull(responseEntity);
    }




}